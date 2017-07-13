package com.digitaldan.jomnilinkII.MessageTypes.systemEvents;

public class AcPowerOffEvent extends SystemEvent {

	/**
	 * AC POWER OFF
	 * 0000 0011 0000 0100
	 *
	 * @param event
	 */
	public AcPowerOffEvent(int event) {
		super(event, SystemEventType.AC_POWER_OFF);
	}
}
