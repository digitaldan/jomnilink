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
 * WRITE NAME
 *
 * To write names to the HAI controller, send a WRITE NAME message with the name
 * type, object name, and the name data. Each WRITE NAME message contains the
 * name of a single object.
 *
 *     Start character      0x21
 *     Message length       (maximum name length) + 5
 *     Message Type         0x0C
 *     Data 1               name type
 *     Data 2               object number (MSB)
 *     Data 3               object number (LSB)
 *     Data 4               first byte of name data
 *     Data 5               second byte of name data
 *     ...
 *     Data n               last byte of name data
 *     CRC 1                varies
 *     CRC 2                varies
 */
@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class WriteName implements Message {
	private final int objectType;
	private final int objectNumber;
	private final String name;

	@Override
	public int getMessageType() {
		return MESG_TYPE_WRITE_NAME;
	}
}
