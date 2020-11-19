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
public class OtherEventNotifications implements Message {

	@Singular
	private final List<Integer> notifications;

	@Override
	public int getMessageType() {
		return MESG_TYPE_OTHER_EVENT_NOTIFY;
	}

}
