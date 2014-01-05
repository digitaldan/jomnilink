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

public class SystemInformation implements Message {
	private int model;
	private int major;
	private int minor;
	private int revision;
	private String phone;
	
	/*
	 *This message is sent by the HAI controller in reply to a REQUEST SYSTEM INFORMATION message. The
controller reports its model number, software version, and local phone number.
         Start character             0x21
         Message length              0x1E
         Message type                0x17
         Data 1                      model number
         Data 2                      major version
         Data 3                      minor version
         Data 4                      revision
         Data 5-29                   local phone number
         CRC 1                       varies
         CRC 2                       varies
The model number identifies the controller model, such as Omni IIe, OmniPro II, Lumina, or Lumina Pro. The
following model numbers are defined:
  Model Number               Model
         30            HAI Omni IIe
         16            HAI OmniPro II
         36            HAI Lumina
         37            HAI Lumina Pro
The major version, minor version, and revision identify the controller software version. For example, if the
software version is 2.16b, the major version would be 0x02, the minor version would be 0x10, and the revision
would be 0x02. Revision 0x00 specifies no revision letter, revision 0x01 specifies revision “a”, and so on. If the
revision is a 2's complement negative number, such as 0xFF, it specifies a prototype revision such as X1 or X2.
Revision 0xFF specifies revision X1, revision 0xFE specifies revision X2, and so on.

The local phone number corresponds to the "MY PHONE NUMBER" setting in the controller. It is an ASCII text
string up to 24 characters long, terminated with a trailing 0x00.
 
	 */
	
	public SystemInformation(int model, int major, int minor, int revision, String phone){
		this.model = model;
		this.major = major;
		this.minor = minor;
		this.revision = revision;
		this.phone = phone;
	}

	public int getModel() {
		return model;
	}

	public int getMajor() {
		return major;
	}

	public int getMinor() {
		return minor;
	}

	public int getRevision() {
		return revision;
	}

	public String getPhone() {
		return phone;
	}
	
	public int getMessageType() {
		return MESG_TYPE_SYS_INFO;
	}

	public String toString() {
	    final String TAB = "    ";
	    String retValue = "";
	    
	    retValue = "SystemInformation ( "
	        + "model = " + this.model + TAB
	        + "major = " + this.major + TAB
	        + "minor = " + this.minor + TAB
	        + "revision = " + this.revision + TAB
	        + "phone = " + this.phone + TAB
	        + " )";
	
	    return retValue;
	}

}
