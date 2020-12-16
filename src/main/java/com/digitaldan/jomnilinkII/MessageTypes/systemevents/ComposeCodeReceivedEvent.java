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
 * COMPOSE CODE RECEIVED     0111 ssss hhhh uuuu     s = state
 *                                                       0 = off
 *                                                       1 = on
 *                                                       2-13 = scene A-L
 *                                                   h = compose house code
 *                                                       0-15 = A-P
 *                                                   u = compose unit number
 *                                                       0-15 = 1-16
 */
public class ComposeCodeReceivedEvent extends SystemEvent {
	public ComposeCodeReceivedEvent(int event) {
		super(event, SystemEventType.COMPOSE_CODE_RECEIVED);
	}

	public int getComposeState() {
		// 0x0F = 15 = 1111
		return event & 0x0f;
	}

	public int getComposeHouseCode() {
		// 0x0F = 15 = 1111
		return event >> 4 & 0x0f;
	}

	public int getComposeUnitNumber() {
		// 0x0F = 15 = 1111
		return event >> 8 & 0x0f;
	}
}
