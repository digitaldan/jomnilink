package com.digitaldan.jomnilinkII.MessageTypes;

import com.digitaldan.jomnilinkII.Message;

public class SetTimeCommand implements Message {

	int year;
	int month;
	int day;
	int dayOfWeek;
	int hour;
	int minute;
	boolean daylightSavings;
	
	/*
	 * SET TIME COMMAND 
 
This message is used to set the time, date, and daylight savings time flag in an HAI controller. 
 
 Start character  0x21 
 Message length  0x08 
 Message type  0x13 
Data 1    year (0-99)  
Data 2    month (1-12)  
Data 3    day (1-31)  
Data 4    day of week (1-7)  
Data 5    hour (0-23)  
Data 6    minute (0-59)  
 Data 7   daylight savings time flag (0-1) 
 CRC 1   varies 
 CRC 2   varies 
 
Expected reply   ACKNOWLEDGE 
 
	 */
	
	public SetTimeCommand(int year, int month, int day, int dayOfWeek,
			int hour, int minute, boolean daylightSavings) {
		super();
		this.year = year;
		this.month = month;
		this.day = day;
		this.dayOfWeek = dayOfWeek;
		this.hour = hour;
		this.minute = minute;
		this.daylightSavings = daylightSavings;
	}


	public int getMessageType() {
		return MESG_TYPE_SET_TIME;
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


	public boolean isDaylightSavings() {
		return daylightSavings;
	}

}
