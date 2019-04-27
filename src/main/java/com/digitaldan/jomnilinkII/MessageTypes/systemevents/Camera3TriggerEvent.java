package com.digitaldan.jomnilinkII.MessageTypes.systemevents;

public class Camera3TriggerEvent extends SystemEvent {

    /**
     * CAMERA 3 TRIGGER
     * 0000 0011 0001 0000
     */
    public Camera3TriggerEvent(int event) {
        super(event, SystemEventType.CAMERA_3_TRIGGER);
    }
}
