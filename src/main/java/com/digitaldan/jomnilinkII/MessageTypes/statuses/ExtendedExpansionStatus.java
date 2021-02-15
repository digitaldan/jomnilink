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
 * EXPANSION ENCLOSURE STATUS
 *
 * The controller reports the status of an expansion enclosure object or a group
 * of expansion enclosure objects. The status reported for each expansion
 * enclosure includes the expansion enclosure number, whether the thermostat is
 * communicating with the controller, and the battery reading.
 *
 *     Start character      0x21
 *     Message length       (4 * number of expansion enclosures) + 3
 *     Message Type         0x3B
 *     Data 1               0x0B (object type)
 *     Data 2               0x04 (object record length)
 *     Data 3               address number for first expansion enclosure (MSB)
 *     Data 4               address number for first expansion enclosure (LSB)
 *     Data 5               communications status for first expansion enclosure (0-1)
 *     Data 6               battery reading for first expansion enclosure (0-255)
 *     Data 7               address number for second expansion enclosure (MSB)
 *     Data 8               address number for second expansion enclosure (LSB)
 *     Data 9               communications status for second expansion enclosure (0-1)
 *     Data 10              battery reading for second expansion enclosure (0-255)
 *     ...
 *     Data n-3             address number for last expansion enclosure (MSB)
 *     Data n-2             address number for last expansion enclosure (LSB)
 *     Data n-1             communications status for last expansion enclosure (0-1)
 *     Data n               battery reading for last expansion enclosure (0-255)
 *     CRC 1                varies
 *     CRC 2                varies
 */
@Value
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ExtendedExpansionStatus extends Status {
	@Delegate
	private final ExpansionStatus expansionStatus;

	@Builder
	private ExtendedExpansionStatus(int number, boolean status, int battery) {
		super(number);
		expansionStatus = ExpansionStatus.builder().number(number).status(status).battery(battery).build();
	}
}
