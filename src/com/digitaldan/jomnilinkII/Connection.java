package com.digitaldan.jomnilinkII;

/**
 *  Copyright (C) 2009  Dan Cunningham                                         
 *                                                                             
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation, version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

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
import java.security.AccessController;
import java.util.LinkedList;
import java.util.Vector;

import com.digitaldan.jomnilinkII.MessageTypes.ActivateKeypadEmergency;
import com.digitaldan.jomnilinkII.MessageTypes.CommandMessage;
import com.digitaldan.jomnilinkII.MessageTypes.ConnectedSecurityCommand;
import com.digitaldan.jomnilinkII.MessageTypes.ConnectedSecurityStatus;
import com.digitaldan.jomnilinkII.MessageTypes.DownloadNames;
import com.digitaldan.jomnilinkII.MessageTypes.EnableNotifications;
import com.digitaldan.jomnilinkII.MessageTypes.EventLogData;
import com.digitaldan.jomnilinkII.MessageTypes.ExtendedObjectStatus;
import com.digitaldan.jomnilinkII.MessageTypes.ObjectStatus;
import com.digitaldan.jomnilinkII.MessageTypes.ObjectTypeCapacities;
import com.digitaldan.jomnilinkII.MessageTypes.OtherEventNotifications;
import com.digitaldan.jomnilinkII.MessageTypes.ReqAudioSourceStatus;
import com.digitaldan.jomnilinkII.MessageTypes.ReqConnectedSecurityStatus;
import com.digitaldan.jomnilinkII.MessageTypes.ReqExtenedObjectStatus;
import com.digitaldan.jomnilinkII.MessageTypes.ReqObjectProperties;
import com.digitaldan.jomnilinkII.MessageTypes.ReqObjectStatus;
import com.digitaldan.jomnilinkII.MessageTypes.ReqObjectTypeCapacities;
import com.digitaldan.jomnilinkII.MessageTypes.ReqSecurityCodeValidation;
import com.digitaldan.jomnilinkII.MessageTypes.ReqSystemFeatures;
import com.digitaldan.jomnilinkII.MessageTypes.ReqSystemFormats;
import com.digitaldan.jomnilinkII.MessageTypes.ReqSystemInformation;
import com.digitaldan.jomnilinkII.MessageTypes.ReqSystemStatus;
import com.digitaldan.jomnilinkII.MessageTypes.ReqSystemTroubles;
import com.digitaldan.jomnilinkII.MessageTypes.ReqZoneReadyStatus;
import com.digitaldan.jomnilinkII.MessageTypes.SecurityCodeValidation;
import com.digitaldan.jomnilinkII.MessageTypes.SetTimeCommand;
import com.digitaldan.jomnilinkII.MessageTypes.SystemFeatures;
import com.digitaldan.jomnilinkII.MessageTypes.SystemFormats;
import com.digitaldan.jomnilinkII.MessageTypes.SystemInformation;
import com.digitaldan.jomnilinkII.MessageTypes.SystemStatus;
import com.digitaldan.jomnilinkII.MessageTypes.SystemTroubles;
import com.digitaldan.jomnilinkII.MessageTypes.UploadEventRecord;
import com.digitaldan.jomnilinkII.MessageTypes.UploadNames;
import com.digitaldan.jomnilinkII.MessageTypes.ZoneReadyStatus;
import com.digitaldan.jomnilinkII.MessageTypes.statuses.AccessControlReaderLockStatus;
import com.digitaldan.jomnilinkII.MessageTypes.statuses.AccessControlReaderStatus;
import com.digitaldan.jomnilinkII.MessageTypes.statuses.AreaStatus;
import com.digitaldan.jomnilinkII.MessageTypes.statuses.AudioZoneStatus;
import com.digitaldan.jomnilinkII.MessageTypes.statuses.AuxSensorStatus;
import com.digitaldan.jomnilinkII.MessageTypes.statuses.ExpansionStatus;
import com.digitaldan.jomnilinkII.MessageTypes.statuses.ExtendedThermostatStatus;
import com.digitaldan.jomnilinkII.MessageTypes.statuses.MessageStatus;
import com.digitaldan.jomnilinkII.MessageTypes.statuses.Status;
import com.digitaldan.jomnilinkII.MessageTypes.statuses.ThermostatStatus;
import com.digitaldan.jomnilinkII.MessageTypes.statuses.UnitStatus;
import com.digitaldan.jomnilinkII.MessageTypes.statuses.UserSettingStatus;
import com.digitaldan.jomnilinkII.MessageTypes.statuses.ZoneStatus;

public class Connection extends Thread {
	private static int PACKET_TYPE_CLIENT_REQUEST_NEW_SESSION = 1;
	private static int PACKET_TYPE_CONTROLLER_ACKNOWLEDGE_NEW_SESSION = 2;
	private static int PACKET_TYPE_CLIENT_REQUEST_SECURE_CONNECTION = 3;
	private static int PACKET_TYPE_CONTROLLER_ACKNOWLEDGE_SECURE_CONNECTION = 4;
	private static int PACKET_TYPE_CLIENT_SESSION_TERMINATED = 5;
	private static int PACKET_TYPE_CONTROLLER_SESSION_TERMINATED = 6;
	private static int PACKET_TYPE_CONTROLLER_CANNOT_START_NEW_SESSION = 7;
	private static int PACKET_TYPE_OMNI_LINK_MESSAGE = 32;

	public static int MAX_PACKET_SIZE = 255;
	//Omni will kick you after 5 minutes of not receiveing anything
	public static int OMNI_TO = 60 * 5 * 1000;
	//Keep alive time, omni timeout minus one minute
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
	private LinkedList<Message> notifications;
	private OmniPacket response;
	private Object readLock = new Object();
	private Object writeLock = new Object();
	private Object notifyLock = new Object();
	private Exception lastException;
	private Vector<NotificationListener> notificationListeners;
	private Vector<DisconnectListener> disconnectListeners;
	private NotificationHandler notificationHandler;
	private ConnectionWatchdog watchdog;
	public Connection(String address, int port, String key) 
	throws Exception,IOException,UnknownHostException {

		ping = true;
		notifications = new LinkedList<Message>();
		response = null;
		lastException = null;
		notificationListeners = new Vector();
		disconnectListeners = new Vector();

		byte[] _key = hexStringToByteArray(key.replaceAll("\\W", ""));		

		socket = new Socket(address,port);
		is = socket.getInputStream();
		os = socket.getOutputStream();
		socket.setSoTimeout(OMNI_TO);
		tx = 1;
		rx = 1;

		sendBytes(new OmniPacket(PACKET_TYPE_CLIENT_REQUEST_NEW_SESSION,null));

		OmniPacket rec = readBytes();

		if(rec.type() != PACKET_TYPE_CONTROLLER_ACKNOWLEDGE_NEW_SESSION)
			throw new Exception("Controller not accepting new connections");

		byte [] data = rec.data();

		int version = (int)((data[0] << 8) + (data[1] << 0));
		if(debug)
			System.out.println("Controller version " + version);

		byte [] sessionid = new byte[5];
		System.arraycopy(data, 2, sessionid, 0, 5);

		for (int i = 0; i < 5; i++){
			_key[i + 11] ^= sessionid[i];
		}

		aes = new Aes(_key);
		sendBytesEncrypted(new OmniPacket(PACKET_TYPE_CLIENT_REQUEST_SECURE_CONNECTION,sessionid));

		rec = readBytesEncrypted();
		if(rec.type() != PACKET_TYPE_CONTROLLER_ACKNOWLEDGE_SECURE_CONNECTION)
			throw new Exception("Could not establish secure connection");

		data = rec.data();

		for(int i=0; i<5; i++){
			if(debug)
				System.out.println("Data " + i + " mine " + sessionid[i] + " controllers " + data[i]);
			if( (int)data[i] != (int)sessionid[i]){
				throw new IOException("Controller returned wrong sessioid");
			}
		}
		connected = true;
		lastTXMessageTime = System.currentTimeMillis();
		
		notificationListeners = new Vector<NotificationListener>();
		
		this.setName("OmniReaderThread");
		this.start();
		
		notificationHandler = new NotificationHandler();
		notificationHandler.setName("NotificationHandlerThread");
		notificationHandler.start();
		
		ConnectionWatchdog watchdog = new ConnectionWatchdog();
		watchdog.setName("ConnectionWatchdogThread");
		watchdog.start();
	}

	public void disconnect(){
		connected = false;
		if(socket != null){
			try {
				socket.close();
			} catch (Exception e){

			}
		}
	}

	public boolean connected(){
		return connected;
	}

	public Exception lastError(){
		return lastException;
	}

	public boolean autoPingOmni(){
		return ping;
	}

	public void autoPingOmni(boolean ping){
		this.ping = ping;
	}

	public void addNotificationListener(NotificationListener listener){
		synchronized (notificationListeners) {
			notificationListeners.add(listener);
		}
	}

	public void removeNotificationListener(NotificationListener listener){
		synchronized (notificationListeners) {
			if(notificationListeners.contains(listener))
				notificationListeners.remove(listener);
		}
	}

	public void addDisconnectListener(DisconnectListener listener){
		synchronized (disconnectListeners) {
			disconnectListeners.add(listener);
		}
	}

	public void removeDisconnecListener(DisconnectListener listener){
		synchronized (disconnectListeners) {
			if(disconnectListeners.contains(listener))
				disconnectListeners.remove(listener);
		}
	}

	public Message sendAndReceive(Message message) throws IOException, OmniNotConnectedException, OmniUnknownMessageTypeException{

		synchronized(writeLock){
			if(!connected)
				throw new OmniNotConnectedException(lastError());

			OmniPacket ret;
			sendBytesEncrypted(new OmniPacket(PACKET_TYPE_OMNI_LINK_MESSAGE, 
					MessageFactory.toBytes(message)));
			synchronized(readLock){
				//wait for notfiy when response comes in on thread
				while(response == null && connected) {
					try { readLock.wait();} catch (InterruptedException ignored){}
				}
				ret = response;
				//no longer need this reference
				response = null;
				//notify reader it can continue;
				readLock.notify();
				//if an error occurs on our other thread it saves the exception
				if(!connected)
				    throw new OmniNotConnectedException(lastError());
			}
			if(ret.type() != PACKET_TYPE_OMNI_LINK_MESSAGE) {
				System.out.println(bytesToString(ret.data()));
				throw new IOException("RECEIEVD NON OMNI_LINK_MESG ("+ ( ret == null ? "NULL MESG" : ret.type() ) +")");
			}

			//used to ping after a certain amount of time
			lastTXMessageTime = System.currentTimeMillis();

			writeLock.notifyAll();
			return MessageFactory.fromBytes(ret.data());
		}
	}

	public void run() {
		OmniPacket ret;
		while(connected){
			synchronized (readLock) {
				try {
					if((ret = readBytesEncrypted2()).seq() == 0 &&
							ret.type() == PACKET_TYPE_OMNI_LINK_MESSAGE){
						notifications.add(MessageFactory.fromBytes(ret.data()));
						if(debug)
							System.out.println("run: NOTIFICATION: Added message with type " + ret.type);
						synchronized (notifyLock) {
							notifyLock.notifyAll();
						}
					} else if(ret.type() == PACKET_TYPE_OMNI_LINK_MESSAGE) {
						response = ret;
						//notify calling request lock
						readLock.notify();
						//wait until the response is finished
						try { readLock.wait();} catch (InterruptedException ignored){}
					} else {
						throw new IOException("Non omnilink message");
					}
				} catch(OmniUnknownMessageTypeException e){
					//ignored
					if(debug){
						e.printStackTrace();
						System.out.println("run: Uknown Messgage type " + e.getUnknowMessageType() + " Continuing");
					}
//				}catch(SocketTimeoutException e){
//					//ignored
//					if(debug){
//						e.printStackTrace();
//						System.out.println("Ignoring SocketTimeoutException, will try and send omni a ping if needed");
//					}
				}catch(Exception e){
					connected = false;
					lastException = e;
					//tell listeners about exception
					notifyDisconnectHandlers(lastException);
				} finally {
					readLock.notifyAll();
				}
				//omni will kick you off in 5 minutes if they don't get a message
//				if(ping && connected &&
//						System.currentTimeMillis() >= PING_TO + lastTXMessageTime){
//					if(debug){
//						System.out.println("Pinging Server");
//					}
//					pingServer();
//				}					
			}
		}
		if(debug)
			System.out.println("run: not connected, thread exiting");
	}

//	private void pingServer(){
//		Thread p = new Thread("pingServer"){
//			public void run(){
//				try {
//					reqSystemStatus();
//				} catch (Exception e){
//
//				}
//			}
//		};
//		p.start();
//	}
	/*
	 * The following procedure is used to encrypt Omni-Link II application data:
    1.   Process data in 128-bit (16-byte) blocks. If available data does not fill a 16-byte block, the data is
         left-justified and padded on the right with zeros to fill the block.
    2.   Modify the first two bytes of the 16-byte encryption block by performing a logical XOR operation with the
         two bytes of the “message sequence number” in the HAI header (i.e., XOR the first byte of the encryption
         block with the MSB of the message sequence number, and XOR the second byte of the encryption block
         with the LSB of the message sequence number).
    3.   Encrypt the 16-byte block using the AES encryption algorithm and the 128-bit session key that was
         negotiated when the client and controller established the secure connection.
    4.   Process the next block of data until all data has been processed.

	 */
	private void sendBytesEncrypted(OmniPacket p) throws IOException {
		/* 1. */
		if(debug)
			System.out.println("TX: " + bytesToString(p.data()));
		int txlength = (p.data().length + 15) & ~0xF;
		byte [] paddedData = new byte[txlength];

		System.arraycopy(p.data(), 0, paddedData, 0, p.data().length);
		for(int i = p.data().length; i < txlength ;i++){
			paddedData[i] = 0x00;
		}
		/* 2 */
		for (int i = 0; i < (txlength / 16); i++){
			paddedData[0 + (16 * i)] ^= (tx >> 8) & 0xFF;
			paddedData[1 + (16 * i)] ^= (tx) & 0xFF;
		}
		/*3*/
		byte []encData; 
		try {
			encData = aes.encrypt(paddedData);
		} catch (Exception e){
			throw new IOException(e.getMessage());
		}
		sendBytes(new OmniPacket(p.type(),encData));
	}

	private void sendBytes(OmniPacket p) throws IOException {
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		DataOutputStream dout = new DataOutputStream(bout);
		dout.writeShort(tx);
		dout.writeByte(p.type());
		dout.writeByte(0);
		if(p.data() != null)
			dout.write(p.data());
		os.write(bout.toByteArray());
		os.flush();
		tx++;
		if(tx >= 65535)
			tx = 1;
	}

	private OmniPacket readBytesEncrypted() throws IOException, SocketTimeoutException {
		OmniPacket p = readBytes();
		if(debug)
			System.out.println("Enc Dec " + bytesToString(p.data()));
		if(p.data().length == 0)
			return p;
		byte [] decData = aes.decrypt(p.data());
		/* XOR data */
		for (int i = 0; i < (decData.length / 16); i++){
			decData[0 + (16 * i)] ^= (p.seq >> 8) & 0xFF;
			decData[1 + (16 * i)] ^= (p.seq) & 0xFF;
		}
		if(debug)
			System.out.println("Data Dec " + bytesToString(decData));
		return new OmniPacket(p.seq,p.type(), decData);

	}

	private OmniPacket readBytesEncrypted2() throws IOException, SocketTimeoutException{
		//Notifications have thrown a bit of a curve ball, its possible to have
		//two packets on the wire, but because the length of the packet
		//is encrypted we have to peek into the first 16 bytes, decrypt those
		//bytes and get the length, this makes the following code tricky and
		//unattractive, 
		DataInputStream dis = new DataInputStream(is);

		if(debug)
			if(debug)System.out.println("readBytesEncrypted2: Bytes available for reading: " + is.available());

		int seq = dis.readUnsignedShort();
		int type = dis.readUnsignedByte();
		int reserved = dis.readUnsignedByte();

		byte [] encData = new byte [16];
		dis.readFully(encData);
		byte [] decData = aes.decrypt(encData);

		decData[0]^=(seq >> 8) & 0xFF;
		decData[1]^=(seq) & 0xFF;


		//not all messages are omnilink
		if(type != PACKET_TYPE_OMNI_LINK_MESSAGE) {
			if(debug){
				System.out.println("RX: " + bytesToString(decData));
				System.out.println("NON OMNI LINK PACKET: " + type);
			}
			return new OmniPacket(seq,type,decData);
		}

		//continue with omnilink decoding
		int start = (int) decData[0] & 0xFF;
		int length = (int) decData[1] & 0xFF;

		if(start != Message.MESG_START)
			System.out.println("invalid start char (" + start + ")");
		//throw new IOException("invalid start char (" + start + ")");
		if(length < 0) 
			throw new IOException("invalid message length (" + length + ")");


		if(debug)
			System.out.println("readBytesEncrypted2: Omni message Length " + length);
		//length plus start and crc fields, round up to next 16, minus the bytes we have already read
		int readLength = ((((length+3)/16)+1)*16) -16;
		if(debug)
			System.out.println("readBytesEncrypted2: Additional bytes to read " + readLength);

		if(readLength > 0){
			//buffer for existing 16 bytes of data plus any on the wire
			byte [] decData2 = new byte[decData.length + readLength];
			encData = new byte[readLength];
			//copy the data we already have from decData to decData2
			System.arraycopy(decData, 0,decData2,0,decData.length);
			//read the rest 
			dis.readFully(encData);
			//add decrypted data to the buffer
			aes.decrypt(encData,0,readLength,decData2,decData.length);
			/* XOR data */
			for (int i = 1; i < (decData2.length / 16); i++){
				decData2[0 + (16 * i)] ^= (seq >> 8) & 0xFF;
				decData2[1 + (16 * i)] ^= (seq) & 0xFF;
			}
			decData = decData2;
		}
		if(debug)
			System.out.println("RX: " + bytesToString(decData));

		if(debug)
			System.out.println("readBytesEncrypted2: Data still available after read " + is.available());
		return new OmniPacket(seq,type,decData);

	}

	private OmniPacket readBytes() throws IOException, SocketTimeoutException {
		byte[] data = new byte[MAX_PACKET_SIZE];
		int cnt = is.read(data);

		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));

		int seq = dis.readUnsignedShort();
		int type = dis.readUnsignedByte();
		int reserved = dis.readUnsignedByte();

		byte [] msgdata = new byte [cnt -4];
		dis.readFully(msgdata);

		return new OmniPacket(seq,type,msgdata);
	}

	public void enableNotifications() throws IOException, OmniNotConnectedException, OmniInvalidResponseException, OmniUnknownMessageTypeException{
		Message msg = sendAndReceive(new EnableNotifications());
		if(msg.getMessageType() != Message.MESG_TYPE_ACK)
			throw new OmniInvalidResponseException(msg);
	}

	public SystemInformation reqSystemInformation() throws IOException, OmniNotConnectedException, OmniInvalidResponseException, OmniUnknownMessageTypeException{
		Message msg = sendAndReceive(new ReqSystemInformation());
		if(msg.getMessageType() != Message.MESG_TYPE_SYS_INFO)
			throw new OmniInvalidResponseException(msg);
		return (SystemInformation)msg;
	}

	public SystemStatus reqSystemStatus() throws IOException, OmniNotConnectedException, OmniInvalidResponseException, OmniUnknownMessageTypeException {
		Message msg = sendAndReceive(new ReqSystemStatus());
		if(msg.getMessageType() != Message.MESG_TYPE_SYS_STATUS)
			throw new OmniInvalidResponseException(msg);
		return (SystemStatus)msg;
	}

	public SystemTroubles reqSystemTroubles() throws IOException, OmniNotConnectedException, OmniInvalidResponseException, OmniUnknownMessageTypeException {
		Message msg = sendAndReceive(new ReqSystemTroubles());
		if(msg.getMessageType() != Message.MESG_TYPE_SYS_TROUBLES)
			throw new OmniInvalidResponseException(msg);
		return (SystemTroubles)msg;
	}

	public SystemFeatures reqSystemFeatures() throws IOException, OmniNotConnectedException, OmniInvalidResponseException, OmniUnknownMessageTypeException {
		Message msg = sendAndReceive(new ReqSystemFeatures());
		if(msg.getMessageType() != Message.MESG_TYPE_SYS_FEATURES)
			throw new OmniInvalidResponseException(msg);
		return (SystemFeatures)msg;
	}

	public SystemFormats reqSystemFormats() throws IOException, OmniNotConnectedException, OmniInvalidResponseException, OmniUnknownMessageTypeException {
		Message msg = sendAndReceive(new ReqSystemFormats());
		if(msg.getMessageType() != Message.MESG_TYPE_SYS_FORMATS)
			throw new OmniInvalidResponseException(msg);
		return (SystemFormats)msg;
	}

	public ObjectTypeCapacities reqObjectTypeCapacities(int objectType) throws IOException, OmniNotConnectedException, OmniInvalidResponseException, OmniUnknownMessageTypeException {
		Message msg = sendAndReceive(new ReqObjectTypeCapacities(objectType));
		if(msg.getMessageType() != Message.MESG_TYPE_OBJ_CAPACITY)
			throw new OmniInvalidResponseException(msg);
		return (ObjectTypeCapacities)msg;
	}

	public Message reqObjectProperties(int objectType, int objectNum, int direction,
			int filter1, int filter2, int filter3) throws IOException, OmniNotConnectedException, OmniInvalidResponseException, OmniUnknownMessageTypeException {
		Message msg = sendAndReceive(new ReqObjectProperties(objectType, objectNum, direction,
				filter1, filter2, filter3));
		if(msg.getMessageType() != Message.MESG_TYPE_OBJ_PROP && msg.getMessageType() 
				!= Message.MESG_TYPE_END_OF_DATA)
			throw new OmniInvalidResponseException(msg);
		return msg;
	}

	public ObjectStatus reqObjectStatus(int objectType, int startObject, int endObject) throws IOException, OmniNotConnectedException, OmniInvalidResponseException, OmniUnknownMessageTypeException {
		return reqObjectStatus(objectType,startObject,endObject,false);
	}
	public ExtendedObjectStatus reqExtendedObjectStatus(int objectType, int startObject, int endObject) throws IOException, OmniNotConnectedException, OmniInvalidResponseException, OmniUnknownMessageTypeException {
		return (ExtendedObjectStatus)reqObjectStatus(objectType,startObject,endObject,true);
	}

	public ObjectStatus reqObjectStatus(int objectType, int startObject, int endObject, boolean extended) throws IOException, OmniNotConnectedException, OmniInvalidResponseException, OmniUnknownMessageTypeException {
		Status []s = null;
		switch (objectType) {
		case Message.OBJ_TYPE_AREA:{
			s = new AreaStatus[endObject - startObject + 1];
		}
		break;
		case Message.OBJ_TYPE_AUDIO_ZONE:{
			s = new AudioZoneStatus[endObject - startObject + 1];
		}
		break;
		case Message.OBJ_TYPE_AUX_SENSOR:{
			s = new AuxSensorStatus[endObject - startObject + 1];
		}
		break;
		case Message.OBJ_TYPE_EXP:{
			s = new ExpansionStatus[endObject - startObject + 1];
		}
		break;
		case Message.OBJ_TYPE_MESG:{
			s = new MessageStatus[endObject - startObject + 1];
		}
		break;
		case Message.OBJ_TYPE_THERMO:{
			if(extended)
				s = new ExtendedThermostatStatus[endObject - startObject + 1];
			else
				s = new ThermostatStatus[endObject - startObject + 1];
		}
		break;

		case Message.OBJ_TYPE_UNIT: {
			s = new UnitStatus[endObject - startObject + 1];
		}
		break;
		case Message.OBJ_TYPE_ZONE: {
			s = new ZoneStatus[endObject - startObject + 1];
		}
		break;
		case Message.OBJ_TYPE_USER_SETTING: {
			s = new UserSettingStatus[endObject - startObject + 1];
		}
		break;
		case Message.OBJ_TYPE_CONTROL_READER: {
			s = new AccessControlReaderStatus[endObject - startObject + 1];
		}
		break;
		case Message.OBJ_TYPE_CONTROL_LOCK: {
			s = new AccessControlReaderLockStatus[endObject - startObject + 1];
		}
		break;
		default:
			break;
		}
		int current = startObject;
		int next = current;
		while(current <= endObject){
			next += 25;
			if(next > endObject)
				next = endObject;
			Message msg = null;
			if(extended)
				msg = sendAndReceive(new ReqObjectStatus(objectType,current,next));
			else
				msg = sendAndReceive(new ReqExtenedObjectStatus(objectType,current,next));

			if(msg.getMessageType() != Message.MESG_TYPE_OBJ_STATUS &&
					msg.getMessageType() != Message.MESG_TYPE_EXT_OBJ_STATUS)
				throw new OmniInvalidResponseException(msg);
			System.arraycopy(((ObjectStatus)msg).getStatuses(), 0, s,current - startObject, next - current + 1 );
			current = next;
			if(current == endObject) break;
			System.out.println("Current: " + current + " end " + endObject);
		}
		return new ObjectStatus(objectType,s);
	}

	public Message reqAudioSourceStatus(int source, int position) throws IOException, OmniNotConnectedException, OmniInvalidResponseException, OmniUnknownMessageTypeException {
		Message msg = sendAndReceive(new ReqAudioSourceStatus(source, position));
		if(msg.getMessageType() != Message.MESG_TYPE_AUDIO_SOURCE_STATUS &&
				msg.getMessageType() != Message.MESG_TYPE_END_OF_DATA)
			throw new OmniInvalidResponseException(msg);
		return msg;
	}

	public ZoneReadyStatus reqZoneReadyStatus() throws IOException, OmniNotConnectedException, OmniInvalidResponseException, OmniUnknownMessageTypeException {
		Message msg = sendAndReceive(new ReqZoneReadyStatus());
		if(msg.getMessageType() != Message.MESG_TYPE_ZONE_READY)
			throw new OmniInvalidResponseException(msg);
		return (ZoneReadyStatus)msg;
	}

	public ConnectedSecurityStatus reqConnectedSecurityStatus() throws IOException, OmniNotConnectedException, OmniInvalidResponseException, OmniUnknownMessageTypeException {
		Message msg = sendAndReceive(new ReqConnectedSecurityStatus());
		if(msg.getMessageType() != Message.MESG_TYPE_CONN_SEC_STATUS)
			throw new OmniInvalidResponseException(msg);
		return (ConnectedSecurityStatus)msg;
	}

	public Message uploadEventLogData(int number, int direction) throws IOException, OmniNotConnectedException, OmniInvalidResponseException, OmniUnknownMessageTypeException {
		Message msg = sendAndReceive(new UploadEventRecord(number,direction));
		if(msg.getMessageType() != Message.MESG_TYPE_EVENT_LOG_DATA &&
				msg.getMessageType() != Message.MESG_TYPE_END_OF_DATA)
			throw new OmniInvalidResponseException(msg);
		return msg;
	}

	public Message uploadNames(int objectType, int objectNumber) throws IOException, OmniNotConnectedException, OmniInvalidResponseException, OmniUnknownMessageTypeException {
		Message msg = sendAndReceive(new UploadNames(objectType,objectNumber));
		if(msg.getMessageType() != Message.MESG_TYPE_NAME_DATA &&
				msg.getMessageType() != Message.MESG_TYPE_END_OF_DATA)
			throw new OmniInvalidResponseException(msg);
		return msg;
	}


	public void downloadNames(int objectType, int objectNumber, String name) throws IOException, OmniNotConnectedException , OmniInvalidResponseException, OmniUnknownMessageTypeException {
		Message msg = sendAndReceive(new DownloadNames(objectType,objectNumber,name));
		if(msg.getMessageType() != Message.MESG_TYPE_ACK)
			throw new OmniInvalidResponseException(msg);
	}

	public  void connectedSecurityCommand(int command, int partition, int digit1,
			int digit2,int digit3,int digit4,int digit5,int digit6) throws IOException, OmniNotConnectedException, OmniInvalidResponseException, OmniUnknownMessageTypeException {
		Message msg = sendAndReceive(new ConnectedSecurityCommand(command,partition, digit1,
				digit2,digit3,digit4,digit5,digit6));
		if(msg.getMessageType() != Message.MESG_TYPE_ACK)
			throw new OmniInvalidResponseException(msg);
	}

	public  void controllerCommand(int command, int p1, int p2) throws IOException, OmniNotConnectedException, OmniInvalidResponseException, OmniUnknownMessageTypeException {
		Message msg = sendAndReceive(new CommandMessage(command,p1,p2));
		if(msg.getMessageType() != Message.MESG_TYPE_ACK)
			throw new OmniInvalidResponseException(msg);
	}

	public  void setTimeCommand(int year, int month, int day, int dayOfWeek,
			int hour, int minute, boolean daylightSavings) throws IOException, OmniNotConnectedException, OmniInvalidResponseException, OmniUnknownMessageTypeException {
		Message msg = sendAndReceive(new SetTimeCommand(year, month, day, dayOfWeek,
				hour, minute, daylightSavings));
		if(msg.getMessageType() != Message.MESG_TYPE_ACK)
			throw new OmniInvalidResponseException(msg);
	}

	public  void activateKeypadEmergency(int area,int emergencyType) throws IOException, OmniNotConnectedException, OmniInvalidResponseException, OmniUnknownMessageTypeException {
		Message msg = sendAndReceive(new ActivateKeypadEmergency(area,emergencyType));
		if(msg.getMessageType() != Message.MESG_TYPE_ACK)
			throw new OmniInvalidResponseException(msg);
	}

	public SecurityCodeValidation reqSecurityCodeValidation(int area, int digit1,
			int digit2,int digit3,int digit4) throws IOException, OmniNotConnectedException, OmniInvalidResponseException, OmniUnknownMessageTypeException {
		Message msg = sendAndReceive(new ReqSecurityCodeValidation(area, digit1,
				digit2,digit3,digit4));
		if(msg.getMessageType() != Message.MESG_TYPE_SEC_CODE_VALID)
			throw new OmniInvalidResponseException(msg);
		return (SecurityCodeValidation)msg;
	}

	private static byte[] hexStringToByteArray(String s) {
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
					+ Character.digit(s.charAt(i+1), 16));
		}
		return data;
	}

	private String bytesToString(byte[] bytes){
		StringBuffer buff = new StringBuffer();
		for(int i=0;i<bytes.length;i++){
			buff.append("0x");
			buff.append(Integer.toString( ( bytes[i] & 0xff ) + 0x100, 16).substring( 1 ));
			buff.append(" ");
		}
		return buff.toString();
	}

	private class OmniPacket {
		private int seq;
		private int type;
		private byte [] data;

		public OmniPacket(int seq, int type, byte[] data) {
			this.seq = seq;
			this.type = type;
			this.data = data;
		}

		public OmniPacket(int type, byte[] data) {
			this.type = type;
			this.data = data;
		}

		public int seq(){return seq;}
		public int type(){return type;}
		public byte[] data(){return data;}
	}

	private void notifyDisconnectHandlers(Exception e){
		synchronized (disconnectListeners) {	
			for (DisconnectListener l : disconnectListeners) {
				l.notConnectedEvent(e);
			}
		}
	}
	
	private class ConnectionWatchdog extends Thread {
		public void run(){
			this.setName("ConnectionWatchdog");
			while(connected){
				if(ping &&
						System.currentTimeMillis() >= PING_TO + lastTXMessageTime){
					if(debug){
						System.out.println("Pinging Server");
					}
					try {
					reqSystemStatus();
					} catch(Exception ignored){};
				}					
				try {
					sleep(1000);
				} catch (InterruptedException e) {}
			}	
		}
	}
	
	private class NotificationHandler extends Thread{
		public void run(){
			while(connected){
				synchronized (notifyLock) {
					while(notifications.size() == 0 && connected){
						try { notifyLock.wait(1000);} catch (InterruptedException ignored){}
					}
				}
				if(connected){
					LinkedList<Message> messages;
					synchronized (notifications) {
						messages = (LinkedList<Message>)notifications.clone();
						notifications.clear();
					}
					synchronized (notificationListeners) {
						for(Message m : messages){
							for (NotificationListener l : notificationListeners) {
								if(m instanceof ObjectStatus){
									l.objectStausNotification((ObjectStatus)m);
								} else {
									l.otherEventNotification((OtherEventNotifications)m);
								}
							}
						}
					}
				}
			}
		}
	}

}

