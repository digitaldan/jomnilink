package com.digitaldan.jomnilinkII.MessageTypes.systemEvents;

public class PhoneLineRingEvent extends SystemEvent {

	/**
	 * PHONE LINE RING
	 * 0000 0011 0000 0001
	 *
	 * @param event
	 */
	public PhoneLineRingEvent(int event) {
		super(event, SystemEventType.PHONE_LINE_RING);
	}
}
