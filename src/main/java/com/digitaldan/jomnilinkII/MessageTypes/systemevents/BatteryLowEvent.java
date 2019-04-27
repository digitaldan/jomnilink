package com.digitaldan.jomnilinkII.MessageTypes.systemevents;

public class BatteryLowEvent extends SystemEvent {

    /**
     * BATTERY LOW
     * 0000 0011 0000 0110
     */
    public BatteryLowEvent(int event) {
        super(event, SystemEventType.BATTERY_LOW);
    }
}
