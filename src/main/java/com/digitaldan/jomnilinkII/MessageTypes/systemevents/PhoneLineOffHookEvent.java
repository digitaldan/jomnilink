package com.digitaldan.jomnilinkII.MessageTypes.systemevents;

public class PhoneLineOffHookEvent extends SystemEvent {

    /**
     * PHONE LINE OFF HOOK
     * 0000 0011 0000 0010
     */
    public PhoneLineOffHookEvent(int event) {
        super(event, SystemEventType.PHONE_LINE_OFF_HOOK);
    }
}
