package com.digitaldan.jomnilinkII.examples;

import com.digitaldan.jomnilinkII.MessageTypes.statuses.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.digitaldan.jomnilinkII.Connection;
import com.digitaldan.jomnilinkII.DisconnectListener;
import com.digitaldan.jomnilinkII.Message;
import com.digitaldan.jomnilinkII.NotificationListener;
import com.digitaldan.jomnilinkII.OmniInvalidResponseException;
import com.digitaldan.jomnilinkII.OmniNotConnectedException;
import com.digitaldan.jomnilinkII.MessageTypes.AudioSourceStatus;
import com.digitaldan.jomnilinkII.MessageTypes.EventLogData;
import com.digitaldan.jomnilinkII.MessageTypes.ObjectProperties;
import com.digitaldan.jomnilinkII.MessageTypes.ObjectStatus;
import com.digitaldan.jomnilinkII.MessageTypes.systemevents.ButtonEvent;
import com.digitaldan.jomnilinkII.MessageTypes.systemevents.SystemEvent;

/*
 * Notes for NIO
 * Luanch connection in a worker thread, connect will operate as a blocking operation, on success add the connection object to a NIO thread for reading/writing.
 *
 * write web service, sign up with email, password and keys.  On account creation, connect to house, if successful add to database and have our loop pick it up for monitoring.
 *
 * what to do with exceptions?  Pull connections out to a slow que for retry, email user on three bad attemps
 */

