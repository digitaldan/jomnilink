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

/*
 * REQUEST AUDIO SOURCE STATUS
 *
 * This message requests the HAI controller to report the status of an audio
 * source. This is used to report any metadata (album, song, artist, etc.) or
 * other feedback from the specified source.
 *
 *     Start character      0x21
 *     Message length       0x04
 *     Message Type         0x30
 *     Data 1               source number (MSB)
 *     Data 2               source number (LSB)
 *     Data 3               position
 *     CRC 1                varies
 *     CRC 2                varies
 *
 *     Expected Reply       AUDIO SOURCE STATUS
 *
 * The source status is sent as a series of zero or more AUDIO SOURCE STATUS
 * messages, followed by an END OF DATA message. Each AUDIO SOURCE STATUS
 * message contains the data for one field position of metadata (album, song,
 * artist, etc.) or the overall feedback from the source, such as the frequency
 * of a tuner.
 *
 * A field ID is sent as part of each AUDIO SOURCE STATUS message to identify
 * the metadata field that is being reported. If this field ID is zero, it
 * indicates that there is only one field of information, such as a frequency or
 * other status information. The field IDs reported by the HAI controller are
 * the field IDs reported by the audio system. If an END OF DATA message is sent
 * back immediately in response to the REQUEST AUDIO SOURCE STATUS message, no
 * source data is available.
 *
 * If the source status has not yet been obtained, send a REQUEST AUDIO SOURCE
 * STATUS message with the position number set to “0”. When an AUDIO SOURCE
 * STATUS message is received from the controller, it contains a sequence number
 * and position number. Send a second AUDIO SOURCE STATUS message incrementing
 * the position number contained in the AUDIO SOURCE STATUS message by one to
 * cause the HAI controller to send the next field of information. If the AUDIO
 * SOURCE STATUS message contains a different sequence number from the previous
 * message, the metadata in the connected audio system has been updated. Send a
 * REQUEST AUDIO SOURCE STATUS message with the position number set to “0” to
 * get the updated metadata.
 *
 * Sending an AUDIO SOURCE STATUS message with the same position number to the
 * HAI controller will cause the HAI controller to resend its last message.
 */
@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReqAudioSourceStatus implements Message {
	private final int source;
	private final int position;

	@Override
	public int getMessageType() {
		return MESG_TYPE_REQ_AUDIO_SOURCE_STATUS;
	}
}
