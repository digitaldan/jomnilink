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

public class ReqObjectProperties implements Message {

	private int objType;
	private int objNum;
	private int dir;
	private int f1;
	private int f2;
	private int f3;
	
	/*
	 * REQUEST OBJECT PROPERTIES
This message requests the HAI controller to report the properties of the specified object. The object type and index
number specifies what is being requested. The object type identifies whether the requested object is a zone, unit,
button, code, area, thermostat, message, auxiliary sensor, audio source, audio zone, expansion, or console. The
index number (0-511) identifies the specific object.
The index number is used in conjunction with the relative direction (offset) value to determine which object in the
list will be sent. If the offset is 0, the controller will return the properties of the specified object (index number). If
the offset is -1, the controller will return the properties of the object before the specified index number. If the offset
is 1, the controller will return the properties of the object after the specified index number.
Filters are used to narrow the return to an object with specific properties.
Filter 1: allows only named objects to be returned (0=Named or Unnamed, 1=Named, 2=Unnamed).
Filter 2: allows only an object that is in specific Areas to be returned. The area statuses for eight areas are packed
           into one message byte. The status for Area 1 is indicated by bit 7. Lower order bits indicate the statuses of
           Area 2 – Area 8, respectively. The bits corresponding to specified Areas are on.
Filter 3: allows only an object that is defined as a Load in a Room, Room, or Independent Load to be returned
           (0=Any Load, 1-31=Load in a Room, 254=Room, 255=Independent Load).
           Start character              0x21
           Message length               0x08
           Message type                 0x20
           Data 1                       object type
           Data 2                       index number (MSB)
           Data 3                       index number (LSB)
           Data 4                       relative direction (-1, 0, 1)
           Data 5                       filter 1
           Data 6                       filter 2
           Data 7                       filter 3
           CRC 1                        varies
           CRC 2                        varies
           Expected reply:              OBJECT PROPERTIES

The available object types and filters are as follows:
                                                  Filter 1 Filter 2 Filter 3
 Object Type      Object Description
       1          Zone                            Name     Area
       2          Unit                            Name     Area     Room
       3          Button                          Name     Area
       4          Code                            Name     Area
       5          Area                            Name
       6          Thermostat                      Name     Area
       7          Message                         Name     Area
       8          Auxiliary Sensor                Name     Area
       9          Audio Source                    Name
      10          Audio Zone                      Name
      11          Expansion Enclosure
      12          Console                                  Area

*/

	public ReqObjectProperties(int objectType, int objectNum, int direction,
			int filter1, int filter2, int filter3){
		objType = objectType;
		objNum = objectNum;
		dir = direction;
		f1 = filter1;
		f2 = filter2;
		f3 = filter3;
	}
	
	public int objectType(){
		return objType;
	}
	public int objectNum(){
		return objNum;
	}
	public int direction(){
		return dir;
	}
	public int filter1(){
		return f1;
	}
	public int filter2(){
		return f2;
	}
	public int filter3(){
		return f3;
	}
	public int getMessageType() {
		return MESG_TYPE_REQ_OBJ_PROP;
	}

	public String toString() {
	    final String TAB = "    ";
	    String retValue = "";
	    
	    retValue = "ReqObjectProperties ( "
	        + "objType = " + this.objType + TAB
	        + "objNum = " + this.objNum + TAB
	        + "dir = " + this.dir + TAB
	        + "f1 = " + this.f1 + TAB
	        + "f2 = " + this.f2 + TAB
	        + "f3 = " + this.f3 + TAB
	        + " )";
	
	    return retValue;
	}

}
