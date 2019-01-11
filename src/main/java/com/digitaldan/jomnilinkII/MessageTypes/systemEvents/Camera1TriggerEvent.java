package com.digitaldan.jomnilinkII.MessageTypes.systemEvents;

public class Camera1TriggerEvent extends SystemEvent {

	/**
	 * CAMERA 1 TRIGGER
	 * 0000 0011 0000 1110
	 *
	 * @param event
	 */
	public Camera1TriggerEvent(int event) {
		super(event, SystemEventType.CAMERA_1_TRIGGER);
	}
}
