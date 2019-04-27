package com.digitaldan.jomnilinkII.MessageTypes.systemevents;

public class AllOnOffEvent extends SystemEvent {

	/**
	 * ALL ON/OFF
	 * 0000 0011 111s aaaa
	 * s= state
	 *   0 = off
	 *   1 = on
	 * a = area
	 */
	public AllOnOffEvent(int event) {
		super(event, SystemEventType.ALL_ON_OFF);
	}

	public int getAllOnOffState() {
		return event >> 4 & 0x01;
	}

	public boolean isOn() {
		return getAllOnOffState() > 0;
	}

	public int getArea() {
		return event & 0x0f;
	}
}
