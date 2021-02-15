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
import lombok.experimental.NonFinal;

/*
 * UNIT STATUS
 *
 * The controller reports the status of a control unit object or group of
 * control unit objects. The status reported for each unit includes the unit
 * number, the unit's current condition, and any time remaining (specified in
 * seconds) on a timed command.
 *
 *     Start character      0x21
 *     Message length       (5 * number of units) + 2
 *     Message Type         0x23
 *     Data 1               0x02
 *     Data 2               unit number for first unit (MSB)
 *     Data 3               unit number for first unit (LSB)
 *     Data 4               unit status for first unit
 *     Data 5               high byte of time for first unit
 *     Data 6               low byte of time for first unit
 *     Data 7               unit number for second unit (MSB)
 *     Data 8               unit number for second unit (LSB)
 *     Data 9               unit status for second unit
 *     Data 10              high byte of time for second unit
 *     Data 11              low byte of time for second unit
 *     ...
 *     Data n-4             unit number for last unit (MSB)
 *     Data n-3             unit number for last unit (LSB)
 *     Data n-2             unit status for last unit
 *     Data n-1             high byte of time for last unit
 *     Data n               low byte of time for last unit
 *     CRC 1                varies
 *     CRC 2                varies
 *
 * The current condition of the unit depends on the type of the unit.
 *
 * For X-10 units, the possible conditions are:
 *
 *     0                    Last commanded off
 *     1                    Last commanded on
 *     17-25                Last commanded dim 1-9, respectively
 *     33-41                Last commanded brighten 1-9, respectively
 *     100-200              Last commanded level 0%-100%, respectively
 *
 * For Lightolier Compose PLC units:
 *
 *     0                    Off
 *     1                    On
 *     2-13                 Scene A-L, respectively
 *     17-25                Last commanded dim 1-9, respectively
 *     33-41                Last commanded brighten 1-9, respectively
 *
 * For Advanced Lighting Control (ALC) relay modules:
 *
 *     0                    Off
 *     1                    On
 *
 * For Advanced Lighting Control (ALC) dimmer modules:
 *
 *     0                    Off
 *     1                    On
 *     100-200              Last commanded level 0%-100%, respectively
 *
 * For Universal Powerline Bus (UPB) units:
 *
 *     0                    Off
 *     1                    On
 *     100-200              Last commanded level 0%-100%, respectively
 *
 * For voltage outputs:
 *
 *     0                    Off
 *     1                    On
 *
 * For flags:
 *
 *     0                    Off
 *     Non-zero             On
 *
 * For counters:
 *
 *     0-255                Counter value
 */
@Value
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NonFinal
public class UnitStatus extends Status {
	private final int status;
	private final int time;

	@Builder
	private UnitStatus(int number, int status, int time) {
		super(number);
		this.status = status;
		this.time = time;
	}
}
