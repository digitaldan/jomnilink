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

public class ObjectProperties implements Message{
	//dc -e "16o 2i 00000001 p"
	public static int 	FILTER_1_NONE		 		= 0;
	public static int 	FILTER_1_NAMED_UNAMED 		= 0;
	public static int 	FILTER_1_NAMED				= 1;
	public static int	FILTER_1_UNAMED				= 2;

	public static int	FILTER_2_NONE				= 0;
	public static int[]	FILTER_2_AREA_BITMAP		= {0x01,0x02,0x04,0x08,0x10,0x20,0x40,0x80}; //00000001
	public static int	FILTER_2_AREA_1				= 0x01; //00000001
	public static int	FILTER_2_AREA_2				= 0x02; //00000010
	public static int	FILTER_2_AREA_3				= 0x04; //00000100
	public static int	FILTER_2_AREA_4				= 0x08; //00001000
	public static int	FILTER_2_AREA_5				= 0x10; //00010000
	public static int	FILTER_2_AREA_6				= 0x20; //00100000
	public static int	FILTER_2_AREA_7				= 0x40; //01000000
	public static int	FILTER_2_AREA_8				= 0x80; //10000000
	public static int	FILTER_2_AREA_ALL			= 0xFF; //11111111

	public static int	FILTER_3_NONE				= 0;
	public static int	FILTER_3_ANY_LOAD			= 0;
	public static int	FILTER_3_ROOM_LOAD_START	= 1;
	public static int	FILTER_3_ROOM_LOAD_END		= 31; //load in room filter is 1 though 31
	public static int	FILTER_3_ROOM				= 254;
	public static int	FILTER_3_INDEPENDENT_LOAD	= 255;
	
	protected int objectType;
	protected int number;
	protected String name;
/*
 * OBJECT PROPERTIES 
 
This message is sent by the HAI controller in reply to a REQUEST OBJECT PROPERTIES message.  The HAI 
controller reports the properties of the specified object. 
 
 Start character  0x21 
 Message length  number of data bytes + 1 
 Message type  0x21 
 Data 1   object type 
 Data 2   object number (MSB) 
 Data 3   object number (LSB)  
 �.    
 Data n   last property  
 CRC 1   varies    
 CRC 2   varies   
 
The object type identifies whether the returned properties are for a zone, unit, button, code, area, thermostat, 
message, auxiliary sensor, audio zone, or audio source object.  The object number identifies the specific object 
(zone, unit, button, code, area, thermostat, message, auxiliary sensor, audio zone, or audio source object). 
 
The object name data specifies the name for the respective object.  Each name consists of one or more printable 
ASCII characters, followed by a terminating zero.  Zone and message names can be up to 15 characters long, 
exclusive of the terminating zero.  All other names may be up to 12 characters long.  Names are always transferred 
with a fixed number of data bytes for each name type, as shown in the table below.  The terminating zero indicates 
the actual end of the name.  Data bytes following the terminating zero may be filled with any value.  If the first 
character received is zero, the object is not named in the controller. 
 
Listed below are the available object types and maximum name length for each object type: 
 
Object Type Object Description Maximum Name Length 
1 Zone 15 
2 Unit 12 
3 Button 12 
4 Code 12 
5 Area 12 
6 Thermostat 12 
7 Message 15 
8 Auxiliary Sensor 15 
9 Audio Source 12 
10 Audio Zone 12 
 */

	public ObjectProperties(int objectType, int number, String name) {
		super();
		this.objectType = objectType;
		this.number = number;
		this.name = name;
	}
	public int getObjectType() {
		return objectType;
	}
	public int getNumber() {
		return number;
	}
	
	public String getName(){
		return name;
	}
	
	public int getMessageType() {
		return MESG_TYPE_OBJ_PROP;
	}
	public void setObjectType(int objectType) {
      this.objectType = objectType;
   }
   public void setNumber(int number) {
      this.number = number;
   }
   public void setName(String name) {
      this.name = name;
   }
   public String toString() {
	    final String TAB = "    ";
	    String retValue = "";
	    
	    retValue = "ObjectProperties ( "
	        + "objectType = " + this.objectType + TAB
	        + "number = " + this.number + TAB
	        + "name = " + this.name + TAB
	        + " )";
	
	    return retValue;
	}
}
