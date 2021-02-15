/**
 * Copyright (c) 2009-2021 Dan Cunningham
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

import lombok.*;

/*
 * SYSTEM FEATURES
 *
 * This message is sent by the HAI controller in reply to a REQUEST SYSTEM
 * FEATURES message. The controller reports any enabled features. If multiple
 * features are enabled, each feature is reported in s separate data byte.
 *
 *     Start character      0x21
 *     Message length       number of features + 1
 *     Message Type         0x1D
 *     Data 1               first feature
 *     ...
 *     Data n               last feature
 *     CRC 1                varies
 *     CRC 2                varies
 *
 * The available system features are as follows:
 *
 * -----------------------------------------
 * | Feature Byte |     System Feature     |
 * |--------------|------------------------|
 * | 1            | NuVo Concerto          |
 * |--------------|------------------------|
 * | 2            | NuVo Essentia/Simplese |
 * |--------------|------------------------|
 * | 3            | NuVo Grand Concerto    |
 * |--------------|------------------------|
 * | 4            | Russound               |
 * |--------------|------------------------|
 * | 5            | HAI Hi-Fi              |
 * |--------------|------------------------|
 * | 6            | Xantech                |
 * |--------------|------------------------|
 * | 7            | Speakercraft           |
 * |--------------|------------------------|
 * | 8            | Proficient             |
 * |--------------|------------------------|
 * | 9            | DSC Security           |
 * -----------------------------------------
 */
@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SystemFeatures implements Message {
	@Singular
	private final List<Integer> features;

	@Override
	public int getMessageType() {
		return MESG_TYPE_SYS_FEATURES;
	}
}
