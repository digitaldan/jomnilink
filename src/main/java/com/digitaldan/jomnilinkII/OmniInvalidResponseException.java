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

public class OmniInvalidResponseException extends Exception {

	private Message response;

	public OmniInvalidResponseException(Message response) {
		super();
		this.response = response;
	}

	public OmniInvalidResponseException(String message) {
		super(message);
	}

	public Message getInvalidResponse() {
		return response;
	}

}
