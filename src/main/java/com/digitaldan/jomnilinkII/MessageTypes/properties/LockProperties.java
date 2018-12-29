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
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;


@Value
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class LockProperties extends ObjectProperties {
	private final boolean locked;
	private final int unlockTimer;
	private final boolean accessDenied;
	private final int lastUser;

	@Builder
	private LockProperties(int number, boolean locked, int unlockTimer, boolean accessDenied, int lastUser, String name) {
		super(OBJ_TYPE_AREA, number, name);
		this.locked = locked;
		this.unlockTimer = unlockTimer;
		this.accessDenied = accessDenied;
		this.lastUser = lastUser;
	}

}
