package com.digitaldan.jomnilinkII.MessageTypes.systemEvents;

public enum SystemEventType {
	BUTTON(SystemEventConstants.BUTTONTEMPLATE, SystemEventConstants.BUTTONMASK, ButtonEvent.class), //
	PROLINK_MESSAGE(SystemEventConstants.PROLINKTEMPLATE, SystemEventConstants.PROLINKMASK, ProLinkMessageEvent.class), //
	CENTRALITE_SWITCH(SystemEventConstants.CENTRALITETEMPLATE, SystemEventConstants.CENTRALITEMASK,
			CentralightSwitchEvent.class), //
	ALARM_OMNI(SystemEventConstants.OMNIALARMTEMPLATE, SystemEventConstants.OMNIALARMMASK, OmniAlarmEvent.class), //
	ALARM_LUMINA(SystemEventConstants.LUMINAALARMTEMPLATE, SystemEventConstants.LUMINAALARMMASK,
			LuminaAlarmEvent.class), //
	ZONE_STATE_CHANGE(SystemEventConstants.ZONETEMPLATE, SystemEventConstants.ZONEMASK, ZoneStateChangeEvent.class), //
	UNIT_STATE_CHANGE(SystemEventConstants.UNITTEMPLATE, SystemEventConstants.UNITMASK, UnitStateChangeEvent.class), //
	COMPOSE_CODE_RECEIVED(SystemEventConstants.COMPOSETEMPLATE, SystemEventConstants.COMPOSEMASK,
			ComposeCodeReceivedEvent.class), //
	X10_CODE_RECEIVED(SystemEventConstants.X10TEMPLATE, SystemEventConstants.X10MASK, X10CodeReceivedEvent.class), //
	LUMINA_MODE_CHANGE(SystemEventConstants.LUMINAMODECHANGETEMPLATE, SystemEventConstants.LUMINAMODECHANGEMASK,
			LuminaModeChangeEvent.class), //
	ALC_UPB_RADIORA_STARLITE_SWITCH_PRESS(SystemEventConstants.ACCESSCONTROLREADERTEMPLATE,
			SystemEventConstants.ALCUPBRADIORASTARLIGHTMASK, SwitchPressEvent.class), //
	UPB_LINK(SystemEventConstants.UPBLINKTEMPLATE, SystemEventConstants.UPBLINKMASK, UPBLinkEvent.class), //
	ALL_ON_OFF(SystemEventConstants.ALLONOFFTEMPLATE, SystemEventConstants.ALLONOFFMASK, AllOnOffEvent.class), //
	ACCESS_CONTROL_READER(SystemEventConstants.ACCESSCONTROLREADERTEMPLATE,
			SystemEventConstants.ACCESSCONTROLREADERMASK, AccessControlReaderEvent.class), //
	PHONE_LINE_DEAD(SystemEventConstants.PHONELINEDEADTEMPLATE, SystemEventConstants.ALLMASK, PhoneLineDeadEvent.class), //
	PHONE_LINE_RING(SystemEventConstants.PHONELINERINGTEMPLATE, SystemEventConstants.ALLMASK, PhoneLineRingEvent.class), //
	PHONE_LINE_OFF_HOOK(SystemEventConstants.PHONELINEOFFHOOKTEMPLATE, SystemEventConstants.ALLMASK,
			PhoneLineOffHookEvent.class), //
	PHONE_LINE_ON_HOOK(SystemEventConstants.PHONELINEONHOOKTEMPLATE, SystemEventConstants.ALLMASK,
			PhoneLineOnHookEvent.class), //
	AC_POWER_OFF(SystemEventConstants.ACPOWEROFFTEMPLATE, SystemEventConstants.ALLMASK, AcPowerOffEvent.class), //
	AC_POWER_RESTORED(SystemEventConstants.ACPOWERRESTOREDTEMPLATE, SystemEventConstants.ALLMASK,
			AcPowerRestoredEvent.class), //
	BATTERY_LOW(SystemEventConstants.BATTERYLOWTEMPLATE, SystemEventConstants.ALLMASK, BatteryLowEvent.class), //
	BATTERY_OK(SystemEventConstants.BATTERYOKTEMPLATE, SystemEventConstants.ALLMASK, BatteryOkEvent.class), //
	DCM_TROUBLE(SystemEventConstants.DCMTROUBLETEMPLATE, SystemEventConstants.ALLMASK, DCMTroubleEvent.class), //
	DCM_OK(SystemEventConstants.DCMOKTEMPLATE, SystemEventConstants.ALLMASK, DCMOkEvent.class), //
	ENERGY_COST_LOW(SystemEventConstants.ENERGYCOSTLOWTEMPLATE, SystemEventConstants.ALLMASK, EnergyCostLowEvent.class), //
	ENERGY_COST_MID(SystemEventConstants.ENERGYCOSTMEDIUMTEMPLATE, SystemEventConstants.ALLMASK,
			EnergyCostMidEvent.class), //
	ENERGY_COST_HIGH(SystemEventConstants.ENERGYCOSTHIGHTEMPLATE, SystemEventConstants.ALLMASK,
			EnergyCostHighEvent.class), //
	ENERGY_COST_CRITICAL(SystemEventConstants.ENERGYCOSTCRITICALTEMPLATE, SystemEventConstants.ALLMASK,
			EnergyCostCriticalEvent.class), //
	CAMERA_1_TRIGGER(SystemEventConstants.CAMERATRIGGER1TEMPLATE, SystemEventConstants.ALLMASK,
			Camera1TriggerEvent.class), //
	CAMERA_2_TRIGGER(SystemEventConstants.CAMERATRIGGER2TEMPLATE, SystemEventConstants.ALLMASK,
			Camera2TriggerEvent.class), //
	CAMERA_3_TRIGGER(SystemEventConstants.CAMERATRIGGER3TEMPLATE, SystemEventConstants.ALLMASK,
			Camera3TriggerEvent.class), //
	CAMERA_4_TRIGGER(SystemEventConstants.CAMERATRIGGER4TEMPLATE, SystemEventConstants.ALLMASK,
			Camera4TriggerEvent.class), //
	CAMERA_5_TRIGGER(SystemEventConstants.CAMERATRIGGER5TEMPLATE, SystemEventConstants.ALLMASK,
			Camera5TriggerEvent.class), //
	CAMERA_6_TRIGGER(SystemEventConstants.CAMERATRIGGER6TEMPLATE, SystemEventConstants.ALLMASK,
			Camera6TriggerEvent.class), //
	SECURITY_ARMING(SystemEventConstants.SECURITYARMINGTEMPLATE, SystemEventConstants.SECURITYARMINGMASK,
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
