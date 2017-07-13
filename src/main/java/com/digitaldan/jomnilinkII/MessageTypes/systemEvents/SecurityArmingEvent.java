package com.digitaldan.jomnilinkII.MessageTypes.systemEvents;

public class SecurityArmingEvent extends SystemEvent {

	/**
	 * SECURITY ARMING
	 * dmmm aaaa cccc cccc
	 * d= exit delay flag 0 = end of delay
	 *     1 = start of delay
	 *     must be 1 for off
	 * m = security mode
	 *     0 = off
	 *     1 = day
	 *     2 = night
	 *     3 = away
	 *     4 = vacation
	 *     5 = day instant
	 *     6 = night delayed
	 * a = area
	 * c = code
	 * @param event
	 */
	public SecurityArmingEvent(int event) {
		super(event, SystemEventType.SECURITY_ARMING);
	}

	public int getArmingDelay() {
		return event >> 15 & 0x01;
	}

	public int getSecurityMode() {
		return event >> 12 & 0x07;
	}

	public int getAreaNumber() {
		return event >> 8 & 0x0f;
	}

	public int getCode() {
		return event >> 4 & 0x0f;
	}
}
