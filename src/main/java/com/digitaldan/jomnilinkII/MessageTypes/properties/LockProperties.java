/**
* Copyright (c) 2009-2020 Dan Cunningham
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

@Value
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class LockProperties extends ObjectProperties {
	private final boolean locked;
	private final int unlockTimer;
	private final boolean accessDenied;
	private final int lastUser;

	@Builder
	private LockProperties(int number, boolean locked, int unlockTimer, boolean accessDenied, int lastUser,
			String name) {
		super(OBJ_TYPE_AREA, number, name);
		this.locked = locked;
		this.unlockTimer = unlockTimer;
		this.accessDenied = accessDenied;
		this.lastUser = lastUser;
	}

}
