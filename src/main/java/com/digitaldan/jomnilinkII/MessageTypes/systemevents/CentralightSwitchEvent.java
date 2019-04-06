package com.digitaldan.jomnilinkII.MessageTypes.systemevents;

public class CentralightSwitchEvent extends SystemEvent {

	/**
	 * PCENTRALITE SWITCH
	 * 0000 0001 1sss ssss
	 * s= switch number
	 *
	 * @param event
	 */

	public CentralightSwitchEvent(int event) {
		super(event, SystemEventType.CENTRALITE_SWITCH);
	}

	public int getSwitchNumber() {
		//1111111 = 127 = 0x7f
		return event & 0x7f;
	}
}
