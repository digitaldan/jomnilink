package com.digitaldan.jomnilinkII.MessageTypes.systemEvents;

public enum SystemEventType {
	BUTTON(SystemEventConstants.buttonTemplate, SystemEventConstants.buttonMask, ButtonEvent.class), //
	PROLINK_MESSAGE(SystemEventConstants.prolinkTemplate, SystemEventConstants.prolinkMask, ProLinkMessageEvent.class), //
	CENTRALITE_SWITCH(SystemEventConstants.centraliteTemplate, SystemEventConstants.centraliteMask,
			CentralightSwitchEvent.class), //
	ALARM_OMNI(SystemEventConstants.omnialarmTemplate, SystemEventConstants.omnialarmMask, OmniAlarmEvent.class), //
	ALARM_LUMINA(SystemEventConstants.luminaAlarmTemplate, SystemEventConstants.luminaAlarmMask,
			LuminaAlarmEvent.class), //
	ZONE_STATE_CHANGE(SystemEventConstants.zoneTemplate, SystemEventConstants.zoneMask, ZoneStateChangeEvent.class), //
	UNIT_STATE_CHANGE(SystemEventConstants.unitTemplate, SystemEventConstants.unitMask, UnitStateChangeEvent.class), //
	COMPOSE_CODE_RECEIVED(SystemEventConstants.composeTemplate, SystemEventConstants.composeMask,
			ComposeCodeReceivedEvent.class), //
	X10_CODE_RECEIVED(SystemEventConstants.x10Template, SystemEventConstants.x10Mask, X10CodeReceivedEvent.class), //
	LUMINA_MODE_CHANGE(SystemEventConstants.luminaModeChangeTemplate, SystemEventConstants.luminaModeChangeMask,
			LuminaModeChangeEvent.class), //
	ALC_UPB_RADIORA_STARLITE_SWITCH_PRESS(SystemEventConstants.alcUpbRadioraStarlightTemplate,
			SystemEventConstants.alcUpbRadioraStarlightMask, SwitchPressEvent.class), //
	UPB_LINK(SystemEventConstants.upbLinkTemplate, SystemEventConstants.upbLinkMask, UPBLinkEvent.class), //
	ALL_ON_OFF(SystemEventConstants.allOnOffTemplate, SystemEventConstants.allOnOffMask, AllOnOffEvent.class), //
	ACCESS_CONTROL_READER(SystemEventConstants.accessControlReaderTemplate,
			SystemEventConstants.accessControlReaderMask, AccessControlReaderEvent.class), //
	PHONE_LINE_DEAD(SystemEventConstants.phoneLineDeadTemplate, SystemEventConstants.allMask, PhoneLineDeadEvent.class), //
	PHONE_LINE_RING(SystemEventConstants.phoneLineRingTemplate, SystemEventConstants.allMask, PhoneLineRingEvent.class), //
	PHONE_LINE_OFF_HOOK(SystemEventConstants.phoneLineOffHookTemplate, SystemEventConstants.allMask,
			PhoneLineOffHookEvent.class), //
	PHONE_LINE_ON_HOOK(SystemEventConstants.phoneLineOnHookTemplate, SystemEventConstants.allMask,
			PhoneLineOnHookEvent.class), //
	AC_POWER_OFF(SystemEventConstants.acPowerOffTemplate, SystemEventConstants.allMask, AcPowerOffEvent.class), //
	AC_POWER_RESTORED(SystemEventConstants.acPowerRestoredTemplate, SystemEventConstants.allMask,
			AcPowerRestoredEvent.class), //
	BATTERY_LOW(SystemEventConstants.batteryLowTemplate, SystemEventConstants.allMask, BatteryLowEvent.class), //
	BATTERY_OK(SystemEventConstants.batteryOkTemplate, SystemEventConstants.allMask, BatteryOkEvent.class), //
	DCM_TROUBLE(SystemEventConstants.dcmTroubleTemplate, SystemEventConstants.allMask, DCMTroubleEvent.class), //
	DCM_OK(SystemEventConstants.dcmOkTemplate, SystemEventConstants.allMask, DCMOkEvent.class), //
	ENERGY_COST_LOW(SystemEventConstants.energyCostLowTemplate, SystemEventConstants.allMask, EnergyCostLowEvent.class), //
	ENERGY_COST_MID(SystemEventConstants.energyCostMediumTemplate, SystemEventConstants.allMask,
			EnergyCostMidEvent.class), //
	ENERGY_COST_HIGH(SystemEventConstants.energyCostHighTemplate, SystemEventConstants.allMask,
			EnergyCostHighEvent.class), //
	ENERGY_COST_CRITICAL(SystemEventConstants.energyCostCriticalTemplate, SystemEventConstants.allMask,
			EnergyCostCriticalEvent.class), //
	CAMERA_1_TRIGGER(SystemEventConstants.cameraTrigger1Template, SystemEventConstants.allMask,
			Camera1TriggerEvent.class), //
	CAMERA_2_TRIGGER(SystemEventConstants.cameraTrigger2Template, SystemEventConstants.allMask,
			Camera2TriggerEvent.class), //
	CAMERA_3_TRIGGER(SystemEventConstants.cameraTrigger3Template, SystemEventConstants.allMask,
			Camera3TriggerEvent.class), //
	CAMERA_4_TRIGGER(SystemEventConstants.cameraTrigger4Template, SystemEventConstants.allMask,
			Camera4TriggerEvent.class), //
	CAMERA_5_TRIGGER(SystemEventConstants.cameraTrigger5Template, SystemEventConstants.allMask,
			Camera5TriggerEvent.class), //
	CAMERA_6_TRIGGER(SystemEventConstants.cameraTrigger6Template, SystemEventConstants.allMask,
			Camera6TriggerEvent.class), //
	SECURITY_ARMING(SystemEventConstants.securityArmingTemplate, SystemEventConstants.securityArmingMask,
			SecurityArmingEvent.class);

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
