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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OmniInvalidResponseException extends Exception {
	private final Logger logger = LoggerFactory.getLogger(OmniInvalidResponseException.class);

	private Message response;

	public OmniInvalidResponseException(Message response) {
		super();
		this.response = response;
		logger.error("Invalid message type received: {}", response.toString());
	}

	public OmniInvalidResponseException(String message) {
		super(message);
	}

	public Message getInvalidResponse() {
		return response;
	}
}
