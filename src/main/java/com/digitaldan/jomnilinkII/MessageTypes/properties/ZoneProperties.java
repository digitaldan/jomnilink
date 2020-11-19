/**
 * Copyright (c) 2009-2020 Dan Cunningham
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
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ZoneProperties extends ObjectProperties {
	private final int status;
	private final int loop;
	private final int zoneType;
	private final int area;
	private final int options;

	@Builder
	private ZoneProperties(int number, int status, int loop, int zoneType, int area, int options, String name) {
		super(OBJ_TYPE_ZONE, number, name);
		this.status = status;
		this.loop = loop;
		this.zoneType = zoneType;
		this.area = area;
		this.options = options;
	}

}
