/**
 * Copyright (c) 2009-2020 Dan Cunningham
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
 * ACCESS CONTROL READER LOCK STATUS (Requires Firmware Version 3.0 or Later)
 *
 * The controller reports the lock status of an access control reader object or
 * a group of access control reader objects. The lock status reported for each
 * reader includes whether the door is locked or unlocked and the time remaining
 * if the door was unlocked for a specific time.
 *
 *     Start character      0x21
 *     Message length       (5 * number of readers) + 2
 *     Message Type         0x23
 *     Data 1               0x0F
 *     Data 2               reader number for first reader (MSB)
 *     Data 3               reader number for first reader (LSB)
 *     Data 4               lock status of first reader (0=locked, 1=unlocked)
 *     Data 5               unlock timer for first reader (MSB) (seconds remaining)
 *     Data 6               unlock timer for first reader (LSB)
 *     Data 7               reader number for second reader (MSB)
 *     Data 8               reader number for second reader (LSB)
 *     Data 9               lock status of second reader (0=locked, 1=unlocked)
 *     Data 10              unlock timer for second reader (MSB) (seconds remaining)
 *     Data 11              unlock timer for second reader (LSB)
 *     ...
 *     Data n-4             reader number for last reader (MSB)
 *     Data n-3             reader number for last reader (LSB)
 *     Data n-2             lock status of last reader (0=locked, 1=unlocked)
 *     Data n-1             unlock timer for last reader (MSB) (seconds remaining)
 *     Data n               unlock timer for last reader (LSB)
 *     CRC 1                varies
 *     CRC 2                varies
 */
@Value
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NonFinal
public class AccessControlReaderLockStatus extends Status {
	private final boolean locked;
	private final int timer;

	@Builder
	private AccessControlReaderLockStatus(int number, boolean locked, int timer) {
		super(number);
		this.locked = locked;
		this.timer = timer;
	}
}
