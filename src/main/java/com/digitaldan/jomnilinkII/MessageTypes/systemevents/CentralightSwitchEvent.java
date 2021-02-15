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
 * CENTRALITE SWITCH         0000 0001 1sss ssss     s = switch number
 */
public class CentralightSwitchEvent extends SystemEvent {
	public CentralightSwitchEvent(int event) {
		super(event, SystemEventType.CENTRALITE_SWITCH);
	}

	public int getSwitchNumber() {
		// 0x7F = 127 = 11111111111
		return event & 0x7f;
	}
}
