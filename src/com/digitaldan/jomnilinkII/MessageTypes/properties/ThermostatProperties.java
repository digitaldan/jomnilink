package com.digitaldan.jomnilinkII.MessageTypes.properties;

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

import com.digitaldan.jomnilinkII.MessageTypes.ObjectProperties;
import com.digitaldan.jomnilinkII.MessageTypes.statuses.ThermostatStatus;

public class ThermostatProperties extends ObjectProperties{

	private int status;
	private int temperature;
	private int heatSetpoint;
	private int coolSetpoint;
	private int mode;
	private boolean fan;
	private boolean hold;
	private int thermostatType;
	public ThermostatProperties(int number,
			int status, int temperature, int heatSetpoint,
			int coolSetpoint, int mode, boolean fan, boolean hold,
			int thermostatType, String name) {
		super(OBJ_TYPE_THERMO, number, name);
		this.status = status;
		this.temperature = temperature;
		this.heatSetpoint = heatSetpoint;
		this.coolSetpoint = coolSetpoint;
		this.mode = mode;
		this.fan = fan;
		this.hold = hold;
		this.thermostatType = thermostatType;
	}
	public int getStatus() {
		return status;
	}
	public int getTemperature() {
		return temperature;
	}
	public int getHeatSetpoint() {
		return heatSetpoint;
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
	public int getThermostatType() {
		return thermostatType;
	}
	
	public void setStatus(int status) {
      this.status = status;
   }
   public void setTemperature(int temperature) {
      this.temperature = temperature;
   }
   public void setHeatSetpoint(int heatSetpoint) {
      this.heatSetpoint = heatSetpoint;
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
   public void setThermostatType(int thermostatType) {
      this.thermostatType = thermostatType;
   }
   
   public void updateThermostat(ThermostatStatus thermoStatus) {
      setCoolSetpoint(thermoStatus.getCoolSetpoint());
      setHeatSetpoint(thermoStatus.getHeatSetpotint());
      setMode(thermoStatus.getMode());
      setStatus(thermoStatus.getStatus());
      setTemperature(thermoStatus.getTemperature());
      setHold(thermoStatus.isHold());
      setFan(thermoStatus.isFan());
   }
   
   public String toString() {
	    final String TAB = "    ";
	    String retValue = "";
	    
	    retValue = "ThermostatProperties ( "
	    	+ "number = " + this.number + TAB
	        + "communicating = " + this.status + TAB
	        + "temperature = " + this.temperature + TAB
	        + "heatSetpoint = " + this.heatSetpoint + TAB
	        + "coolSetpoint = " + this.coolSetpoint + TAB
	        + "mode = " + this.mode + TAB
	        + "fan = " + this.fan + TAB
	        + "hold = " + this.hold + TAB
	        + "thermostatType = " + this.thermostatType + TAB
	        + "name = " + this.name + TAB
	        + " )";
	
	    return retValue;
	}
}
