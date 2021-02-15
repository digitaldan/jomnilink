/**
 * Copyright (c) 2009-2021 Dan Cunningham
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package com.digitaldan.jomnilinkII.MessageTypes.properties;

import com.digitaldan.jomnilinkII.MessageTypes.ObjectProperties;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

/*
 * ACCESS CONTROL READER PROPERTIES (Requires Firmware Version 3.0 or Later)
 *
 *     Data 4               lock status (0=locked, 1=unlocked)
 *     Data 5               unlock timer (MSB) (seconds remaining)
 *     Data 6               unlock timer (LSB)
 *     Data 7               access granted/denied (0=granted, 1=denied)
 *     Data 8               last user to access reader
 *     Data 9-24            access control reader name
 */
@Value
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AccessControlReaderProperties extends ObjectProperties {
	private final boolean lockStatus;
	private final int unlockTimer;
	private final boolean accessDenied;
	private final int lastUser;

	@Builder
	private AccessControlReaderProperties(int number, boolean lockStatus, int unlockTimer, boolean accessDenied,
			int lastUser, String name) {
		super(OBJ_TYPE_AREA, number, name);
		this.lockStatus = lockStatus;
		this.unlockTimer = unlockTimer;
		this.accessDenied = accessDenied;
		this.lastUser = lastUser;
	}
}
