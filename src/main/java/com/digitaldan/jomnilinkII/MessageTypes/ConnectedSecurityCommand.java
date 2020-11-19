/**
* Copyright (c) 2009-2020 Dan Cunningham
*
* This program and the accompanying materials are made available under the
* terms of the Eclipse Public License 2.0 which is available at
* http://www.eclipse.org/legal/epl-2.0
*
* SPDX-License-Identifier: EPL-2.0
*/
package com.digitaldan.jomnilinkII.MessageTypes;

import com.digitaldan.jomnilinkII.Message;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ConnectedSecurityCommand implements Message {

	private final int command;
	private final int partition;
	private final int digit1;
	private final int digit2;
	private final int digit3;
	private final int digit4;
	private final int digit5;
	private final int digit6;

	/*
	 * CONNECTED SECURITY SYSTEM COMMAND
	 * 
	 * This message is used to send commands to a connected security system.
	 * 
	 * Start character 0x21 Message length 0x09 Message type 0x2F Data 1 command
	 * Data 2 partition number (1-8) Data 3 digit 1 (0-9) Data 4 digit 2 (0-9) Data
	 * 5 digit 3 (0-9) Data 6 digit 4 (0-9) Data 7 digit 5 (0-9) Data 8 digit 6
	 * (0-9) CRC 1 varies CRC 2 varies
	 * 
	 */

	@Override
	public int getMessageType() {
		return MESG_TYPE_CONN_SEC_COMMAND;
	}

}
