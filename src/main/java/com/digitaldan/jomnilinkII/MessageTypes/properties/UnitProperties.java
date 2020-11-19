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
public class UnitProperties extends ObjectProperties {
	public static final int UNIT_TYPE_STANDARD = 1;
	public static final int UNIT_TYPE_COMPOSE = 3;
	public static final int UNIT_TYPE_UPB = 4;
	public static final int UNIT_TYPE_HLC_ROOM = 5;
	public static final int UNIT_TYPE_HLC_LOAD = 6;
	public static final int UNIT_TYPE_LUMINA_MODE = 7;
	public static final int UNIT_TYPE_RADIORA = 8;
	public static final int UNIT_TYPE_CENTRALITE = 9;
	public static final int UNIT_TYPE_VIZIARF_ROOM = 10;
	public static final int UNIT_TYPE_VIZIARF_LOAD = 11;
	public static final int UNIT_TYPE_FLAG = 12;
	public static final int UNIT_TYPE_OUTPUT = 13;
	public static final int UNIT_TYPE_AUDIO_ZONE = 14;
	public static final int UNIT_TYPE_AUDIO_SRC = 15;

	private final int state;
	private final int time;
	private final int unitType;

	@Builder
	private UnitProperties(int number, int state, int time, int unitType, String name) {
		super(OBJ_TYPE_UNIT, number, name);
		this.state = state;
		this.time = time;
		this.unitType = unitType;
	}

}
