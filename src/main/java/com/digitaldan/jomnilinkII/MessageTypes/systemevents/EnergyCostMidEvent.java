package com.digitaldan.jomnilinkII.MessageTypes.systemevents;

public class EnergyCostMidEvent extends SystemEvent {

	/**
	 * ENERGY COST MID
	 * 0000 0011 0000 1011
	 *
	 * @param event
	 */
	public EnergyCostMidEvent(int event) {
		super(event, SystemEventType.ENERGY_COST_MID);
	}
}
