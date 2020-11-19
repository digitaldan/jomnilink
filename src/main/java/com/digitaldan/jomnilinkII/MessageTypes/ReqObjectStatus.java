/**
 * Copyright (c) 2009-2020 Dan Cunningham
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package com.digitaldan.jomnilinkII.MessageTypes;

import com.digitaldan.jomnilinkII.Message;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

/*
 * REQUEST OBJECT STATUS
 *
 * This message is used to request the status of a group of zone, unit, area,
 * thermostat, message, auxiliary sensor, audio zone, expansion enclosure, user
 * setting, access control reader, or access control reader lock objects.
 *
 * Zones: The status reported for each zone includes the zone number, current
 *        condition of the zone (secure, not ready, or trouble), and the current
 *        analog loop reading for the zone.
 *
 * Units: The status reported for each unit includes the unit number, current
 *        condition of the unit, and any time remaining on a timed command.
 *
 * Areas: The status reported for each area includes the area number, current
 *        mode of the area, alarm status of the area, and time remaining on an
 *        entry or exit timer.
 *
 * Thermostats: The status reported for each thermostat includes the thermostat
 *              number, whether the thermostat is communicating with the
 *              controller, the current temperature, the heat and cool
 *              setpoints, the system mode, the fan mode, and whether the
 *              thermostat has been placed in hold mode.
 *
 * Messages: The status reported for each message includes the message number,
 *           which messages are currently being displayed and what displayed
 *           messages have not been acknowledged.
 *
 * Auxiliary: The status reported for each auxiliary sensor includes the sensor
 *            number, the output status for each PESM, the current temperature
 *            or humidity reading, and the low and high setpoints.
 *
 * Audio Zones: The status reported for each audio zone includes the audio zone
 *              number, the on/off status of the zone, the selected source for
 *              the zone, the volume, and whether the zone is muted.
 *
 * Expansion: The status reported for each expansion enclosure includes the
 *            expansion enclosure number, whether the thermostat is
 *            communicating with the controller, and the battery reading.
 *
 * User Setting: The status reported for each user setting includes the user
 *               setting number, the user setting type, and the current value of
 *               the user setting.
 *
 * Reader: The status reported for each access control reader includes the
 *         reader number, whether access was granted or denied, and the last
 *         user to access the reader.
 *
 * Lock: The status reported for each access control reader lock includes the
 *       reader number, the status of the lock, and any time remaining on a
 *       timed command.
 *
 * The request is sent using two bytes for each object.
 *
 *     Start character      0x21
 *     Message length       0x06
 *     Message Type         0x22
 *     Data 1               object type
 *     Data 2               starting object (MSB)
 *     Data 3               starting object (LSB)
 *     Data 4               ending object (MSB)
 *     Data 5               ending object (LSB)
 *     CRC 1                varies
 *     CRC 2                varies
 *
 *     Expected Reply       OBJECT STATUS
 */
@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReqObjectStatus implements Message {
	private final int objectType;
	private final int startObject;
	private final int endObject;

	@Override
	public int getMessageType() {
		return MESG_TYPE_REQ_OBJ_STATUS;
	}
}
