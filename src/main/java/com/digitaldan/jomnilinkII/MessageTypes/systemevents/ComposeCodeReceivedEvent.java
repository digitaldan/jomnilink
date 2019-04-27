package com.digitaldan.jomnilinkII.MessageTypes.systemevents;

public class ComposeCodeReceivedEvent extends SystemEvent {

	/**
	 * COMPOSE CODE RECEIVED
	 * 0111 ssss hhhh uuuu
	 * s= state
	 * 0 = off
	 * 1 = on
	 *     2-13 = scene A-L
	 * h = Compose house code
	 *     0-15 = A-P
	 * u = Compose unitTemplate number
	 * 	   0-15 = 1-16
	 */
	public ComposeCodeReceivedEvent(int event) {
		super(event, SystemEventType.COMPOSE_CODE_RECEIVED);
	}

	public int getComposeState() {
		// 0x0F = 15 = 1111
		return event & 0x0f;
	}

	public int getComposeHouseCode() {
		// 0x0F = 15 = 1111
		return event >> 4 & 0x0f;
	}

	public int getComposeUnitNumber() {
		// 0x0F = 15 = 1111
		return event >> 8 & 0x0f;
	}
}
