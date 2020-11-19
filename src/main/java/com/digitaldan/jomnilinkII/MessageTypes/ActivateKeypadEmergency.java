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
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ActivateKeypadEmergency implements Message {

	private final int area;
	private final int emergencyType;

	/*
	 * @Builder private ActivateKeypadEmergency(int area, int emergencyType){
	 * this.area = area; this.emergencyType = emergencyType; }
	 */

	/*
	 * ACTIVATE KEYPAD EMERGENCY
	 * 
	 * This message is used to activate a burglary, fire, or auxiliary keypad
	 * emergency alarm in an area on an Omni IIe or OmniPro II system.
	 * 
	 * Start character 0x21 Message length 0x03 Message type 0x2C Data 1 area (1-8)
	 * Data 2 emergency type (1=burglary, 2=fire, 3=auxiliary) CRC 1 varies CRC 2
	 * varies
	 * 
	 * Expected reply ACKNOWLEDGE
	 * 
	 */

	@Override
	public int getMessageType() {
		return MESG_TYPE_ACT_KEYPAD_EMERGENCY;
	}

}
