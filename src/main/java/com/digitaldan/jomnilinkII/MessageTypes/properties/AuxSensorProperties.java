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
import com.digitaldan.jomnilinkII.MessageTypes.statuses.AuxSensorStatus;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

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
