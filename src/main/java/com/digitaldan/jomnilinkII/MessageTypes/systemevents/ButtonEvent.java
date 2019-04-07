package com.digitaldan.jomnilinkII.MessageTypes.systemevents;

public class ButtonEvent extends SystemEvent {

	/**
	 * USER MACRO BUTTON
	 * 0000 0000 bbbb bbbb
	 * b= buttonTemplate number
	 *
	 * @param event
	 */
	public ButtonEvent(int event) {
		super(event, SystemEventType.BUTTON);
	}

	public int getButtonNumber() {
		return event;
	}
}
