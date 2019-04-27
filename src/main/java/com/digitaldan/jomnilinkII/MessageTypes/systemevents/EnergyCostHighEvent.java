package com.digitaldan.jomnilinkII.MessageTypes.systemevents;

public class EnergyCostHighEvent extends SystemEvent {

    /**
     * ENERGY COST HIGH
     * 0000 0011 0000 1100
     */
    public EnergyCostHighEvent(int event) {
        super(event, SystemEventType.ENERGY_COST_HIGH);
    }
}
