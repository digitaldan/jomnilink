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
public class AreaStatus extends Status {

	private final int mode;
	private final int alarms;
	private final int entryTimer;
	private final int exitTimer;


	/*
	 *AREA STATUS
	The controller reports the status of an area object or group of area objects. The status reported for each
	area includes the area number, mode, alarms, entry timer, and exit timer.
	     Start character            0x21
	     Message length             (6 * number of areas) + 2
	     Message type               0x23
	     Data 1                     0x05
	     Data 2                     area number for first area (MSB)
	     Data 3                     area number for first area (LSB)
	     Data 4                     area mode for first area
	     Data 5                     area alarms for first area
	     Data 6                     entry timer for first area
	     Data 7                     exit timer for first area
	     Data 8                     area number for second area (MSB)
	     Data 9                     area number for second area (LSB)
	     Data 10                    area mode for second area
	     Data 11                    area alarms for first area
	     Data 12                    entry timer for second area
	     Data 13                    exit timer for second area
	     ...
	     Data n-5                   area number for last area (MSB)
	     Data n-4                   area number for last area (LSB)
	     Data n-3                   area mode for last area
	     Data n-2                   area alarms for last area
	     Data n-1                   entry timer for last area
	     Data n                     exit timer for last area
	     CRC 1                      varies
	     CRC 2                      varies
	                        
	For HAI Omni series controllers, the security mode for an area is as follows:
	      0                          Off
	      1                          Day
	      2                          Night
	      3                          Away
	      4                          Vacation
	      5                          Day instant
	      6                          Night delayed
	Bit 3 of the security mode byte will be set during the arming exit delay, resulting in the following
	additional security modes:
	      9                          Arming day
	      10                         Arming night
	      11                         Arming away
	      12                         Arming vacation
	      13                         Arming day instant
	      14                         Arming night delayed
	The bits in the area alarm status bytes are shown below. The corresponding bit is set if the condition is true.
	      0                          Burglary alarm
	      1                          Fire alarm
	      2                          Gas alarm
	      3                          Auxiliary alarm
	      4                          Freeze alarm
	      5                          Water alarm
	      6                          Duress alarm
	      7                          Temperature alarm
	For HAI Lumina series controllers, the mode is as follows:
	      1                          Home
	      2                          Sleep
	      3                          Away
	      4                          Vacation
	      5                          Party
	      6                          Special
	Bit 3 of the security mode byte will be set during the mode change delay, resulting in the following
	additional security modes:
	      9                          Setting home
	      10                         Setting Sleep
	      11                         Setting away
	      12                         Setting vacation
	      13                         Setting party
	      14                         Setting special
	The bits in the area alarm status bytes are shown below. The corresponding bit is set if the condition is true.
	      4                          Freeze alarm
	      5                          Water alarm
	      7                          Temperature alarm
	                        
	 */
	@Builder
	private AreaStatus(int number, int mode, int alarms, int entryTimer, int exitTimer){
		super(number);
		this.mode = mode;
		this.alarms = alarms;
		this.entryTimer = entryTimer;
		this.exitTimer = exitTimer;
	}


}
