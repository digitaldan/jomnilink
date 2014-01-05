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

public class SystemTroubles implements Message {

	private int [] troubles;
	
	/*
	 *This message is sent by the HAI controller in reply to a REQUEST SYSTEM TROUBLES message. The controller
reports any system troubles. If multiple troubles exist, each trouble is reported in a separate data byte.
         Start character            0x21
         Message length             number of troubles + 1
         Message type               0x1B
         Data 1                     first trouble
         ...
         Data n                     last trouble
         CRC 1                      varies
         CRC 2                      varies
The system trouble conditions are shown below.
 Trouble Byte            Condition
        1         Freeze
        2         Battery low
        3         AC power
        4         Phone line
        5         Digital communicator
        6         Fuse
        7         Freeze
        8         Battery low
 
	 */
	
	public SystemTroubles(int[] troubles) {
		super();
		this.troubles = troubles;
	}

	public int[] getTroubles() {
		return troubles;
	}
	
	public int getMessageType() {
		return MESG_TYPE_SYS_TROUBLES;
	}

	public String toString() {
	    final String TAB = "    ";
	    String retValue = "";
	    
	    retValue = "SystemTroubles ( "
	        + "troubles = " + this.troubles + TAB
	        + " )";
	
	    return retValue;
	}

}
