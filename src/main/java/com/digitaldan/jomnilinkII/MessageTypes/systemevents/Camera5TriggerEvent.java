package com.digitaldan.jomnilinkII.MessageTypes.systemevents;

public class Camera5TriggerEvent extends SystemEvent {

	/**
	 * CAMERA 5 TRIGGER
	 * 0000 0011 0001 0010
	 *
	 * @param event
	 */
	public Camera5TriggerEvent(int event) {
		super(event, SystemEventType.CAMERA_5_TRIGGER);
	}
}
