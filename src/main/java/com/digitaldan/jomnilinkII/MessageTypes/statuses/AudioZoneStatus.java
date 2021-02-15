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
 * AUDIO ZONE STATUS
 *
 * The controller reports the status of an audio zone object or a group of audio
 * zone objects. The status reported for each audio zone includes the audio zone
 * number, on/off status of the zone, the selected source for the zone, the
 * volume, and whether the zone is muted.
 *
 *     Start character      0x21
 *     Message length       (6 * number of audio zones) + 2
 *     Message Type         0x23
 *     Data 1               0x0A
 *     Data 2               audio zone number for first audio zone (MSB)
 *     Data 3               audio zone number for first audio zone (LSB)
 *     Data 4               power on/off for first audio zone (0-1)
 *     Data 5               selected source for first audio zone (1-n)
 *     Data 6               volume for first audio zone (0-100)
 *     Data 7               mute status for first audio zone (0-1)
 *     Data 8               audio zone number for second audio zone (MSB)
 *     Data 9               audio zone number for second audio zone (LSB)
 *     Data 10              power on/off for second audio zone (0-1)
 *     Data 11              selected source for second audio zone (1-n)
 *     Data 12              volume for second audio zone (0-100)
 *     Data 13              mute status for second audio zone (0-1)
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
@NonFinal
public class AudioZoneStatus extends Status {
	private final boolean power;
	private final int source;
	private final int volume;
	private final boolean mute;

	@Builder
	private AudioZoneStatus(int number, boolean power, int source, int volume, boolean mute) {
		super(number);
		this.power = power;
		this.source = source;
		this.volume = volume;
		this.mute = mute;
	}
}
