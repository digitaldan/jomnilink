package com.digitaldan.jomnilinkII.MessageTypes.statuses;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

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

@Value
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AccessControlReaderLockStatus extends Status {

	private final boolean locked;
	private final int timer;

	/*
	 *ACCESS CONTROL READER LOCK STATUS (Requires Firmware Version 3.0 or Later)
	The controller reports the lock status of an access control reader object or a group of access control reader
	objects. The lock status reported for each reader includes whether the door is locked or unlocked and the
	time remaining if the door was unlocked for a specific time.
	     Start character             0x21
	     Message length              ( 5 * number of readers) + 2
	     Message Type                0x23
	     Data 1                      0x0F
	     Data 2                      reader number for first reader (MSB)
	     Data 3                      reader number for first reader (LSB)
	     Data 4                      lock status of first reader (0=locked, 1=unlocked)
	     Data 5                      unlock timer for first reader (MSB) (seconds remaining)
	     Data 6                      unlock timer for first reader (LSB)
	     Data 7                      reader number for second reader (MSB)
	     Data 8                      reader number for second reader (LSB)
	     Data 9                      lock status of second reader (0=locked, 1=unlocked)
	     Data 10                     unlock timer for second reader (MSB) (seconds remaining)
	     Data 11                     unlock timer for second reader (LSB)
	     ...
	     Data n-4                    reader number for last reader (MSB)
	     Data n-3                    reader number for last reader (LSB)
	     Data n-2                    lock status of last reader (0=locked, 1=unlocked)
	     Data n-1                    unlock timer for last reader (MSB) (seconds remaining)
	     Data n                      unlock timer for last reader (LSB)
	     CRC 1                       varies
	     CRC 2                       varies
	
	
	 */

	@Builder
	private AccessControlReaderLockStatus(int number, boolean locked, int timer) {
		super(number);
		this.locked = locked;
		this.timer = timer;
	}


}
