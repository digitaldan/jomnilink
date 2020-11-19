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

import java.util.List;

import com.digitaldan.jomnilinkII.Message;

import lombok.*;

/*
 * SYSTEM STATUS
 *
 * This message is sent by the HAI controller in reply to a REQUEST SYSTEM
 * TROUBLES message. The controller reports any system troubles. If multiple
 * troubles exist, each trouble is reported in a separate data byte.
 *
 *     Start character      0x21
 *     Message length       number of troubles + 1
 *     Message Type         0x1B
 *     Data 1               first trouble
 *     ...
 *     Data n               last trouble
 *     CRC 1                varies
 *     CRC 2                varies
 *
 * The system trouble conditions are shown below.
 *
 * ------------------------------
 * | Bit |      Condition       |
 * |-----|----------------------|
 * | 1   | Freeze               |
 * |-----|----------------------|
 * | 2   | Battery low          |
 * |-----|----------------------|
 * | 3   | AC power             |
 * |-----|----------------------|
 * | 4   | Phone line           |
 * |-----|----------------------|
 * | 5   | Digital communicator |
 * |-----|----------------------|
 * | 6   | Fuse                 |
 * |-----|----------------------|
 * | 7   | Freeze               |
 * |-----|----------------------|
 * | 8   | Battery low          |
 * ------------------------------
 */
@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SystemTroubles implements Message {
	@Singular
	private final List<Integer> troubles;

	@Override
	public int getMessageType() {
		return MESG_TYPE_SYS_TROUBLES;
	}
}
