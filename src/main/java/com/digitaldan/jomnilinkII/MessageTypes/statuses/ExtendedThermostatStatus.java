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

@Value
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ExtendedThermostatStatus extends Status {

	private final int humidity;
	private final int humiditySetpoint;
	private final int dehumidifySetpoint;
	private final int outdoorTemp;
	private final int extendedStatus;

	@Delegate
	private final ThermostatStatus thermostatStatus;

	@Builder
	private ExtendedThermostatStatus(int number, int status, int temperature, int heatSetpoint, int coolSetpoint,
			int mode, int fan, int hold, int humidity, int humiditySetpoint, int dehumidifySetpoint, int outdoorTemp,
			int extendedStatus) {
		super(number);
		thermostatStatus = ThermostatStatus.builder().number(number).status(status).temperature(temperature)
				.heatSetpoint(heatSetpoint).coolSetpoint(coolSetpoint).mode(mode).fan(fan).hold(hold).build();
		this.humidity = humidity;
		this.humiditySetpoint = humiditySetpoint;
		this.dehumidifySetpoint = dehumidifySetpoint;
		this.outdoorTemp = outdoorTemp;
		this.extendedStatus = extendedStatus;

	}
}
