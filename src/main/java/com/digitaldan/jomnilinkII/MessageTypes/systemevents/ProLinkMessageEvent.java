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
 * PRO-LINK MESSAGE          0000 0001 0mmm mmmm     m = message number
 */
public class ProLinkMessageEvent extends SystemEvent {
	public ProLinkMessageEvent(int event) {
		super(event, SystemEventType.PROLINK_MESSAGE);
	}

	public int getMessageNumber() {
		// 0x7F = 1111111 = 127
		return event & 0x7f;
	}
}
