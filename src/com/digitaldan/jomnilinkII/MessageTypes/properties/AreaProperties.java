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
import com.digitaldan.jomnilinkII.MessageTypes.statuses.AreaStatus;

public class AreaProperties extends ObjectProperties {
	private int mode;
	private int alarms;
	private int entryTimer;
	private int exitTimer;
	private boolean enabled;
	private int exitDelay;
	private int entryDelay;
	public AreaProperties(int number, int mode, int alarms,
			int entryTimer, int exitTimer, boolean enabled, int exitDelay,
			int entryDelay, String name) {
		super(OBJ_TYPE_AREA, number,name);
		this.mode = mode;
		this.alarms = alarms;
		this.entryTimer = entryTimer;
		this.exitTimer = exitTimer;
		this.enabled = enabled;
		this.exitDelay = exitDelay;
		this.entryDelay = entryDelay;
	}
	public int getMode() {
		return mode;
	}
	public int getAlarms() {
		return alarms;
	}
	public int getEntryTimer() {
		return entryTimer;
	}
	public int getExitTimer() {
		return exitTimer;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public int getExitDelay() {
		return exitDelay;
	}
	public int getEntryDelay() {
		return entryDelay;
	}
	public void setMode(int mode) {
      this.mode = mode;
   }
   public void setAlarms(int alarms) {
      this.alarms = alarms;
   }
   public void setEntryTimer(int entryTimer) {
      this.entryTimer = entryTimer;
   }
   public void setExitTimer(int exitTimer) {
      this.exitTimer = exitTimer;
   }
   public void setEnabled(boolean enabled) {
      this.enabled = enabled;
   }
   public void setExitDelay(int exitDelay) {
      this.exitDelay = exitDelay;
   }
   public void setEntryDelay(int entryDelay) {
      this.entryDelay = entryDelay;
   }
   public void updateArea(AreaStatus areaStatus) {
      setAlarms(areaStatus.getAlarms());
      setEntryTimer(areaStatus.getEntryTimer());
      setExitTimer(areaStatus.getExitTimer());
      setMode(areaStatus.getMode());
   }
   public String toString() {
	    final String TAB = "    ";
	    String retValue = "";
	    
	    retValue = "AreaProperties ( "
	    	+ "number = " + this.number + TAB
	        + "mode = " + this.mode + TAB
	        + "alarms = " + this.alarms + TAB
	        + "entryTimer = " + this.entryTimer + TAB
	        + "exitTimer = " + this.exitTimer + TAB
	        + "enabled = " + this.enabled + TAB
	        + "exitDelay = " + this.exitDelay + TAB
	        + "entryDelay = " + this.entryDelay + TAB
	        + "name = " + this.name + TAB
	        + " )";
	
	    return retValue;
	}
	
}
