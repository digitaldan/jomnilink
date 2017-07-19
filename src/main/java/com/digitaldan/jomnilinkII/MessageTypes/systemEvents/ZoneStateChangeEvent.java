package com.digitaldan.jomnilinkII.MessageTypes.systemEvents;

public class ZoneStateChangeEvent extends SystemEvent {

	/**
	 * ZONE STATE CHANGE
	 * 0000 01sz zzzz zzzz
	 * 	s= state
	 *	0 = off
	 *	1 = on
	 *	z = zoneTemplate number
	 *
	 * @param event
	 */
	public ZoneStateChangeEvent(int event) {
		super(event, SystemEventType.ZONE_STATE_CHANGE);
	}

	public int getZoneState() {
		return event >> 9 & 0x01;
	}

	public boolean isOn() {
		return getZoneState() > 0;
	}

	public int getZoneNumber() {
		//511 = 0x1FF = 111111111
		return event & 0x1FF;
	}
}
