/**
* Copyright (c) 2009-2020 Dan Cunningham
*
* This program and the accompanying materials are made available under the
* terms of the Eclipse Public License 2.0 which is available at
* http://www.eclipse.org/legal/epl-2.0
*
* SPDX-License-Identifier: EPL-2.0
*/
package com.digitaldan.jomnilinkII.MessageTypes;

import com.digitaldan.jomnilinkII.Message;

import lombok.ToString;

@ToString
public class ReqSystemStatus implements Message {

	private static ReqSystemStatus INSTANCE = new ReqSystemStatus();

	private ReqSystemStatus() {
	}

	public static ReqSystemStatus getInstance() {
		return INSTANCE;
	}

	/*
	 * This message requests the HAI controller to report its time, date, calculated
	 * time of sunrise and sunset, battery reading, alarm status for any area that
	 * is in alarm. Start character 0x21 Message length 0x01 Message type 0x18 Data
	 * none CRC 1 0x01 CRC 2 0x9A Expected reply SYSTEM STATUS
	 * 
	 */

	@Override
	public int getMessageType() {
		return MESG_TYPE_REQ_SYS_STATUS;
	}

}
