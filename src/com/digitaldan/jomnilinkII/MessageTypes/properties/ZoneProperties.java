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
import com.digitaldan.jomnilinkII.MessageTypes.statuses.ZoneStatus;

public class ZoneProperties extends ObjectProperties {
	private int status;
	private int loop;
	private int zoneType;
	private int area;
	private int options;
	
	public ZoneProperties(int number, int status, int loop,
			int zoneType, int area, int options, String name) {
		super(OBJ_TYPE_ZONE, number,name);
		this.status = status;
		this.loop = loop;
		this.zoneType = zoneType;
		this.area = area;
		this.options = options;
	}
	public int getStatus() {
		return status;
	}
	public int getLoop() {
		return loop;
	}
	public int getZoneType() {
		return zoneType;
	}
	public int getArea() {
		return area;
	}
	public int getOptions() {
		return options;
	}
	
	public void setStatus(int status) {
      this.status = status;
   }
   public void setLoop(int loop) {
      this.loop = loop;
   }
   public void setZoneType(int zoneType) {
      this.zoneType = zoneType;
   }
   public void setArea(int area) {
      this.area = area;
   }
   public void setOptions(int options) {
      this.options = options;
   }
   
   public void updateZone(ZoneStatus zoneStatus) {
      setLoop(zoneStatus.getLoop());
      setStatus(zoneStatus.getStatus());
   }
   
   public String toString() {
	    final String TAB = "    ";
	    String retValue = "";
	    
	    retValue = "ZoneProperties ( "
	    	+ "number = " + this.number + TAB
	        + "status = " + this.status + TAB
	        + "loop = " + this.loop + TAB
	        + "type = " + this.zoneType + TAB
	        + "area = " + this.area + TAB
	        + "options = " + this.options + TAB
	        + "name = " + this.name + TAB
	        + " )";
	
	    return retValue;
	}
	
	
}
