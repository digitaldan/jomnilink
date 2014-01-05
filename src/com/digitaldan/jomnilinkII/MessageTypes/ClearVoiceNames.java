package com.digitaldan.jomnilinkII.MessageTypes;

import com.digitaldan.jomnilinkII.Message;

public class ClearVoiceNames implements Message {

/*
 * CLEAR VOICE NAMES 
 
If a group of voice names will be downloaded to the HAI controller, first send the CLEAR VOICE NAMES 
message to the controller.  This instructs the controller to clear the voice names of all objects.  This is essential to 
ensure that object voice names that have been removed, are cleared from the controller�s memory. 
 
 Start Character  0x21 
 Message Length  0x01 
 Message Type  0x0F 
Data   none 
 CRC 1   0x41 
 CRC 2   0x94 

 */
	
	public int getMessageType() {
		return MESG_TYPE_CLEAR_VOICES;
	}

}
