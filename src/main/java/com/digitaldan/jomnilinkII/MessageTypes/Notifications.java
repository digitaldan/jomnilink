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

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

/*
 * ENABLE NOTIFICATIONS
 *
 * The ENABLE NOTIFICATIONS message requests the HAI controller to send event
 * notifications as they occur. If the ENABLE NOTIFICATIONS feature is disabled,
 * the HAI controller will not send event data.
 *
 *     Start character      0x21
 *     Message length       0x02
 *     Message Type         0x15
 *     Data 1               enable byte (0=disable, 1=enable)
 *     CRC 1                varies
 *     CRC 2                varies
 *
 *     Expected Reply       ACKNOWLEDGE
 *
 * NOTE notifiations that come back have a SEQ of 00
 */
@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Notifications implements Message {
	private final static Notifications ENABLED = new Notifications(true);
	private final static Notifications DISABLED = new Notifications(false);

	public static Notifications enable() {
		return ENABLED;
	}

	public static Notifications disable() {
		return DISABLED;
	}

	private final boolean enabled;

	@Override
	public int getMessageType() {
		return MESG_TYPE_ENABLE_NOTIFICATIONS;
	}
}
