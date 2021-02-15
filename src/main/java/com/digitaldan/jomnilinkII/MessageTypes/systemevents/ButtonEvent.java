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
 * USER MACRO BUTTON         0000 0000 bbbb bbbb     b = button number
 */
public class ButtonEvent extends SystemEvent {
	public ButtonEvent(int event) {
		super(event, SystemEventType.BUTTON);
	}

	public int getButtonNumber() {
		// 0xFF = 255 = 11111111
		return event & 0xff;
	}
}
