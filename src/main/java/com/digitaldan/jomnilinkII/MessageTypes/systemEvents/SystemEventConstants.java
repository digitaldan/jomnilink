package com.digitaldan.jomnilinkII.MessageTypes.systemEvents;

/**
 * This file contains the bit masks, that when applied to a system event will match a template pattern.
 * @author Dan Cunningham
 *
 */
public class SystemEventConstants {
	public static final int buttonTemplate = Integer.parseInt("0000000000000000", 2);
	public static final int buttonMask = Integer.parseInt("1111111100000000", 2);
	public static final int prolinkTemplate = Integer.parseInt("0000000100000000", 2);
	public static final int prolinkMask = Integer.parseInt("1111111110000000", 2);
	public static final int centraliteTemplate = Integer.parseInt("0000000110000000", 2);
	public static final int centraliteMask = Integer.parseInt("1111111110000000", 2);
	public static final int omnialarmTemplate = Integer.parseInt("0000001000000000", 2);
	public static final int omnialarmMask = Integer.parseInt("1111111100000000", 2);
	public static final int luminaAlarmTemplate = Integer.parseInt("0000001000000001", 2);
	public static final int luminaAlarmMask = Integer.parseInt("1111111100001111", 2);
	public static final int zoneTemplate = Integer.parseInt("0000010000000000", 2);
	public static final int zoneMask = Integer.parseInt("1111110000000000", 2);
	public static final int unitTemplate = Integer.parseInt("0000100000000000", 2);
	public static final int unitMask = Integer.parseInt("1111110000000000", 2);
	public static final int composeTemplate = Integer.parseInt("0111000000000000", 2);
	public static final int composeMask = Integer.parseInt("1111000000000000", 2);
	public static final int x10Template = Integer.parseInt("0000110000000000", 2);
	public static final int x10Mask = Integer.parseInt("1111110000000000", 2);
	public static final int securityArmingTemplate = Integer.parseInt("0000000000000000", 2);
	public static final int securityArmingMask = Integer.parseInt("0000000000000000", 2);
	public static final int luminaModeChangeTemplate = Integer.parseInt("0000000100000000", 2);
	public static final int luminaModeChangeMask = Integer.parseInt("0000111100000000", 2);
	public static final int alcUpbRadioraStarlightTemplate = Integer.parseInt("1111000000000000", 2);
	public static final int alcUpbRadioraStarlightMask = Integer.parseInt("1111000000000000", 2);
	public static final int upbLinkTemplate = Integer.parseInt("1111110000000000", 2);
	public static final int upbLinkMask = Integer.parseInt("1111110000000000", 2);
	public static final int allOnOffTemplate = Integer.parseInt("0000001111100000", 2);
	public static final int allOnOffMask = Integer.parseInt("1111111111100000", 2);
	public static final int accessControlReaderTemplate = Integer.parseInt("0000001111010000", 2);
	public static final int accessControlReaderMask = Integer.parseInt("1111111111110000", 2);
	public static final int phoneLineDeadTemplate = Integer.parseInt("0000001100000000", 2);
	public static final int phoneLineRingTemplate = Integer.parseInt("0000001100000001", 2);
	public static final int phoneLineOffHookTemplate = Integer.parseInt("0000001100000010", 2);
	public static final int phoneLineOnHookTemplate = Integer.parseInt("0000001100000011", 2);
	public static final int acPowerOffTemplate = Integer.parseInt("0000001100000100", 2);
	public static final int acPowerRestoredTemplate = Integer.parseInt("0000001100000101", 2);
	public static final int batteryLowTemplate = Integer.parseInt("0000001100000110", 2);
	public static final int batteryOkTemplate = Integer.parseInt("0000001100000111", 2);
	public static final int dcmTroubleTemplate = Integer.parseInt("0000001100001000", 2);
	public static final int dcmOkTemplate = Integer.parseInt("0000001100001001", 2);
	public static final int energyCostLowTemplate = Integer.parseInt("0000001100001010", 2);
	public static final int energyCostMediumTemplate = Integer.parseInt("0000001100001011", 2);
	public static final int energyCostHighTemplate = Integer.parseInt("0000001100001100", 2);
	public static final int energyCostCriticalTemplate = Integer.parseInt("0000001100001101", 2);
	public static final int cameraTrigger1Template = Integer.parseInt("0000001100001110", 2);
	public static final int cameraTrigger2Template = Integer.parseInt("0000001100001111", 2);
	public static final int cameraTrigger3Template = Integer.parseInt("0000001100010000", 2);
	public static final int cameraTrigger4Template = Integer.parseInt("0000001100010001", 2);
	public static final int cameraTrigger5Template = Integer.parseInt("0000001100010010", 2);
	public static final int cameraTrigger6Template = Integer.parseInt("0000001100010011", 2);
	public static final int allMask = Integer.parseInt("1111111111111111", 2);
}
