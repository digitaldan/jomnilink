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
 * REQUEST OBJECT PROPERTIES
 *
 * This message requests the HAI controller to report the properties of the
 * specified object. The object type and index number specifies what is being
 * requested. The object type identifies whether the requested object is a zone,
 * unit, button, code, area, thermostat, message, auxiliary sensor, audio
 * source, audio zone, expansion, or console. The index number (0-511)
 * identifies the specific object.
 *
 * The index number is used in conjunction with the relative direction (offset)
 * value to determine which object in the list will be sent. If the offset is 0,
 * the controller will return the properties of the specified object (index
 * number). If the offset is -1, the controller will return the properties of
 * the object before the specified index number. If the offset is 1, the
 * controller will return the properties of the object after the specified index
 * number.
 *
 * Filters are used to narrow the return to an object with specific properties.
 *
 * Filter 1: allows only named objects to be returned (0=Named or Unnamed,
 *           1=Named, 2=Unnamed).
 *
 * Filter 2: allows only an object that is in specific Areas to be returned. The
 *           area statuses for eight areas are packed into one message byte. The
 *           status for Area 1 is indicated by bit 7. Lower order bits indicate
 *           the statuses of Area 2 – Area 8, respectively. The bits
 *           corresponding to specified Areas are on.
 *
 * Filter 3: allows only an object that is defined as a Load in a Room, Room, or
 *           Independent Load to be returned (0=Any Load, 1- 31=Load in a Room,
 *           254=Room, 255=Independent Load).
 *
 * The request is sent as follows:
 *
 *     Start character      0x21
 *     Message length       0x08
 *     Message Type         0x20
 *     Data 1               object type
 *     Data 2               index number (MSB)
 *     Data 3               index number (LSB)
 *     Data 4               relative direction (-1, 0, 1)
 *     Data 5               filter 1
 *     Data 6               filter 2
 *     Data 7               filter 3
 *     CRC 1                varies
 *     CRC 2                varies
 *
 *     Expected Reply       OBJECT PROPERTIES
 */
@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReqObjectProperties implements Message {
	private final int objectType;
	private final int objectNumber;
	private final int direction;
	private final int filter1;
	private final int filter2;
	private final int filter3;

	@Override
	public int getMessageType() {
		return MESG_TYPE_REQ_OBJ_PROP;
	}
}
