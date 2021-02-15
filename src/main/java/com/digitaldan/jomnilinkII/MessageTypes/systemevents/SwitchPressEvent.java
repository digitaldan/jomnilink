/**
 * Copyright (c) 2009-2021 Dan Cunningham
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package com.digitaldan.jomnilinkII.MessageTypes.systemevents;

/*
 * ALC, UPB, RADIO RA,       1111 ssss uuuu uuuu     s = switch
 * OR STARLITE                                           0 = off
 * SWITCH PRESS                                          1 = on
 *                                                       2-11 = switch 1-10
 *                                                   u = unit number
 */
public class SwitchPressEvent extends SystemEvent {
	public SwitchPressEvent(int event) {
		super(event, SystemEventType.ALC_UPB_RADIORA_STARLITE_SWITCH_PRESS);
	}

	public int getSwitchValue() {
		// 0x0F = 15 = 1111
		return event >> 8 & 0x0f;
	}

	public int getUnitNumber() {
		// 0xFF = 255 = 11111111
		return event & 0xff;
	}
}
