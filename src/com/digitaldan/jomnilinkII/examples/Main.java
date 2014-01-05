package com.digitaldan.jomnilinkII.examples;

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

import java.io.IOException;

import com.digitaldan.jomnilinkII.Connection;
import com.digitaldan.jomnilinkII.DisconnectListener;
import com.digitaldan.jomnilinkII.Message;
import com.digitaldan.jomnilinkII.MessageUtils;
import com.digitaldan.jomnilinkII.NotificationListener;
import com.digitaldan.jomnilinkII.OmniInvalidResponseException;
import com.digitaldan.jomnilinkII.OmniNotConnectedException;
import com.digitaldan.jomnilinkII.MessageTypes.AudioSourceStatus;
import com.digitaldan.jomnilinkII.MessageTypes.EventLogData;
import com.digitaldan.jomnilinkII.MessageTypes.ObjectProperties;
import com.digitaldan.jomnilinkII.MessageTypes.ObjectStatus;
import com.digitaldan.jomnilinkII.MessageTypes.OtherEventNotifications;
import com.digitaldan.jomnilinkII.MessageTypes.statuses.AreaStatus;
import com.digitaldan.jomnilinkII.MessageTypes.statuses.AudioZoneStatus;
import com.digitaldan.jomnilinkII.MessageTypes.statuses.AuxSensorStatus;
import com.digitaldan.jomnilinkII.MessageTypes.statuses.MessageStatus;
import com.digitaldan.jomnilinkII.MessageTypes.statuses.ThermostatStatus;
import com.digitaldan.jomnilinkII.MessageTypes.statuses.UnitStatus;
import com.digitaldan.jomnilinkII.MessageTypes.statuses.ZoneStatus;

/*
 * Notes for NIO
 * Luanch connection in a worker thread, connect will operate as a blocking operation, on success add the connection object to a NIO thread for reading/writing.
 * 
 * write web service, sign up with email, password and keys.  On account creation, connect to house, if successful add to database and have our loop pick it up for monitoring.
 * 
 * what to do with exceptions?  Pull connections out to a slow que for retry, email user on three bad attemps
 */


public class Main {

