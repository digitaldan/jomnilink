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

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

/*
 * READ NAME
 *
 * To read names from the HAI controller, send a READ NAME message with the
 * desired name type set and the object number set to zero. The controller
 * will then send a NAME DATA message for the first named object of that type.
 * Then send subsequent READ NAMES messages for that name type with the object
 * number set to the object number returned in the previous NAME DATA message.
 * The controller will then respond with a NAME DATA message for the next
 * named object of that type. When there are no more named objects of that
 * type the controller will respond with an END OF DATA MESSAGE. Repeat the
 * process for the next desired name type until all desired names have been
 * read.
 *
 *     Start character      0x21
 *     Message length       0x05
 *     Message Type         0x0D
 *     Data 1               name type
 *     Data 2               object number (MSB)
 *     Data 3               object number (LSB)
 *     Data 4               0x01 (reserved)
 *     CRC 1                varies
 *     CRC 2                varies
 */
@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReadName implements Message {
	private final int objectType;
	private final int objectNumber;

	@Override
	public int getMessageType() {
		return MESG_TYPE_READ_NAME;
	}
}
