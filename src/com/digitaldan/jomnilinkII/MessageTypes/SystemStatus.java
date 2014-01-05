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

import com.digitaldan.jomnilinkII.Message;

public class SystemStatus implements Message {
	private boolean timeDateValid;
	private int year;
	private int month;
	private int day;
	private int dayOfWeek;
	private int hour;
	private int minute;
	private int second;
	private boolean daylightSavings;
	private int sunriseHour;
	private int sunriseMinute;
	private int sunsetHour;
	private int sunsetMinute;
	private int batteryReading;
	private HashMap<Integer,Integer>alarms;
	
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
	public SystemStatus(boolean timeDateValid, int year, int month, int day,
			int dayOfWeek, int hour, int minute, int second,
			boolean daylightSavings, int sunriseHour, int sunriseMinute,
			int sunsetHour, int sunsetMinute, int batteryReading,
			HashMap<Integer, Integer> alarms) {
		super();
		this.timeDateValid = timeDateValid;
		this.year = year;
		this.month = month;
		this.day = day;
		this.dayOfWeek = dayOfWeek;
		this.hour = hour;
		this.minute = minute;
		this.second = second;
		this.daylightSavings = daylightSavings;
		this.sunriseHour = sunriseHour;
		this.sunriseMinute = sunriseMinute;
		this.sunsetHour = sunsetHour;
		this.sunsetMinute = sunsetMinute;
		this.batteryReading = batteryReading;
		this.alarms = alarms;
	}


	public boolean isTimeDateValid() {
		return timeDateValid;
	}


	public int getYear() {
		return year;
	}


	public int getMonth() {
		return month;
	}


	public int getDay() {
		return day;
	}


	public int getDayOfWeek() {
		return dayOfWeek;
	}


	public int getHour() {
		return hour;
	}


	public int getMinute() {
		return minute;
	}


	public int getSecond() {
		return second;
	}


	public boolean isDaylightSavings() {
		return daylightSavings;
	}


	public int getSunriseHour() {
		return sunriseHour;
	}


	public int getSunriseMinute() {
		return sunriseMinute;
	}


	public int getSunsetHour() {
		return sunsetHour;
	}


	public int getSunsetMinute() {
		return sunsetMinute;
	}


	public int getBatteryReading() {
		return batteryReading;
	}


	public HashMap<Integer, Integer> getAlarms() {
		return alarms;
	}


	public int getMessageType() {
		return MESG_TYPE_SYS_STATUS;
	}


	public String toString() {
	    final String TAB = "    ";
	    String retValue = "";
	    
	    retValue = "SystemStatus ( "
	        + "timeDateValid = " + this.timeDateValid + TAB
	        + "year = " + this.year + TAB
	        + "month = " + this.month + TAB
	        + "day = " + this.day + TAB
	        + "dayOfWeek = " + this.dayOfWeek + TAB
	        + "hour = " + this.hour + TAB
	        + "minute = " + this.minute + TAB
	        + "second = " + this.second + TAB
	        + "daylightSavings = " + this.daylightSavings + TAB
	        + "sunriseHour = " + this.sunriseHour + TAB
	        + "sunriseMinute = " + this.sunriseMinute + TAB
	        + "sunsetHour = " + this.sunsetHour + TAB
	        + "sunsetMinute = " + this.sunsetMinute + TAB
	        + "batteryReading = " + this.batteryReading + TAB
	        + "alarms = " + this.alarms + TAB
	        + " )";
	
	    return retValue;
	}

}
