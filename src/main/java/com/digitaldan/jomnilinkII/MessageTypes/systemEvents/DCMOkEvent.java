package com.digitaldan.jomnilinkII.MessageTypes.systemEvents;

public class DCMOkEvent extends SystemEvent {

	/**
	 * DCM OK
	 * 0000 0011 0000 1001
	 *
	 * @param event
	 */
	public DCMOkEvent(int event) {
		super(event, SystemEventType.DCM_OK);
	}
}
