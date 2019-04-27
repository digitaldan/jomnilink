package com.digitaldan.jomnilinkII.MessageTypes.systemevents;

public class BatteryOkEvent extends SystemEvent {

    /**
     * BATTERY OK
     * 0000 0011 0000 0111
     */
    public BatteryOkEvent(int event) {
        super(event, SystemEventType.BATTERY_OK);
    }
}
