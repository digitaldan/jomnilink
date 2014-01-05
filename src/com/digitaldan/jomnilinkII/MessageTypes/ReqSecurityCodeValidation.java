package com.digitaldan.jomnilinkII.MessageTypes;

import com.digitaldan.jomnilinkII.Message;

public class ReqSecurityCodeValidation implements Message {
	
	private int area;
	private int digit1;
	private int digit2;
	private int digit3;
	private int digit4;
/*
 *  
These messages instruct the controller to confirm that the specified four-digit security code is valid in the specified 
area.  The code is only valid if it matches a four-digit user code in the area, and that code is currently time-enabled.  
The controller will return the user code number and authority level for the code.  The controller will also check to 
see if the duress code was specified.  If so, it will return the duress code number (251) as the user code number and 
set the authority level to user. 
 
� Request security code validation 
� Security code validation 
 
 
REQUEST SECURITY CODE VALIDATION 
 
 Start character  0x21 
 Message length  0x06 
 Message type  0x26 
 Data 1   area number (1-8) 
 Data 2   first digit of code 
 Data 3   second digit of code 
 Data 4   third digit of code 
 Data 5   fourth digit of code 
 CRC 1   varies 
 CRC 2   varies 
 
Each of the digits of the security code must be sent as the numeric value of the digit, 0x00 through 0x09. 
	
 */
	
	public ReqSecurityCodeValidation(int area, int digit1, int digit2,
			int digit3, int digit4) {
		super();
		this.area = area;
		this.digit1 = digit1;
		this.digit2 = digit2;
		this.digit3 = digit3;
		this.digit4 = digit4;
	}


	public int getMessageType() {
		return MESG_TYPE_REQ_SEC_CODE_VALID;
	}


	public int getArea() {
		return area;
	}


	public int getDigit1() {
		return digit1;
	}


	public int getDigit2() {
		return digit2;
	}


	public int getDigit3() {
		return digit3;
	}


	public int getDigit4() {
		return digit4;
	}

}
