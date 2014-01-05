package com.digitaldan.jomnilinkII.MessageTypes;

import com.digitaldan.jomnilinkII.Message;

public class SecurityCodeValidation implements Message {

	private int codeNumber;
	private int authorityLevel;
	
	/*
	 * SECURITY CODE VALIDATION 
 
 Start character  0x21 
 Message length  0x03 
 Message type  0x27 
 Data 1   user code number (1-99, 251 for duress, 0 if invalid) 
 Data 2   authority level (0-3) 
 CRC 1   varies 
 CRC 2   varies 
 
 
The authority level is as follows: 
 
0   Invalid code 
1   Master 
2   Manager 
3   User 
 
	 */
	public SecurityCodeValidation(int codeNumber, int authorityLevel) {
		super();
		this.codeNumber = codeNumber;
		this.authorityLevel = authorityLevel;
	}


	public int getMessageType() {
		return MESG_TYPE_SEC_CODE_VALID;
	}


	public int getCodeNumber() {
		return codeNumber;
	}


	public int getAuthorityLevel() {
		return authorityLevel;
	}


	public String toString()
	{
	    final String TAB = "    ";
	    
	    String retValue = "";
	    
	    retValue = "SecurityCodeValidation ( "
	        + "codeNumber = " + this.codeNumber + TAB
	        + "authorityLevel = " + this.authorityLevel + TAB
	        + " )";
	
	    return retValue;
	}

}
