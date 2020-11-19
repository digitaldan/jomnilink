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
 * ACKNOWLEDGEMENT MESSAGES
 *
 * Acknowledgement messages are sent to acknowledge the receipt of another
 * message. A NEGATIVE ACKNOWLEDGE message is sent in response to another
 * message to indicate that the message was received correctly, but was not
 * processed due to an error in the message format or to an inability to
 * successfully perform the requested action.
 *
 * NEGATIVE ACKNOWLEDGE
 *
 *     Start character      0x21
 *     Message length       0x01
 *     Message Type         0x02
 *     Data                 none
 *     CRC 1                0x80
 *     CRC 2                0x51
 */
@ToString
public class NegativeAcknowledge implements Message {
	private static NegativeAcknowledge INSTANCE = new NegativeAcknowledge();

	public static NegativeAcknowledge getInstance() {
		return INSTANCE;
	}

	@Override
	public int getMessageType() {
		return MESG_TYPE_NEG_ACK;
	}
}
