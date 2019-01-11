package com.digitaldan.jomnilinkII.MessageTypes;

/**
*  Copyright (C) 2009  Dan Cunningham
*
* This program is free software; you can redistribute it and/or
* modify it under the terms of the GNU General Public License
* as published by the Free Software Foundation, version 2
* of the License, or (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
*/

import java.util.HashMap;
import java.util.Map;

import com.digitaldan.jomnilinkII.Message;
import lombok.*;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SystemStatus implements Message {

	private final boolean timeDateValid;
	private final int year;
	private final int month;
	private final int day;
	private final int dayOfWeek;
	private final int hour;
	private final int minute;
	private final int second;
	private final boolean daylightSavings;
	private final int sunriseHour;
	private final int sunriseMinute;
	private final int sunsetHour;
	private final int sunsetMinute;
	private final int batteryReading;
	@Singular private final Map<Integer, Integer> alarms;

	/*
	 * This message is sent by the HAI controller in reply to a REQUEST SYSTEM STATUS message. The controller
	reports its time, date, calculated time of sunrise and sunset, battery reading, and current alarm(s) in each area.
	      Start character             0x21
	      Message length              (2 * number of alarms) + 15
	      Message type                0x19
	      Data 1                      time/date valid flag (0-1)
	      Data 2                      year (0-99)
	      Data 3                      month (1-12)
	      Data 4                      day (1-31)
	      Data 5                      day of week (1-7)
	      Data 6                      hour (0-23)
	      Data 7                      minute (0-59)
	      Data 8                      second (0-59)
	      Data 9                      daylight savings time flag (0-1)
	      Data 10                     calculated sunrise hour (0-23)
	      Data 11                     calculated sunrise minute (0-59)
	      Data 12                     calculated sunset hour (0-23)
	      Data 13                     calculated sunset minute (0-59)
	      Data 14                     battery reading
	      Data 15                     area in alarm (1-8)
	      Data 16                     alarm status for first alarm (0-255)
	      ...
	      Data n-1                    area in alarm (1-8)
	      Data n                      alarm status for last alarm (0-255)
	      CRC 1                       varies
	      CRC 2                       varies
	The time/date valid flag is zero if the time and date have not been set in the controller. The daylight savings time
	flag is nonzero if daylight savings time is in effect. The day of the week is 1 for Monday through 7 for Sunday.
	
	The bits in the area alarm status bytes are shown below. The corresponding bit is set if the condition is true.
	   Bit              Condition
	    0          Burglary alarm
	    1          Fire alarm
	    2          Gas alarm
	    3          Auxiliary alarm
	    4          Freeze alarm
	    5          Water alarm
	    6          Duress alarm
	    7          Temperature alarm
	
	 */

	@Override
	public int getMessageType() {
		return MESG_TYPE_SYS_STATUS;
	}

}
