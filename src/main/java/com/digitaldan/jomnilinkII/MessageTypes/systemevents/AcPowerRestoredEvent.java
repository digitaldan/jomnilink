package com.digitaldan.jomnilinkII.MessageTypes.systemevents;

public class AcPowerRestoredEvent extends SystemEvent {

	/**
	 * AC POWER OFF
	 * 0000 0011 0000 0100
	 *
	 * @param event
	 */
	public AcPowerRestoredEvent(int event) {
		super(event, SystemEventType.AC_POWER_RESTORED);
	}
}
