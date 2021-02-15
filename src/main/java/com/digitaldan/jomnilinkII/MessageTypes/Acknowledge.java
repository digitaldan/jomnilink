/**
 * Copyright (c) 2009-2021 Dan Cunningham
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
 * ACKNOWLEDGEMENT MESSAGES
 *
 * Acknowledgement messages are sent to acknowledge the receipt of another
 * message. An ACKNOWLEDGE message is sent in response to another message to
 * indicate that the message was received correctly and processed.
 *
 * ACKNOWLEDGE
 *
 *     Start character      0x21
 *     Message length       0x01
 *     Message Type         0x01
 *     Data                 none
 *     CRC 1                0xC0
 *     CRC 2                0x50
 */
@ToString
public class Acknowledge implements Message {
	private static final Acknowledge INSTANCE = new Acknowledge();

	public static Acknowledge getInstance() {
		return INSTANCE;
	}

	@Override
	public int getMessageType() {
		return MESG_TYPE_ACK;
	}
}
