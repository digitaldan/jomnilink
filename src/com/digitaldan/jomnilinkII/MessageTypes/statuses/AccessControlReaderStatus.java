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


public class AccessControlReaderStatus extends Status{
	
	private boolean granted;
	private int lastUser;

	/*
	 *ACCESS CONTROL READER STATUS (Requires Firmware Version 3.0 or Later)
The controller reports the status of an access control reader object or a group of access control reader
objects. The status reported for each access control reader includes the reader number, whether access was
granted or denied, and the last user to access the reader.
         Start character
         Message length
         Message Type
         Data 1
         Data 2
         Data 3
         Data 4
         Data 5
         Data 6
         Data 7
         Data 8
         Data 9
         ...
         Data n-3
         Data n-2
         Data n-1
         Data n
         CRC 1
         CRC 2

	 */
	public AccessControlReaderStatus(int number, boolean granted, int lastUser) {
		super(number);
		this.granted = granted;
		this.lastUser = lastUser;
	}
	
	public boolean isGranted() {
		return granted;
	}
	public int getLastUser() {
		return lastUser;
	}

	public void setGranted(boolean granted) {
      this.granted = granted;
   }

   public void setLastUser(int lastUser) {
      this.lastUser = lastUser;
   }

   public String toString() {
	    final String TAB = "    ";
	    String retValue = "";
	    
	    retValue = "UnitStatus ( "
	    	+ "number = " + this.number + TAB
	        + "isGranted = " + this.granted + TAB
	        + "lastUser = " + this.lastUser + TAB
	        + " )";
	
	    return retValue;
	}
}
