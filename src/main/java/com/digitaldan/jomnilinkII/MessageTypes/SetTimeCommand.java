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
public class SetTimeCommand implements Message {

	private final int year;
	private final int month;
	private final int day;
	private final int dayOfWeek;
	private final int hour;
	private final int minute;
	private final boolean daylightSavings;

	/*
	 * SET TIME COMMAND
	 * 
	 * This message is used to set the time, date, and daylight savings time flag in
	 * an HAI controller.
	 * 
	 * Start character 0x21 Message length 0x08 Message type 0x13 Data 1 year (0-99)
	 * Data 2 month (1-12) Data 3 day (1-31) Data 4 day of week (1-7) Data 5 hour
	 * (0-23) Data 6 minute (0-59) Data 7 daylight savings time flag (0-1) CRC 1
	 * varies CRC 2 varies
	 * 
	 * Expected reply ACKNOWLEDGE
	 * 
	 */

	@Override
	public int getMessageType() {
		return MESG_TYPE_SET_TIME;
	}

}
