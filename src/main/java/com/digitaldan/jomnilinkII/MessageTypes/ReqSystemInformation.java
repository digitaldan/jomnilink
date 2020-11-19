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

@ToString
public class ReqSystemInformation implements Message {

	private static ReqSystemInformation INSTANCE = new ReqSystemInformation();

	private ReqSystemInformation() {
	}

	public static ReqSystemInformation getInstance() {
		return INSTANCE;
	}

	/*
	 * This message requests the HAI controller to report its model number, software
	 * version, and local phone number. Start character 0x21 Message length 0x01
	 * Message type 0x16 Data none CRC 1 0x80 CRC 2 0x5E Expected reply SYSTEM
	 * INFORMATION
	 *
	 */

	@Override
	public int getMessageType() {
		return MESG_TYPE_REQ_SYS_INFO;
	}

}
