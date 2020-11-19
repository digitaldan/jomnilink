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

import java.util.List;

import com.digitaldan.jomnilinkII.Message;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ZoneReadyStatus implements Message {

	@Singular
	private List<Integer> zones;

	/*
	 * ZONE READY STATUS This message is sent in response to a REQUEST ZONE READY
	 * STATUS message. The secure/not ready statuses for eight zones are packed into
	 * one message byte. The status of the lower numbered zone is indicated by bit
	 * 7. Lower order bits indicate the statuses of the higher numbered zones. The
	 * bit corresponding to a zone is set if that zone is not ready. Start character
	 * 0x21 Message length number of data bytes + 1 Message Type 0x39 Data 1 status
	 * of first 8 zones Data 2 status of second 8 zones ... Data n status of last 8
	 * zones CRC 1 varies CRC 2 varies
	 * 
	 */

	@Override
	public int getMessageType() {
		return MESG_TYPE_SYS_STATUS;
	}

}
