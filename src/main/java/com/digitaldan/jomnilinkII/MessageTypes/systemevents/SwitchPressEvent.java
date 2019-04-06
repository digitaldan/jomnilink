package com.digitaldan.jomnilinkII.MessageTypes.systemevents;

public class SwitchPressEvent extends SystemEvent {

	/**
	 * ALC, UPB, RADIO RA,
	 * OR STARLITE
	 * SWITCH PRESS
	 * 1111 ssss uuuu uuuu
	 * s= switch 0=off
	 *	1=on
	 *  2-11 = switch 1-10
	 * u = unitTemplate number
	 *
	 * @param event
	 */
	public SwitchPressEvent(int event) {
		super(event, SystemEventType.ALC_UPB_RADIORA_STARLITE_SWITCH_PRESS);
	}

	public int getSwitchValue() {
		return event >> 8 & 0x0f;
	}

	public int getUnitNumber() {
		return event & 0xff;
	}

}
