package com.digitaldan.jomnilinkII.MessageTypes.systemevents;

/**
 * This file contains the bit masks, that when applied to a system event will match a template pattern.
 * @author Dan Cunningham
 *
 */
public class SystemEventConstants {
	public static final int BUTTONTEMPLATE = 0; // 0000000000000000
	public static final int BUTTONMASK = 65280; // 1111111100000000
	public static final int PROLINKTEMPLATE = 256; // 0000000100000000
	public static final int PROLINKMASK = 65408; // 1111111110000000
	public static final int CENTRALITETEMPLATE = 384; // 0000000110000000
	public static final int CENTRALITEMASK = 65408; // 1111111110000000

	public static final int COMPOSETEMPLATE = 28672; // 0111000000000000
	public static final int COMPOSEMASK = 61440; // 1111000000000000
	public static final int X10TEMPLATE = 3072; // 0000110000000000
	public static final int X10MASK = 64512; // 1111110000000000

	public static final int ALCUPBRADIORASTARLIGHTTEMPLATE = 61440; // 1111000000000000
	public static final int ALCUPBRADIORASTARLIGHTMASK = 61440; // 1111000000000000
	public static final int UPBLINKTEMPLATE = 64512; // 1111110000000000
	public static final int UPBLINKMASK = 64512; // 1111110000000000
	public static final int ALLONOFFTEMPLATE = 992; // 0000001111100000
	public static final int ALLONOFFMASK = 65504; // 1111111111100000
	public static final int PHONELINEDEADTEMPLATE = 768; // 0000001100000000
	public static final int PHONELINERINGTEMPLATE = 769; // 0000001100000001
	public static final int PHONELINEOFFHOOKTEMPLATE = 770; // 0000001100000010
	public static final int PHONELINEONHOOKTEMPLATE = 771; // 0000001100000011
	public static final int ACPOWEROFFTEMPLATE = 772; // 0000001100000100
	public static final int ACPOWERRESTOREDTEMPLATE = 773; // 0000001100000101
	public static final int BATTERYLOWTEMPLATE = 774; // 0000001100000110
	public static final int BATTERYOKTEMPLATE = 775; // 0000001100000111
	public static final int DCMTROUBLETEMPLATE = 776; // 0000001100001000
	public static final int DCMOKTEMPLATE = 777; // 0000001100001001
	public static final int ENERGYCOSTLOWTEMPLATE = 778; // 0000001100001010
	public static final int ENERGYCOSTMEDIUMTEMPLATE = 779; // 0000001100001011
	public static final int ENERGYCOSTHIGHTEMPLATE = 780; // 0000001100001100
	public static final int ENERGYCOSTCRITICALTEMPLATE = 781; // 0000001100001101
	public static final int CAMERATRIGGER1TEMPLATE = 782; // 0000001100001110
	public static final int CAMERATRIGGER2TEMPLATE = 783; // 0000001100001111
	public static final int CAMERATRIGGER3TEMPLATE = 784; // 0000001100010000
	public static final int CAMERATRIGGER4TEMPLATE = 785; // 0000001100010001
	public static final int CAMERATRIGGER5TEMPLATE = 786; // 0000001100010010
	public static final int CAMERATRIGGER6TEMPLATE = 787; // 0000001100010011
	public static final int ALLMASK = 65535; // 1111111111111111
}
