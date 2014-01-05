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

import com.digitaldan.jomnilinkII.Message;

public class SystemFormats implements Message {

	private int tempFormat;
	private int timeformat;
	private int dateFormat;
	
	/*
	 * This message is sent by the HAI controller in reply to a REQUEST SYSTEM FORMATS message. The controller
reports the configured temperature format, time format, and date format.
          Start character           0x21
          Message length            0x04
          Message type              0x29
          Data 1                    temperature format (1-2)
          Data 2                    time format (1-2)
          Data 3                    date format (1-2)
          CRC 1                     varies
          CRC 2                     varies
    The temperature format byte is shown below.
          1=F
          2=C
    The time format byte is shown below.
          1 = 12 HR
          2 = 24 HR
    The date format byte is shown below.
          1 = MMDD
          2 = DDMM

	 */
	public SystemFormats(int tempFormat, int timeformat, int dateFormat) {
		super();
		this.tempFormat = tempFormat;
		this.timeformat = timeformat;
		this.dateFormat = dateFormat;
	}

	public int getTempFormat() {
		return tempFormat;
	}

	public int getTimeformat() {
		return timeformat;
	}

	public int getDateFormat() {
		return dateFormat;
	}
	public int getMessageType() {
		return MESG_TYPE_SYS_FORMATS;
	}

	public String toString() {
	    final String TAB = "    ";
	    String retValue = "";
	    
	    retValue = "SystemFormats ( "
	        + "tempFormat = " + this.tempFormat + TAB
	        + "timeformat = " + this.timeformat + TAB
	        + "dateFormat = " + this.dateFormat + TAB
	        + " )";
	
	    return retValue;
	}

}
