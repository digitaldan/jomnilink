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

public class ButtonEvent extends SystemEvent {

	/**
	 * USER MACRO BUTTON 0000 0000 bbbb bbbb b= buttonTemplate number
	 */
	public ButtonEvent(int event) {
		super(event, SystemEventType.BUTTON);
	}

	public int getButtonNumber() {
		return event;
	}
}
