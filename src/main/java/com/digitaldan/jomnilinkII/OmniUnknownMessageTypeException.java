/**
 * Copyright (c) 2009-2021 Dan Cunningham
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package com.digitaldan.jomnilinkII;

public class OmniUnknownMessageTypeException extends Exception {
	private int type;

	public OmniUnknownMessageTypeException(int type) {
		super();
		this.type = type;
	}

	public OmniUnknownMessageTypeException(String message) {
		super(message);
	}

	public int getUnknowMessageType() {
		return type;
	}
}
