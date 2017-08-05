package com.digitaldan.jomnilinkII.MessageTypes.statuses;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

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
public class AuxSensorStatus extends Status {

	private final int status;
	private final int temp;
	private final int heatSetpoint;
	private final int coolSetpoint;

	/*
	 *AUXILIARY SENSOR STATUS
	The controller reports the status of an auxiliary sensor object or a group of auxiliary sensor objects. The
	status reported for each auxiliary sensor includes the auxiliary sensor number, the output status for each
	Programmable Energy Saver Module (PESM), the current temperature or humidity reading, and the low
	and high setpoints.
	     Start character             0x21
	     Message length              (6 * number of auxiliary sensors) + 2
	     Message type                0x23
	     Data 1                      0x08
	     Data 2                      auxiliary sensor number for first auxiliary sensor (MSB)
	     Data 3                      auxiliary sensor number for first auxiliary sensor (LSB)
	     Data 4                      output status for first auxiliary sensor
	     Data 5                      current temperature or humidity for first auxiliary sensor
	     Data 6                      low/heat setpoint for first auxiliary sensor
	     Data 7                      high/cool setpoint for first auxiliary sensor
	     Data 8                      auxiliary sensor number for second auxiliary sensor (MSB)
	     Data 9                      auxiliary sensor number for second auxiliary sensor (LSB)
	     Data 10                     output status for second auxiliary sensor
	     Data 11                     current temperature or humidity for second auxiliary sensor
	     Data 12                     low/heat setpoint for second auxiliary sensor
	     Data 13                     high/cool setpoint for second auxiliary sensor
	     ...
	     Data n-5                    auxiliary sensor number for last auxiliary sensor (MSB)
	     Data n-4                    auxiliary sensor number for last auxiliary sensor (LSB)
	     Data n-3                    output status for last auxiliary sensor
	     Data n-2                    current temperature or humidity for last auxiliary sensor
	     Data n-1                    low/heat setpoint for last auxiliary sensor
	     Data n                      high/cool setpoint for last auxiliary sensor
	     CRC 1                       varies
	     CRC 2                       varies
	The output status is non-zero if the output is energized.
	The temperatures are reported in the Omni temperature format (see Appendix C).
	
	 */
	@Builder
	private AuxSensorStatus(int number, int status, int temp, int heatSetpoint, int coolSetpoint) {
		super(number);
		this.status = status;
		this.temp = temp;
		this.heatSetpoint = heatSetpoint;
		this.coolSetpoint = coolSetpoint;
	}


}
