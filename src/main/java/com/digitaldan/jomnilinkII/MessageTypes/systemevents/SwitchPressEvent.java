/**
 * Copyright (c) 2009-2020 Dan Cunningham
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package com.digitaldan.jomnilinkII.MessageTypes.systemevents;

public class SwitchPressEvent extends SystemEvent {

	/**
	 * ALC, UPB, RADIO RA, OR STARLITE SWITCH PRESS 1111 ssss uuuu uuuu s= switch
	 * 0=off 1=on 2-11 = switch 1-10 u = unitTemplate number
	 */
	public SwitchPressEvent(int event) {
		super(event, SystemEventType.ALC_UPB_RADIORA_STARLITE_SWITCH_PRESS);
	}

	public int getSwitchValue() {
		return event >> 8 & 0x0f;
	}

	public int getUnitNumber() {
		return event & 0xff;
	}

}
