/**
 * Copyright (c) 2009-2021 Dan Cunningham
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package com.digitaldan.jomnilinkII.examples;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.digitaldan.jomnilinkII.Connection;
import com.digitaldan.jomnilinkII.DisconnectListener;
import com.digitaldan.jomnilinkII.Message;
import com.digitaldan.jomnilinkII.MessageTypes.AudioSourceStatus;
import com.digitaldan.jomnilinkII.MessageTypes.EventLogData;
import com.digitaldan.jomnilinkII.MessageTypes.ObjectProperties;
import com.digitaldan.jomnilinkII.MessageTypes.ObjectStatus;
import com.digitaldan.jomnilinkII.MessageTypes.statuses.*;
import com.digitaldan.jomnilinkII.MessageTypes.systemevents.ButtonEvent;
import com.digitaldan.jomnilinkII.MessageTypes.systemevents.SystemEvent;
import com.digitaldan.jomnilinkII.MessageTypes.systemevents.UPBLinkEvent;
import com.digitaldan.jomnilinkII.NotificationListener;
import com.digitaldan.jomnilinkII.OmniInvalidResponseException;
import com.digitaldan.jomnilinkII.OmniNotConnectedException;

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
				logger.info("Usage:com.digitaldan.jomnilinkII.Main host port encKey");
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
						case Message.OBJ_TYPE_AREA :
							logger.info("STATUS_AREA changed");
							break;
						case Message.OBJ_TYPE_AUDIO_ZONE :
							logger.info("STATUS_AUDIO_ZONE changed");
							break;
						case Message.OBJ_TYPE_AUX_SENSOR :
							logger.info("STATUS_AUX changed");
							break;
						case Message.OBJ_TYPE_EXP :
							logger.info("STATUS_EXP changed");
							break;
						case Message.OBJ_TYPE_MESG :
							logger.info("STATUS_MESG changed");
							break;
						case Message.OBJ_TYPE_THERMO :
							logger.info("STATUS_THERMO changed");
							break;
						case Message.OBJ_TYPE_UNIT :
							logger.info("STATUS_UNIT changed");
							break;
						case Message.OBJ_TYPE_ZONE :
							logger.info("STATUS_ZONE changed");
							break;
						default :
							logger.info("Unknown type " + s.getStatusType());
							break;
					}
					logger.info(s.toString());
				}

				@Override
				public void systemEventNotification(SystemEvent event) {
					logger.info("Got SystemEvent type {}", event.getType());
					switch (event.getType()) {
						case UPB_LINK :
							UPBLinkEvent linkEvent = (UPBLinkEvent) event;
							logger.info("UPB Link command({}) for link({})", linkEvent.getLinkCommand(),
									linkEvent.getLinkNumber());
							break;
						case BUTTON :
							logger.info("ButtonEvent number {}", ((ButtonEvent) event).getButtonNumber());
							break;
						case PHONE_LINE_OFF_HOOK :
							logger.info("PHONE_LINE_OFF_HOOK event");
							break;
						default :
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
			// c.debug = true;
			c.enableNotifications();
			logger.info(c.reqSystemInformation().toString());
			logger.info(c.reqSystemStatus().toString());
			logger.info(c.reqSystemTroubles().toString());
			logger.info(c.reqSystemFormats().toString());
			logger.info(c.reqSystemFeatures().toString());
			logger.info(c.reqObjectTypeCapacities(Message.OBJ_TYPE_ZONE).toString());
			logger.info(c.reqObjectTypeCapacities(Message.OBJ_TYPE_UNIT).toString());
			logger.info(c.reqObjectTypeCapacities(Message.OBJ_TYPE_BUTTON).toString());
			logger.info(c.reqObjectTypeCapacities(Message.OBJ_TYPE_CODE).toString());
			logger.info(c.reqObjectTypeCapacities(Message.OBJ_TYPE_AREA).toString());
			logger.info(c.reqObjectTypeCapacities(Message.OBJ_TYPE_THERMO).toString());
			logger.info(c.reqObjectTypeCapacities(Message.OBJ_TYPE_MESG).toString());
			// Aux sensors returns error, They are considered zones by HAI
			// logger.info(c.reqObjectTypeCapacities(Message.OBJ_TYPE_AUX_SENSOR).toString());
			logger.info(c.reqObjectTypeCapacities(Message.OBJ_TYPE_AUDIO_SOURCE).toString());
			logger.info(c.reqObjectTypeCapacities(Message.OBJ_TYPE_AUDIO_ZONE).toString());
			logger.info(c.reqObjectTypeCapacities(Message.OBJ_TYPE_EXP).toString());
			logger.info(c.reqObjectTypeCapacities(Message.OBJ_TYPE_CONSOLE).toString());
			logger.info(c.reqObjectTypeCapacities(Message.OBJ_TYPE_USER_SETTING).toString());
			logger.info(c.reqObjectTypeCapacities(Message.OBJ_TYPE_CONTROL_READER).toString());

			int zoneNum = 0;
			while ((m = c.reqObjectProperties(Message.OBJ_TYPE_ZONE, zoneNum, 1, ObjectProperties.FILTER_1_NAMED,
					ObjectProperties.FILTER_2_AREA_ALL, ObjectProperties.FILTER_3_ANY_LOAD))
							.getMessageType() == Message.MESG_TYPE_OBJ_PROP) {
				logger.info(m.toString());
				zoneNum = ((ObjectProperties) m).getNumber();
			}
			int unitNum = 0;
			while ((m = c.reqObjectProperties(Message.OBJ_TYPE_UNIT, unitNum, 1, ObjectProperties.FILTER_1_NAMED,
					ObjectProperties.FILTER_2_AREA_ALL, ObjectProperties.FILTER_3_ANY_LOAD))
							.getMessageType() == Message.MESG_TYPE_OBJ_PROP) {
				logger.info(m.toString());
				unitNum = ((ObjectProperties) m).getNumber();
			}
			int buttonNum = 0;
			while ((m = c.reqObjectProperties(Message.OBJ_TYPE_BUTTON, buttonNum, 1, ObjectProperties.FILTER_1_NAMED,
					ObjectProperties.FILTER_2_AREA_ALL, ObjectProperties.FILTER_3_NONE))
							.getMessageType() == Message.MESG_TYPE_OBJ_PROP) {
				logger.info(m.toString());
				buttonNum = ((ObjectProperties) m).getNumber();
			}
			int codeNum = 0;
			while ((m = c.reqObjectProperties(Message.OBJ_TYPE_CODE, codeNum, 1, ObjectProperties.FILTER_1_NAMED,
					ObjectProperties.FILTER_2_AREA_ALL, ObjectProperties.FILTER_3_NONE))
							.getMessageType() == Message.MESG_TYPE_OBJ_PROP) {
				logger.info(m.toString());
				codeNum = ((ObjectProperties) m).getNumber();
			}
			int areaNum = 0;
			while ((m = c.reqObjectProperties(Message.OBJ_TYPE_AREA, areaNum, 1, ObjectProperties.FILTER_1_NAMED_UNAMED,
					ObjectProperties.FILTER_2_NONE, ObjectProperties.FILTER_3_NONE))
							.getMessageType() == Message.MESG_TYPE_OBJ_PROP) {
				logger.info(m.toString());
				areaNum = ((ObjectProperties) m).getNumber();
			}
			int thermoNum = 0;
			while ((m = c.reqObjectProperties(Message.OBJ_TYPE_THERMO, thermoNum, 1, ObjectProperties.FILTER_1_NAMED,
					ObjectProperties.FILTER_2_AREA_ALL, ObjectProperties.FILTER_3_NONE))
							.getMessageType() == Message.MESG_TYPE_OBJ_PROP) {
				logger.info(m.toString());
				thermoNum = ((ObjectProperties) m).getNumber();
			}
			int messageNum = 0;
			while ((m = c.reqObjectProperties(Message.OBJ_TYPE_MESG, messageNum, 1, ObjectProperties.FILTER_1_NAMED,
					ObjectProperties.FILTER_2_AREA_ALL, ObjectProperties.FILTER_3_NONE))
							.getMessageType() == Message.MESG_TYPE_OBJ_PROP) {
				logger.info(m.toString());
				messageNum = ((ObjectProperties) m).getNumber();
			}
			int auxNum = 0;
			while ((m = c.reqObjectProperties(Message.OBJ_TYPE_AUX_SENSOR, auxNum, 1, ObjectProperties.FILTER_1_NAMED,
					ObjectProperties.FILTER_2_AREA_ALL, ObjectProperties.FILTER_3_NONE))
							.getMessageType() == Message.MESG_TYPE_OBJ_PROP) {
				logger.info(m.toString());
				auxNum = ((ObjectProperties) m).getNumber();
			}
			int audioSourceNum = 0;
			while ((m = c.reqObjectProperties(Message.OBJ_TYPE_AUDIO_SOURCE, audioSourceNum, 1,
					ObjectProperties.FILTER_1_NAMED, ObjectProperties.FILTER_2_NONE, ObjectProperties.FILTER_3_NONE))
							.getMessageType() == Message.MESG_TYPE_OBJ_PROP) {
				logger.info(m.toString());
				audioSourceNum = ((ObjectProperties) m).getNumber();
			}
			int audioZoneNum = 0;
			while ((m = c.reqObjectProperties(Message.OBJ_TYPE_AUDIO_ZONE, audioZoneNum, 1,
					ObjectProperties.FILTER_1_NAMED, ObjectProperties.FILTER_2_NONE, ObjectProperties.FILTER_3_NONE))
							.getMessageType() == Message.MESG_TYPE_OBJ_PROP) {
				logger.info(m.toString());
				audioZoneNum = ((ObjectProperties) m).getNumber();
			}
			/* @formatter:off
			 * int expNum = 8;
			 * // Expansion enclosures return error for properties request
			 * while ((m = c.reqObjectProperties(Message.OBJ_TYPE_EXP, expNum, 1, ObjectProperties.FILTER_1_NONE,
			 * 		ObjectProperties.FILTER_2_NONE, ObjectProperties.FILTER_3_NONE))
			 * 				.getMessageType() == Message.MESG_TYPE_OBJ_PROP) {
			 * 	logger.info(m.toString());
			 * 	expNum = ((ObjectProperties) m).getNumber();
			 * }
			 * int consoleNum = 16;
			 * // Consoles return error for properties request
			 * while ((m = c.reqObjectProperties(Message.OBJ_TYPE_CONSOLE, consoleNum, 1, ObjectProperties.FILTER_1_NONE,
			 * 		ObjectProperties.FILTER_2_AREA_ALL, ObjectProperties.FILTER_3_NONE))
			 * 				.getMessageType() == Message.MESG_TYPE_OBJ_PROP) {
			 * 	logger.info(m.toString());
			 * 	consoleNum = ((ObjectProperties) m).getNumber();
			 * }
			 * @formatter:on
			 */
			int userSettingNum = 0;
			while ((m = c.reqObjectProperties(Message.OBJ_TYPE_USER_SETTING, userSettingNum, 1,
					ObjectProperties.FILTER_1_NAMED, ObjectProperties.FILTER_2_NONE, ObjectProperties.FILTER_3_NONE))
							.getMessageType() == Message.MESG_TYPE_OBJ_PROP) {
				logger.info(m.toString());
				userSettingNum = ((ObjectProperties) m).getNumber();
			}
			int readerNum = 0;
			while ((m = c.reqObjectProperties(Message.OBJ_TYPE_CONTROL_READER, readerNum, 1,
					ObjectProperties.FILTER_1_NAMED, ObjectProperties.FILTER_2_NONE, ObjectProperties.FILTER_3_NONE))
							.getMessageType() == Message.MESG_TYPE_OBJ_PROP) {
				logger.info(m.toString());
				readerNum = ((ObjectProperties) m).getNumber();
			}

			if (zoneNum > 0) {
				ObjectStatus status = c.reqObjectStatus(Message.OBJ_TYPE_ZONE, 1, zoneNum);
				ZoneStatus[] zones = (ZoneStatus[]) status.getStatuses();
				for (int i = 0; i < zones.length; i++) {
					logger.info(zones[i].toString());
				}
				status = c.reqObjectStatus(Message.OBJ_TYPE_ZONE, 1, zoneNum, true);
				ExtendedZoneStatus[] zonesExt = (ExtendedZoneStatus[]) status.getStatuses();
				for (int i = 0; i < zonesExt.length; i++) {
					logger.info(zonesExt[i].toString());
				}
			}
			if (unitNum > 0) {
				ObjectStatus status = c.reqObjectStatus(Message.OBJ_TYPE_UNIT, 1, unitNum);
				UnitStatus[] units = (UnitStatus[]) status.getStatuses();
				for (int i = 0; i < units.length; i++) {
					logger.info(units[i].toString());
				}
				status = c.reqObjectStatus(Message.OBJ_TYPE_UNIT, 1, unitNum, true);
				ExtendedUnitStatus[] unitsExt = (ExtendedUnitStatus[]) status.getStatuses();
				for (int i = 0; i < unitsExt.length; i++) {
					logger.info(unitsExt[i].toString());
				}
			}
			if (areaNum > 0) {
				ObjectStatus status = c.reqObjectStatus(Message.OBJ_TYPE_AREA, 1, areaNum);
				AreaStatus[] areas = (AreaStatus[]) status.getStatuses();
				for (int i = 0; i < areas.length; i++) {
					logger.info(areas[i].toString());
				}
				status = c.reqObjectStatus(Message.OBJ_TYPE_AREA, 1, areaNum, true);
				ExtendedAreaStatus[] areasExt = (ExtendedAreaStatus[]) status.getStatuses();
				for (int i = 0; i < areasExt.length; i++) {
					logger.info(areasExt[i].toString());
				}
			}
			if (thermoNum > 0) {
				ObjectStatus status = c.reqObjectStatus(Message.OBJ_TYPE_THERMO, 1, thermoNum);
				ThermostatStatus[] thermos = (ThermostatStatus[]) status.getStatuses();
				for (int i = 0; i < thermos.length; i++) {
					logger.info(thermos[i].toString());
				}
				status = c.reqObjectStatus(Message.OBJ_TYPE_THERMO, 1, thermoNum, true);
				ExtendedThermostatStatus[] thermosExt = (ExtendedThermostatStatus[]) status.getStatuses();
				for (int i = 0; i < thermosExt.length; i++) {
					logger.info(thermosExt[i].toString());
				}
			}
			if (messageNum > 0) {
				ObjectStatus status = c.reqObjectStatus(Message.OBJ_TYPE_MESG, 1, messageNum);
				MessageStatus[] megs = (MessageStatus[]) status.getStatuses();
				for (int i = 0; i < megs.length; i++) {
					logger.info(megs[i].toString());
				}
				status = c.reqObjectStatus(Message.OBJ_TYPE_MESG, 1, messageNum, true);
				ExtendedMessageStatus[] megsExt = (ExtendedMessageStatus[]) status.getStatuses();
				for (int i = 0; i < megsExt.length; i++) {
					logger.info(megsExt[i].toString());
				}
			}
			if (auxNum > 0) {
				ObjectStatus status = c.reqObjectStatus(Message.OBJ_TYPE_AUX_SENSOR, 1, auxNum);
				AuxSensorStatus[] auxs = (AuxSensorStatus[]) status.getStatuses();
				for (int i = 0; i < auxs.length; i++) {
					logger.info(auxs[i].toString());
				}
				status = c.reqObjectStatus(Message.OBJ_TYPE_AUX_SENSOR, 1, auxNum, true);
				ExtendedAuxSensorStatus[] auxsExt = (ExtendedAuxSensorStatus[]) status.getStatuses();
				for (int i = 0; i < auxsExt.length; i++) {
					logger.info(auxsExt[i].toString());
				}
			}
			if (audioZoneNum > 0) {
				ObjectStatus status = c.reqObjectStatus(Message.OBJ_TYPE_AUDIO_ZONE, 1, audioZoneNum);
				AudioZoneStatus[] audiozs = (AudioZoneStatus[]) status.getStatuses();
				for (int i = 0; i < audiozs.length; i++) {
					logger.info(audiozs[i].toString());
				}
				status = c.reqObjectStatus(Message.OBJ_TYPE_AUDIO_ZONE, 1, audioZoneNum, true);
				ExtendedAudioZoneStatus[] audiozsExt = (ExtendedAudioZoneStatus[]) status.getStatuses();
				for (int i = 0; i < audiozsExt.length; i++) {
					logger.info(audiozsExt[i].toString());
				}
			}
			/* @formatter:off
			 * if (expNum > 0) {
			 * 	ObjectStatus status = c.reqObjectStatus(Message.OBJ_TYPE_EXP, 1, expNum);
			 * 	ExpansionStatus[] exps = (ExpansionStatus[]) status.getStatuses();
			 * 	for (int i = 0; i < exps.length; i++) {
			 * 		logger.info(exps[i].toString());
			 * 	}
			 * 	status = c.reqObjectStatus(Message.OBJ_TYPE_EXP, 1, expNum, true);
			 * 	ExtendedExpansionStatus[] expsExt = (ExtendedExpansionStatus[]) status.getStatuses();
			 * 	for (int i = 0; i < expsExt.length; i++) {
			 * 		logger.info(expsExt[i].toString());
			 * 	}
			 * }
			 * @formatter:on
			 */
			if (userSettingNum > 0) {
				ObjectStatus status = c.reqObjectStatus(Message.OBJ_TYPE_USER_SETTING, 1, userSettingNum);
				UserSettingStatus[] userSettings = (UserSettingStatus[]) status.getStatuses();
				for (int i = 0; i < userSettings.length; i++) {
					logger.info(userSettings[i].toString());
				}
				status = c.reqObjectStatus(Message.OBJ_TYPE_USER_SETTING, 1, userSettingNum, true);
				ExtendedUserSettingStatus[] userSettingsExt = (ExtendedUserSettingStatus[]) status.getStatuses();
				for (int i = 0; i < userSettingsExt.length; i++) {
					logger.info(userSettingsExt[i].toString());
				}
			}
			if (readerNum > 0) {
				ObjectStatus status = c.reqObjectStatus(Message.OBJ_TYPE_CONTROL_READER, 1, readerNum);
				AccessControlReaderStatus[] readers = (AccessControlReaderStatus[]) status.getStatuses();
				for (int i = 0; i < readers.length; i++) {
					logger.info(readers[i].toString());
				}
				status = c.reqObjectStatus(Message.OBJ_TYPE_CONTROL_READER, 1, readerNum, true);
				ExtendedAccessControlReaderStatus[] readersExt = (ExtendedAccessControlReaderStatus[]) status
						.getStatuses();
				for (int i = 0; i < readersExt.length; i++) {
					logger.info(readersExt[i].toString());
				}
				status = c.reqObjectStatus(Message.OBJ_TYPE_CONTROL_LOCK, 1, readerNum);
				AccessControlReaderLockStatus[] locks = (AccessControlReaderLockStatus[]) status.getStatuses();
				for (int i = 0; i < locks.length; i++) {
					logger.info(locks[i].toString());
				}
				status = c.reqObjectStatus(Message.OBJ_TYPE_CONTROL_LOCK, 1, readerNum, true);
				ExtendedAccessControlReaderLockStatus[] locksExt = (ExtendedAccessControlReaderLockStatus[]) status
						.getStatuses();
				for (int i = 0; i < locksExt.length; i++) {
					logger.info(locksExt[i].toString());
				}
			}

			for (int as = 1; as < audioSourceNum; as++) {
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
			while ((m = c.readEventRecord(num, 1)).getMessageType() == Message.MESG_TYPE_EVENT_LOG_DATA && count < 10) {
				EventLogData e = (EventLogData) m;
				logger.info(e.toString());
				num = e.getEventNumber();
				count++;
			}

			logger.info(c.readName(Message.OBJ_TYPE_UNIT, 0).toString());
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
