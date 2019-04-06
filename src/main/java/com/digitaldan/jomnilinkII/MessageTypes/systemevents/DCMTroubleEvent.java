package com.digitaldan.jomnilinkII.MessageTypes.systemevents;

public class DCMTroubleEvent extends SystemEvent {

	/**
	 * DCM TROUBLE
	 * 0000 0011 0000 1000
	 *
	 * @param event
	 */
	public DCMTroubleEvent(int event) {
		super(event, SystemEventType.DCM_TROUBLE);
	}
}
