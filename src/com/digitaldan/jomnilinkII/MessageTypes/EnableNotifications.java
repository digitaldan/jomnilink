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

public class EnableNotifications implements Message {

	
	/*
	 *ENABLE NOTIFICATIONS 
 
The ENABLE NOTIFICATIONS message requests the HAI controller to send event notifications as they occur.  If 
the ENABLE NOTIFICATIONS feature is disabled, the HAI controller will not send event data. 
 
 Start character  0x21 
 Message length  0x02 
 Message type  0x15 
 Data 1   enable byte (0=disable, 1=enable) 
 CRC 1   varies 
 CRC 2   varies 
 
Expected reply   ACKNOWLEDGE 

NOTE notifiations that come back have a SEQ of 00

	 */
	private boolean enabled;
	
	public EnableNotifications(){
		super();
		enabled = true;
	}
	
	public EnableNotifications(boolean enabled){
		super();
	}
	
	public int getMessageType() {
		return MESG_TYPE_ENABLE_NOTIFICATIONS;
	}

	public boolean isEnabled(){
		return enabled;
	}
	
	public String toString() {
	    final String TAB = "    ";
	    String retValue = "";
	    
	    retValue = "EnableNotifications ( "
	
	        + " )";
	
	    return retValue;
	}

}
