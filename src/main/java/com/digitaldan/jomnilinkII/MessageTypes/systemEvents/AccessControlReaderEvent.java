package com.digitaldan.jomnilinkII.MessageTypes.systemEvents;

public class AccessControlReaderEvent extends SystemEvent {

	/**
	 * ACCESS CONTROL READER
	 * 0000 0011 1101 rrrr
	 * r = reader
	 *  0-15 = reader 1-16
	 *
	 * @param event
	 */
	public AccessControlReaderEvent(int event) {
		super(event, SystemEventType.ACCESS_CONTROL_READER);
	}

	public int getReaderNumber() {
		return event & 0x0f;
	}
}
