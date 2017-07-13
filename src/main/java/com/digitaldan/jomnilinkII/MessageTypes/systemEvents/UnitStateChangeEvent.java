package com.digitaldan.jomnilinkII.MessageTypes.systemEvents;

public class UnitStateChangeEvent extends SystemEvent {

	/**
	 * UNIT STATE CHANGE
	 * 0000 10su uuuu uuuu
	 * 	s= state
	 *  0 = off
	 *	1 = on
	 *  u = unitTemplate number
	 *
	 * @param event
	 */
	public UnitStateChangeEvent(int event) {
		super(event, SystemEventType.ZONE_STATE_CHANGE);
	}

	public int getUnitState() {
		return event >> 9 & 0x01;
	}

	public int getUnitNumber() {
		//511 = 0x1FF = 111111111
		return event & 0x1FF;
	}
}
