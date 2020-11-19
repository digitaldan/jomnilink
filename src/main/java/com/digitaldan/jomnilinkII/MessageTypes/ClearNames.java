/**
 * Copyright (c) 2009-2020 Dan Cunningham
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package com.digitaldan.jomnilinkII.MessageTypes;

import com.digitaldan.jomnilinkII.Message;

/*
 * CLEAR NAMES
 *
 * If a group of names will be written to the HAI controller, first send the
 * CLEAR NAMES message to the controller. This instructs the controller to clear
 * the names of all objects. This is essential to ensure that object names that
 * have been removed are cleared from the controller’s memory.
 *
 *     Start character      0x21
 *     Message length       0x01
 *     Message Type         0x0B
 *     Data                 none
 *     CRC 1                0x40
 *     CRC 2                0x57
 */
public class ClearNames implements Message {
	private static ClearNames INSTANCE = new ClearNames();

	public static ClearNames getInstance() {
		return INSTANCE;
	}

	@Override
	public int getMessageType() {
		return MESG_TYPE_CLEAR_NAMES;
	}
}
