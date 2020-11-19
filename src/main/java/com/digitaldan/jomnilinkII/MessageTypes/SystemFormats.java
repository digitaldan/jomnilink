/**
* Copyright (c) 2009-2020 Dan Cunningham
*
* This program and the accompanying materials are made available under the
* terms of the Eclipse Public License 2.0 which is available at
* http://www.eclipse.org/legal/epl-2.0
*
* SPDX-License-Identifier: EPL-2.0
*/
package com.digitaldan.jomnilinkII.MessageTypes;

import com.digitaldan.jomnilinkII.Message;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SystemFormats implements Message {

	private final int tempFormat;
	private final int timeFormat;
	private final int dateFormat;

	/*
	 * This message is sent by the HAI controller in reply to a REQUEST SYSTEM
	 * FORMATS message. The controller reports the configured temperature format,
	 * time format, and date format. Start character 0x21 Message length 0x04
	 * Message type 0x29 Data 1 temperature format (1-2) Data 2 time format (1-2)
	 * Data 3 date format (1-2) CRC 1 varies CRC 2 varies The temperature format
	 * byte is shown below. 1=F 2=C The time format byte is shown below. 1 = 12 HR 2
	 * = 24 HR The date format byte is shown below. 1 = MMDD 2 = DDMM
	 * 
	 */

	@Override
	public int getMessageType() {
		return MESG_TYPE_SYS_FORMATS;
	}

}
