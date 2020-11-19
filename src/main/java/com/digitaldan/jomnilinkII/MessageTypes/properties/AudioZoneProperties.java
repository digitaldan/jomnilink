/**
* Copyright (c) 2009-2020 Dan Cunningham
*
* This program and the accompanying materials are made available under the
* terms of the Eclipse Public License 2.0 which is available at
* http://www.eclipse.org/legal/epl-2.0
*
* SPDX-License-Identifier: EPL-2.0
*/
package com.digitaldan.jomnilinkII.MessageTypes.properties;

import com.digitaldan.jomnilinkII.MessageTypes.ObjectProperties;
import com.digitaldan.jomnilinkII.MessageTypes.statuses.AudioZoneStatus;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AudioZoneProperties extends ObjectProperties {

	private final boolean on;
	private final int source;
	private final int volume;
	private final boolean mute;

	@Builder
	private AudioZoneProperties(int number, boolean on, int source, int volume, boolean mute, String name) {
		super(OBJ_TYPE_AUDIO_ZONE, number, name);
		this.on = on;
		this.source = source;
		this.volume = volume;
		this.mute = mute;
	}

}
