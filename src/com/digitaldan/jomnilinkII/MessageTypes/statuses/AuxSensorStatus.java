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


public class AuxSensorStatus extends Status{
	
	private int status;
	private int temp;
	private int heatSetpotint;
	private int coolSetpoint;
	
	/*
	 *AUXILIARY SENSOR STATUS
The controller reports the status of an auxiliary sensor object or a group of auxiliary sensor objects. The
status reported for each auxiliary sensor includes the auxiliary sensor number, the output status for each
Programmable Energy Saver Module (PESM), the current temperature or humidity reading, and the low
and high setpoints.
         Start character             0x21
         Message length              (6 * number of auxiliary sensors) + 2
         Message type                0x23
         Data 1                      0x08
         Data 2                      auxiliary sensor number for first auxiliary sensor (MSB)
         Data 3                      auxiliary sensor number for first auxiliary sensor (LSB)
         Data 4                      output status for first auxiliary sensor
         Data 5                      current temperature or humidity for first auxiliary sensor
         Data 6                      low/heat setpoint for first auxiliary sensor
         Data 7                      high/cool setpoint for first auxiliary sensor
         Data 8                      auxiliary sensor number for second auxiliary sensor (MSB)
         Data 9                      auxiliary sensor number for second auxiliary sensor (LSB)
         Data 10                     output status for second auxiliary sensor
         Data 11                     current temperature or humidity for second auxiliary sensor
         Data 12                     low/heat setpoint for second auxiliary sensor
         Data 13                     high/cool setpoint for second auxiliary sensor
         ...
         Data n-5                    auxiliary sensor number for last auxiliary sensor (MSB)
         Data n-4                    auxiliary sensor number for last auxiliary sensor (LSB)
         Data n-3                    output status for last auxiliary sensor
         Data n-2                    current temperature or humidity for last auxiliary sensor
         Data n-1                    low/heat setpoint for last auxiliary sensor
         Data n                      high/cool setpoint for last auxiliary sensor
         CRC 1                       varies
         CRC 2                       varies
The output status is non-zero if the output is energized.
The temperatures are reported in the Omni temperature format (see Appendix C).

	 */
	public AuxSensorStatus(int number, int status, int temp,
			int heatSetpotint, int coolSetpoint) {
		super(number);
		this.status = status;
		this.temp = temp;
		this.heatSetpotint = heatSetpotint;
		this.coolSetpoint = coolSetpoint;
	}
	public int getStatus() {
		return status;
	}
	public int getTemp() {
		return temp;
	}
	public int getHeatSetpotint() {
		return heatSetpotint;
	}
	public int getCoolSetpoint() {
		return coolSetpoint;
	}
	
	public void setStatus(int status) {
      this.status = status;
   }
   public void setTemp(int temp) {
      this.temp = temp;
   }
   public void setHeatSetpotint(int heatSetpotint) {
      this.heatSetpotint = heatSetpotint;
   }
   public void setCoolSetpoint(int coolSetpoint) {
      this.coolSetpoint = coolSetpoint;
   }
   public String toString() {
	    final String TAB = "    ";
	    String retValue = "";
	    
	    retValue = "AuxSensorStatus ( "
	    	+ "number = " + this.number + TAB
	        + "status = " + this.status + TAB
	        + "temp = " + this.temp + TAB
	        + "heatSetpotint = " + this.heatSetpotint + TAB
	        + "coolSetpoint = " + this.coolSetpoint + TAB
	        + " )";
	
	    return retValue;
	}
}
