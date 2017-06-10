package com.digitaldan.jomnilinkII.MessageTypes.properties;

import com.digitaldan.jomnilinkII.MessageTypes.ObjectProperties;
import com.digitaldan.jomnilinkII.MessageTypes.statuses.AudioZoneStatus;

public class AudioZoneProperties extends ObjectProperties {

	private boolean on;
	private int source;
	private int volume;
	private boolean mute;

	public AudioZoneProperties(int number, boolean on, int source, int volume, boolean mute, String name) {
		super(OBJ_TYPE_AUDIO_ZONE, number, name);
		this.on = on;
		this.source = source;
		this.volume = volume;
		this.mute = mute;
		this.name = name;
	}

	public boolean isOn() {
		return on;
	}

	public int getSource() {
		return source;
	}

	public int getVolume() {
		return volume;
	}

	public boolean isMute() {
		return mute;
	}

	public void setOn(boolean on) {
		this.on = on;
	}

	public void setSource(int source) {
		this.source = source;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public void setMute(boolean mute) {
		this.mute = mute;
	}

	public void updateAudioZone(AudioZoneStatus audioZoneStatus) {
		setSource(audioZoneStatus.getSource());
		setVolume(audioZoneStatus.getVolume());
		setMute(audioZoneStatus.isMute());
		setOn(audioZoneStatus.isPower());
	}

	@Override
	public String toString() {
		final String TAB = "    ";
		String retValue = "";

		retValue = "AudioZoneProperties ( " + "number = " + this.number + TAB + "on = " + this.on + TAB + "source = "
				+ this.source + TAB + "volume = " + this.volume + TAB + "mute = " + this.mute + TAB + "name = "
				+ this.name + TAB + " )";

		return retValue;
	}
}
