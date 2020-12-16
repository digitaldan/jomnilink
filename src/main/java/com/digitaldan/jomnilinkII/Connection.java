/**
 * Copyright (c) 2009-2020 Dan Cunningham
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package com.digitaldan.jomnilinkII;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.digitaldan.jomnilinkII.MessageTypes.*;
import com.digitaldan.jomnilinkII.MessageTypes.statuses.*;
import com.digitaldan.jomnilinkII.MessageTypes.systemevents.SystemEvent;

public class Connection extends Thread {
	private static final Logger logger = LoggerFactory.getLogger(Connection.class);

	private static int PACKET_TYPE_CLIENT_REQUEST_NEW_SESSION = 1;
	private static int PACKET_TYPE_CONTROLLER_ACKNOWLEDGE_NEW_SESSION = 2;
	private static int PACKET_TYPE_CLIENT_REQUEST_SECURE_CONNECTION = 3;
	private static int PACKET_TYPE_CONTROLLER_ACKNOWLEDGE_SECURE_CONNECTION = 4;
	private static int PACKET_TYPE_CLIENT_SESSION_TERMINATED = 5;
	private static int PACKET_TYPE_CONTROLLER_SESSION_TERMINATED = 6;
	private static int PACKET_TYPE_CONTROLLER_CANNOT_START_NEW_SESSION = 7;
	private static int PACKET_TYPE_OMNI_LINK_MESSAGE = 32;

	public static int MAX_PACKET_SIZE = 255;
	// Omni will kick you after 5 minutes of not receiveing anything
	public static int OMNI_TO = 60 * 5 * 1000;
	// Keep alive time, omni timeout minus one minute
	public static int PING_TO = OMNI_TO - (1000 * 60);

	public boolean debug;
	private boolean connected;
	private boolean ping;
	private long lastTXMessageTime;
	private Socket socket;
	private InputStream is;
	private OutputStream os;
	private int tx;
	private int rx;
	private Aes aes;
	private Object writeLock = new Object();
	private Exception lastException;
	private final BlockingQueue<Message> notifications;
	private final List<NotificationListener> notificationListeners;
	private final List<DisconnectListener> disconnectListeners;
	private final BlockingQueue<OmniPacket> responses;
	private ConnectionWatchdog watchdog;

	public Connection(String address, int port, String key) throws Exception, IOException, UnknownHostException {
		ping = true;
		notifications = new LinkedBlockingQueue<>();
		responses = new LinkedBlockingQueue<>();
		lastException = null;
		notificationListeners = new CopyOnWriteArrayList<NotificationListener>();
		disconnectListeners = new CopyOnWriteArrayList<DisconnectListener>();

		byte[] _key = hexStringToByteArray(key.replaceAll("\\W", ""));

		socket = new Socket(address, port);
		is = socket.getInputStream();
		os = socket.getOutputStream();
		socket.setSoTimeout(OMNI_TO);
		tx = 1;
		rx = 1;

		sendBytes(new OmniPacket(PACKET_TYPE_CLIENT_REQUEST_NEW_SESSION, null));
		OmniPacket rec = readBytes();

		if (rec.type() != PACKET_TYPE_CONTROLLER_ACKNOWLEDGE_NEW_SESSION) {
			throw new Exception("Controller not accepting new connections");
		}

		byte[] data = rec.data();

		int version = (data[0] << 8) + (data[1] << 0);

		logger.debug("Controller version {}", version);

		byte[] sessionid = new byte[5];
		System.arraycopy(data, 2, sessionid, 0, 5);

		for (int i = 0; i < 5; i++) {
			_key[i + 11] ^= sessionid[i];
		}

		aes = new Aes(_key);
		sendBytesEncrypted(new OmniPacket(PACKET_TYPE_CLIENT_REQUEST_SECURE_CONNECTION, sessionid));

		rec = readBytesEncrypted();
		if (rec.type() != PACKET_TYPE_CONTROLLER_ACKNOWLEDGE_SECURE_CONNECTION) {
			throw new Exception("Could not establish secure connection");
		}

		data = rec.data();

		for (int i = 0; i < 5; i++) {
			logger.trace("Data {} mine {} controllers {}" + data[i], i, sessionid[i], data[i]);
			if (data[i] != sessionid[i]) {
				throw new IOException("Controller returned wrong sessioid");
			}
		}
		connected = true;
		lastTXMessageTime = System.currentTimeMillis();

		this.setName("OmniReaderThread");
		this.start();

		Thread notificationHandlerThread = new Thread(new NotificationHandler(notifications, notificationListeners));
		notificationHandlerThread.setName("NotificationHandlerThread");
		notificationHandlerThread.start();

		ConnectionWatchdog watchdog = new ConnectionWatchdog();
		watchdog.setName("ConnectionWatchdogThread");
		watchdog.start();
	}

	public void disconnect() {
		connected = false;
		if (socket != null) {
			try {
				socket.close();
			} catch (Exception e) {
			}
		}
		notifications.offer(NotificationHandler.POISON);
	}

	public boolean connected() {
		return connected;
	}

	public Exception lastError() {
		return lastException;
	}

	public boolean autoPingOmni() {
		return ping;
	}

	public void autoPingOmni(boolean ping) {
		this.ping = ping;
	}

	public void addNotificationListener(NotificationListener listener) {
		notificationListeners.add(listener);
	}

	public void removeNotificationListener(NotificationListener listener) {
		notificationListeners.remove(listener);
	}

	public void addDisconnectListener(DisconnectListener listener) {
		disconnectListeners.add(listener);
	}

	public void removeDisconnectListener(DisconnectListener listener) {
		disconnectListeners.remove(listener);
	}

	public Message sendAndReceive(Message message)
			throws IOException, OmniNotConnectedException, OmniUnknownMessageTypeException {
		synchronized (writeLock) {
			try {
				if (!connected) {
					throw new OmniNotConnectedException(lastError());
				}
				sendBytesEncrypted(new OmniPacket(PACKET_TYPE_OMNI_LINK_MESSAGE, MessageFactory.toBytes(message)));
				OmniPacket omniPacket = responses.poll(30, TimeUnit.SECONDS);
				if (omniPacket == null) {
					// Throw exception if poll times out.
					throw new IOException("Response not returned from Omni within 30 seconds");
				}
				// If an error occurs on our other thread it saves the exception
				if (!connected) {
					throw new OmniNotConnectedException(lastError());
				}

				// Used to ping after a certain amount of time
				lastTXMessageTime = System.currentTimeMillis();
				return MessageFactory.fromBytes(omniPacket.data());
			} catch (InterruptedException ex) {
				throw new IOException(ex);
			}
		}
	}

	@Override
	public void run() {
		while (connected) {
			try {
				OmniPacket omniPacket = readBytesEncryptedExtended();
				if ((omniPacket.seq() == 0 && omniPacket.type() == PACKET_TYPE_OMNI_LINK_MESSAGE)) {
					notifications.put(MessageFactory.fromBytes(omniPacket.data()));
					logger.debug("run: NOTIFICATION: Added message with type {}", omniPacket.type);
				} else if (omniPacket.type() == PACKET_TYPE_OMNI_LINK_MESSAGE) {
					responses.put(omniPacket);
				} else {
					throw new IOException("Non omnilink message");
				}
			} catch (OmniUnknownMessageTypeException e) {
				// ignore
				logger.debug("run: Uknown Messgage type {}  Continuing", e.getUnknowMessageType());
			} catch (Exception e) {
				disconnect();
				lastException = e;
				// tell listeners about exception
				notifyDisconnectHandlers(lastException);
			}
		}
		logger.debug("run: not connected, thread exiting");
	}

	/*
	 * The following procedure is used to encrypt Omni-Link II application data:
	 */
	private void sendBytesEncrypted(OmniPacket p) throws IOException {
		/*
		 * 1. Process data in 128-bit (16-byte) blocks. If available data does not fill
		 * a 16-byte block, the data is left-justified and padded on the right with
		 * zeros to fill the block.
		 */
		logger.trace("TX: {}", bytesToString(p.data()));
		int txlength = (p.data().length + 15) & ~0xF;
		byte[] paddedData = new byte[txlength];
		System.arraycopy(p.data(), 0, paddedData, 0, p.data().length);
		for (int i = p.data().length; i < txlength; i++) {
			paddedData[i] = 0x00;
		}

		/*
		 * 2. Modify the first two bytes of the 16-byte encryption block by performing a
		 * logical XOR operation with the two bytes of the “message sequence number” in
		 * the HAI header (i.e., XOR the first byte of the encryption block with the MSB
		 * of the message sequence number, and XOR the second byte of the encryption
		 * block with the LSB of the message sequence number).
		 */
		for (int i = 0; i < (txlength / 16); i++) {
			paddedData[0 + (16 * i)] ^= (tx >> 8) & 0xFF;
			paddedData[1 + (16 * i)] ^= (tx) & 0xFF;
		}

		/*
		 * 3. Encrypt the 16-byte block using the AES encryption algorithm and the
		 * 128-bit session key that was negotiated when the client and controller
		 * established the secure connection.
		 */
		byte[] encData;
		try {
			encData = aes.encrypt(paddedData);
		} catch (Exception e) {
			throw new IOException(e.getMessage());
		}

		/*
		 * 4. Process the next block of data until all data has been processed.
		 */
		sendBytes(new OmniPacket(p.type(), encData));
	}

	private void sendBytes(OmniPacket p) throws IOException {
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		DataOutputStream dout = new DataOutputStream(bout);
		dout.writeShort(tx);
		dout.writeByte(p.type());
		dout.writeByte(0);
		if (p.data() != null) {
			dout.write(p.data());
		}
		os.write(bout.toByteArray());
		os.flush();
		tx++;
		if (tx >= 65535) {
			tx = 1;
		}
	}

	private OmniPacket readBytesEncrypted() throws IOException, SocketTimeoutException {
		OmniPacket p = readBytes();
		logger.trace("Enc Dec " + bytesToString(p.data()));
		if (p.data().length == 0) {
			return p;
		}
		byte[] decData = aes.decrypt(p.data());
		/* XOR data */
		for (int i = 0; i < (decData.length / 16); i++) {
			decData[0 + (16 * i)] ^= (p.seq >> 8) & 0xFF;
			decData[1 + (16 * i)] ^= (p.seq) & 0xFF;
		}
		logger.trace("Data Dec {}", bytesToString(decData));
		return new OmniPacket(p.seq, p.type(), decData);
	}

	private OmniPacket readBytesEncryptedExtended() throws IOException, SocketTimeoutException {
		// Notifications have thrown a bit of a curve ball, its possible to have
		// two packets on the wire, but because the length of the packet
		// is encrypted we have to peek into the first 16 bytes, decrypt those
		// bytes and get the length, this makes the following code tricky and
		// unattractive,
		DataInputStream dis = new DataInputStream(is);
		logger.trace("Bytes available for reading: {}", is.available());
		int seq = dis.readUnsignedShort();
		int type = dis.readUnsignedByte();
		int reserved = dis.readUnsignedByte();

		byte[] encData = new byte[16];
		dis.readFully(encData);
		byte[] decData = aes.decrypt(encData);

		decData[0] ^= (seq >> 8) & 0xFF;
		decData[1] ^= (seq) & 0xFF;

		// not all messages are omnilink
		if (type != PACKET_TYPE_OMNI_LINK_MESSAGE) {
			logger.trace("NON OMNI LINK PACKET: {} RX Bytes: {}", type, bytesToString(decData));
			return new OmniPacket(seq, type, decData);
		}

		// continue with omnilink decoding
		int start = decData[0] & 0xFF;
		int length = decData[1] & 0xFF;

		if (start != Message.MESG_START) {
			logger.debug("invalid start char ({})", start);
		}
		// throw new IOException("invalid start char (" + start + ")");
		if (length < 0) {
			throw new IOException("invalid message length (" + length + ")");
		}

		logger.trace("Omni message Length {}", length);

		// length plus start and crc fields, round up to next 16, minus the bytes we
		// have already read
		int readLength = ((((length + 3) / 16) + 1) * 16) - 16;

		logger.trace("Additional bytes to read {}", readLength);

		if (readLength > 0) {
			// buffer for existing 16 bytes of data plus any on the wire
			byte[] decData2 = new byte[decData.length + readLength];
			encData = new byte[readLength];
			// copy the data we already have from decData to decData2
			System.arraycopy(decData, 0, decData2, 0, decData.length);
			// read the rest
			dis.readFully(encData);
			// add decrypted data to the buffer
			aes.decrypt(encData, 0, readLength, decData2, decData.length);
			/* XOR data */
			for (int i = 1; i < (decData2.length / 16); i++) {
				decData2[0 + (16 * i)] ^= (seq >> 8) & 0xFF;
				decData2[1 + (16 * i)] ^= (seq) & 0xFF;
			}
			decData = decData2;
		}

		logger.trace("RX: {} Data still available after read {}", bytesToString(decData), is.available());

		return new OmniPacket(seq, type, decData);
	}

	private OmniPacket readBytes() throws IOException, SocketTimeoutException {
		byte[] data = new byte[MAX_PACKET_SIZE];
		int cnt = is.read(data);

		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));

		int seq = dis.readUnsignedShort();
		int type = dis.readUnsignedByte();
		int reserved = dis.readUnsignedByte();

		byte[] msgdata = new byte[cnt - 4];
		dis.readFully(msgdata);

		return new OmniPacket(seq, type, msgdata);
	}

	public void enableNotifications() throws IOException, OmniNotConnectedException, OmniInvalidResponseException,
			OmniUnknownMessageTypeException {
		Message msg = sendAndReceive(Notifications.enable());
		if (msg.getMessageType() != Message.MESG_TYPE_ACK) {
			throw new OmniInvalidResponseException(msg);
		}
	}

	public SystemInformation reqSystemInformation() throws IOException, OmniNotConnectedException,
			OmniInvalidResponseException, OmniUnknownMessageTypeException {
		Message msg = sendAndReceive(ReqSystemInformation.getInstance());
		if (msg.getMessageType() != Message.MESG_TYPE_SYS_INFO) {
			throw new OmniInvalidResponseException(msg);
		}
		return (SystemInformation) msg;
	}

	public SystemStatus reqSystemStatus() throws IOException, OmniNotConnectedException, OmniInvalidResponseException,
			OmniUnknownMessageTypeException {
		Message msg = sendAndReceive(ReqSystemStatus.getInstance());
		if (msg.getMessageType() != Message.MESG_TYPE_SYS_STATUS) {
			throw new OmniInvalidResponseException(msg);
		}
		return (SystemStatus) msg;
	}

	public SystemTroubles reqSystemTroubles() throws IOException, OmniNotConnectedException,
			OmniInvalidResponseException, OmniUnknownMessageTypeException {
		Message msg = sendAndReceive(ReqSystemTroubles.getInstance());
		if (msg.getMessageType() != Message.MESG_TYPE_SYS_TROUBLES) {
			throw new OmniInvalidResponseException(msg);
		}
		return (SystemTroubles) msg;
	}

	public SystemFeatures reqSystemFeatures() throws IOException, OmniNotConnectedException,
			OmniInvalidResponseException, OmniUnknownMessageTypeException {
		Message msg = sendAndReceive(ReqSystemFeatures.getInstance());
		if (msg.getMessageType() != Message.MESG_TYPE_SYS_FEATURES) {
			throw new OmniInvalidResponseException(msg);
		}
		return (SystemFeatures) msg;
	}

	public SystemFormats reqSystemFormats() throws IOException, OmniNotConnectedException, OmniInvalidResponseException,
			OmniUnknownMessageTypeException {
		Message msg = sendAndReceive(ReqSystemFormats.getInstance());
		if (msg.getMessageType() != Message.MESG_TYPE_SYS_FORMATS) {
			throw new OmniInvalidResponseException(msg);
		}
		return (SystemFormats) msg;
	}

	public ObjectTypeCapacities reqObjectTypeCapacities(int objectType) throws IOException, OmniNotConnectedException,
			OmniInvalidResponseException, OmniUnknownMessageTypeException {
		Message msg = sendAndReceive(ReqObjectTypeCapacities.builder().objectType(objectType).build());
		if (msg.getMessageType() != Message.MESG_TYPE_OBJ_CAPACITY) {
			throw new OmniInvalidResponseException(msg);
		}
		return (ObjectTypeCapacities) msg;
	}

	public Message reqObjectProperties(int objectType, int objectNum, int direction, int filter1, int filter2,
			int filter3) throws IOException, OmniNotConnectedException, OmniInvalidResponseException,
			OmniUnknownMessageTypeException {
		ReqObjectProperties reqObjectProperties = ReqObjectProperties.builder().objectType(objectType)
				.objectNumber(objectNum).direction(direction).filter1(filter1).filter2(filter2).filter3(filter3)
				.build();
		Message msg = sendAndReceive(reqObjectProperties);
		if (msg.getMessageType() != Message.MESG_TYPE_OBJ_PROP
				&& msg.getMessageType() != Message.MESG_TYPE_END_OF_DATA) {
			throw new OmniInvalidResponseException(msg);
		}
		return msg;
	}

	public ObjectStatus reqObjectStatus(int objectType, int startObject, int endObject) throws IOException,
			OmniNotConnectedException, OmniInvalidResponseException, OmniUnknownMessageTypeException {
		return reqObjectStatus(objectType, startObject, endObject, false);
	}

	public ExtendedObjectStatus reqExtendedObjectStatus(int objectType, int startObject, int endObject)
			throws IOException, OmniNotConnectedException, OmniInvalidResponseException,
			OmniUnknownMessageTypeException {
		return (ExtendedObjectStatus) reqObjectStatus(objectType, startObject, endObject, true);
	}

	public ObjectStatus reqObjectStatus(int objectType, int startObject, int endObject, boolean extended)
			throws IOException, OmniNotConnectedException, OmniInvalidResponseException,
			OmniUnknownMessageTypeException {
		Status[] s = null;
		switch (objectType) {
			case Message.OBJ_TYPE_ZONE : {
				if (extended) {
					s = new ExtendedZoneStatus[endObject - startObject + 1];
				} else {
					s = new ZoneStatus[endObject - startObject + 1];
				}
			}
				break;
			case Message.OBJ_TYPE_UNIT : {
				if (extended) {
					s = new ExtendedUnitStatus[endObject - startObject + 1];
				} else {
					s = new UnitStatus[endObject - startObject + 1];
				}
			}
				break;
			case Message.OBJ_TYPE_AREA : {
				if (extended) {
					s = new ExtendedAreaStatus[endObject - startObject + 1];
				} else {
					s = new AreaStatus[endObject - startObject + 1];
				}
			}
				break;
			case Message.OBJ_TYPE_THERMO : {
				if (extended) {
					s = new ExtendedThermostatStatus[endObject - startObject + 1];
				} else {
					s = new ThermostatStatus[endObject - startObject + 1];
				}
			}
				break;
			case Message.OBJ_TYPE_MESG : {
				if (extended) {
					s = new ExtendedMessageStatus[endObject - startObject + 1];
				} else {
					s = new MessageStatus[endObject - startObject + 1];
				}
			}
				break;
			case Message.OBJ_TYPE_AUX_SENSOR : {
				if (extended) {
					s = new ExtendedAuxSensorStatus[endObject - startObject + 1];
				} else {
					s = new AuxSensorStatus[endObject - startObject + 1];
				}
			}
				break;
			case Message.OBJ_TYPE_AUDIO_ZONE : {
				if (extended) {
					s = new ExtendedAudioZoneStatus[endObject - startObject + 1];
				} else {
					s = new AudioZoneStatus[endObject - startObject + 1];
				}
			}
				break;
			case Message.OBJ_TYPE_EXP : {
				if (extended) {
					s = new ExtendedExpansionStatus[endObject - startObject + 1];
				} else {
					s = new ExpansionStatus[endObject - startObject + 1];
				}
			}
				break;
			case Message.OBJ_TYPE_USER_SETTING : {
				if (extended) {
					s = new ExtendedUserSettingStatus[endObject - startObject + 1];
				} else {
					s = new UserSettingStatus[endObject - startObject + 1];
				}
			}
				break;
			case Message.OBJ_TYPE_CONTROL_READER : {
				if (extended) {
					s = new ExtendedAccessControlReaderStatus[endObject - startObject + 1];
				} else {
					s = new AccessControlReaderStatus[endObject - startObject + 1];
				}
			}
				break;
			case Message.OBJ_TYPE_CONTROL_LOCK : {
				if (extended) {
					s = new ExtendedAccessControlReaderLockStatus[endObject - startObject + 1];
				} else {
					s = new AccessControlReaderLockStatus[endObject - startObject + 1];
				}
			}
				break;
			default :
				break;
		}
		int current = startObject;
		int next = current;
		while (current <= endObject) {
			next += 25;
			if (next > endObject) {
				next = endObject;
			}

			Message msg = null;
			if (extended) {
				msg = sendAndReceive(ReqExtendedObjectStatus.builder().objectType(objectType).startObject(current)
						.endObject(next).build());
			} else {
				msg = sendAndReceive(
						ReqObjectStatus.builder().objectType(objectType).startObject(current).endObject(next).build());
			}

			if (msg.getMessageType() != Message.MESG_TYPE_OBJ_STATUS
					&& msg.getMessageType() != Message.MESG_TYPE_EXT_OBJ_STATUS) {
				throw new OmniInvalidResponseException(msg);
			}
			System.arraycopy(((ObjectStatus) msg).getStatuses(), 0, s, current - startObject, next - current + 1);
			current = next;
			if (current == endObject) {
				break;
			}
			logger.trace("Current: {}  end: {} ", current, endObject);
		}
		return ObjectStatus.builder().statusType(objectType).statuses(s).build();
	}

	public Message reqAudioSourceStatus(int source, int position) throws IOException, OmniNotConnectedException,
			OmniInvalidResponseException, OmniUnknownMessageTypeException {
		Message msg = sendAndReceive(ReqAudioSourceStatus.builder().source(source).position(position).build());
		if (msg.getMessageType() != Message.MESG_TYPE_AUDIO_SOURCE_STATUS
				&& msg.getMessageType() != Message.MESG_TYPE_END_OF_DATA) {
			throw new OmniInvalidResponseException(msg);
		}
		return msg;
	}

	public ZoneReadyStatus reqZoneReadyStatus() throws IOException, OmniNotConnectedException,
			OmniInvalidResponseException, OmniUnknownMessageTypeException {
		Message msg = sendAndReceive(ReqZoneReadyStatus.getInstance());
		if (msg.getMessageType() != Message.MESG_TYPE_ZONE_READY) {
			throw new OmniInvalidResponseException(msg);
		}
		return (ZoneReadyStatus) msg;
	}

	public ConnectedSecurityStatus reqConnectedSecurityStatus() throws IOException, OmniNotConnectedException,
			OmniInvalidResponseException, OmniUnknownMessageTypeException {
		Message msg = sendAndReceive(ReqConnectedSecurityStatus.getInstance());
		if (msg.getMessageType() != Message.MESG_TYPE_CONN_SEC_STATUS) {
			throw new OmniInvalidResponseException(msg);
		}
		return (ConnectedSecurityStatus) msg;
	}

	public Message readEventRecord(int number, int direction) throws IOException, OmniNotConnectedException,
			OmniInvalidResponseException, OmniUnknownMessageTypeException {
		Message msg = sendAndReceive(ReadEventRecord.builder().eventNumber(number).direction(direction).build());
		if (msg.getMessageType() != Message.MESG_TYPE_EVENT_LOG_DATA
				&& msg.getMessageType() != Message.MESG_TYPE_END_OF_DATA) {
			throw new OmniInvalidResponseException(msg);
		}
		return msg;
	}

	public Message readName(int objectType, int objectNumber) throws IOException, OmniNotConnectedException,
			OmniInvalidResponseException, OmniUnknownMessageTypeException {
		Message msg = sendAndReceive(ReadName.builder().objectType(objectType).objectNumber(objectNumber).build());
		if (msg.getMessageType() != Message.MESG_TYPE_NAME_DATA
				&& msg.getMessageType() != Message.MESG_TYPE_END_OF_DATA) {
			throw new OmniInvalidResponseException(msg);
		}
		return msg;
	}

	public void writeName(int objectType, int objectNumber, String name) throws IOException, OmniNotConnectedException,
			OmniInvalidResponseException, OmniUnknownMessageTypeException {
		Message msg = sendAndReceive(
				WriteName.builder().objectType(objectType).objectNumber(objectNumber).name(name).build());
		if (msg.getMessageType() != Message.MESG_TYPE_ACK) {
			throw new OmniInvalidResponseException(msg);
		}
	}

	public void connectedSecurityCommand(int command, int partition, int digit1, int digit2, int digit3, int digit4,
			int digit5, int digit6) throws IOException, OmniNotConnectedException, OmniInvalidResponseException,
			OmniUnknownMessageTypeException {
		ConnectedSecurityCommand connectedSecurityCommand = ConnectedSecurityCommand.builder().command(command)
				.partition(partition).digit1(digit1).digit2(digit2).digit3(digit3).digit4(digit4).digit5(digit5)
				.digit6(digit6).build();

		Message msg = sendAndReceive(connectedSecurityCommand);
		if (msg.getMessageType() != Message.MESG_TYPE_ACK) {
			throw new OmniInvalidResponseException(msg);
		}
	}

	public void controllerCommand(int command, int p1, int p2) throws IOException, OmniNotConnectedException,
			OmniInvalidResponseException, OmniUnknownMessageTypeException {
		Message msg = sendAndReceive(CommandMessage.builder().command(command).parameter1(p1).parameter2(p2).build());
		if (msg.getMessageType() != Message.MESG_TYPE_ACK) {
			throw new OmniInvalidResponseException(msg);
		}
	}

	public void setTimeCommand(int year, int month, int day, int dayOfWeek, int hour, int minute,
			boolean daylightSavings) throws IOException, OmniNotConnectedException, OmniInvalidResponseException,
			OmniUnknownMessageTypeException {
		Message msg = sendAndReceive(SetTimeCommand.builder().year(year).month(month).day(day).dayOfWeek(dayOfWeek)
				.hour(hour).minute(minute).daylightSavings(daylightSavings).build());
		if (msg.getMessageType() != Message.MESG_TYPE_ACK) {
			throw new OmniInvalidResponseException(msg);
		}
	}

	public void activateKeypadEmergency(int area, int emergencyType) throws IOException, OmniNotConnectedException,
			OmniInvalidResponseException, OmniUnknownMessageTypeException {
		Message msg = sendAndReceive(ActivateKeypadEmergency.builder().area(area).emergencyType(emergencyType).build());
		if (msg.getMessageType() != Message.MESG_TYPE_ACK) {
			throw new OmniInvalidResponseException(msg);
		}
	}

	public SecurityCodeValidation reqSecurityCodeValidation(int area, int digit1, int digit2, int digit3, int digit4)
			throws IOException, OmniNotConnectedException, OmniInvalidResponseException,
			OmniUnknownMessageTypeException {
		Message msg = sendAndReceive(ReqSecurityCodeValidation.builder().area(area).digit1(digit1).digit2(digit2)
				.digit3(digit3).digit4(digit4).build());
		if (msg.getMessageType() != Message.MESG_TYPE_SEC_CODE_VALID) {
			throw new OmniInvalidResponseException(msg);
		}
		return (SecurityCodeValidation) msg;
	}

	private static byte[] hexStringToByteArray(String s) {
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
		}
		return data;
	}

	private String bytesToString(byte[] bytes) {
		StringBuffer buff = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			buff.append("0x");
			buff.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			buff.append(" ");
		}
		return buff.toString();
	}

	private class OmniPacket {
		private int seq;
		private int type;
		private byte[] data;

		public OmniPacket(int seq, int type, byte[] data) {
			this.seq = seq;
			this.type = type;
			this.data = data;
		}

		public OmniPacket(int type, byte[] data) {
			this.type = type;
			this.data = data;
		}

		public int seq() {
			return seq;
		}

		public int type() {
			return type;
		}

		public byte[] data() {
			return data;
		}
	}

	private void notifyDisconnectHandlers(Exception e) {
		for (DisconnectListener l : disconnectListeners) {
			l.notConnectedEvent(e);
		}
	}

	private class ConnectionWatchdog extends Thread {
		@Override
		public void run() {
			this.setName("ConnectionWatchdog");
			while (connected) {
				if (ping && System.currentTimeMillis() >= PING_TO + lastTXMessageTime) {
					logger.debug("Pinging Server");
					try {
						reqSystemStatus();
					} catch (Exception ignored) {
					} ;
				}
				try {
					sleep(1000);
				} catch (InterruptedException e) {
				}
			}
		}
	}

	private static class NotificationHandler implements Runnable {

		public static final Message POISON = new Message() {
			@Override
			public int getMessageType() {
				return 0;
			}
		};

		private final BlockingQueue<Message> notifications;
		private final List<NotificationListener> listeners;
		private boolean alive = true;

		private NotificationHandler(BlockingQueue<Message> notifications, List<NotificationListener> listeners) {
			this.notifications = notifications;
			this.listeners = listeners;
		}

		@Override
		public void run() {
			while (alive) {
				try {
					Message message = notifications.take();
					if (message == POISON) {
						alive = false;
					} else {
						for (NotificationListener listener : listeners) {
							if (message instanceof ObjectStatus) {
								listener.objectStatusNotification((ObjectStatus) message);
							} else if (message instanceof OtherEventNotifications) {
								for (int i : ((OtherEventNotifications) message).getNotifications()) {
									SystemEvent se = SystemEvent.fromEvent(i);
									if (se != null) {
										listener.systemEventNotification(se);
									}
								}
							} else {
								logger.debug("Unhandled notficiation message: {}", message);
							}
						}
					}
				} catch (Throwable t) {
					// Catch all exceptions to prevent notification thread from dying.
					logger.error("Notification Handler Caught Exception", t);
					t.printStackTrace();
				}
			}
		}
	}
}
