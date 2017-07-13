package com.digitaldan.jomnilinkII.MessageTypes.systemEvents;

public class X10CodeReceivedEvent extends SystemEvent {

	/**
	 * X-10 CODE RECEIVED
	 * 0000 11sa hhhh uuuu
	 * s= state
	 * 0 = off
	 *     1 = on
	 * a = all units flag
	 *     0 = one unitTemplate only
	 *     1 = all on/off
	 * h = X-10 house code
	 *     0-15 = A-P
	 * u = X-10 unitTemplate number
	 *     0-15 = 1-16
	 *
	 * @param event
	 */
	public X10CodeReceivedEvent(int event) {
		super(event, SystemEventType.X10_CODE_RECEIVED);
	}

	public int getState() {
		return event >> 9 & 0x01;
	}

	public int getAllUnitsFlag() {
		return event >> 8 & 0x01;
	}

	public int getHouseCode() {
		return event >> 4 & 0x0f;
	}
}
