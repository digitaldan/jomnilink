package com.digitaldan.jomnilinkII.MessageTypes;

import com.digitaldan.jomnilinkII.Message;

public class ActivateKeypadEmergency implements Message {

	private int area;
	private int emergencyType;
	
	/*
	 * ACTIVATE KEYPAD EMERGENCY 
 
This message is used to activate a burglary, fire, or auxiliary keypad emergency alarm in an area on an Omni IIe or 
OmniPro II system. 
 
 Start character  0x21 
 Message length  0x03 
 Message type  0x2C 
 Data 1   area (1-8) 
Data 2    emergency type (1=burglary, 2=fire, 3=auxiliary)  
 CRC 1    varies 
 CRC 2    varies 
 
Expected reply   ACKNOWLEDGE 

	 */
	public ActivateKeypadEmergency(int area, int emergencyType) {
		super();
		this.area = area;
		this.emergencyType = emergencyType;
	}


	public int getMessageType() {
		return MESG_TYPE_ACT_KEYPAD_EMERGENCY;
	}


	public int getArea() {
		return area;
	}


	public int getEmergencyType() {
		return emergencyType;
	}

}
