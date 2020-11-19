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
import com.digitaldan.jomnilinkII.MessageTypes.statuses.Status;

import lombok.Builder;
import lombok.Value;
import lombok.experimental.NonFinal;

@Value
@NonFinal
public class ObjectStatus implements Message {

	private final int statusType;
	private final Status[] statuses;

	/*
	 *
	 * OBJECT STATUS
	 *
	 * This message is sent by the HAI controller in reply to an OBJECT STATUS
	 * message. The HAI controller reports the status for the specified object(s).
	 */

	@Override
	public int getMessageType() {
		return MESG_TYPE_OBJ_STATUS;
	}

	@Builder
	protected ObjectStatus(int statusType, Status[] statuses) {
		this.statusType = statusType;
		this.statuses = statuses;
	}
}
