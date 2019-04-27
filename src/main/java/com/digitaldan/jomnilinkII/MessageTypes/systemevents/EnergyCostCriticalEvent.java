package com.digitaldan.jomnilinkII.MessageTypes.systemevents;

public class EnergyCostCriticalEvent extends SystemEvent {

    /**
     * ENERGY COST CRITICAL
     * 0000 0011 0000 1101
     */
    public EnergyCostCriticalEvent(int event) {
        super(event, SystemEventType.ENERGY_COST_CRITICAL);
    }
}
