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
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UploadEventRecord implements Message {

	private final int eventNumber;
	private final int direction;

	/*
	 * UPLOAD EVENT RECORD
	 *
	 * The event number specifies the event record that is being requested
	 * (0-65535). The event number is used in conjunction with the relative
	 * direction (offset) value to determine which event in the list will be sent. A
	 * special case event number of 0 is used to receive the most recent or oldest
	 * event. If the event number is 0 and the relative direction is -1, the
	 * controller will return the most recent event (along with the event number).
	 * The returned event number along with the relative direction of -1 can be used
	 * to request the next most recent event. If the event number is 0 and the
	 * relative direction is 1, the controller will return the oldest event. The
	 * returned event number along with the relative direction of 1 can be used to
	 * request the second oldest event.
	 *
	 * If the offset is 0, the controller will return the specified event record. If
	 * the offset is -1, the controller will return the event record before the
	 * specified event number. If the offset is 1, the controller will return the
	 * event record after the specified event number.
	 *
	 * Start character 0x21 Message length 0x04 Message type 0x24 Data 1 event
	 * number (MSB) Data 2 event number (LSB) Data 3 relative direction (-1, 0, 1)
	 * CRC 1 varies CRC 2 varies
	 *
	 */
	@Override
	public int getMessageType() {
		return MESG_TYPE_UPLOAD_EVENT_LOG;
	}

}
