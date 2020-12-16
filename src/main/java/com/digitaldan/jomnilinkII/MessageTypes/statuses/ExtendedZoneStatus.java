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

/*
 * EXTENDED ZONE STATUS (Requires Firmware Version 3.0 or Later)
 *
 * The controller reports the status of a zone object or group of zone objects.
 * The status reported for each zone includes the zone number, current condition
 * of the zone (secure, not ready, or trouble), the latched alarm status for the
 * zone, whether the zone is armed, whether the zone has had any trouble, and
 * the current analog loop reading for the zone.
 *
 *     Start character      0x21
 *     Message length       (4 * number of zone) + 3
 *     Message Type         0x3B
 *     Data 1               0x01 (object type)
 *     Data 2               0x04 (object record length)
 *     Data 3               zone number for first zone (MSB)
 *     Data 4               zone number for first zone (LSB)
 *     Data 5               zone status for first zone
 *     Data 6               zone loop reading for first zone
 *     Data 7               zone number for second zone (MSB)
 *     Data 8               zone number for second zone (LSB)
 *     Data 9               zone status for second zone
 *     Data 10              zone loop reading for second zone
 *     ...
 *     Data n-3             zone number for last zone (MSB)
 *     Data n-2             zone number for last zone (LSB)
 *     Data n-1             zone status for last zone
 *     Data n               zone loop reading for last zone
 *     CRC 1                varies
 *     CRC 2                varies
 *
 * The zone status for a zone is packed into a single byte. Bits 0 and 1
 * indicate the zone’s current condition:
 *
 * -------------------------------------
 * | Bit 1 | Bit 0 | Current Condition |
 * |-------|-------|-------------------|
 * | 0     | 0     | Secure            |
 * |-------|-------|-------------------|
 * | 0     | 1     | Not ready         |
 * |-------|-------|-------------------|
 * | 1     | 0     | Trouble           |
 * -------------------------------------
 *
 * Bits 2 and 3 indicate the latched alarm status for the zone:
 *
 * -------------------------------------------------
 * | Bit 3 | Bit 2 |     Latched Alarm Status      |
 * |-------|-------|-------------------------------|
 * | 0     | 0     | Secure                        |
 * |-------|-------|-------------------------------|
 * | 0     | 1     | Tripped                       |
 * |-------|-------|-------------------------------|
 * | 1     | 0     | Reset, but previously tripped |
 * -------------------------------------------------
 *
 * Bits 4 and 5 indicate the arming status for the zone:
 *
 * --------------------------------------
 * | Bit 5 | Bit 4 |   Arming Status    |
 * |-------|-------|--------------------|
 * | 0     | 0     | Disarmed           |
 * |-------|-------|--------------------|
 * | 0     | 1     | Armed              |
 * |-------|-------|--------------------|
 * | 1     | 0     | Bypassed by user   |
 * |-------|-------|--------------------|
 * | 1     | 1     | Bypassed by system |
 * --------------------------------------
 *
 * Bit 6 is set if a trouble condition has occurred that has not been
 * acknowledged by the user. The current condition of the zone will indicate
 * whether the zone currently has a trouble condition. If the zone does not
 * currently have a trouble condition, but bit 6 is set, it indicates that the
 * zone has previously had a trouble condition that has not yet been
 * acknowledged.
 */
@Value
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ExtendedZoneStatus extends Status {
	@Delegate
	private final ZoneStatus zoneStatus;

	@Builder
	private ExtendedZoneStatus(int number, int status, int loop) {
		super(number);
		zoneStatus = ZoneStatus.builder().number(number).status(status).loop(loop).build();
	}
}
