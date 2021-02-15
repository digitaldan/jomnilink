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

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

/*
 * AUDIO SOURCE STATUS
 *
 * This message is sent by the HAI controller in response to a REQUEST AUDIO
 * SOURCE STATUS message.
 *
 *     Start character      0x21
 *     Message length       source data length (exclusive of terminating zero) + 7
 *     Message Type         0x31
 *     Data 1               source number (MSB)
 *     Data 2               source number (LSB)
 *     Data 3               sequence number (0-255)
 *     Data 4               position (1-6)
 *     Data 5               field ID
 *     Data 6               first byte of source data
 *     Data 7               second byte of source data
 *     ...
 *     Data n-1             last byte of source data
 *     Data n               terminating zero
 *     CRC 1                varies
 *     CRC 2                vaires
 */
@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AudioSourceStatus implements Message {
	private final int sourceNumber;
	private final int sequenceNumber;
	private final int position;
	private final int fieldId;
	private final String sourceData;

	@Override
	public int getMessageType() {
		return MESG_TYPE_AUDIO_SOURCE_STATUS;
	}
}
