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
import com.digitaldan.jomnilinkII.MessageTypes.statuses.Status;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

/*
 * EXTENDED OBJECT STATUS (Requires Firmware Version 3.0 or Later)
 *
 * This message is sent by the HAI controller in reply to an REQUEST EXTENDED
 * OBJECT STATUS message. The HAI controller reports the status for the
 * specified object(s).
 */
@Value
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ExtendedObjectStatus extends ObjectStatus implements Message {
	private final int recordLength;

	@Override
	public int getMessageType() {
		return MESG_TYPE_EXT_OBJ_STATUS;
	}

	@Builder(builderMethodName = "extendedBuilder")
	private ExtendedObjectStatus(int statusType, int recordLength, Status[] statuses) {
		super(statusType, statuses);
		this.recordLength = recordLength;
	}
}
