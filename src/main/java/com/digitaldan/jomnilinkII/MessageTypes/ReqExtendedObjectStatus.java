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
