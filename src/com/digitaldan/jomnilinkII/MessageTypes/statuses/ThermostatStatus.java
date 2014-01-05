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


public class ThermostatStatus extends Status{
	
	private int status;
	private int temperature;
	private int heatSetpotint;
	private int coolSetpoint;
	private int mode;
	private boolean fan;
	private boolean hold;
	
	/*
	 *THERMOSTAT STATUS
The controller reports the status of a thermostat object or group of thermostat objects. The status reported
for each thermostat includes the thermostat number, whether the thermostat is communicating with the
controller, whether a freeze condition has been detected by the thermostat, the current temperature, the heat
and cool setpoints, the system mode, the fan mode, and whether the thermostat has been placed in hold
mode.
         Start character             0x21
         Message length              (9 * number of thermostats) + 2
         Message type                0x23
         Data 1                      0x06
         Data 2                      thermostat number for first thermostat (MSB)
         Data 3                      thermostat number for first thermostat (LSB)
         Data 4                      status byte for first thermostat
         Data 5                      current temperature for first thermostat
         Data 6                      heat setpoint for first thermostat
         Data 7                      cool setpoint for first thermostat
         Data 8                      system mode for first thermostat
         Data 9                      fan mode for first thermostat
         Data 10                     hold status for first thermostat
         Data 11                     thermostat number for second thermostat (MSB)
         Data 12                     thermostat number for second thermostat (LSB)
         Data 13                     status byte for second thermostat
         Data 14                     current temperature for second thermostat
         Data 15                     heat setpoint for second thermostat
         Data 16                     cool setpoint for second thermostat
         Data 17                     system mode for second thermostat
         Data 18                     fan mode for second thermostat
         Data 19                     hold status for second thermostat
         ...
         Data n-8                    thermostat number for last thermostat (MSB)
         Data n-7                    thermostat number for last thermostat (LSB)
         Data n-6                    status byte for last thermostat
         Data n-5                    current temperature for last thermostat
         Data n-4                    heat setpoint for last thermostat
         Data n-3                    cool setpoint for last thermostat
         Data n-2                    system mode for last thermostat
         Data n-1                    fan mode for last thermostat
         Data n                      hold status for last thermostat
         CRC 1                       varies
         CRC 2                       varies
The bits in the thermostat status byte are shown below. The corresponding bit is set if the condition is true.
         Bit 0                       Communications failure
         Bit 1                       Freeze alarm
The temperatures are reported in the Omni temperature format (see Appendix C).

The system mode is as follows:
         0                           Off
         1                           Heat
         2                           Cool
         3                           Auto
         4                           Emergency heat
The fan mode is as follows:
         0                           Auto
         1                           On
The hold status is non-zero if the thermostat is in hold mode.

	 */
	
	public ThermostatStatus(int number, int status, int temp,
			int heatSetpotint, int coolSetpoint, int systemMode, boolean fan,
			boolean hold) {
		super(number);
		this.status = status;
		this.temperature = temp;
		this.heatSetpotint = heatSetpotint;
		this.coolSetpoint = coolSetpoint;
		this.mode = systemMode;
		this.fan = fan;
		this.hold = hold;
	}
	
	public int getStatus() {
		return status;
	}
	public int getTemperature() {
		return temperature;
	}
	public int getHeatSetpotint() {
		return heatSetpotint;
	}
	public int getCoolSetpoint() {
		return coolSetpoint;
	}
	public int getMode() {
		return mode;
	}
	public boolean isFan() {
		return fan;
	}
	public boolean isHold() {
		return hold;
	}

	public void setStatus(int status) {
      this.status = status;
   }

   public void setTemperature(int temperature) {
      this.temperature = temperature;
   }

   public void setHeatSetpotint(int heatSetpotint) {
      this.heatSetpotint = heatSetpotint;
   }

   public void setCoolSetpoint(int coolSetpoint) {
      this.coolSetpoint = coolSetpoint;
   }

   public void setMode(int mode) {
      this.mode = mode;
   }

   public void setFan(boolean fan) {
      this.fan = fan;
   }

   public void setHold(boolean hold) {
      this.hold = hold;
   }

   public String toString() {
	    final String TAB = "    ";
	    String retValue = "";
	    
	    retValue = "ThermostatStatus ( "
	    	+ "number = " + this.number + TAB
	        + "status = " + this.status + TAB
	        + "temperature = " + this.temperature + TAB
	        + "heatSetpotint = " + this.heatSetpotint + TAB
	        + "coolSetpoint = " + this.coolSetpoint + TAB
	        + "systemMode = " + this.mode + TAB
	        + "fanMode = " + this.fan + TAB
	        + "holdStatus = " + this.hold + TAB
	        + " )";
	
	    return retValue;
	}
	
}
