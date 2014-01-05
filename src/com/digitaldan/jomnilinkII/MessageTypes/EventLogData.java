package com.digitaldan.jomnilinkII.MessageTypes;

import com.digitaldan.jomnilinkII.Message;

public class EventLogData implements Message {

	private int eventNumber;
	private boolean timeDataValid;
	private int month;
	private int day;
	private int hour;
	private int minute;
	private int eventType;
	private int parameter1;
	private int parameter2;
	
	/*
	 * EVENT LOG DATA 
 
 Start character  0x21 
 Message length  0x0C 
 Message type  0x25 
 Data 1   event number (MSB) 
 Data 2   event number (LSB) 
 Data 3   time/date valid 
 Data 4   month (1-12) 
 Data 5   day (1-31) 
Data 6   hour (0-23) 
 Data 7   minute (0-59) 
 Data 8   event type 
 Data 9   parameter 1 
 Data 10   high byte of parameter 2 
 Data 11   low byte of parameter 2 
 CRC 1   varies 
 CRC 2   varies 
 
The highest numbered event is the most recent event.  When the event number counter (Data 1 and Data 2) reaches 
65535, when the next event occurs, the counter rolls over to 1. 

 
Copyright � 2008 Home Automation, Inc. 
All Rights Reserved 
Page 30 
 
 
The month, day, hour, and minute specify the time that the event occurred.  The time/date valid flag is zero if the 
controller time was not set when the event occurred.  In this case, the month, day, hour, and minute fields do not 
contain valid data and should not be used.  The time/date valid flag is non-zero when the time has been properly set 
in the controller. 
 
The event, parameter 1, and parameter 2 identify the specific event that has occurred.  The possible events are 
shown in the �Event Log Event Types� tables.  When a security code is specified, the value is the user code number 
rather than the actual four-digit security code. 
 
In addition to the user codes, the following security codes can be reported: 
 
251 Duress code 
252 Keyswitch 
253 Quick arm 
254 PC Access 
255 Programmed 
 
 
LUMINA SERIES EVENT LOG EVENT TYPES 
 
 
 
Event Type Parameter 1 P1 Parameter 2  P2  Description 
48+m 1-n 0-n set mode m with code P1 

m = mode: 
1 = home mode 
2 = sleep mode 
3 = away mode 
4 = vacation mode 
5 = party mode 
6 = special mode 

128  1-n Zone P2 tripped 
129  1-n zone P2 trouble 
130 1-n  remote phone access with code P1 
131   remote phone lockout 
133  1-n zone P2 trouble cleared 
134 1-n  PC access with code P1 
135 1-n 1 alarm P1 activated 

5 = freeze 
6 = water 
8 = temperature 

136 1-n 1 alarm P1 reset 

5 = freeze 
6 = water 
8 = temperature 
137   system reset 
138  1-n message P2 logged 
 
 
 
 
OMNI SERIES EVENT LOG EVENT TYPES 
 
 
 
Event Type Parameter 1 P1 Parameter 2  P2  Description 
4 1-n 1-n zone P2 bypassed with code P1 
5 1-n 1-n zone P2 restored with code P1 
6 1-n 0-n all area P2 zones restored with code P1 
P2 = 0 means all areas/zones 
48+m 1-n 0-n area P2 armed in mode m with code P1 

P2 = 0 means all areas 
m = security mode: 
0 = disarm 
1 = day mode 
2 = night mode 
3 = away mode 
4 = vacation mode 
5 = day instant mode 
6 = night delayed mode 

128  1-n zone P2 tripped 
129  1-n zone P2 trouble 
130 1-n  remote phone access with code P1 
131   remote phone lockout 
132  1-n zone P2 auto bypassed 
133  1-n zone P2 trouble cleared 
134 1-n  PC access with code P1 
135 1-n 1-n alarm P1 activated in area P2 

1 = burglary 
2 = fire 
3 = gas 
4 = auxiliary 
5 = freeze 
6 = water 
7 = duress 
8 = temperature 

136 1-n 1-n alarm P1 reset in area P2 

1 = burglary 
2 = fire 
3 = gas 
4 = auxiliary 
5 = freeze 
6 = water 
7 = duress 
8 = temperature 

137   system reset 
138  1-n message P2 logged 
 
	 */
	public EventLogData(int eventNumber, boolean timeDataValid, int month,
			int day, int hour, int minute, int eventType, int parameter1,
			int parameter2) {
		super();
		this.eventNumber = eventNumber;
		this.timeDataValid = timeDataValid;
		this.month = month;
		this.day = day;
		this.hour = hour;
		this.minute = minute;
		this.eventType = eventType;
		this.parameter1 = parameter1;
		this.parameter2 = parameter2;
	}


	public int getMessageType() {
		return MESG_TYPE_EVENT_LOG_DATA;
	}


	public int getEventNumber() {
		return eventNumber;
	}


	public boolean isTimeDataValid() {
		return timeDataValid;
	}


	public int getMonth() {
		return month;
	}


	public int getDay() {
		return day;
	}


	public int getHour() {
		return hour;
	}


	public int getMinute() {
		return minute;
	}


	public int getEventType() {
		return eventType;
	}


	public int getParameter1() {
		return parameter1;
	}


	public int getParameter2() {
		return parameter2;
	}


	public String toString() {
	    final String TAB = "    ";
	    String retValue = "";
	    
	    retValue = "EventLogData ( "
	        + "eventNumber = " + this.eventNumber + TAB
	        + "timeDataValid = " + this.timeDataValid + TAB
	        + "month = " + this.month + TAB
	        + "day = " + this.day + TAB
	        + "hour = " + this.hour + TAB
	        + "minute = " + this.minute + TAB
	        + "eventType = " + this.eventType + TAB
	        + "parameter1 = " + this.parameter1 + TAB
	        + "parameter2 = " + this.parameter2 + TAB
	        + " )";
	
	    return retValue;
	}

}
