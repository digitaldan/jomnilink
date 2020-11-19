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

/**
*  Copyright (C) 2009  Dan Cunningham
*
* This program is free software; you can redistribute it and/or
* modify it under the terms of the GNU General Public License
* as published by the Free Software Foundation, version 2
* of the License, or (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
*/

import com.digitaldan.jomnilinkII.Message;
import lombok.ToString;

@ToString
public class ReqSystemFormats implements Message {

	private static ReqSystemFormats INSTANCE = new ReqSystemFormats();

	private ReqSystemFormats() {
	}

	public static ReqSystemFormats getInstance() {
		return INSTANCE;
	}

	/*
	 * This message requests the HAI controller to report the configured temperature
	 * format, time format, and date format. Start character 0x21 Message length
	 * 0x01 Message type 0x28 Data none CRC 1 0x01 CRC 2 0x8E Expected reply: SYSTEM
	 * FORMATS
	 * 
	 */

	@Override
	public int getMessageType() {
		return MESG_TYPE_REQ_SYS_FORMATS;
	}

}
