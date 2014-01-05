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

public class UploadEventRecord implements Message {

	private int eventNumber;
	private int direction;
	
	/*
	 * UPLOAD EVENT RECORD 
 
The event number specifies the event record that is being requested (0-65535).  The event number is used in 
conjunction with the relative direction (offset) value to determine which event in the list will be sent.  A special case 
event number of 0 is used to receive the most recent or oldest event.  If the event number is 0 and the relative 
direction is -1, the controller will return the most recent event (along with the event number).  The returned event 
number along with the relative direction of -1 can be used to request the next most recent event.  If the event 
number is 0 and the relative direction is 1, the controller will return the oldest event. The returned event number 
along with the relative direction of 1 can be used to request the second oldest event. 
 
If the offset is 0, the controller will return the specified event record.  If the offset is -1, the controller will return the 
event record before the specified event number.  If the offset is 1, the controller will return the event record after the 
specified event number. 
 
 Start character  0x21 
 Message length  0x04 
 Message type  0x24 
 Data 1   event number (MSB) 
 Data 2   event number (LSB) 
 Data 3   relative direction (-1, 0, 1) 
 CRC 1   varies 
 CRC 2   varies 

*/

	public UploadEventRecord(int eventNumber, int direction){
		this.eventNumber = eventNumber;
		this.direction = direction;
	}
	
	public int getEventNumber(){
		return eventNumber;
	}
	
	public int getDirection(){
		return direction;
	}
	
	public int getMessageType() {
		return MESG_TYPE_UPLOAD_EVENT_LOG;
	}

	public String toString() {
	    final String TAB = "    ";
	    String retValue = "";
	    
	    retValue = "UploadEventRecord ( "
	        + "eventNumber = " + this.eventNumber + TAB
	        + "direction = " + this.direction + TAB
	        + " )";
	
	    return retValue;
	}


}
