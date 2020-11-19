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

import lombok.Builder;
import lombok.Singular;
import lombok.Value;

@Value
@Builder
public class ConnectedSecurityStatus implements Message {

	@Singular
	private final List<Partition> partitions;
	/*
	 * CONNECTED SECURITY SYSTEM STATUS This message is sent in response to a
	 * REQUEST CONNECTED SECURITY SYSTEM STATUS message. Start character 0x21
	 * Message length number of data bytes + 1 Message Type 0x2E Data 1 mode in
	 * partition 1 Data 2 status of partition 1 Data 3 mode in partition 2 Data 4
	 * status of partition 2 ... Data 15 mode in partition 8 Data 16 status of
	 * partition 8 CRC 1 varies CRC 2 varies
	 */

	@Override
	public int getMessageType() {
		return MESG_TYPE_SYS_STATUS;
	}

	@Value
	public static class Partition {
		private final int mode;
		private final int status;

		public Partition(int mode, int status) {
			this.mode = mode;
			this.status = status;
		}
	}

}
