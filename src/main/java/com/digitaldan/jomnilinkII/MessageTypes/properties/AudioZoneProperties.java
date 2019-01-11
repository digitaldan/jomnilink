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
