package com.digitaldan.jomnilinkII.MessageTypes.systemEvents;

public class EnergyCostHighEvent extends SystemEvent {

	/**
	 * ENERGY COST HIGH
	 * 0000 0011 0000 1100
	 *
	 * @param event
	 */
	public EnergyCostHighEvent(int event) {
		super(event, SystemEventType.ENERGY_COST_HIGH);
	}
}
