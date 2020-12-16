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
 * ACCESS CONTROL READER STATUS (Requires Firmware Version 3.0 or Later)
 *
 * The controller reports the status of an access control reader object or a
 * group of access control reader objects. The status reported for each access
 * control reader includes the reader number, whether access was granted or
 * denied, and the last user to access the reader.
 *
 *     Start character      0x21
 *     Message length       (4 * number of readers) + 2
 *     Message Type         0x23
 *     Data 1               0x0E
 *     Data 2               reader number for first reader (MSB)
 *     Data 3               reader number for first reader (LSB)
 *     Data 4               access granted/denied for first reader (0=granted, 1=denied)
 *     Data 5               last user for first reader (MSB)
 *     Data 6               reader number for second reader (MSB)
 *     Data 7               reader number for second reader (LSB)
 *     Data 8               access granted/denied for second reader (0=granted, 1=denied)
 *     Data 9               last user for second reader
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
@NonFinal
public class AccessControlReaderStatus extends Status {
	private final boolean granted;
	private final int lastUser;

	@Builder
	private AccessControlReaderStatus(int number, boolean granted, int lastUser) {
		super(number);
		this.granted = granted;
		this.lastUser = lastUser;
	}
}
