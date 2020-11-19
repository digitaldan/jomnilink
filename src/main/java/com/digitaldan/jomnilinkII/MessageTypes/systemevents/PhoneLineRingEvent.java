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
 * PHONE LINE RING           0000 0011 0000 0001
 */
public class PhoneLineRingEvent extends SystemEvent {
	public PhoneLineRingEvent(int event) {
		super(event, SystemEventType.PHONE_LINE_RING);
	}
}
