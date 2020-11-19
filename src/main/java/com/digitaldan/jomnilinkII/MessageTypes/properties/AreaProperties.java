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

/*
 * AREA PROPERTIES
 *
 *     Data 4               area mode (0-255)
 *     Data 5               area alarms (0-255)
 *     Data 6               entry timer
 *     Data 7               exit timer
 *     Data 8               enabled (0-1)
 *     Data 9               exit delay (0-255)
 *     Data 10              entry delay (0-255)
 *     Data 11-23           area name
 *
 * For description of area mode and area alarms, see AREA STATUS.
 *
 * The entry timer and exit timer is the number of seconds remaining for the
 * respective timer. The exit delay and entry delay is the configuration setting
 * for the respective delay.
 *
 * If AREA PROPERTIES are requested for an area that is configured in the HAI
 * controller, the enabled byte will be set to 1; if an area is not configured
 * in the HAI controller, the enabled byte will be set to 0.
 */
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
