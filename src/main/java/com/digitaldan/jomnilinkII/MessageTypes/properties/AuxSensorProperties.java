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
 * AUXILIARY SENSOR PROPERTIES
 *
 *     Data 4               output status
 *     Data 5               current temperature or humidity
 *     Data 6               low setpoint
 *     Data 7               high setpoint
 *     Data 8               sensor type
 *     Data 9-24            sensor name
 *
 * The temperatures are reported in the Omni temperature format.
 *
 * The available sensor types are as follows:
 *
 * ----------------------------------------------------
 * | Sensor Type |            Description             |
 * |-------------|------------------------------------|
 * | 80          | Programmable Energy Saver Module   |
 * |-------------|------------------------------------|
 * | 81          | Outdoor Temperature                |
 * |-------------|------------------------------------|
 * | 82          | Temperature                        |
 * |-------------|------------------------------------|
 * | 83          | Temperature Alarm                  |
 * |-------------|------------------------------------|
 * | 84          | Humidity                           |
 * |-------------|------------------------------------|
 * | 85          | Extended Range Outdoor Temperature |
 * |-------------|------------------------------------|
 * | 86          | Extended Range Temperature         |
 * |-------------|------------------------------------|
 * | 87          | Extended Range Temperature Alarm   |
 * ----------------------------------------------------
 */
@Value
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AuxSensorProperties extends ObjectProperties {
	public static final int SENSOR_TYPE_PROGRAMMABLE_ENERGY_SAVER_MODULE = 80;
	public static final int SENSOR_TYPE_OUTDOOR_TEMPERATURE = 81;
	public static final int SENSOR_TYPE_TEMPERATURE = 82;
	public static final int SENSOR_TYPE_TEMPERATURE_ALARM = 83;
	public static final int SENSOR_TYPE_HUMIDITY = 84;
	public static final int SENSOR_TYPE_EXTENDED_RANGE_OUTDOOR_TEMPERATURE = 85;
	public static final int SENSOR_TYPE_EXTENDED_RANGE_TEMPERATURE = 86;
	public static final int SENSOR_TYPE_EXTENDED_RANGE_TEMPERATURE_ALARM = 87;

	private final int status;
	private final int current;
	private final int lowSetpoint;
	private final int highSetpoint;
	private final int sensorType;

	@Builder
	private AuxSensorProperties(int number, int status, int current, int lowSetpoint, int highSetpoint, int sensorType,
			String name) {
		super(OBJ_TYPE_AUX_SENSOR, number, name);
		this.status = status;
		this.current = current;
		this.lowSetpoint = lowSetpoint;
		this.highSetpoint = highSetpoint;
		this.sensorType = sensorType;
	}
}
