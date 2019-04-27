package com.digitaldan.jomnilinkII.MessageTypes.systemevents;

public class PhoneLineOnHookEvent extends SystemEvent {

    /**
     * PHONE LINE ON HOOK
     * 0000 0011 0000 0011
     */
    public PhoneLineOnHookEvent(int event) {
        super(event, SystemEventType.PHONE_LINE_ON_HOOK);
    }
}
