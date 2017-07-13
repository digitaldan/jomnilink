package com.digitaldan.jomnilinkII.MessageTypes.systemEvents;

public class BatteryOkEvent extends SystemEvent {

	/**
	 * BATTERY OK
	 * 0000 0011 0000 0111
	 *
	 * @param event
	 */
	public BatteryOkEvent(int event) {
		super(event, SystemEventType.BATTERY_OK);
	}
}
