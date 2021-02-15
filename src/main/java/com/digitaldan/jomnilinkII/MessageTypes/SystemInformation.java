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

import com.digitaldan.jomnilinkII.Message;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

/*
 * SYSTEM INFORMATION
 *
 * This message is sent by the HAI controller in reply to a REQUEST SYSTEM
 * INFORMATION message. The controller reports its model number, software
 * version, and local phone number.
 *
 *     Start character      0x21
 *     Message length       0x1E
 *     Message Type         0x17
 *     Data 1               model number
 *     Data 2               major version
 *     Data 3               minor version
 *     Data 4               revision
 *     Data 5-29            local phone number
 *     CRC 1                varies
 *     CRC 2                varies
 *
 * The model number identifies the controller model, such as Omni IIe,
 * OmniPro II, Omni LTe, Lumina, or Lumina Pro. The following model numbers are
 * defined:
 *
 * ---------------------------------
 * | Model Number |     Model      |
 * |--------------|----------------|
 * | 30           | HAI Omni IIe   |
 * |--------------|----------------|
 * | 16           | HAI OmniPro II |
 * |--------------|----------------|
 * | 38           | HAI Omni LTe   |
 * |--------------|----------------|
 * | 36           | HAI Lumina     |
 * |--------------|----------------|
 * | 37           | HAI Lumina Pro |
 * ---------------------------------
 *
 * The major version, minor version, and revision identify the controller
 * software version. For example, if the software version is 2.16b, the major
 * version would be 0x02, the minor version would be 0x10, and the revision
 * would be 0x02. Revision 0x00 specifies no revision letter, revision 0x01
 * specifies revision “a”, and so on. If the revision is a 2's complement
 * negative number, such as 0xFF, it specifies a prototype revision such as X1
 * or X2. Revision 0xFF specifies revision X1, revision 0xFE specifies revision
 * X2, and so on.
 *
 * The local phone number corresponds to the "MY PHONE NUMBER" setting in the
 * controller. It is an ASCII text string up to 24 characters long, terminated
 * with a trailing 0x00.
 */
@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SystemInformation implements Message {
	private final int model;
	private final int major;
	private final int minor;
	private final int revision;
	private final String phone;

	@Override
	public int getMessageType() {
		return MESG_TYPE_SYS_INFO;
	}
}
