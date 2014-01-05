package com.digitaldan.jomnilinkII.MessageTypes;

import com.digitaldan.jomnilinkII.Message;

public class ClearNames implements Message {

/*
 * CLEAR NAMES 
 
If a group of names will be downloaded to the HAI controller, first send the CLEAR NAMES message to the 
controller.  This instructs the controller to clear the names of all objects.  This is essential to ensure that object 
names that have been removed, are cleared from the controller�s memory. 
 
 Start character  0x21 
 Message length  0x01 
 Message type  0x0B 
 Data   none 
 CRC 1   0x40 
 CRC 2   0x57 
 

 */
	
	public int getMessageType() {
		return MESG_TYPE_CLEAR_NAMES;
	}

}
