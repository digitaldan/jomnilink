package com.digitaldan.jomnilinkII.MessageTypes.systemevents;

public class Camera4TriggerEvent extends SystemEvent {

	/**
	 * CAMERA 4 TRIGGER
	 * 0000 0011 0001 0001
	 *
	 * @param event
	 */
	public Camera4TriggerEvent(int event) {
		super(event, SystemEventType.CAMERA_4_TRIGGER);
	}
}
