package com.digitaldan.jomnilinkII.MessageTypes.systemEvents;

public class Camera2TriggerEvent extends SystemEvent {

	/**
	 * CAMERA 2 TRIGGER
	 * 0000 0011 0000 1111
	 *
	 * @param event
	 */
	public Camera2TriggerEvent(int event) {
		super(event, SystemEventType.CAMERA_2_TRIGGER);
	}
}
