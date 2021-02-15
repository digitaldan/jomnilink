/**
 * Copyright (c) 2009-2021 Dan Cunningham
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package com.digitaldan.jomnilinkII.MessageTypes.properties;

import com.digitaldan.jomnilinkII.MessageTypes.ObjectProperties;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

/*
 * USER SETTING PROPERTIES (Requires Firmware Version 3.0 or Later)
 *
 *     Data 4               user setting type
 *     Data 5               value (MSB)
 *     Data 6               value (LSB)
 *     Data 7-22            user setting name
 *
 * The user setting types are as follows:
 *
 * -------------------------------
 * | Setting Type | Description  |
 * |--------------|--------------|
 * | 0            | Not Used     |
 * |--------------|--------------|
 * | 1            | Number       |
 * |--------------|--------------|
 * | 2            | Duration     |
 * |--------------|--------------|
 * | 3            | Temperature  |
 * |--------------|--------------|
 * | 4            | Humidity     |
 * |--------------|--------------|
 * | 5            | Date         |
 * |--------------|--------------|
 * | 6            | Time         |
 * |--------------|--------------|
 * | 7            | Days of Week |
 * |--------------|--------------|
 * | 8            | Level        |
 * -------------------------------
 *
 * Numbers are reported as 0-255 in the low byte.
 *
 * For durations, 1-99 = 1-99 seconds, 101-199 = 1-99 minutes, and
 * 201-218 = 1-18 hours.
 *
 * Temperatures are reported in the Omni temperature format.
 *
 * Humidity is reported in the Omni temperature format where Fahrenheit
 * temperatures 0-100 correspond to 0-100% relative humidity.
 *
 * Dates are reported with the month in the high byte and the day in the low
 * byte.
 *
 * Times are reported with the hour in the high byte and the minute in the low
 * byte.
 *
 * Days of the week are reported in the low byte, with bit 1 set for Monday, bit
 * 2 set for Tuesday, and so on with bit 7 set for Sunday. Bit 0 is not used.
 *
 * Levels are reported as 0-100 in the low byte.
 */
@Value
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserSettingProperties extends ObjectProperties {
	private final int userSettingType;
	private final int value;

	@Builder
	private UserSettingProperties(int number, int userSettingType, int value, String name) {
		super(OBJ_TYPE_USER_SETTING, number, name);
		this.userSettingType = userSettingType;
		this.value = value;
	}
}
