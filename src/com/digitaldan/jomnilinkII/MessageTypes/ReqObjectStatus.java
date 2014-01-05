package com.digitaldan.jomnilinkII.MessageTypes;

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

import com.digitaldan.jomnilinkII.Message;

public class ReqObjectStatus implements Message {

	private int objType;
	private int objStart;
	private int objEnd;
	
	/*
	 * REQUEST OBJECT STATUS
This message is used to request the status of a group of zone, unit, area, thermostat, message, auxiliary sensor, audio
zone, or expansion enclosure objects.
       Zones: The status reported for each zone includes the zone number, current condition of the zone (secure,
                not ready, or trouble), and the current analog loop reading for the zone.
       Units: The status reported for each unit includes the unit number, current condition of the unit, and any
                time remaining on a timed command.
       Areas: The status reported for each area includes the area number, current mode of the area, alarm status of
                the area, and time remaining on an entry or exit timer.
Thermostats: The status reported for each thermostat includes the thermostat number, whether the thermostat is
                communicating with the controller, the current temperature, the heat and cool setpoints, the system
                mode, the fan mode, and whether the thermostat has been placed in hold mode.
   Messages: The status reported for each message includes the message number, which messages are currently
                being displayed and what displayed messages have not been acknowledged.
   Auxiliary: The status reported for each auxiliary sensor includes the sensor number, the output status for each
                PESM, the current temperature or humidity reading, and the low and high setpoints.
 Audio Zone: The status reported for each audio zone includes the audio zone number, the on/off status of the
                zone, the selected source for the zone, the volume, and whether the zone is muted.
  Expansion: The status reported for each expansion enclosure includes the expansion enclosure number, whether
                the thermostat is communicating with the controller, and the battery reading.
The request is sent using two bytes for each object.
         Start character             0x21
         Message length              0x06
         Message type                0x22
         Data 1                      object type
         Data 2                      starting object (MSB)
         Date 3                      starting object (LSB)
         Data 4                      ending object (MSB)
         Data 5                      ending object (LSB)
         CRC 1                       varies
         CRC 2                       varies
         Expected reply:             OBJECT STATUS

	 */
	
	public ReqObjectStatus(int objectType, int startObject, int endObject){
		objType = objectType;
		objStart = startObject;
		objEnd = endObject;
	}
	
	public int objectType(){
		return objType;
	}
	public int objectStart(){
		return objStart;
	}
	public int objectEnd(){
		return objEnd;
	}
	
	public int getMessageType() {
		return MESG_TYPE_REQ_OBJ_STATUS;
	}

	public String toString() {
	    final String TAB = "    ";
	    String retValue = "";
	    
	    retValue = "ReqObjectStatus ( "
	        + "objType = " + this.objType + TAB
	        + "objStart = " + this.objStart + TAB
	        + "objEnd = " + this.objEnd + TAB
	        + " )";
	
	    return retValue;
	}

}
