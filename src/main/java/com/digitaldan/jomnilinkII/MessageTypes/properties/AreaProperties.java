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
public class AreaProperties extends ObjectProperties {
	private final int mode;
	private final int alarms;
	private final int entryTimer;
	private final int exitTimer;
	private final boolean enabled;
	private final int exitDelay;
	private final int entryDelay;

	@Builder
	private AreaProperties(int number, int mode, int alarms, int entryTimer, int exitTimer, boolean enabled,
			int exitDelay, int entryDelay, String name) {
		super(OBJ_TYPE_AREA, number, name);
		this.mode = mode;
		this.alarms = alarms;
		this.entryTimer = entryTimer;
		this.exitTimer = exitTimer;
		this.enabled = enabled;
		this.exitDelay = exitDelay;
		this.entryDelay = entryDelay;
	}

}
