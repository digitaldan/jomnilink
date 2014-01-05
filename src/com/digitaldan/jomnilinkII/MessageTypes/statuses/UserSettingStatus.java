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


public class UserSettingStatus extends Status{
	
	private int settingType;
	private int settingValue;

	/*
	 *USER SETTING STATUS (Requires Firmware Version 3.0 or Later)
The controller reports the status of a user setting object or a group of user setting objects. The status
reported for each user setting includes the user setting number, the type of the user setting, and the value of
the user setting.
          Start character            0x21
          Message length             ( 5 * number of user settings) + 2
          Message Type               0x23
          Data 1                     0x0D
          Data 2                     user setting number for first user setting (MSB)
          Data 3                     user setting number for first user setting (LSB)
          Data 4                     type for first user setting
          Data 5                     value for first user setting (MSB)
          Data 6                     value for first user setting (LSB)
          Data 7                     user setting number for second user setting (MSB)
          Data 8                     user setting number for second user setting (LSB)
          Data 9                     type for second user setting
          Data 10                    value for second user setting (MSB)
          Data 11                    value for second user setting (LSB)
          ...
          Data n-4                   user setting number for last user setting (MSB)
          Data n-3                   user setting number for last user setting (LSB)
          Data n-2                   type for last user setting
          Data n-1                   value for last user setting (MSB)
          Data n                     value for last user setting (LSB)
          CRC 1                      varies
          CRC 2                      varies

	 */
	public UserSettingStatus(int number, int settingType, int settingValue) {
		super(number);
		this.settingType = settingType;
		this.settingValue = settingValue;
	}
	
	public int getSettingType() {
		return settingType;
	}
	public int getSettingValue() {
		return settingValue;
	}
	public void setSettingType(int settingType) {
      this.settingType = settingType;
   }
   public void setSettingValue(int settingValue) {
      this.settingValue = settingValue;
   }

   public String toString() {
	    final String TAB = "    ";
	    String retValue = "";
	    
	    retValue = "UnitStatus ( "
	    	+ "number = " + this.number + TAB
	        + "settingType = " + this.settingType + TAB
	        + "settingValue = " + this.settingValue + TAB
	        + " )";
	
	    return retValue;
	}
}
