package com.digitaldan.jomnilinkII.MessageTypes.systemEvents;

public class ProLinkMessageEvent extends SystemEvent {

	/**
	 * PRO-LINK MESSAGE
	 * 0000 0001 0mmm mmmm
	 * m= message number
	 *
	 * @param event
	 */

	public ProLinkMessageEvent(int event) {
		super(event, SystemEventType.PROLINK_MESSAGE);
	}

	public int getMessageNumber() {
		//1111111 = 127 = 0x7f
		return event & 0x7f;
	}
}
