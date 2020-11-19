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

/*
 * ZONE PROPERTIES
 *
 *     Data 4               zone status
 *     Data 5               zone loop reading
 *     Data 6               zone type (0-87)
 *     Data 7               zone area (1-8)
 *     Data 8               zone options (0-7)
 *     Data 9               zone name
 *     ...
 *     Data 9               zone name
 *
 * For description of zone status, see ZONE STATUS.
 *
 * The available zone types are as follows:
 *
 * --------------------------------------------------
 * | Zone Type |            Description             |
 * |-----------|------------------------------------|
 * | 0         | Entry/Exit                         |
 * |-----------|------------------------------------|
 * | 1         | Perimeter                          |
 * |-----------|------------------------------------|
 * | 2         | Night Interior                     |
 * |-----------|------------------------------------|
 * | 3         | Away Interior                      |
 * |-----------|------------------------------------|
 * | 4         | Double Entry Delay                 |
 * |-----------|------------------------------------|
 * | 5         | Quadruple Entry Delay              |
 * |-----------|------------------------------------|
 * | 6         | Latching Perimeter                 |
 * |-----------|------------------------------------|
 * | 7         | Latching Night Interior            |
 * |-----------|------------------------------------|
 * | 8         | Latching Away Interior             |
 * |-----------|------------------------------------|
 * | 16        | Panic                              |
 * |-----------|------------------------------------|
 * | 17        | Police Emergency                   |
 * |-----------|------------------------------------|
 * | 18        | Duress                             |
 * |-----------|------------------------------------|
 * | 19        | Tamper                             |
 * |-----------|------------------------------------|
 * | 20        | Latching Tamper                    |
 * |-----------|------------------------------------|
 * | 32        | Fire                               |
 * |-----------|------------------------------------|
 * | 33        | Fire Emergency                     |
 * |-----------|------------------------------------|
 * | 34        | Gas Alarm                          |
 * |-----------|------------------------------------|
 * | 48        | Auxiliary Emergency                |
 * |-----------|------------------------------------|
 * | 49        | Trouble                            |
 * |-----------|------------------------------------|
 * | 54        | Freeze                             |
 * |-----------|------------------------------------|
 * | 55        | Water                              |
 * |-----------|------------------------------------|
 * | 56        | Fire Tamper                        |
 * |-----------|------------------------------------|
 * | 64        | Auxiliary                          |
 * |-----------|------------------------------------|
 * | 65        | Keyswitch Input                    |
 * |-----------|------------------------------------|
 * | 80        | Programmable Energy Saver Module   |
 * |-----------|------------------------------------|
 * | 81        | Outdoor Temperature                |
 * |-----------|------------------------------------|
 * | 82        | Temperature                        |
 * |-----------|------------------------------------|
 * | 83        | Temperature Alarm                  |
 * |-----------|------------------------------------|
 * | 84        | Humidity                           |
 * |-----------|------------------------------------|
 * | 85        | Extended Range Outdoor Temperature |
 * |-----------|------------------------------------|
 * | 86        | Extended Range Temperature         |
 * |-----------|------------------------------------|
 * | 87        | Extended Range Temperature Alarm   |
 * --------------------------------------------------
 *
 * The available zone options are as follows:
 *
 * --------------------------------------------------------------------
 * |   Zone Options   |  0  |  1  |  2  |  3  |  4  |  5  |  6  |  7  |
 * |------------------|-----|-----|-----|-----|-----|-----|-----|-----|
 * | Cross Zoning     | No  | Yes | No  | Yes | No  | Yes | No  | Yes |
 * |------------------|-----|-----|-----|-----|-----|-----|-----|-----|
 * | Swinger Shutdown | No  | No  | Yes | Yes | No  | No  | Yes | Yes |
 * |------------------|-----|-----|-----|-----|-----|-----|-----|-----|
 * | Dial Out Delay   | No  | No  | No  | No  | Yes | Yes | Yes | Yes |
 * --------------------------------------------------------------------
 */
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
