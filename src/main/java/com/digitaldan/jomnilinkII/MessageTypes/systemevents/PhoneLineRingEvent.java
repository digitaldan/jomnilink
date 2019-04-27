package com.digitaldan.jomnilinkII.MessageTypes.systemevents;

public class PhoneLineRingEvent extends SystemEvent {

    /**
     * PHONE LINE RING
     * 0000 0011 0000 0001
     */
    public PhoneLineRingEvent(int event) {
        super(event, SystemEventType.PHONE_LINE_RING);
    }
}
