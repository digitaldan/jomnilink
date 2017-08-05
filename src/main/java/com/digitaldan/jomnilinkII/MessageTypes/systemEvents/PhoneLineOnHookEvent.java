package com.digitaldan.jomnilinkII.MessageTypes.systemEvents;

public class PhoneLineOnHookEvent extends SystemEvent {

	/**
	 * PHONE LINE ON HOOK
	 * 0000 0011 0000 0011
	 *
	 * @param event
	 */
	public PhoneLineOnHookEvent(int event) {
		super(event, SystemEventType.PHONE_LINE_ON_HOOK);
	}
}
