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
public class ReqSystemTroubles implements Message {

	private static ReqSystemTroubles INSTANCE = new ReqSystemTroubles();

	private ReqSystemTroubles() {
	}

	public static ReqSystemTroubles getInstance() {
		return INSTANCE;
	}

	/*
	 * (This message requests the HAI controller to report any system troubles. The
	 * response will be variable. Start character 0x21 Message length 0x01 Message
	 * type 0x1A Data none CRC 1 0x80 CRC 2 0x5B Expected reply SYSTEM TROUBLES
	 *
	 */

	@Override
	public int getMessageType() {
		return MESG_TYPE_REQ_SYS_TROUBLES;
	}

}
