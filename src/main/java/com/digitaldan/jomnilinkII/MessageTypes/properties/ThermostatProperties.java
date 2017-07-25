package com.digitaldan.jomnilinkII.MessageTypes.properties;

/**
*  Copyright (C) 2009  Dan Cunningham
*
* This program is free software; you can redistribute it and/or
* modify it under the terms of the GNU General Public License
* as published by the Free Software Foundation, version 2
* of the License, or (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
*/

import com.digitaldan.jomnilinkII.MessageTypes.ObjectProperties;
import com.digitaldan.jomnilinkII.MessageTypes.statuses.ThermostatStatus;
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
