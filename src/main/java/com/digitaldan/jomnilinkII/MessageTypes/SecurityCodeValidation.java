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
public class SecurityCodeValidation implements Message {

	private final int codeNumber;
	private final int authorityLevel;

	/*
	 * SECURITY CODE VALIDATION
	 * 
	 * Start character 0x21 Message length 0x03 Message type 0x27 Data 1 user code
	 * number (1-99, 251 for duress, 0 if invalid) Data 2 authority level (0-3) CRC
	 * 1 varies CRC 2 varies
	 * 
	 * 
	 * The authority level is as follows:
	 * 
	 * 0 Invalid code 1 Master 2 Manager 3 User
	 * 
	 */

	@Override
	public int getMessageType() {
		return MESG_TYPE_SEC_CODE_VALID;
	}

}
