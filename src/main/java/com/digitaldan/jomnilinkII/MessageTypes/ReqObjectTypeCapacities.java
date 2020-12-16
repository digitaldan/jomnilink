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

/*
 * REQUEST OBJECT TYPE CAPACITIES
 *
 * This message requests the HAI controller to report the number of objects of
 * the specified type that the controller supports.
 *
 *     Start character      0x21
 *     Message length       0x02
 *     Message Type         0x1E
 *     Data 1               object type
 *     CRC 1                varies
 *     CRC 2                varies
 *
 *     Expected Reply       OBJECT TYPE CAPACITIES
 *
 * The available object types and filters are as follows:
 *
 * ---------------------------------------------------------------------
 * | Object Type |  Object Description   | Fiter 1 | Fiter 2 | Fiter 3 |
 * |-------------|-----------------------|---------|---------|---------|
 * | 1           | Zone                  | Name    | Area    |         |
 * |-------------|-----------------------|---------|---------|---------|
 * | 2           | Unit                  | Name    | Area    | Room    |
 * |-------------|-----------------------|---------|---------|---------|
 * | 3           | Button                | Name    | Area    |         |
 * |-------------|-----------------------|---------|---------|---------|
 * | 4           | Code                  | Name    | Area    |         |
 * |-------------|-----------------------|---------|---------|---------|
 * | 5           | Area                  | Name    |         |         |
 * |-------------|-----------------------|---------|---------|---------|
 * | 6           | Thermostat            | Name    | Area    |         |
 * |-------------|-----------------------|---------|---------|---------|
 * | 7           | Message               | Name    | Area    |         |
 * |-------------|-----------------------|---------|---------|---------|
 * | 8           | Auxiliary Sensor      | Name    | Area    |         |
 * |-------------|-----------------------|---------|---------|---------|
 * | 9           | Audio Source          | Name    |         |         |
 * |-------------|-----------------------|---------|---------|---------|
 * | 10          | Audio Zone            | Name    |         |         |
 * |-------------|-----------------------|---------|---------|---------|
 * | 11          | Expansion Enclosure   |         |         |         |
 * |-------------|-----------------------|---------|---------|---------|
 * | 12          | Console               |         | Area    |         |
 * |-------------|-----------------------|---------|---------|---------|
 * | 13          | User Setting          | Name    |         |         |
 * |-------------|-----------------------|---------|---------|---------|
 * | 14          | Access Control Reader | Name    |         |         |
 * |-------------|-----------------------|---------|---------|---------|
 * | 15          | Access Control Lock   |         |         |         |
 * ---------------------------------------------------------------------
 *
 * Access control reader locks are part of access control reader objects.
 * Although the status of access control readers and the lock status of the
 * readers are reported separately, one cannot request the capacity or property
 * of an access control reader lock object. The access control reader lock
 * object type is only used in status messages.
 */
@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReqObjectTypeCapacities implements Message {
	private final int objectType;

	@Override
	public int getMessageType() {
		return MESG_TYPE_REQ_OBJ_CAPACITY;
	}
}
