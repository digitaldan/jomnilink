package com.digitaldan.jomnilinkII.MessageTypes;

import com.digitaldan.jomnilinkII.Message;

public class ConnectedSecurityCommand implements Message {

	private int command;
	private int partition;
	private int digit1;
	private int digit2;
	private int digit3;
	private int digit4;
	private int digit5;
	private int digit6;
	
	/*
	 * CONNECTED SECURITY SYSTEM COMMAND 
 
This message is used to send commands to a connected security system. 
 
 Start character  0x21 
 Message length  0x09 
 Message type  0x2F 
 Data 1   command 
 Data 2   partition number (1-8) 
 Data 3   digit 1 (0-9) 
 Data 4   digit 2 (0-9) 
 Data 5   digit 3 (0-9) 
 Data 6   digit 4 (0-9) 
 Data 7   digit 5 (0-9) 
 Data 8   digit 6 (0-9) 
 CRC 1    varies 
 CRC 2    varies 
 
	 */
	
	public ConnectedSecurityCommand(int command, int partition, int digit1,
			int digit2, int digit3, int digit4, int digit5, int digit6) {
		super();
		this.command = command;
		this.partition = partition;
		this.digit1 = digit1;
		this.digit2 = digit2;
		this.digit3 = digit3;
		this.digit4 = digit4;
		this.digit5 = digit5;
		this.digit6 = digit6;
	}

	public int getMessageType() {
		return MESG_TYPE_CONN_SEC_COMMAND;
	}

	public int getCommand() {
		return command;
	}

	public int getPartition() {
		return partition;
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

	public int getDigit5() {
		return digit5;
	}

	public int getDigit6() {
		return digit6;
	}

}
