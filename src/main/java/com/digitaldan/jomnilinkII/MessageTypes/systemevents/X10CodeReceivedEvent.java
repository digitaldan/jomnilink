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

/*
 * X-10 CODE RECEIVED        0000 11sa hhhh uuuu     s = state
 *                                                       0 = off
 *                                                       1 = on
 *                                                   a = all units flag
 *                                                       0 = one unit only
 *                                                       1 = all on/off
 *                                                   h = X-10 house code
 *                                                       0-15 = A-P
 *                                                   u = X-10 unit number
 *                                                       0-15 = 1-16
 */
public class X10CodeReceivedEvent extends SystemEvent {
	public X10CodeReceivedEvent(int event) {
		super(event, SystemEventType.X10_CODE_RECEIVED);
	}

	public int getState() {
		// 0x01 = 1 = 1
		return event >> 9 & 0x01;
	}

	public int getAllUnitsFlag() {
		// 0x01 = 1 = 1
		return event >> 8 & 0x01;
	}

	public int getHouseCode() {
		// 0x0F = 15 = 1111
		return event >> 4 & 0x0f;
	}
}
