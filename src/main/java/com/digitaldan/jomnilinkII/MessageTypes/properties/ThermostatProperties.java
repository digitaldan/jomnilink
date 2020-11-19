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
public class ThermostatProperties extends ObjectProperties {

	private final int status;
	private final int temperature;
	private final int heatSetpoint;
	private final int coolSetpoint;
	private final int mode;
	private final int fan;
	private final int hold;
	private final int thermostatType;

	@Builder
	private ThermostatProperties(int number, int status, int temperature, int heatSetpoint, int coolSetpoint, int mode,
			int fan, int hold, int thermostatType, String name) {
		super(OBJ_TYPE_THERMO, number, name);
		this.status = status;
		this.temperature = temperature;
		this.heatSetpoint = heatSetpoint;
		this.coolSetpoint = coolSetpoint;
		this.mode = mode;
		this.fan = fan;
		this.hold = hold;
		this.thermostatType = thermostatType;
	}
}
