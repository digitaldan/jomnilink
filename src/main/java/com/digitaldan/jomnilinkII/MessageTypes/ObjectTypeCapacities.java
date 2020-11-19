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

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ObjectTypeCapacities implements Message {
	private final int objectType;
	private final int capacity;

	/*
	 * This message is sent by the HAI controller in reply to a REQUEST OBJECT TYPE
	 * CAPACITIES message. The HAI controller reports the number of objects of the
	 * specified type that the controller supports. Start Character 0x21 Message
	 * Length 0x04 Message Type 0x1F Data 1 capacity type Data 2 capacity (MSB) Data
	 * 3 capacity (LSB) CRC 1 varies CRC 2 varies
	 *
	 */

	@Override
	public int getMessageType() {
		return MESG_TYPE_OBJ_CAPACITY;
	}

}
