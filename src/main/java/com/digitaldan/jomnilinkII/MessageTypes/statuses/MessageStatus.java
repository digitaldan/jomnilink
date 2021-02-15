/**
 * Copyright (c) 2009-2021 Dan Cunningham
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package com.digitaldan.jomnilinkII.MessageTypes.statuses;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import lombok.experimental.NonFinal;

/*
 * MESSAGE STATUS
 *
 * The controller reports the status of a displayed text message object or a
 * group of displayed text message objects. The status reported for each message
 * includes the message number, whether the message is currently being
 * displayed, and whether the displayed message has not been acknowledged.
 *
 *     Start character      0x21
 *     Message length       (3 * number of messages) + 2
 *     Message Type         0x23
 *     Data 1               0x07
 *     Data 2               message number for first message (MSB)
 *     Data 3               message number for first message (LSB)
 *     Data 4               status byte for first message
 *     Data 5               message number for second message (MSB)
 *     Data 6               message number for second message (LSB)
 *     Data 7               status byte for second message
 *     ...
 *     Data n-2             message number for last message (MSB)
 *     Data n-1             message number for last message (LSB)
 *     Data n               status byte for last message
 *     CRC 1                varies
 *     CRC 2                varies
 *
 * The status bytes are defined as follows:
 *
 *     0                    Off (message in not being displayed)
 *     1                    Displayed (the message is currently being displayed)
 *     2                    Not acknowledged (the displayed message has not been acknowledged)
 */
@Value
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NonFinal
public class MessageStatus extends Status {
	private final int status;

	@Builder
	private MessageStatus(int number, int status) {
		super(number);
		this.status = status;
	}
}
