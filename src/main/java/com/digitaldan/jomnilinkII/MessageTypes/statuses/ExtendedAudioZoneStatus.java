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
 * EXTENDED AUDIO ZONE STATUS (Requires Firmware Version 3.0 or Later)
 *
 * The controller reports the status of an audio zone object or a group of audio
 * zone objects. The status reported for each audio zone includes the audio zone
 * number, on/off status of the zone, the selected source for the zone, the
 * volume, and whether the zone is muted.
 *
 *     Start character      0x21
 *     Message length       (6 * number of audio zones) + 3
 *     Message Type         0x3B
 *     Data 1               0x0A (object type)
 *     Data 2               0x06 (object record length)
 *     Data 3               audio zone number for first audio zone (MSB)
 *     Data 4               audio zone number for first audio zone (LSB)
 *     Data 5               power on/off for first audio zone (0-1)
 *     Data 6               selected source for first audio zone (1-n)
 *     Data 7               volume for first audio zone (0-100)
 *     Data 8               mute status for first audio zone (0-1)
 *     Data 9               audio zone number for second audio zone (MSB)
 *     Data 10              audio zone number for second audio zone (LSB)
 *     Data 11              power on/off for second audio zone (0-1)
 *     Data 12              selected source for second audio zone (1-n)
 *     Data 13              volume for second audio zone (0-100)
 *     Data 14              mute status for second audio zone (0-1)
 *     ...
 *     Data n-5             audio zone number for last audio zone (MSB)
 *     Data n-4             audio zone number for last audio zone (LSB)
 *     Data n-3             power on/off for last audio zone (0-1)
 *     Data n-2             selected source for last audio zone (1-n)
 *     Data n-1             volume for last audio zone (0-100)
 *     Data n               mute status for last audio zone (0-1)
 *     CRC 1                varies
 *     CRC 2                varies
 */
@Value
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ExtendedAudioZoneStatus extends Status {
	@Delegate
	private final AudioZoneStatus audioZoneStatus;

	@Builder
	private ExtendedAudioZoneStatus(int number, boolean power, int source, int volume, boolean mute) {
		super(number);
		audioZoneStatus = AudioZoneStatus.builder().number(number).power(power).source(source).volume(volume).mute(mute)
				.build();
	}
}
