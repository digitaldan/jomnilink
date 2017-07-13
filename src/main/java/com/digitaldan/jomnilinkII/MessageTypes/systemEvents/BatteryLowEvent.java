package com.digitaldan.jomnilinkII.MessageTypes.systemEvents;

public class BatteryLowEvent extends SystemEvent {

	/**
	 * BATTERY LOW
	 * 0000 0011 0000 0110
	 *
	 * @param event
	 */
	public BatteryLowEvent(int event) {
		super(event, SystemEventType.BATTERY_LOW);
	}
}
