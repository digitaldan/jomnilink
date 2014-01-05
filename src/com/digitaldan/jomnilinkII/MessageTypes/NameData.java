package com.digitaldan.jomnilinkII.MessageTypes;

import com.digitaldan.jomnilinkII.Message;

public class NameData implements Message {

	private int objectType;
	private int objetcNumber;
	private String name;
	
	/*
	 * NAME DATA 
 
 Start character  0x21 
 Message length  (maximum name length) + 5    
 Message type  0x0E 
 Data 1   object type 
 Data 2   object number (MSB) 
 Data 3   object number (LSB) 
 Data 4   first byte of name data 
 Data 5   second byte of name data 
� 
 Data n   last byte of name data 
 CRC 1   varies 
 CRC 2   varies 
 
The object name data specifies the name for the respective object.  Each name consists of one or more printable 
ASCII characters, followed by a terminating zero.  Zone and message names can be up to 15 characters long, 
exclusive of the terminating zero.  All other names may be up to 12 characters long.  Names are always transferred 
with a fixed number of data bytes for each name type.  Thus, a zone name will always be sent as 16 bytes, no matter 
how long the name really is.  The terminating zero indicates the actual end of the name.  Data bytes following the 
terminating zero may be filled with any value. 
 
The object type and object number specify what is being named.  The object type identifies whether the name is for 
a zone, unit, button, code, area, thermostat, message, auxiliary sensor, audio zone, or audio source.  The object 
number identifies the specific object to be named. 
 
  
Listed below are the object type, maximum name length, and maximum number of each object type: 
 
 
OBJECT  TYPE  LENGTH NUMBER  (Omni IIe) NUMBER  (OmniPro II) NUMBER  (Lumina) NUMBER  (Lumina Pro) 
Zone 1 15 48 176 48 176 
Unit 2 12 128 511 128 511 
Button 3 12 64 128 64 128 
Code 4 12 16 99 16 99 
Area 5 12 2 8 1 1 
Thermostat 6 12 4 64 4 64 
Message 7 15 64 128 64 128 
Auxiliary Sensor 8 15 48 176 48 176 
Audio Source 9 12 6 8 6 8 
Audio Zone 10 12 8 36 8 36 

	 */
	public NameData(int objectType, int objetcNumber, String name) {
		super();
		this.objectType = objectType;
		this.objetcNumber = objetcNumber;
		this.name = name;
	}

	public int getMessageType() {
		return MESG_TYPE_NAME_DATA;
	}

	public int getObjectType() {
		return objectType;
	}

	public int getObjetcNumber() {
		return objetcNumber;
	}

	public String getName() {
		return name;
	}

	public String toString() {
	    final String TAB = "    ";
	    String retValue = "";
	    
	    retValue = "NameData ( "
	        + "objectType = " + this.objectType + TAB
	        + "objetcNumber = " + this.objetcNumber + TAB
	        + "name = " + this.name + TAB
	        + " )";
	
	    return retValue;
	}

}
