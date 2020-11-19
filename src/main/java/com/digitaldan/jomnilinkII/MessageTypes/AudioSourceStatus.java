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
public class AudioSourceStatus implements Message {

	private final int sourceNumber;
	private final int sequenceNumber;
	private final int position;
	private final int fieldId;
	private final String sourceData;

	/*
	 * AUDIO SOURCE STATUS This message is sent by the HAI controller in response to
	 * a REQUEST AUDIO SOURCE STATUS message. Start character 0x21 Message length
	 * source data length (exclusive of terminating zero) + 7 Message type 0x31 Data
	 * 1 source number (MSB) Data 1 source number (LSB) Data 2 sequence number
	 * (0-255) Data 3 position (1-6) Data 4 field ID Data 5 first byte of source
	 * data Data 6 second byte of source data ... Data n-1 last byte of source data
	 * Data n terminating zero CRC 1 varies CRC 2 varies
	 */

	@Override
	public int getMessageType() {
		return MESG_TYPE_AUDIO_SOURCE_STATUS;
	}

}
