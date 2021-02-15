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
import lombok.experimental.Delegate;

/*
 * EXTENDED ACCESS CONTROL READER LOCK STATUS (Requires Firmware Version 3.0 or Later)
 *
 * The controller reports the lock status of an access control reader object or
 * a group of access control reader objects. The lock status reported for each
 * reader includes whether the door is locked or unlocked and the time remaining
 * if the door was unlocked for a specific time.
 *
 *     Start character      0x21
 *     Message length       (5 * number of readers) + 3
 *     Message Type         0x3B
 *     Data 1               0x0F (object type)
 *     Data 2               0x05 (object record length)
 *     Data 3               reader number for first reader (MSB)
 *     Data 4               reader number for first reader (LSB)
 *     Data 5               lock status of first reader (0=locked, 1=unlocked)
 *     Data 6               unlock timer for first reader (MSB) (seconds remaining)
 *     Data 7               unlock timer for first reader (LSB)
 *     Data 8               reader number for second reader (MSB)
 *     Data 9               reader number for second reader (LSB)
 *     Data 10              lock status of second reader (0=locked, 1=unlocked)
 *     Data 11              unlock timer for second reader (MSB) (seconds remaining)
 *     Data 12              unlock timer for second reader (LSB)
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
public class ExtendedAccessControlReaderLockStatus extends Status {
	@Delegate
	private final AccessControlReaderLockStatus accessControlReaderLockStatus;

	@Builder
	private ExtendedAccessControlReaderLockStatus(int number, boolean locked, int timer) {
		super(number);
		accessControlReaderLockStatus = AccessControlReaderLockStatus.builder().number(number).locked(locked)
				.timer(timer).build();
	}
}
