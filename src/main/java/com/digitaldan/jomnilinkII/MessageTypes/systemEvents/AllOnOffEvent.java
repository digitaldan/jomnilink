package com.digitaldan.jomnilinkII.MessageTypes.systemEvents;

public class AllOnOffEvent extends SystemEvent {

	/**
	 * ALL ON/OFF
	 * 0000 0011 111s aaaa
	 * s= state
	 *   0 = off
	 *   1 = on
	 * a = area
	 *
	 * @param event
	 */
	public AllOnOffEvent(int event) {
		super(event, SystemEventType.ALL_ON_OFF);
	}

	public boolean isOn() {
		return (event >> 4 & 0x01) == 1;
	}

	public int getArea() {
		return event & 0x0f;
	}
}