public class Main {

	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		try {
			if (args.length != 3) {
				logger.info("Usage:com.digitaldan.jomnilinkII.Main  host port encKey");
				System.exit(-1);
			}
			String host = args[0];
			int port = Integer.parseInt(args[1]);
			String key = args[2];
			Message m;
			Connection c = new Connection(host, port, key);
			c.debug = true;
			c.addNotificationListener(new NotificationListener() {
				@Override
				public void objectStatusNotification(ObjectStatus s) {
					switch (s.getStatusType()) {
					case Message.OBJ_TYPE_AREA:
						logger.info("STATUS_AREA changed");
						break;
					case Message.OBJ_TYPE_AUDIO_ZONE:
						logger.info("STATUS_AUDIO_ZONE changed");
						break;
					case Message.OBJ_TYPE_AUX_SENSOR:
						logger.info("STATUS_AUX changed");
						break;
					case Message.OBJ_TYPE_EXP:
						logger.info("STATUS_EXP changed");
						break;
					case Message.OBJ_TYPE_MESG:
						logger.info("STATUS_MESG changed");
						break;
					case Message.OBJ_TYPE_THERMO:
						logger.info("STATUS_THERMO changed");
						break;
					case Message.OBJ_TYPE_UNIT:
						logger.info("STATUS_UNIT changed");
						break;
					case Message.OBJ_TYPE_ZONE:
						logger.info("STATUS_ZONE changed");
						break;
					default:
						logger.info("Unknown type " + s.getStatusType());
						break;
					}
					logger.info(s.toString());
				}

				@Override
				public void systemEventNotification(SystemEvent event) {
					logger.info("Got SystemEvent tyoe {}", event.getType());
					switch (event.getType()) {
					case BUTTON:
						logger.info("ButtonEvent number {}", ((ButtonEvent) event).getButtonNumber());
						break;
					case PHONE_LINE_OFF_HOOK:
						logger.info("PHONE_LINE_OFF_HOOK event");
						break;
					default:
						break;
					}

				}
			});

			c.addDisconnectListener(new DisconnectListener() {
				@Override
				public void notConnectedEvent(Exception e) {
					e.printStackTrace();
					System.exit(-1);
				}
			});
			//			c.debug = true;
			c.enableNotifications();
			logger.info(c.reqSystemInformation().toString());
			logger.info(c.reqSystemStatus().toString());
			logger.info(c.reqSystemTroubles().toString());
			logger.info(c.reqSystemFormats().toString());
			logger.info(c.reqSystemFeatures().toString());
			int max_zones = c.reqObjectTypeCapacities(Message.OBJ_TYPE_ZONE).getCapacity();
			int max_units = c.reqObjectTypeCapacities(Message.OBJ_TYPE_UNIT).getCapacity();
			int max_areas = c.reqObjectTypeCapacities(Message.OBJ_TYPE_AREA).getCapacity();
			int max_buttons = c.reqObjectTypeCapacities(Message.OBJ_TYPE_BUTTON).getCapacity();
			int max_codes = c.reqObjectTypeCapacities(Message.OBJ_TYPE_CODE).getCapacity();
			int max_thermos = c.reqObjectTypeCapacities(Message.OBJ_TYPE_THERMO).getCapacity();
			int max_mesgs = c.reqObjectTypeCapacities(Message.OBJ_TYPE_MESG).getCapacity();
			int max_audio_zones = c.reqObjectTypeCapacities(Message.OBJ_TYPE_AUDIO_ZONE).getCapacity();
			int max_audio_sources = c.reqObjectTypeCapacities(Message.OBJ_TYPE_AUDIO_SOURCE).getCapacity();
			int max_locks = c.reqObjectTypeCapacities(Message.OBJ_TYPE_CONTROL_READER).getCapacity();

			//Aux sensors returns error, They are considered zones by HAI
			// logger.info(c.reqObjectTypeCapacities(Message.OBJ_TYPE_AUX_SENSOR).toString());
			logger.info(c.reqObjectTypeCapacities(Message.OBJ_TYPE_AUDIO_SOURCE).toString());
			logger.info(c.reqObjectTypeCapacities(Message.OBJ_TYPE_AUDIO_ZONE).toString());

			int objnum = 0;
			while ((m = c.reqObjectProperties(Message.OBJ_TYPE_ZONE, objnum, 1, ObjectProperties.FILTER_1_NAMED,
					ObjectProperties.FILTER_2_AREA_ALL, ObjectProperties.FILTER_3_ANY_LOAD))
							.getMessageType() == Message.MESG_TYPE_OBJ_PROP) {
				logger.info(m.toString());
				objnum = ((ObjectProperties) m).getNumber();
			}
			objnum = 0;
			while ((m = c.reqObjectProperties(Message.OBJ_TYPE_AREA, objnum, 1, ObjectProperties.FILTER_1_NAMED_UNAMED,
					ObjectProperties.FILTER_2_NONE, ObjectProperties.FILTER_3_NONE))
							.getMessageType() == Message.MESG_TYPE_OBJ_PROP) {
				logger.info(m.toString());
				objnum = ((ObjectProperties) m).getNumber();
			}
			objnum = 0;
			while ((m = c.reqObjectProperties(Message.OBJ_TYPE_UNIT, objnum, 1, ObjectProperties.FILTER_1_NAMED,
					ObjectProperties.FILTER_2_AREA_ALL, ObjectProperties.FILTER_3_ANY_LOAD))
							.getMessageType() == Message.MESG_TYPE_OBJ_PROP) {
				logger.info(m.toString());
				objnum = ((ObjectProperties) m).getNumber();
			}
			objnum = 0;
			while ((m = c.reqObjectProperties(Message.OBJ_TYPE_BUTTON, objnum, 1, ObjectProperties.FILTER_1_NAMED,
					ObjectProperties.FILTER_2_AREA_ALL, ObjectProperties.FILTER_3_NONE))
							.getMessageType() == Message.MESG_TYPE_OBJ_PROP) {
				logger.info(m.toString());
				objnum = ((ObjectProperties) m).getNumber();
			}
			objnum = 0;
			while ((m = c.reqObjectProperties(Message.OBJ_TYPE_CODE, objnum, 1, ObjectProperties.FILTER_1_NAMED,
					ObjectProperties.FILTER_2_AREA_ALL, ObjectProperties.FILTER_3_NONE))
							.getMessageType() == Message.MESG_TYPE_OBJ_PROP) {
				logger.info(m.toString());
				objnum = ((ObjectProperties) m).getNumber();
			}
			objnum = 0;
			while ((m = c.reqObjectProperties(Message.OBJ_TYPE_THERMO, objnum, 1, ObjectProperties.FILTER_1_NAMED,
					ObjectProperties.FILTER_2_AREA_ALL, ObjectProperties.FILTER_3_NONE))
							.getMessageType() == Message.MESG_TYPE_OBJ_PROP) {
				logger.info(m.toString());
				objnum = ((ObjectProperties) m).getNumber();
			}
			objnum = 0;
			while ((m = c.reqObjectProperties(Message.OBJ_TYPE_AUX_SENSOR, objnum, 1, ObjectProperties.FILTER_1_NAMED,
					ObjectProperties.FILTER_2_AREA_ALL, ObjectProperties.FILTER_3_NONE))
							.getMessageType() == Message.MESG_TYPE_OBJ_PROP) {
				logger.info(m.toString());
				objnum = ((ObjectProperties) m).getNumber();
			}
			objnum = 0;
			while ((m = c.reqObjectProperties(Message.OBJ_TYPE_CONTROL_READER, objnum, 1, ObjectProperties.FILTER_1_NAMED,
					ObjectProperties.FILTER_2_NONE, ObjectProperties.FILTER_3_NONE))
					.getMessageType() == Message.MESG_TYPE_OBJ_PROP) {
				logger.info(m.toString());
				objnum = ((ObjectProperties) m).getNumber();
			}

			ObjectStatus status = c.reqObjectStatus(Message.OBJ_TYPE_UNIT, 1, max_units);
			UnitStatus[] units = (UnitStatus[]) status.getStatuses();
			for (int i = 0; i < units.length; i++) {
				logger.info(units[i].toString());
			}
			status = c.reqObjectStatus(Message.OBJ_TYPE_ZONE, 1, max_zones);
			ZoneStatus[] zones = (ZoneStatus[]) status.getStatuses();
			for (int i = 0; i < zones.length; i++) {
				logger.info(zones[i].toString());
			}
			status = c.reqObjectStatus(Message.OBJ_TYPE_AREA, 1, max_areas);
			AreaStatus[] areas = (AreaStatus[]) status.getStatuses();
			for (int i = 0; i < areas.length; i++) {
				logger.info(areas[i].toString());
			}
			status = c.reqObjectStatus(Message.OBJ_TYPE_THERMO, 1, max_thermos);
			ThermostatStatus[] thermos = (ThermostatStatus[]) status.getStatuses();
			for (int i = 0; i < thermos.length; i++) {
				logger.info(thermos[i].toString());
			}
			status = c.reqObjectStatus(Message.OBJ_TYPE_MESG, 1, max_mesgs);
			MessageStatus[] megs = (MessageStatus[]) status.getStatuses();
			for (int i = 0; i < megs.length; i++) {
				logger.info(megs[i].toString());
			}
			status = c.reqObjectStatus(Message.OBJ_TYPE_AUX_SENSOR, 1, max_zones);
			AuxSensorStatus[] auxs = (AuxSensorStatus[]) status.getStatuses();
			for (int i = 0; i < auxs.length; i++) {
				logger.info(auxs[i].toString());
			}
			status = c.reqObjectStatus(Message.OBJ_TYPE_AUDIO_ZONE, 1, max_audio_zones);
			AudioZoneStatus[] audiozs = (AudioZoneStatus[]) status.getStatuses();
			for (int i = 0; i < audiozs.length; i++) {
				logger.info(audiozs[i].toString());
			}
			status = c.reqObjectStatus(Message.OBJ_TYPE_CONTROL_LOCK, 1, max_locks);
			AccessControlReaderLockStatus[] locks = (AccessControlReaderLockStatus[]) status.getStatuses();
			for (int i = 0; i < locks.length; i++) {
				logger.info(locks[i].toString());
			}

			for (int as = 1; as < max_audio_sources; as++) {
				int pos = 0;
				while ((m = c.reqAudioSourceStatus(as, pos))
						.getMessageType() == Message.MESG_TYPE_AUDIO_SOURCE_STATUS) {
					AudioSourceStatus a = (AudioSourceStatus) m;
					logger.info(a.toString());
					pos = a.getPosition();
				}
			}
			int num = 0;
			int count = 0;
			while ((m = c.uploadEventLogData(num, 1)).getMessageType() == Message.MESG_TYPE_EVENT_LOG_DATA
					&& count < 10) {
				EventLogData e = (EventLogData) m;
				logger.info(e.toString());
				num = e.getEventNumber();
				count++;
			}

			logger.info(c.uploadNames(Message.OBJ_TYPE_UNIT, 0).toString());
			logger.info(c.reqSecurityCodeValidation(1, 1, 2, 3, 4).toString());

			logger.info("All Done, OmniConnection thread now running");

		} catch (OmniInvalidResponseException e) {
			logger.error("Invalid Response", e);
			System.exit(-1);
		} catch (OmniNotConnectedException e) {
			logger.error("Error connecting", e);
			System.exit(-1);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}

	}

}
