package com.digitaldan.jomnilinkII.MessageTypes.systemEvents;

public class LuminaAlarmEvent extends SystemEvent {

	/**
	 * ALARM (OMNI FAMILY)
	 * 0000 0010 tttt 0001
	 * 	t= alarm type
	 *  5 = freeze
	 *	6 = water
	 *	8 = temperature
	 *
	 * @param event
	 */
	public LuminaAlarmEvent(int event) {
		super(event, SystemEventType.ALARM_LUMINA);
	}

	public int getAlarmType() {
		// 0x0F = 15 = 1111
		return event >> 4 & 0x0f;
	}
}
