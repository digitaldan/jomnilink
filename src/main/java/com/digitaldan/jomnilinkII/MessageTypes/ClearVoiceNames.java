/**
 * Copyright (c) 2009-2021 Dan Cunningham
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
 * CLEAR VOICE NAMES
 *
 * If a group of voice names will be written to the HAI controller, first send
 * the CLEAR VOICE NAMES message to the controller. This instructs the
 * controller to clear the voice names of all objects. This is essential to
 * ensure that object voice names that have been removed are cleared from the
 * controller’s memory.
 *
 *     Start character      0x21
 *     Message length       0x01
 *     Message Type         0x0F
 *     Data                 none
 *     CRC 1                0x41
 *     CRC 2                0x94
 */
public class ClearVoiceNames implements Message {
	private static ClearVoiceNames INSTANCE = new ClearVoiceNames();

	public static ClearVoiceNames getInstance() {
		return INSTANCE;
	}

	@Override
	public int getMessageType() {
		return MESG_TYPE_CLEAR_VOICES;
	}
}
