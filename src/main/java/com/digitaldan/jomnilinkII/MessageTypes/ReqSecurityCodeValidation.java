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

/*
 * VALIDATE SECURITY CODE MESSAGES
 *
 * These messages instruct the controller to confirm that the specified
 * four-digit security code is valid in the specified area. The code is only
 * valid if it matches a four-digit user code in the area, and that code is
 * currently time-enabled. The controller will return the user code number and
 * authority level for the code. The controller will also check to see if the
 * duress code was specified. If so, it will return the duress code number (251)
 * as the user code number and set the authority level to user.
 *
 *     • Request security code validation
 *     • Security code validation
 *
 * REQUEST SECURITY CODE VALIDATION
 *
 *     Start character      0x21
 *     Message length       0x06
 *     Message Type         0x26
 *     Data 1               area number (1-8)
 *     Data 2               first digit of code
 *     Data 3               second digit of code
 *     Data 4               third digit of code
 *     Data 5               fourth digit of code
 *     CRC 1                varies
 *     CRC 2                varies
 *
 * Each of the digits of the security code must be sent as the numeric value of
 * the digit, 0x00 through 0x09.
 */
@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReqSecurityCodeValidation implements Message {
	private final int area;
	private final int digit1;
	private final int digit2;
	private final int digit3;
	private final int digit4;

	@Override
	public int getMessageType() {
		return MESG_TYPE_REQ_SEC_CODE_VALID;
	}
}
