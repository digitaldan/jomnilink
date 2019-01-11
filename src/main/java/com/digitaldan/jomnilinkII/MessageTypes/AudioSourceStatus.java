package com.digitaldan.jomnilinkII.MessageTypes;

/**
*  Copyright (C) 2009  Dan Cunningham
*
* This program is free software; you can redistribute it and/or
* modify it under the terms of the GNU General Public License
* as published by the Free Software Foundation, version 2
* of the License, or (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
*/

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
	 * AUDIO SOURCE STATUS
	This message is sent by the HAI controller in response to a REQUEST AUDIO SOURCE STATUS message.
	    Start character           0x21
	    Message length            source data length (exclusive of terminating zero) + 7
	    Message type              0x31
	    Data 1                    source number (MSB)
	    Data 1                    source number (LSB)
	    Data 2                    sequence number (0-255)
	    Data 3                    position (1-6)
	    Data 4                    field ID
	    Data 5                    first byte of source data
	    Data 6                    second byte of source data
	    ...
	    Data n-1                  last byte of source data
	    Data n                    terminating zero
	    CRC 1                     varies
	    CRC 2                     varies
	 */

	@Override
	public int getMessageType() {
		return MESG_TYPE_AUDIO_SOURCE_STATUS;
	}

}
