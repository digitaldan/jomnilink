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

import lombok.Builder;
import lombok.experimental.Delegate;

/*
 * REQUEST EXTENDED OBJECT STATUS (Requires Firmware Version 3.0 or Later)
 *
 * Starting with controller firmware 3.0, objects now have an alternate extended
 * status report. The extended status report includes a record length byte that
 * specifies the number of status bytes for each item in the report and possibly
 * additional information for each item that is not included in the standard
 * status report. Any additional information is added to the end of the current
 * status information, so the record length byte can be used to ignore any added
 * information that is not of interest and to skip to the data for the next
 * item.
 *
 * The request is sent using two bytes for each object.
 *
 *     Start character      0x21
 *     Message length       0x06
 *     Message Type         0x3A
 *     Data 1               object type
 *     Data 2               starting object (MSB)
 *     Data 3               starting object (LSB)
 *     Data 4               ending object (MSB)
 *     Data 5               ending object (LSB)
 *     CRC 1                varies
 *     CRC 2                varies
 *
 *     Expected Reply       EXTENDED OBJECT STATUS
 */
public class ReqExtendedObjectStatus implements Message {
	@Delegate(excludes = Message.class)
	private final ReqObjectStatus reqObjectStatus;

	@Builder
	private ReqExtendedObjectStatus(int objectType, int startObject, int endObject) {
		reqObjectStatus = ReqObjectStatus.builder().objectType(objectType).startObject(startObject).endObject(endObject)
				.build();
	}

	@Override
	public int getMessageType() {
		return MESG_TYPE_REQ_EXT_OBJ_STATUS;
	}
}
