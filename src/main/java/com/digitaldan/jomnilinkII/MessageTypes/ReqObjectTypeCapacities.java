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
public class ReqObjectTypeCapacities implements Message {

	private final int objectType;

	/*
	 * This message requests the HAI controller to report the number of objects of
	 * the specified type that the controller supports. Start character 0x21 Message
	 * length 0x02 Message type 0x1E Data 1 object type CRC 1 varies CRC 2 varies
	 * Expected reply: OBJECT TYPE CAPACITIES
	 * 
	 */

	@Override
	public int getMessageType() {
		return MESG_TYPE_REQ_OBJ_CAPACITY;
	}

}
