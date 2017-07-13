package com.digitaldan.jomnilinkII.MessageTypes.systemEvents;

public class OmniAlarmEvent extends SystemEvent {

	/**
	 * ALARM (OMNI FAMILY)
	 * 0000 0010 tttt aaaa
	 * t= alarm type
		1 = burglary
		2 = fire
		3 = gas
		4 = auxiliary
		5 = freeze
		6 = water
		7 = duress
		8 = temperature
		a = area
	 *
	 * @param event
	 */
	public OmniAlarmEvent(int event) {
		super(event, SystemEventType.ALARM_OMNI);
	}

	public int getArea() {
		// 0x0F = 15 = 1111
		return event & 0x0f;
	}

	public int getAlarmType() {
		// 0x0F = 15 = 1111
		return event >> 4 & 0x0f;
	}
}
