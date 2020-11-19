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

/**
 * This file contains the bit masks, that when applied to a system event will
 * match a template pattern.
 * 
 * @author Dan Cunningham
 *
 */
class SystemEventConstants {
	static final int BUTTON_TEMPLATE = 0b0000_0000_0000_0000;
	static final int BUTTON_MASK = 0b1111_1111_0000_0000;

	static final int PROLINK_TEMPLATE = 0b0000_0001_0000_0000;
	static final int PROLINK_MASK = 0b1111_1111_1000_0000;

	static final int CENTRALITE_TEMPLATE = 0b0000_0001_1000_0000;
	static final int CENTRALITE_MASK = 0b1111_1111_1000_0000;

	static final int COMPOSE_TEMPLATE = 0b0111_0000_0000_0000;
	static final int COMPOSE_MASK = 0b1111_0000_0000_0000;

	static final int X10_TEMPLATE = 0b0000_1100_0000_0000;
	static final int X10_MASK = 0b1111_1100_0000_0000;

	static final int ALC_UPB_RADIORA_STARLIGHT_TEMPLATE = 0b1111_0000_0000_0000;
	static final int ALC_UPB_RADIORA_STARLIGHT_MASK = 0b1111_0000_0000_0000;

	static final int UPB_LINK_TEMPLATE = 0b1111_1100_0000_0000;
	static final int UPB_LINK_MASK = 0b1111_1100_0000_0000;

	static final int ALL_ON_OFF_TEMPLATE = 0b0000_0011_1110_0000;
	static final int ALL_ON_OFF_MASK = 0b111_1111_1111_00000;

	static final int PHONE_LINE_DEAD_TEMPLATE = 0b0000_0011_0000_0000;
	static final int PHONE_LINE_RING_TEMPLATE = 0b0000_0011_0000_0001;
	static final int PHONE_LINE_OFF_HOOK_TEMPLATE = 0b0000_0011_0000_0010;
	static final int PHONE_LINE_ON_HOOK_TEMPLATE = 0b0000_0011_0000_0011;

	static final int AC_POWER_OFF_TEMPLATE = 0b0000_0011_0000_0100;
	static final int AC_POWER_RESTORED_TEMPLATE = 0b0000_0011_0000_0101;

	static final int BATTERY_LOW_TEMPLATE = 0b0000_0011_0000_0110;
	static final int BATTERY_OK_TEMPLATE = 0b0000_0011_0000_0111;

	static final int DCM_TROUBLE_TEMPLATE = 0b0000_0011_0000_1000;
	static final int DCM_OK_TEMPLATE = 0b0000_0011_0000_1001;

	static final int ENERGY_COST_LOW_TEMPLATE = 0b0000_0011_0000_1010;
	static final int ENERGY_COST_MEDIUM_TEMPLATE = 0b0000_0011_0000_1011;
	static final int ENERGY_COST_HIGH_TEMPLATE = 0b0000_0011_0000_1100;
	static final int ENERGY_COST_CRITICAL_TEMPLATE = 0b0000_0011_0000_1101;

	static final int CAMERA_TRIGGER_1_TEMPLATE = 0b0000_0011_0000_1110;
	static final int CAMERA_TRIGGER_2_TEMPLATE = 0b0000_0011_0000_1111;
	static final int CAMERA_TRIGGER_3_TEMPLATE = 0b0000_0011_0001_0000;
	static final int CAMERA_TRIGGER_4_TEMPLATE = 0b0000_0011_0001_0001;
	static final int CAMERA_TRIGGER_5_TEMPLATE = 0b0000_0011_0001_0010;
	static final int CAMERA_TRIGGER_6_TEMPLATE = 0b0000_0011_0001_0011;

	static final int ALL_MASK = 0b1111_1111_1111_1111;
}
