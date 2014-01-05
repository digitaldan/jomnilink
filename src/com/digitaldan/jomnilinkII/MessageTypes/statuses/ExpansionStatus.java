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


public class ExpansionStatus extends Status{
	
	private int status;
	private int battery;
	
	/*
	 *EXPANSION ENCLOSURE STATUS
The controller reports the status of an expansion enclosure object or a group of expansion enclosure
objects. The status reported for each expansion enclosure includes the expansion enclosure number,
whether the thermostat is communicating with the controller, and the battery reading.
         Start character           0x21
         Message length            (4 * number of expansion enclosures) + 2
         Message type              0x23
         Data 1                    0x0B
         Data 2                    address number for first expansion enclosure (MSB)
         Data 3                    address number for first expansion enclosure (LSB)
         Data 4                    communications status for first expansion enclosure (0-1)
         Data 5                    battery reading for first expansion enclosure (0-255)
                                   address number for second expansion enclosure (MSB)
         Data 6
         Data 7                    address number for second expansion enclosure (LSB)
         Data 8                    communications status for second expansion enclosure (0-1)
         Data 9                    battery reading for second expansion enclosure (0-255)
         ...
         Data n-3                  address number for last expansion enclosure (MSB)
         Data n-2                  address number for last expansion enclosure (LSB)
         Data n-1                  communications status for last expansion enclosure (0-1)
         Data n                    battery reading for last expansion enclosure (0-255)
         CRC 1                     varies
         CRC 2                     varies

	 */
	
	public ExpansionStatus(int number, int status, int battery) {
		super(number);
		this.status = status;
		this.battery = battery;
	}
	
	public int getStatus() {
		return status;
	}
	public int getBattery() {
		return battery;
	}

	public void setStatus(int status) {
      this.status = status;
   }

   public void setBattery(int battery) {
      this.battery = battery;
   }

   public String toString() {
	    final String TAB = "    ";
	    String retValue = "";
	    
	    retValue = "ExpansionStatus ( "
	    	+ "number = " + this.number + TAB
	        + "status = " + this.status + TAB
	        + "battery = " + this.battery + TAB
	        + " )";
	
	    return retValue;
	}
	
}
