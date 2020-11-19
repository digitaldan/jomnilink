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

@Value
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ExpansionStatus extends Status {

	private int status;
	private int battery;

	/*
	 * EXPANSION ENCLOSURE STATUS The controller reports the status of an expansion
	 * enclosure object or a group of expansion enclosure objects. The status
	 * reported for each expansion enclosure includes the expansion enclosure
	 * number, whether the thermostat is communicating with the controller, and the
	 * battery reading. Start character 0x21 Message length (4 * number of expansion
	 * enclosures) + 2 Message type 0x23 Data 1 0x0B Data 2 address number for first
	 * expansion enclosure (MSB) Data 3 address number for first expansion enclosure
	 * (LSB) Data 4 communications status for first expansion enclosure (0-1) Data 5
	 * battery reading for first expansion enclosure (0-255) address number for
	 * second expansion enclosure (MSB) Data 6 Data 7 address number for second
	 * expansion enclosure (LSB) Data 8 communications status for second expansion
	 * enclosure (0-1) Data 9 battery reading for second expansion enclosure (0-255)
	 * ... Data n-3 address number for last expansion enclosure (MSB) Data n-2
	 * address number for last expansion enclosure (LSB) Data n-1 communications
	 * status for last expansion enclosure (0-1) Data n battery reading for last
	 * expansion enclosure (0-255) CRC 1 varies CRC 2 varies
	 *
	 */
	@Builder
	private ExpansionStatus(int number, int status, int battery) {
		super(number);
		this.status = status;
		this.battery = battery;
	}

}
