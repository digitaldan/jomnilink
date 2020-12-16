/**
 * Copyright (c) 2009-2009-2020 Dan Cunningham
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
 * NAME DATA
 *
 *     Start character      0x21
 *     Message length       (maximum name length) + 5
 *     Message Type         0x0E
 *     Data 1               name type
 *     Data 2               object number (MSB)
 *     Data 3               object number (LSB)
 *     Data 4               first byte of name data
 *     Data 5               second byte of name data
 *     ...
 *     Data n               last byte of name data
 *     CRC 1                varies
 *     CRC 2                varies
 *
 * The object name data specifies the name for the respective object. Each name
 * consists of one or more printable ASCII characters, followed by a terminating
 * zero. Zone, message, user setting, and access control reader names can be up
 * to 15 characters long, exclusive of the terminating zero. All other names may
 * be up to 12 characters long. Names are always transferred with a fixed number
 * of data bytes for each name type. Thus, a zone name will always be sent as 16
 * bytes, no matter how long the name really is. The terminating zero indicates
 * the actual end of the name. Data bytes following the terminating zero may be
 * filled with any value.
 *
 * The name type and object number specify what is being named. The name type
 * identifies whether the name is for a zone, unit, button, code, area,
 * thermostat, message, user setting, or access control reader. The object
 * number identifies the specific object to be named.
 *
 * Listed below are the name type, maximum name length, and maximum number of
 * each name type:
 *
 * --------------------------------------------------------------------------------------
 * |              |      |        |   Number   |    Number    |  Number  |    Number    |
 * |    Object    | Type | Length | (Omni IIe) | (OmniPro II) | (Lumina) | (Lumina Pro) |
 * |--------------|------|--------|------------|--------------|----------|--------------|
 * | Zone         | 1    | 15     | 48         | 176          | 48       | 176          |
 * |--------------|------|--------|------------|--------------|----------|--------------|
 * | Unit         | 2    | 12     | 128        | 511          | 128      | 511          |
 * |--------------|------|--------|------------|--------------|----------|--------------|
 * | Button       | 3    | 12     | 64         | 128          | 64       | 128          |
 * |--------------|------|--------|------------|--------------|----------|--------------|
 * | Code         | 4    | 12     | 16         | 99           | 16       | 99           |
 * |--------------|------|--------|------------|--------------|----------|--------------|
 * | Area         | 5    | 12     | 2          | 8            | 1        | 1            |
 * |--------------|------|--------|------------|--------------|----------|--------------|
 * | Thermostat   | 6    | 12     | 4          | 64           | 4        | 64           |
 * |--------------|------|--------|------------|--------------|----------|--------------|
 * | Message      | 7    | 15     | 64         | 128          | 64       | 128          |
 * |--------------|------|--------|------------|--------------|----------|--------------|
 * | User Setting | 8    | 15     | 10         | 25           | 10       | 25           |
 * |--------------|------|--------|------------|--------------|----------|--------------|
 * | Reader       | 9    | 15     | 4          | 16           | 4        | 16           |
 * --------------------------------------------------------------------------------------
 */
@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class NameData implements Message {
	private final int objectType;
	private final int objectNumber;
	private final String name;

	@Override
	public int getMessageType() {
		return MESG_TYPE_NAME_DATA;
	}
}
