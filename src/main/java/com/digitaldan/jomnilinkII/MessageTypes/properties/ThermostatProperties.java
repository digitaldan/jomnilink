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
 * THERMOSTAT PROPERTIES
 *
 *     Data 4               communicating (0-1)
 *     Data 5               temperature
 *     Data 6               heat setpoint
 *     Data 7               cool setpoint
 *     Data 8               mode
 *     Data 9               fan (0-2)
 *     Data 10              hold (0-2)
 *     Data 11              thermostat type
 *     Data 12-24           thermostat name
 *     Data 25              humidity
 *     Data 26              humidify setpoint
 *     Data 27              dehumidify setpoint
 *     Data 28              outdoor temperature
 *     Data 29              heating/cooling/humidifying/dehumidifying status
 *
 * For description of communicating, temperature, heat and cool setpoints, mode,
 * fan status, hold status, humidity, humidity setpoints, outdoor temperature,
 * and heating/cooling/humidifying/dehumidifying status see THERMOSTAT STATUS.
 *
 * The temperatures are reported in the Omni temperature format.
 *
 * The available thermostat types are as follows:
 *
 * ------------------------------------
 * | Thermostat Type |  Description   |
 * |-----------------|----------------|
 * | 0               | Not Used       |
 * |-----------------|----------------|
 * | 1               | Auto Heat/Cool |
 * |-----------------|----------------|
 * | 2               | Heat/Cool      |
 * |-----------------|----------------|
 * | 3               | Heat Only      |
 * |-----------------|----------------|
 * | 4               | Cool Only      |
 * |-----------------|----------------|
 * | 5               | Setpoint Only  |
 * ------------------------------------
 */
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
