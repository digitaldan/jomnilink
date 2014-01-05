package com.digitaldan.jomnilinkII.MessageTypes.statuses;

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


public class MessageStatus extends Status{
	
	private int status;
	
	/*
	 *The controller reports the status of a displayed text message object or a group of displayed text message
objects. The status reported for each message includes the message number, whether the message is
currently being displayed, and whether the displayed message has not been acknowledged.
         Start character             0x21
         Message length              (3 * number of messages) + 2
         Message type                0x23
         Data 1                      0x07
         Data 2                      message number for first message (MSB)
         Data 3                      message number for first message (LSB)
         Data 4                      status byte for first message
         Data 5                      message number for second message (MSB)
         Data 6                      message number for second message (LSB)
         Data 7                      status byte for second message
         ...
         Data n-2                    message number for last message (MSB)
         Data n-1                    message number for last message (LSB)
         Data n                      status byte for last message
         CRC 1                       varies
         CRC 2                       varies
The status bytes are defined as follows:
         0                           Off (message in not being displayed)
         1                           Displayed (the message is currently being displayed)
         2                           Not acknowledged (the displayed message has not been acknowledged)

	 */
	
	public MessageStatus(int number, int status) {
		super(number);
		this.status = status;
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
      this.status = status;
   }

   public String toString() {
	    final String TAB = "    ";
	    String retValue = "";
	    
	    retValue = "MessageStatus ( "
	    	+ "number = " + this.number + TAB
	        + "status = " + this.status + TAB
	        + " )";
	
	    return retValue;
	}

}
