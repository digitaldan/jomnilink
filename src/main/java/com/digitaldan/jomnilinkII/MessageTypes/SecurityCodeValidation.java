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
 * SECURITY CODE VALIDATION
 *
 *     Start character      0x21
 *     Message length       0x03
 *     Message Type         0x27
 *     Data 1               user code number (1-99, 251 for duress, 0 if invalid)
 *     Data 2               authority level (0-3)
 *     CRC 1                varies
 *     CRC 2                varies
 *
 * The authority level is as follows:
 *
 *     0                    Invalid code
 *     1                    Master
 *     2                    Manager
 *     3                    User
 */
@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SecurityCodeValidation implements Message {
	private final int codeNumber;
	private final int authorityLevel;

	@Override
	public int getMessageType() {
		return MESG_TYPE_SEC_CODE_VALID;
	}
}
