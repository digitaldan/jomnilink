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
 * PHONE LINE DEAD           0000 0011 0000 0000
 */
public class PhoneLineDeadEvent extends SystemEvent {
	public PhoneLineDeadEvent(int event) {
		super(event, SystemEventType.PHONE_LINE_DEAD);
	}
}
