/**
 * Copyright (c) 2009-2020 Dan Cunningham
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package com.digitaldan.jomnilinkII;

public class OmniNotConnectedException extends Exception {
	private Exception exception;

	public OmniNotConnectedException(Exception exception) {
		this.exception = exception;
	}

	public OmniNotConnectedException(String message) {
		super(message);
	}

	public OmniNotConnectedException(Throwable cause) {
		super(cause);
	}

	public OmniNotConnectedException(String message, Throwable cause) {
		super(message, cause);
	}

	public Exception getNotConnectedReason() {
		return exception;
	}
}
