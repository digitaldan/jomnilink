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

import lombok.ToString;

/*
 * REQUEST CONNECTED SECURITY SYSTEM STATUS
 *
 * This message is used to report the secure/not ready status of security zones.
 * Any burglary or 24 hour zone that is not in the secure state will be reported
 * as not ready. Auxiliary and temperature zones are always reported as secure.
 *
 *     Start character      0x21
 *     Message length       0x01
 *     Message Type         0x2D
 *     Data                 none
 *     CRC 1                0xC1
 *     CRC 2                0x8D
 *
 *     Expected Reply       CONNECTED SECURITY SYSTEM STATUS
 */
@ToString
public class ReqConnectedSecurityStatus implements Message {
	private static ReqConnectedSecurityStatus INSTANCE = new ReqConnectedSecurityStatus();

	public static ReqConnectedSecurityStatus getInstance() {
		return INSTANCE;
	}

	@Override
	public int getMessageType() {
		return MESG_TYPE_REQ_CONN_SEC_STATUS;
	}
}
