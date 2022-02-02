/**
 * Copyright (c) 2009-2022 Dan Cunningham
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package com.digitaldan.jomnilinkII.MessageTypes.systemevents;

import static com.digitaldan.jomnilinkII.MessageTypes.systemevents.SystemEventConstants.*;

public enum SystemEventType {
	// @formatter:off
	BUTTON(BUTTON_TEMPLATE, BUTTON_MASK, ButtonEvent.class),
	PROLINK_MESSAGE(PROLINK_TEMPLATE, PROLINK_MASK, ProLinkMessageEvent.class),
	CENTRALITE_SWITCH(CENTRALITE_TEMPLATE, CENTRALITE_MASK, CentralightSwitchEvent.class),
	COMPOSE_CODE_RECEIVED(COMPOSE_TEMPLATE, COMPOSE_MASK, ComposeCodeReceivedEvent.class),
	X10_CODE_RECEIVED(X10_TEMPLATE, X10_MASK, X10CodeReceivedEvent.class),
	UPB_LINK(UPB_LINK_TEMPLATE, UPB_LINK_MASK, UPBLinkEvent.class),
	ALC_UPB_RADIORA_STARLITE_SWITCH_PRESS(ALC_UPB_RADIORA_STARLIGHT_TEMPLATE, ALC_UPB_RADIORA_STARLIGHT_MASK, SwitchPressEvent.class),
	ALL_ON_OFF(ALL_ON_OFF_TEMPLATE, ALL_ON_OFF_MASK, AllOnOffEvent.class),
	PHONE_LINE_DEAD(PHONE_LINE_DEAD_TEMPLATE, ALL_MASK, PhoneLineDeadEvent.class),
	PHONE_LINE_RING(PHONE_LINE_RING_TEMPLATE, ALL_MASK, PhoneLineRingEvent.class),
	PHONE_LINE_OFF_HOOK(PHONE_LINE_OFF_HOOK_TEMPLATE, ALL_MASK, PhoneLineOffHookEvent.class),
	PHONE_LINE_ON_HOOK(PHONE_LINE_ON_HOOK_TEMPLATE, ALL_MASK, PhoneLineOnHookEvent.class),
	AC_POWER_OFF(AC_POWER_OFF_TEMPLATE, ALL_MASK, AcPowerOffEvent.class),
	AC_POWER_RESTORED(AC_POWER_RESTORED_TEMPLATE, ALL_MASK, AcPowerRestoredEvent.class),
	BATTERY_LOW(BATTERY_LOW_TEMPLATE, ALL_MASK, BatteryLowEvent.class),
	BATTERY_OK(BATTERY_OK_TEMPLATE, ALL_MASK, BatteryOkEvent.class),
	DCM_TROUBLE(DCM_TROUBLE_TEMPLATE, ALL_MASK, DCMTroubleEvent.class),
	DCM_OK(DCM_OK_TEMPLATE, ALL_MASK, DCMOkEvent.class),
	ENERGY_COST_LOW(ENERGY_COST_LOW_TEMPLATE, ALL_MASK, EnergyCostLowEvent.class),
	ENERGY_COST_MID(ENERGY_COST_MEDIUM_TEMPLATE, ALL_MASK, EnergyCostMidEvent.class),
	ENERGY_COST_HIGH(ENERGY_COST_HIGH_TEMPLATE, ALL_MASK, EnergyCostHighEvent.class),
	ENERGY_COST_CRITICAL(ENERGY_COST_CRITICAL_TEMPLATE, ALL_MASK, EnergyCostCriticalEvent.class),
	CAMERA_1_TRIGGER(CAMERA_TRIGGER_1_TEMPLATE, ALL_MASK, Camera1TriggerEvent.class),
	CAMERA_2_TRIGGER(CAMERA_TRIGGER_2_TEMPLATE, ALL_MASK, Camera2TriggerEvent.class),
	CAMERA_3_TRIGGER(CAMERA_TRIGGER_3_TEMPLATE, ALL_MASK, Camera3TriggerEvent.class),
	CAMERA_4_TRIGGER(CAMERA_TRIGGER_4_TEMPLATE, ALL_MASK, Camera4TriggerEvent.class),
	CAMERA_5_TRIGGER(CAMERA_TRIGGER_5_TEMPLATE, ALL_MASK, Camera5TriggerEvent.class),
	CAMERA_6_TRIGGER(CAMERA_TRIGGER_6_TEMPLATE, ALL_MASK, Camera6TriggerEvent.class);
	// @formatter:on

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
