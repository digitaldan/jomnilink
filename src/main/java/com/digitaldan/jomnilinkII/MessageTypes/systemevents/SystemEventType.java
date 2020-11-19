/**
* Copyright (c) 2009-2020 Dan Cunningham
*
* This program and the accompanying materials are made available under the
* terms of the Eclipse Public License 2.0 which is available at
* http://www.eclipse.org/legal/epl-2.0
*
* SPDX-License-Identifier: EPL-2.0
*/
package com.digitaldan.jomnilinkII.MessageTypes.systemevents;

public enum SystemEventType {
	BUTTON(SystemEventConstants.BUTTON_TEMPLATE, SystemEventConstants.BUTTON_MASK, ButtonEvent.class), //
	PROLINK_MESSAGE(SystemEventConstants.PROLINK_TEMPLATE, SystemEventConstants.PROLINK_MASK,
			ProLinkMessageEvent.class), //
	CENTRALITE_SWITCH(SystemEventConstants.CENTRALITE_TEMPLATE, SystemEventConstants.CENTRALITE_MASK,
			CentralightSwitchEvent.class), //
	COMPOSE_CODE_RECEIVED(SystemEventConstants.COMPOSE_TEMPLATE, SystemEventConstants.COMPOSE_MASK,
			ComposeCodeReceivedEvent.class), //
	X10_CODE_RECEIVED(SystemEventConstants.X10_TEMPLATE, SystemEventConstants.X10_MASK, X10CodeReceivedEvent.class), //
	UPB_LINK(SystemEventConstants.UPB_LINK_TEMPLATE, SystemEventConstants.UPB_LINK_MASK, UPBLinkEvent.class), //
	ALC_UPB_RADIORA_STARLITE_SWITCH_PRESS(SystemEventConstants.ALC_UPB_RADIORA_STARLIGHT_TEMPLATE,
			SystemEventConstants.ALC_UPB_RADIORA_STARLIGHT_MASK, SwitchPressEvent.class), //
	ALL_ON_OFF(SystemEventConstants.ALL_ON_OFF_TEMPLATE, SystemEventConstants.ALL_ON_OFF_MASK, AllOnOffEvent.class), //
	PHONE_LINE_DEAD(SystemEventConstants.PHONE_LINE_DEAD_TEMPLATE, SystemEventConstants.ALL_MASK,
			PhoneLineDeadEvent.class), //
	PHONE_LINE_RING(SystemEventConstants.PHONE_LINE_RING_TEMPLATE, SystemEventConstants.ALL_MASK,
			PhoneLineRingEvent.class), //
	PHONE_LINE_OFF_HOOK(SystemEventConstants.PHONE_LINE_OFF_HOOK_TEMPLATE, SystemEventConstants.ALL_MASK,
			PhoneLineOffHookEvent.class), //
	PHONE_LINE_ON_HOOK(SystemEventConstants.PHONE_LINE_ON_HOOK_TEMPLATE, SystemEventConstants.ALL_MASK,
			PhoneLineOnHookEvent.class), //
	AC_POWER_OFF(SystemEventConstants.AC_POWER_OFF_TEMPLATE, SystemEventConstants.ALL_MASK, AcPowerOffEvent.class), //
	AC_POWER_RESTORED(SystemEventConstants.AC_POWER_RESTORED_TEMPLATE, SystemEventConstants.ALL_MASK,
			AcPowerRestoredEvent.class), //
	BATTERY_LOW(SystemEventConstants.BATTERY_LOW_TEMPLATE, SystemEventConstants.ALL_MASK, BatteryLowEvent.class), //
	BATTERY_OK(SystemEventConstants.BATTERY_OK_TEMPLATE, SystemEventConstants.ALL_MASK, BatteryOkEvent.class), //
	DCM_TROUBLE(SystemEventConstants.DCM_TROUBLE_TEMPLATE, SystemEventConstants.ALL_MASK, DCMTroubleEvent.class), //
	DCM_OK(SystemEventConstants.DCM_OK_TEMPLATE, SystemEventConstants.ALL_MASK, DCMOkEvent.class), //
	ENERGY_COST_LOW(SystemEventConstants.ENERGY_COST_LOW_TEMPLATE, SystemEventConstants.ALL_MASK,
			EnergyCostLowEvent.class), //
	ENERGY_COST_MID(SystemEventConstants.ENERGY_COST_MEDIUM_TEMPLATE, SystemEventConstants.ALL_MASK,
			EnergyCostMidEvent.class), //
	ENERGY_COST_HIGH(SystemEventConstants.ENERGY_COST_HIGH_TEMPLATE, SystemEventConstants.ALL_MASK,
			EnergyCostHighEvent.class), //
	ENERGY_COST_CRITICAL(SystemEventConstants.ENERGY_COST_CRITICAL_TEMPLATE, SystemEventConstants.ALL_MASK,
			EnergyCostCriticalEvent.class), //
	CAMERA_1_TRIGGER(SystemEventConstants.CAMERA_TRIGGER_1_TEMPLATE, SystemEventConstants.ALL_MASK,
			Camera1TriggerEvent.class), //
	CAMERA_2_TRIGGER(SystemEventConstants.CAMERA_TRIGGER_2_TEMPLATE, SystemEventConstants.ALL_MASK,
			Camera2TriggerEvent.class), //
	CAMERA_3_TRIGGER(SystemEventConstants.CAMERA_TRIGGER_3_TEMPLATE, SystemEventConstants.ALL_MASK,
			Camera3TriggerEvent.class), //
	CAMERA_4_TRIGGER(SystemEventConstants.CAMERA_TRIGGER_4_TEMPLATE, SystemEventConstants.ALL_MASK,
			Camera4TriggerEvent.class), //
	CAMERA_5_TRIGGER(SystemEventConstants.CAMERA_TRIGGER_5_TEMPLATE, SystemEventConstants.ALL_MASK,
			Camera5TriggerEvent.class), //
	CAMERA_6_TRIGGER(SystemEventConstants.CAMERA_TRIGGER_6_TEMPLATE, SystemEventConstants.ALL_MASK,
			Camera6TriggerEvent.class);

	private final int bitTemplate;
	private final int bitMask;
	private final Class<?> systemEventClass;

	private SystemEventType(int bitTemplate, int bitMask, Class<?> systemEventClass) {
		this.bitTemplate = bitTemplate;
		this.bitMask = bitMask;
		this.systemEventClass = systemEventClass;
	}

	public Class<?> getSystemEventClass() {
		return systemEventClass;
	}

	public static SystemEventType fromInt(int number) {
		for (SystemEventType st : SystemEventType.values()) {
			if ((number & st.bitMask) == st.bitTemplate) {
				return st;
			}
		}
		return null;
	}
}
