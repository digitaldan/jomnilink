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

@Value
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AccessControlReaderStatus extends Status {

	private final boolean granted;
	private final int lastUser;

	/*
	 * ACCESS CONTROL READER STATUS (Requires Firmware Version 3.0 or Later) The
	 * controller reports the status of an access control reader object or a group
	 * of access control reader objects. The status reported for each access control
	 * reader includes the reader number, whether access was granted or denied, and
	 * the last user to access the reader. Start character Message length Message
	 * Type Data 1 Data 2 Data 3 Data 4 Data 5 Data 6 Data 7 Data 8 Data 9 ... Data
	 * n-3 Data n-2 Data n-1 Data n CRC 1 CRC 2
	 *
	 */
	@Builder
	private AccessControlReaderStatus(int number, boolean granted, int lastUser) {
		super(number);
		this.granted = granted;
		this.lastUser = lastUser;
	}

}
