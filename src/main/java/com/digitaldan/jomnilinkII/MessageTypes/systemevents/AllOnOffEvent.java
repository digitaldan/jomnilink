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
 * ALL ON/OFF                0000 0011 111s aaaa     s = state
 *                                                       0 = off
 *                                                       1 = on
 *                                                   a = area
 */
public class AllOnOffEvent extends SystemEvent {
	public AllOnOffEvent(int event) {
		super(event, SystemEventType.ALL_ON_OFF);
	}

	public int getAllOnOffState() {
		// 0x01 = 1 = 1
		return event >> 4 & 0x01;
	}

	public boolean isOn() {
		return getAllOnOffState() > 0;
	}

	public int getArea() {
		// 0x0F = 15 = 1111
		return event & 0x0f;
	}
}
