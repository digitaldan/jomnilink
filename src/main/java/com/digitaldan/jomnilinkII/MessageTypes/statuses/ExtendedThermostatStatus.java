/**
 * Copyright (c) 2009-2021 Dan Cunningham
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
 * EXTENDED THERMOSTAT STATUS (Requires Firmware Version 3.0 or Later)
 *
 * The controller reports the status of a thermostat object or group of
 * thermostat objects. The status reported for each thermostat includes the
 * thermostat number, whether the thermostat is communicating with the
 * controller, whether a freeze condition has been detected by the thermostat,
 * the current temperature, the heat and cool setpoints, the system mode, the
 * fan mode, and whether the thermostat has been placed in hold mode.
 *
 *     Start character      0x21
 *     Message length       (14 * number of thermostats) + 3
 *     Message Type         0x3B
 *     Data 1               0x06 (object type)
 *     Data 2               0x0E (object record length)
 *     Data 3               thermostat number for first thermostat (MSB)
 *     Data 4               thermostat number for first thermostat (LSB)
 *     Data 5               status byte for first thermostat
 *     Data 6               current temperature for first thermostat
 *     Data 7               heat setpoint for first thermostat
 *     Data 8               cool setpoint for first thermostat
 *     Data 9               system mode for first thermostat
 *     Data 10              fan mode for first thermostat
 *     Data 11              hold status for first thermostat
 *     Data 12              current humidity for first thermostat
 *     Data 13              humidify setpoint for first thermostat
 *     Data 14              dehumidify setpoint for first thermostat
 *     Data 15              outdoor temperature for first thermostat
 *     Data 16              heating/cooling/humidifying/dehumidifying status for first thermostat
 *     Data 17              thermostat number for second thermostat (MSB)
 *     Data 18              thermostat number for second thermostat (LSB)
 *     Data 19              status byte for second thermostat
 *     Data 20              current temperature for second thermostat
 *     Data 21              heat setpoint for second thermostat
 *     Data 22              cool setpoint for second thermostat
 *     Data 23              system mode for second thermostat
 *     Data 24              fan mode for second thermostat
 *     Data 25              hold status for second thermostat
 *     Data 26              current humidity for second thermostat
 *     Data 27              humidify setpoint for second thermostat
 *     Data 28              dehumidify setpoint for second thermostat
 *     Data 29              outdoor temperature for second thermostat
 *     Data 30              heating/cooling/humidifying/dehumidifying status for second thermostat
 *     ...
 *     Data n-13            thermostat number for last thermostat (MSB)
 *     Data n-12            thermostat number for last thermostat (LSB)
 *     Data n-11            status byte for last thermostat
 *     Data n-10            current temperature for last thermostat
 *     Data n-9             heat setpoint for last thermostat
 *     Data n-8             cool setpoint for last thermostat
 *     Data n-7             system mode for last thermostat
 *     Data n-6             fan mode for last thermostat
 *     Data n-5             hold status for last thermostat
 *     Data n-4             current humidity for last thermostat
 *     Data n-3             humidify setpoint for last thermostat
 *     Data n-2             dehumidify setpoint for last thermostat
 *     Data n-1             outdoor temperature for last thermostat
 *     Data n               heating/cooling/humidifying/dehumidifying status for last thermostat
 *     CRC 1                varies
 *     CRC 2                varies
 *
 * The bits in the thermostat status byte are shown below. The corresponding bit
 * is set if the condition is true.
 *
 *     Bit 0                Communications failure
 *     Bit 1                Freeze alarm
 *
 * The temperatures are reported in the Omni temperature format.
 *
 * The system mode is as follows:
 *
 *     0                    Off
 *     1                    Heat
 *     2                    Cool
 *     3                    Auto
 *     4                    Emergency heat
 *
 * The fan mode is as follows:
 *
 *     0                    Auto
 *     1                    On
 *     2                    Cycle
 *
 * The fan mode is as follows:
 *
 *     0                    Off
 *     1                    Hold
 *     2                    Vacation hold
 *     Other                Hold
 *
 * Humidity is reported in the Omni temperature format where Fahrenheit
 * temperatures 0-100 correspond to 0-100% relative humidity.
 *
 * The bits in the heating/cooling/humidifying/dehumidifying status byte are
 * shown below. The corresponding bit is set if the thermostat is currently
 * performing that action.
 *
 *     Bit 0                Heating
 *     Bit 1                Cooling
 *     Bit 2                Humidifying
 *     Bit 3                Dehumidifying
 */
@Value
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ExtendedThermostatStatus extends Status {
	private final int currentHumidity;
	private final int humidifySetpoint;
	private final int dehumidifySetpoint;
	private final int outdoorTemperature;
	private final int extendedStatus;

	@Delegate
	private final ThermostatStatus thermostatStatus;

	@Builder
	private ExtendedThermostatStatus(int number, int status, int currentTemperature, int heatSetpoint, int coolSetpoint,
			int systemMode, int fanMode, int holdStatus, int currentHumidity, int humidifySetpoint,
			int dehumidifySetpoint, int outdoorTemperature, int extendedStatus) {
		super(number);
		thermostatStatus = ThermostatStatus.builder().number(number).status(status)
				.currentTemperature(currentTemperature).heatSetpoint(heatSetpoint).coolSetpoint(coolSetpoint)
				.systemMode(systemMode).fanMode(fanMode).holdStatus(holdStatus).build();
		this.currentHumidity = currentHumidity;
		this.humidifySetpoint = humidifySetpoint;
		this.dehumidifySetpoint = dehumidifySetpoint;
		this.outdoorTemperature = outdoorTemperature;
		this.extendedStatus = extendedStatus;
	}
}
