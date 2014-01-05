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

public class ReqConnectedSecurityStatus implements Message {
	/*
	 *This message is used to report the mode and status of each partition in a security system connected to a Lumina or
Lumina Pro controller.
        Start character            0x21
        Message length             0x01
        Message type               0x2D
        Data                       none
        CRC 1                      0xC1
        CRC 2                      0x8D
        Expected Reply:            CONNECTED SECURITY SYSTEM STATUS

	 */
	
	public int getMessageType() {
		return MESG_TYPE_REQ_CONN_SEC_STATUS;
	}

	public String toString() {
	    final String TAB = "    ";
	    String retValue = "";
	    
	    retValue = "ReqConnectedSecurityStatus ( "
	
	        + " )";
	
	    return retValue;
	}


}
