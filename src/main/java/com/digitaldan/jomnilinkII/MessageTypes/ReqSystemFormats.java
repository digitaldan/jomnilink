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
 * REQUEST SYSTEM FORMATS
 *
 * This message requests the HAI controller to report the configured temperature
 * format, time format, and date format.
 *
 *     Start character      0x21
 *     Message length       0x01
 *     Message Type         0x28
 *     Data                 none
 *     CRC 1                0x01
 *     CRC 2                0x8E
 *
 *     Expected Reply       SYSTEM FORMATS
 */
@ToString
public class ReqSystemFormats implements Message {
	private static ReqSystemFormats INSTANCE = new ReqSystemFormats();

	public static ReqSystemFormats getInstance() {
		return INSTANCE;
	}

	@Override
	public int getMessageType() {
		return MESG_TYPE_REQ_SYS_FORMATS;
	}
}
