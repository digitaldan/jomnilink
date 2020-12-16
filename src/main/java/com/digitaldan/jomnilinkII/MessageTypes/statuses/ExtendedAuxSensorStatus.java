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
 * EXTENDED AUXILIARY SENSOR STATUS (Requires Firmware Version 3.0 or Later)
 *
 * The controller reports the status of an auxiliary sensor object or a group of
 * auxiliary sensor objects. The status reported for each auxiliary sensor
 * includes the auxiliary sensor number, the output status for each Programmable
 * Energy Saver Module (PESM), the current temperature or humidity reading, and
 * the low and high setpoints.
 *
 *     Start character      0x21
 *     Message length       (6 * number of auxiliary sensors) + 3
 *     Message Type         0x3B
 *     Data 1               0x08 (object type)
 *     Data 2               0x06 (object record length)
 *     Data 3               auxiliary sensor number for first auxiliary sensor (MSB)
 *     Data 4               auxiliary sensor number for first auxiliary sensor (LSB)
 *     Data 5               output status for first auxiliary sensor
 *     Data 6               current temperature or humidity for first auxiliary sensor
 *     Data 7               low/heat setpoint for first auxiliary sensor
 *     Data 8               high/cool setpoint for first auxiliary sensor
 *     Data 9               auxiliary sensor number for second auxiliary sensor (MSB)
 *     Data 10              auxiliary sensor number for second auxiliary sensor (LSB)
 *     Data 11              output status for second auxiliary sensor
 *     Data 12              current temperature or humidity for second auxiliary sensor
 *     Data 13              low/heat setpoint for second auxiliary sensor
 *     Data 14              high/cool setpoint for second auxiliary sensor
 *     ...
 *     Data n-5             auxiliary sensor number for last auxiliary sensor (MSB)
 *     Data n-4             auxiliary sensor number for last auxiliary sensor (LSB)
 *     Data n-3             output status for last auxiliary sensor
 *     Data n-2             current temperature or humidity for last auxiliary sensor
 *     Data n-1             low/heat setpoint for last auxiliary sensor
 *     Data n               high/cool setpoint for last auxiliary sensor
 *     CRC 1                varies
 *     CRC 2                varies
 *
 * The output status is non-zero if the output is energized.
 *
 * The temperatures are reported in the Omni temperature format.
 */
@Value
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ExtendedAuxSensorStatus extends Status {
	@Delegate
	private final AuxSensorStatus auxSensorStatus;

	@Builder
	private ExtendedAuxSensorStatus(int number, int outputStatus, int temperature, int heatSetpoint, int coolSetpoint) {
		super(number);
		auxSensorStatus = AuxSensorStatus.builder().number(number).outputStatus(outputStatus).temperature(temperature)
				.heatSetpoint(heatSetpoint).coolSetpoint(coolSetpoint).build();
	}
}
