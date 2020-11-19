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

/**
 * Copyright (C) 2009 Dan Cunningham
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, version 2 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 59 Temple
 * Place - Suite 330, Boston, MA 02111-1307, USA.
 */

@Value
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ZoneStatus extends Status {

	private final int status;
	private final int loop;

	/*
	 * ZONE STATUS The controller reports the status of a zone object or group of
	 * zone objects. The status reported for each zone includes the zone number,
	 * current condition of the zone (secure, not ready, or trouble), the latched
	 * alarm status for the zone, whether the zone is armed, whether the zone has
	 * had any trouble, and the current analog loop reading for the zone. Start
	 * character 0x21 Message length (4 * number of zone) + 2 Message type 0x23 Data
	 * 1 0x01 Data 2 zone number for first zone (MSB) Data 3 zone number for first
	 * zone (LSB) Data 4 zone status for first zone Data 5 zone loop reading for
	 * first zone Data 6 zone number for second zone (MSB) Data 7 zone number for
	 * second zone (LSB) Data 8 zone status for second zone Data 9 zone loop reading
	 * for second zone ... Data n-3 zone number for last zone (MSB) Data n-2 zone
	 * number for last zone (LSB) Data n-1 zone status for last zone Data n zone
	 * loop reading for last zone CRC 1 varies CRC 2 varies The zone status for a
	 * zone is packed into a single byte. Bits 0 and 1 indicate the zone’s current
	 * condition: Bit 1 Bit 0 Current Condition 0 0 Secure 0 1 Not ready 1 0 Trouble
	 * Bits 2 and 3 indicate the latched alarm status for the zone: Bit 3 Bit 2
	 * Latched Alarm Status 0 0 Secure 0 1 Tripped 1 0 Reset, but previously tripped
	 * Bits 4 and 5 indicate the arming status for the zone: Bit 5 Bit 4 Arming
	 * Status 0 0 Disarmed 0 1 Armed 1 0 Bypassed by user 1 1 Bypassed by system
	 * 
	 * 
	 * Bit 6 is set if a trouble condition has occurred that has not been
	 * acknowledged by the user. The current condition of the zone will indicate
	 * whether the zone currently has a trouble condition. If the zone does not
	 * currently have a trouble condition, but bit 6 is set, it indicates that the
	 * zone has previously had a trouble condition that has not yet been
	 * acknowledged.
	 * 
	 */
	@Builder
	private ZoneStatus(int number, int status, int loop) {
		super(number);
		this.status = status;
		this.loop = loop;
	}
}
