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
 * REQUEST SYSTEM FEATURES
 *
 * This message requests the HAI controller to report its enabled features.
 *
 *     Start character      0x21
 *     Message length       0x01
 *     Message Type         0x1C
 *     Data                 none
 *     CRC 1                0x00
 *     CRC 2                0x59
 *
 *     Expected Reply       SYSTEM FEATURES
 */
@ToString
public class ReqSystemFeatures implements Message {
	private static ReqSystemFeatures INSTANCE = new ReqSystemFeatures();

	public static ReqSystemFeatures getInstance() {
		return INSTANCE;
	}

	@Override
	public int getMessageType() {
		return MESG_TYPE_REQ_SYS_FEATURES;
	}
}
