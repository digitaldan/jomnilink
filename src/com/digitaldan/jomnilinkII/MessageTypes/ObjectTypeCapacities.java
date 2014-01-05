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

public class ObjectTypeCapacities implements Message {
	private int objectType;
	private int capacity;
	
	
	/*
	 * This message is sent by the HAI controller in reply to a REQUEST OBJECT TYPE CAPACITIES message. The
HAI controller reports the number of objects of the specified type that the controller supports.
        Start Character            0x21
        Message Length             0x04
        Message Type               0x1F
        Data 1                     capacity type
        Data 2                     capacity (MSB)
        Data 3                     capacity (LSB)
        CRC 1                      varies
        CRC 2                      varies

	 */
	
	public ObjectTypeCapacities(int objectType, int capacity) {
		super();
		this.objectType = objectType;
		this.capacity = capacity;
	}

	public int getObjectType() {
		return objectType;
	}

	public int getCapacity() {
		return capacity;
	}
	

	public int getMessageType() {
		return MESG_TYPE_OBJ_CAPACITY;
	}

	public String toString() {
	    final String TAB = "    ";
	    String retValue = "";
	    
	    retValue = "ObjectTypeCapacities ( "
	        + "objectType = " + this.objectType + TAB
	        + "capacity = " + this.capacity + TAB
	        + " )";
	
	    return retValue;
	}

}
