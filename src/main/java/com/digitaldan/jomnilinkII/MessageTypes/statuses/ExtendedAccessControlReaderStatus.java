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
import lombok.experimental.Delegate;

/*
 * EXTENDED ACCESS CONTROL READER STATUS (Requires Firmware Version 3.0 or Later)
 *
 * The controller reports the status of an access control reader object or a
 * group of access control reader objects. The status reported for each access
 * control reader includes the reader number, whether access was granted or
 * denied, and the last user to access the reader.
 *
 *     Start character      0x21
 *     Message length       (4 * number of readers) + 2
 *     Message Type         0x3B
 *     Data 1               0x0E (object type)
 *     Data 2               0x04 (object record length)
 *     Data 3               reader number for first reader (MSB)
 *     Data 4               reader number for first reader (LSB)
 *     Data 5               access granted/denied for first reader (0=granted, 1=denied)
 *     Data 6               last user for first reader
 *     Data 7               reader number for second reader (MSB)
 *     Data 8               reader number for second reader (LSB)
 *     Data 9               access granted/denied for second reader (0=granted, 1=denied)
 *     Data 10              last user for second reader
 *     ...
 *     Data n-3             reader number for last reader (MSB)
 *     Data n-2             reader number for last reader (LSB)
 *     Data n-1             access granted/denied for last reader (0=granted, 1=denied)
 *     Data n               last user for last reader
 *     CRC 1                varies
 *     CRC 2                varies
 */
@Value
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ExtendedAccessControlReaderStatus extends Status {
	@Delegate
	private final AccessControlReaderStatus accessControlReaderStatus;

	@Builder
	private ExtendedAccessControlReaderStatus(int number, boolean granted, int lastUser) {
		super(number);
		accessControlReaderStatus = AccessControlReaderStatus.builder().number(number).granted(granted)
				.lastUser(lastUser).build();
	}
}
