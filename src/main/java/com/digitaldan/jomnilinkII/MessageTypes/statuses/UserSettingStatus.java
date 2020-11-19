/**
 * Copyright (c) 2009-2020 Dan Cunningham
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package com.digitaldan.jomnilinkII.MessageTypes.statuses;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserSettingStatus extends Status {

	private final int settingType;
	private final int settingValue;

	/*
	 * USER SETTING STATUS (Requires Firmware Version 3.0 or Later) The controller
	 * reports the status of a user setting object or a group of user setting
	 * objects. The status reported for each user setting includes the user setting
	 * number, the type of the user setting, and the value of the user setting.
	 * Start character 0x21 Message length ( 5 * number of user settings) + 2
	 * Message Type 0x23 Data 1 0x0D Data 2 user setting number for first user
	 * setting (MSB) Data 3 user setting number for first user setting (LSB) Data 4
	 * type for first user setting Data 5 value for first user setting (MSB) Data 6
	 * value for first user setting (LSB) Data 7 user setting number for second user
	 * setting (MSB) Data 8 user setting number for second user setting (LSB) Data 9
	 * type for second user setting Data 10 value for second user setting (MSB) Data
	 * 11 value for second user setting (LSB) ... Data n-4 user setting number for
	 * last user setting (MSB) Data n-3 user setting number for last user setting
	 * (LSB) Data n-2 type for last user setting Data n-1 value for last user
	 * setting (MSB) Data n value for last user setting (LSB) CRC 1 varies CRC 2
	 * varies
	 *
	 */
	@Builder
	private UserSettingStatus(int number, int settingType, int settingValue) {
		super(number);
		this.settingType = settingType;
		this.settingValue = settingValue;
	}
}
