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
 * OTHER EVENT NOTIFICATIONS
 *
 * When other system events occur, the HAI controller will send the respective
 * event notification messages. Other event notifications are sent when:
 *
 *     • X-10 / UPB / RadioRA signals are received
 *     • Certain trouble conditions are detected
 *     • The phone line changes state
 *     • The cost of energy changes
 *     • The user activates a macro button
 *     • Pro-Link message received
 *     • CentraLite switch is pressed
 *     • Camera trigger activated
 *
 * When these other event notifications are sent, they are sent in the following
 * format:
 *
 *     Start character      0x21
 *     Message length       (2 * number of system events) + 1
 *     Message Type         0x37
 *     Data 1               High byte of oldest system event
 *     Data 1               Low byte of oldest system event
 *     Data 1               High byte of next oldest system event
 *     Data 1               Low byte of next oldest system event
 *     ...
 *     Data n-1             High byte of most recent event
 *     Data n               Low byte of most recent event
 *     CRC 1                varies
 *     CRC 2                varies
 *
 * Each event notification is identified by a unique 16-bit event number. The
 * encoding of these events is shown below. The encoding is shown in binary,
 * with the most-significant bit to the left.
 *
 * USER MACRO BUTTON         0000 0000 bbbb bbbb     b = button number
 *
 * PRO-LINK MESSAGE          0000 0001 0mmm mmmm     m = message number
 *
 * CENTRALITE SWITCH         0000 0001 1sss ssss     s = switch number
 *
 * COMPOSE CODE RECEIVED     0111 ssss hhhh uuuu     s = state
 *                                                       0 = off
 *                                                       1 = on
 *                                                       2-13 = scene A-L
 *                                                   h = compose house code
 *                                                       0-15 = A-P
 *                                                   u = compose unit number
 *                                                       0-15 = 1-16
 *
 * X-10 CODE RECEIVED        0000 11sa hhhh uuuu     s = state
 *                                                       0 = off
 *                                                       1 = on
 *                                                   a = all units flag
 *                                                       0 = one unit only
 *                                                       1 = all on/off
 *                                                   h = X-10 house code
 *                                                       0-15 = A-P
 *                                                   u = X-10 unit number
 *                                                       0-15 = 1-16
 *
 * ALC, UPB, RADIO RA,       1111 ssss uuuu uuuu     s = switch
 * OR STARLITE                                           0 = off
 * SWITCH PRESS                                          1 = on
 *                                                       2-11 = switch 1-10
 *                                                   u = unit number
 *
 * UPB LINK                  1111 11cc nnnn nnnn     c = link command
 *                                                       0 = off (deactivate)
 *                                                       1 = on (activate)
 *                                                       2 = set (store preset)
 *                                                       3 = fade stop
 *                                                   n = link number
 *
 * ALL ON/OFF                0000 0011 111s aaaa     s = state
 *                                                       0 = off
 *                                                       1 = on
 *                                                   a = area
 *
 * PHONE LINE DEAD           0000 0011 0000 0000
 * PHONE LINE RING           0000 0011 0000 0001
 * PHONE LINE OFF HOOK       0000 0011 0000 0010
 * PHONE LINE ON HOOK        0000 0011 0000 0011
 *
 * AC POWER OFF              0000 0011 0000 0100
 * AC POWER RESTORED         0000 0011 0000 0101
 *
 * BATTERY LOW               0000 0011 0000 0110
 * BATTERY OK                0000 0011 0000 0111
 *
 * DCM TROUBLE               0000 0011 0000 0110
 * DCM OK                    0000 0011 0000 1001
 *
 * ENERGY COST LOW           0000 0011 0000 1010
 * ENERGY COST MID           0000 0011 0000 1011
 * ENERGY COST HIGH          0000 0011 0000 1100
 * ENERGY COST CRITICAL      0000 0011 0000 1101
 *
 * CAMERA 1 TRIGGER          0000 0011 0000 1110
 * CAMERA 2 TRIGGER          0000 0011 0000 1111
 * CAMERA 3 TRIGGER          0000 0011 0001 0000
 * CAMERA 4 TRIGGER          0000 0011 0001 0001
 * CAMERA 5 TRIGGER          0000 0011 0001 0010
 * CAMERA 6 TRIGGER          0000 0011 0001 0011
 */
@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OtherEventNotifications implements Message {
	@Singular
	private final List<Integer> notifications;

	@Override
	public int getMessageType() {
		return MESG_TYPE_OTHER_EVENT_NOTIFY;
	}
}
