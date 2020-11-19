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

import lombok.ToString;

@ToString
public class NegativeAcknowledge implements Message {

	private static NegativeAcknowledge INSTANCE = new NegativeAcknowledge();

	private NegativeAcknowledge() {
	}

	public static NegativeAcknowledge getInstance() {
		return INSTANCE;
	}

	@Override
	public int getMessageType() {
		return MESG_TYPE_NEG_ACK;
	}

}
