package com.digitaldan.jomnilinkII.MessageTypes.systemevents;

public class AcPowerOffEvent extends SystemEvent {

    /**
     * AC POWER OFF
     * 0000 0011 0000 0100
     */
    public AcPowerOffEvent(int event) {
        super(event, SystemEventType.AC_POWER_OFF);
    }
}
