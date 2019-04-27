package com.digitaldan.jomnilinkII.MessageTypes.systemevents;

public class PhoneLineDeadEvent extends SystemEvent {

    /**
     * PHONE LINE DEAD
     * 0000 0011 0000 0000
     */
    public PhoneLineDeadEvent(int event) {
        super(event, SystemEventType.PHONE_LINE_DEAD);
    }
}
