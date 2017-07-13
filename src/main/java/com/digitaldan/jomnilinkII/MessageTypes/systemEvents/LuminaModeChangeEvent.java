package com.digitaldan.jomnilinkII.MessageTypes.systemEvents;

public class LuminaModeChangeEvent extends SystemEvent {

	/**
	 * LUMINA MODE CHANGE
	 * dmmm 0001 cccc cccc
	 * d= mode change delay flag 0 = end of delay
	 *     1 = start of delay
	 * m = mode
	 *     1 = home
	 *     2 = sleep
	 *     3 = away
	 *     4 = vacation
	 *     5 = party
	 *     6 = special
	 * c = code
	 *
	 * @param event
	 */
	public LuminaModeChangeEvent(int event) {
		super(event, SystemEventType.LUMINA_MODE_CHANGE);
	}

	public int getModeChangeDelay() {
		return event >> 15 & 0x01;
	}

	public int getLuminaMode() {
		return event >> 12 & 0x07;
	}

	public int getCode() {
		return event & 0xff;
	}
}
