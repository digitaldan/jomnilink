package com.digitaldan.jomnilinkII.MessageTypes.systemevents;

public class EnergyCostLowEvent extends SystemEvent {

	/**
	 * ENERGY COST LOW
	 * 0000 0011 0000 1010
	 *
	 * @param event
	 */
	public EnergyCostLowEvent(int event) {
		super(event, SystemEventType.ENERGY_COST_LOW);
	}
}