	public static void main(String[] args) {
		try{
			if(args.length != 3){
				System.out.println("Usage:com.digitaldan.jomnilinkII.Main  host port encKey");
				System.exit(-1);
			}
			String host  = args[0];
			int port = Integer.parseInt(args[1]);
			String key = args[2];
			Message m;
			Connection c = new Connection(host,port,key);
			c.debug = true;
			c.addNotificationListener(new NotificationListener(){
				public void objectStausNotification(ObjectStatus s) {
							switch (s.getStatusType()) {
							case Message.OBJ_TYPE_AREA:
								System.out.println("STATUS_AREA changed");
								break;
							case Message.OBJ_TYPE_AUDIO_ZONE:
								System.out.println("STATUS_AUDIO_ZONE changed");
								break;
							case Message.OBJ_TYPE_AUX_SENSOR:
								System.out.println("STATUS_AUX changed");
								break;
							case Message.OBJ_TYPE_EXP:
								System.out.println("STATUS_EXP changed");
								break;
							case Message.OBJ_TYPE_MESG:
								System.out.println("STATUS_MESG changed");
								break;
							case Message.OBJ_TYPE_THERMO:
								System.out.println("STATUS_THERMO changed");
								break;
							case Message.OBJ_TYPE_UNIT:
								System.out.println("STATUS_UNIT changed");
								break;
							case Message.OBJ_TYPE_ZONE:
								System.out.println("STATUS_ZONE changed");
								break;
							default:
								System.out.println("Unknown type " + s.getStatusType());
							break;
							}
						System.out.println(s.toString());
					}
				@Override
				public void otherEventNotification(OtherEventNotifications o) {
					System.out.println("Other Event");
					for(int k=0;k<o.getNotifications().length;k++){
						System.out.println("Event bits " + 
								MessageUtils.getBits(o.getNotifications()[k]));
					}
				}
			});
			
			c.addDisconnectListener(new DisconnectListener(){
				@Override
				public void notConnectedEvent(Exception e) {
					e.printStackTrace();
					System.exit(-1);
				}
			});
//			c.debug = true;
			c.enableNotifications();
			System.out.println(c.reqSystemInformation().toString());
			System.out.println(c.reqSystemStatus().toString());
			System.out.println(c.reqSystemTroubles().toString());
			System.out.println(c.reqSystemFormats().toString());
			System.out.println(c.reqSystemFeatures().toString());
			int max_zones = c.reqObjectTypeCapacities(Message.OBJ_TYPE_ZONE).getCapacity();
			int max_units = c.reqObjectTypeCapacities(Message.OBJ_TYPE_UNIT).getCapacity();
			int max_areas = c.reqObjectTypeCapacities(Message.OBJ_TYPE_AREA).getCapacity();
			int max_buttons = c.reqObjectTypeCapacities(Message.OBJ_TYPE_BUTTON).getCapacity();
			int max_codes = c.reqObjectTypeCapacities(Message.OBJ_TYPE_CODE).getCapacity();
			int max_thermos = c.reqObjectTypeCapacities(Message.OBJ_TYPE_THERMO).getCapacity();
			int max_mesgs = c.reqObjectTypeCapacities(Message.OBJ_TYPE_MESG).getCapacity();
			int max_audio_zones = c.reqObjectTypeCapacities(Message.OBJ_TYPE_AUDIO_ZONE).getCapacity();
			int max_audio_sources = c.reqObjectTypeCapacities(Message.OBJ_TYPE_AUDIO_SOURCE).getCapacity();
			
			//Aux sensors returns error, They are considered zones by HAI
			// System.out.println(c.reqObjectTypeCapacities(Message.OBJ_TYPE_AUX_SENSOR).toString());
			System.out.println(c.reqObjectTypeCapacities(Message.OBJ_TYPE_AUDIO_SOURCE).toString());
			System.out.println(c.reqObjectTypeCapacities(Message.OBJ_TYPE_AUDIO_ZONE).toString());

			int objnum = 0;
			while((m = c.reqObjectProperties(Message.OBJ_TYPE_ZONE, objnum, 1, 
					ObjectProperties.FILTER_1_NAMED, ObjectProperties.FILTER_2_AREA_ALL, ObjectProperties.FILTER_3_ANY_LOAD)).getMessageType() 
					== Message.MESG_TYPE_OBJ_PROP){
				System.out.println(m.toString());
				objnum = ((ObjectProperties)m).getNumber();
			}
			objnum = 0;
			while((m = c.reqObjectProperties(Message.OBJ_TYPE_AREA, objnum, 1, 
					ObjectProperties.FILTER_1_NAMED_UNAMED, ObjectProperties.FILTER_2_NONE, ObjectProperties.FILTER_3_NONE)).getMessageType() 
					== Message.MESG_TYPE_OBJ_PROP){
				System.out.println(m.toString());
				objnum = ((ObjectProperties)m).getNumber();
			}
			objnum = 0;
			while((m = c.reqObjectProperties(Message.OBJ_TYPE_UNIT, objnum, 1, 
					ObjectProperties.FILTER_1_NAMED, ObjectProperties.FILTER_2_AREA_ALL, ObjectProperties.FILTER_3_ANY_LOAD)).getMessageType() 
					== Message.MESG_TYPE_OBJ_PROP){
				System.out.println(m.toString());
				objnum = ((ObjectProperties)m).getNumber();
			}
			objnum = 0;
			while((m = c.reqObjectProperties(Message.OBJ_TYPE_BUTTON, objnum, 1, 
					ObjectProperties.FILTER_1_NAMED, ObjectProperties.FILTER_2_AREA_ALL, ObjectProperties.FILTER_3_NONE)).getMessageType() 
					== Message.MESG_TYPE_OBJ_PROP){
				System.out.println(m.toString());
				objnum = ((ObjectProperties)m).getNumber();
			}
			objnum = 0;
			while((m = c.reqObjectProperties(Message.OBJ_TYPE_CODE, objnum, 1, 
					ObjectProperties.FILTER_1_NAMED, ObjectProperties.FILTER_2_AREA_ALL, ObjectProperties.FILTER_3_NONE)).getMessageType() 
					== Message.MESG_TYPE_OBJ_PROP){
				System.out.println(m.toString());
				objnum = ((ObjectProperties)m).getNumber();
			}
			objnum = 0;
			while((m = c.reqObjectProperties(Message.OBJ_TYPE_THERMO, objnum, 1, 
					ObjectProperties.FILTER_1_NAMED, ObjectProperties.FILTER_2_AREA_ALL, ObjectProperties.FILTER_3_NONE)).getMessageType() 
					== Message.MESG_TYPE_OBJ_PROP){
				System.out.println(m.toString());
				objnum = ((ObjectProperties)m).getNumber();
			}
			objnum = 0;
			while((m = c.reqObjectProperties(Message.OBJ_TYPE_AUX_SENSOR, objnum, 1, 
					ObjectProperties.FILTER_1_NAMED, ObjectProperties.FILTER_2_AREA_ALL, ObjectProperties.FILTER_3_NONE)).getMessageType() 
					== Message.MESG_TYPE_OBJ_PROP){
				System.out.println(m.toString());
				objnum = ((ObjectProperties)m).getNumber();
			}

			ObjectStatus status = c.reqObjectStatus(Message.OBJ_TYPE_UNIT,1,max_units);
			UnitStatus [] units = (UnitStatus[])status.getStatuses();
			for(int i=0;i<units.length;i++){
				System.out.println(units[i].toString());
			}
			status = c.reqObjectStatus(Message.OBJ_TYPE_ZONE,1,max_zones);
			ZoneStatus [] zones = (ZoneStatus[])status.getStatuses();
			for(int i=0;i<zones.length;i++){
				System.out.println(zones[i].toString());
			}
			status = c.reqObjectStatus(Message.OBJ_TYPE_AREA,1,max_areas);
			AreaStatus [] areas = (AreaStatus[])status.getStatuses();
			for(int i=0;i<areas.length;i++){
				System.out.println(areas[i].toString());
			}
			status = c.reqObjectStatus(Message.OBJ_TYPE_THERMO,1,9);
			ThermostatStatus [] thermos = (ThermostatStatus[])status.getStatuses();
			for(int i=0;i<thermos.length;i++){
				System.out.println(thermos[i].toString());
			}
			 status = c.reqObjectStatus(Message.OBJ_TYPE_MESG,1,max_mesgs);
			 MessageStatus [] megs = (MessageStatus[])status.getStatuses();
			 for(int i=0;i<megs.length;i++){
				 System.out.println(megs[i].toString());
			 }
			status = c.reqObjectStatus(Message.OBJ_TYPE_AUX_SENSOR,1,max_zones);
			AuxSensorStatus [] auxs = (AuxSensorStatus[])status.getStatuses();
			for(int i=0;i<auxs.length;i++){
				System.out.println(auxs[i].toString());
			}
			status = c.reqObjectStatus(Message.OBJ_TYPE_AUDIO_ZONE,1,max_audio_zones);
			AudioZoneStatus [] audiozs = (AudioZoneStatus[])status.getStatuses();
			for(int i=0;i<audiozs.length;i++){
				System.out.println(audiozs[i].toString());
			}
			for(int as=1;as<max_audio_sources;as++){
				int pos = 0;
				while((m = c.reqAudioSourceStatus(as, pos)).getMessageType() == 
					Message.MESG_TYPE_AUDIO_SOURCE_STATUS ){
					AudioSourceStatus a = (AudioSourceStatus)m;
					System.out.println(a.toString());
					pos = a.getPosition();
				}
			}
			int num = 0;
			int count = 0;
			while((m = c.uploadEventLogData(num, 1)).getMessageType() ==
				Message.MESG_TYPE_EVENT_LOG_DATA && count < 10){
				EventLogData e = (EventLogData)m;
				System.out.println(e.toString());
				num = e.getEventNumber();
				count++;
			}

			System.out.println(c.uploadNames(Message.OBJ_TYPE_UNIT, 0).toString());
			System.out.println(c.reqSecurityCodeValidation(1, 1, 2, 3, 4).toString());
			
			System.out.println("All Done, OmniConnection thread now running");
			
		} catch (OmniInvalidResponseException e){
			e.printStackTrace();
			System.out.println("Message:" + e.getInvalidResponse().getMessageType());
			System.exit(-1);
		} catch (OmniNotConnectedException e){
			e.printStackTrace();
			System.out.println("Message:" + e.getNotConnectedReason());
			System.exit(-1);
		} catch (Exception e){
			e.printStackTrace();
			System.exit(-1);
		}

	}

}
