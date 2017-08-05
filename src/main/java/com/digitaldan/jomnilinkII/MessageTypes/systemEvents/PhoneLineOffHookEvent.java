package com.digitaldan.jomnilinkII.MessageTypes.systemEvents;

public class PhoneLineOffHookEvent extends SystemEvent {

	/**
	 * PHONE LINE OFF HOOK
	 * 0000 0011 0000 0010
	 *
	 * @param event
	 */
	public PhoneLineOffHookEvent(int event) {
		super(event, SystemEventType.PHONE_LINE_OFF_HOOK);
	}
}
