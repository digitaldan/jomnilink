package com.digitaldan.jomnilinkII.MessageTypes.statuses;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import lombok.experimental.NonFinal;

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

@Value
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NonFinal
public class ThermostatStatus extends Status {

	private final int status;
	private final int temperature;
	private final int heatSetpoint;
	private final int coolSetpoint;
	private final int mode;
	private final int fan;
	private final int hold;

	/*
	 *THERMOSTAT STATUS
	The controller reports the status of a thermostat object or group of thermostat objects. The status reported
	for each thermostat includes the thermostat number, whether the thermostat is communicating with the
	controller, whether a freeze condition has been detected by the thermostat, the current temperature, the heat
	and cool setpoints, the system mode, the fan mode, and whether the thermostat has been placed in hold
	mode.
	     Start character             0x21
	     Message length              (9 * number of thermostats) + 2
	     Message type                0x23
	     Data 1                      0x06
	     Data 2                      thermostat number for first thermostat (MSB)
	     Data 3                      thermostat number for first thermostat (LSB)
	     Data 4                      status byte for first thermostat
	     Data 5                      current temperature for first thermostat
	     Data 6                      heat setpoint for first thermostat
	     Data 7                      cool setpoint for first thermostat
	     Data 8                      system mode for first thermostat
	     Data 9                      fan mode for first thermostat
	     Data 10                     hold status for first thermostat
	     Data 11                     thermostat number for second thermostat (MSB)
	     Data 12                     thermostat number for second thermostat (LSB)
	     Data 13                     status byte for second thermostat
	     Data 14                     current temperature for second thermostat
	     Data 15                     heat setpoint for second thermostat
	     Data 16                     cool setpoint for second thermostat
	     Data 17                     system mode for second thermostat
	     Data 18                     fan mode for second thermostat
	     Data 19                     hold status for second thermostat
	     ...
	     Data n-8                    thermostat number for last thermostat (MSB)
	     Data n-7                    thermostat number for last thermostat (LSB)
	     Data n-6                    status byte for last thermostat
	     Data n-5                    current temperature for last thermostat
	     Data n-4                    heat setpoint for last thermostat
	     Data n-3                    cool setpoint for last thermostat
	     Data n-2                    system mode for last thermostat
	     Data n-1                    fan mode for last thermostat
	     Data n                      hold status for last thermostat
	     CRC 1                       varies
	     CRC 2                       varies
	The bits in the thermostat status byte are shown below. The corresponding bit is set if the condition is true.
	     Bit 0                       Communications failure
	     Bit 1                       Freeze alarm
	The temperatures are reported in the Omni temperature format (see Appendix C).

	The system mode is as follows:
	     0                           Off
	     1                           Heat
	     2                           Cool
	     3                           Auto
	     4                           Emergency heat
	The fan mode is as follows:
	     0                           Auto
	     1                           On
	The hold status is non-zero if the thermostat is in hold mode.

	 */
	@Builder
	protected ThermostatStatus(int number, int status, int temperature, int heatSetpoint, int coolSetpoint, int mode,
							 int fan, int hold) {
		super(number);
		this.status = status;
		this.temperature = temperature;
		this.heatSetpoint = heatSetpoint;
		this.coolSetpoint = coolSetpoint;
		this.mode = mode;
		this.fan = fan;
		this.hold = hold;
	}



}
