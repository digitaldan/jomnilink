package com.digitaldan.jomnilinkII.MessageTypes;

import com.digitaldan.jomnilinkII.Message;

public class UploadNames implements Message {

	private int objectType;
	private int objectNumber;
	/*
	 * UPLOAD NAMES 
 
To upload names from the HAI controller, send an UPLOAD NAMES message with the object type and object 
number to the HAI controller.  The controller will then send a NAME DATA message.   
 
Each NAME DATA message contains the name of a single object.  If no name has been entered in the controller for 
the specified object, the controller will respond with an END OF DATA message rather than a NAME DATA 
message in reply to the UPLOAD NAMES message. 
 
 Start character  0x21 
 Message length  0x04 
 Message type  0x0D 
 Data 1   object type 
 Data 2   object number (MSB) 
 Data 3   object number (LSB) 
 CRC 1   varies 
 CRC 2   varies 

	 */
	public UploadNames(int objectType, int objectNumber) {
		super();
		this.objectType = objectType;
		this.objectNumber = objectNumber;
	}

	public int getMessageType() {
		return MESG_TYPE_UPLOAD_NAMES;
	}

	public int getObjectType() {
		return objectType;
	}

	public int getObjectNumber() {
		return objectNumber;
	}

}
