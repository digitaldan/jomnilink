/**
 * Copyright (c) 2009-2021 Dan Cunningham
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package com.digitaldan.jomnilinkII.MessageTypes.properties;

import com.digitaldan.jomnilinkII.MessageTypes.ObjectProperties;

import lombok.Builder;

/*
 * CODE PROPERTIES
 *
 *     Data 4-16            code name
 */
public class CodeProperties extends ObjectProperties {
	@Builder
	private CodeProperties(int number, String name) {
		super(OBJ_TYPE_CODE, number, name);
	}
}
