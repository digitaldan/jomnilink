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

public class ZoneReadyStatus implements Message {

	private int [] zones;
	/*
	 * ZONE READY STATUS
This message is sent in response to a REQUEST ZONE READY STATUS message. The secure/not ready statuses
for eight zones are packed into one message byte. The status of the lower numbered zone is indicated by bit 7.
Lower order bits indicate the statuses of the higher numbered zones. The bit corresponding to a zone is set if that
zone is not ready.
         Start character            0x21
         Message length             number of data bytes + 1
         Message Type               0x39
         Data 1                     status of first 8 zones
         Data 2                     status of second 8 zones
         ...
         Data n                     status of last 8 zones
         CRC 1                      varies
         CRC 2                      varies

	 */
	public ZoneReadyStatus(int[] zones) {
		super();
		this.zones = zones;
	}

	public int[] getZones() {
		return zones;
	}
	
	public int getMessageType() {
		return MESG_TYPE_SYS_STATUS;
	}

	public String toString() {
	    final String TAB = "    ";
	    String retValue = "";
	    
	    retValue = "ZoneReadyStatus ( "
	        + "zones = " + this.zones + TAB
	        + " )";
	
	    return retValue;
	}

}
