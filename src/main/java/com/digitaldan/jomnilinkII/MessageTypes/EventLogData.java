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

/*
 * EVENT LOG MESSAGES
 *
 * The HAI controller maintains an event log that records a time stamped listing
 * of significant controller events, such as when the security system is
 * armed/disarmed, alarm activations, and trouble conditions. The event log can
 * store a fixed number of events. Once the event log is full, logging a new
 * event will cause the oldest event to be lost.
 *
 *     • Read event record
 *     • Event log data
 *
 * EVENT LOG DATA
 *
 *     Start character      0x21
 *     Message length       0x0C
 *     Message Type         0x25
 *     Data 1               event number (MSB)
 *     Data 2               event number (LSB)
 *     Data 3               time/date valid
 *     Data 4               month (1-12)
 *     Data 5               day (1-31)
 *     Data 6               hour (0-23)
 *     Data 7               minute (0-59)
 *     Data 8               event type
 *     Data 9               parameter 1
 *     Data 10              high byte of parameter 2
 *     Data 11              low byte of parameter 2
 *     CRC 1                varies
 *     CRC 2                varies
 *
 * The highest numbered event is the most recent event. When the event number
 * counter (Data 1 and Data 2) reaches 65535, when the next event occurs, the
 * counter rolls over to 1.
 *
 * The month, day, hour, and minute specify the time that the event occurred.
 * The time/date valid flag is zero if the controller time was not set when the
 * event occurred. In this case, the month, day, hour, and minute fields do not
 * contain valid data and should not be used. The time/date valid flag is
 * non-zero when the time has been properly set in the controller.
 *
 * The event, parameter 1, and parameter 2 identify the specific event that has
 * occurred. The possible events are shown in the “Event Log Event Types”
 * tables. When a security code is specified, the value is the user code number
 * rather than the actual four-digit security code.
 *
 * In addition to the user codes, the following security codes can be reported:
 *
 *     251                  Duress code
 *     252                  Keyswitch
 *     253                  Quick arm
 *     254                  PC Access
 *     255                  Programmed
 *
 * LUMINA SERIES EVENT LOG EVENT TYPES
 *
 * ------------------------------------------------------------------------------
 * | Event | Parameter 1 | Parameter 2 |                                        |
 * | Type  |     P1      |     P2      |              Description               |
 * |-------|-------------|-------------|----------------------------------------|
 * | 48+m  | 1           | 0-n         | set mode m with code P1                |
 * |       |             |             | m = mode:                              |
 * |       |             |             | 1 = home mode                          |
 * |       |             |             | 2 = sleep mode                         |
 * |       |             |             | 3 = away mode                          |
 * |       |             |             | 4 = vacation mode                      |
 * |       |             |             | 5 = party mode                         |
 * |       |             |             | 6 = special mode                       |
 * |-------|-------------|-------------|----------------------------------------|
 * | 128   |             | 1-n         | zone P2 tripped                        |
 * |-------|-------------|-------------|----------------------------------------|
 * | 129   |             | 1-n         | zone P2 trouble                        |
 * |-------|-------------|-------------|----------------------------------------|
 * | 130   | 1-n         |             | remote phone access with code P1       |
 * |-------|-------------|-------------|----------------------------------------|
 * | 131   |             |             | remote phone lockout                   |
 * |-------|-------------|-------------|----------------------------------------|
 * | 133   |             | 1-n         | zone P2 trouble cleared                |
 * |-------|-------------|-------------|----------------------------------------|
 * | 134   | 1-n         |             | PC access with code P1                 |
 * |-------|-------------|-------------|----------------------------------------|
 * | 135   | 1-n         | 1           | alarm P1 activated                     |
 * |       |             |             | 5 = freeze                             |
 * |       |             |             | 6 = water                              |
 * |       |             |             | 8 = temperature                        |
 * |-------|-------------|-------------|----------------------------------------|
 * | 136   | 1-n         | 1           | alarm P1 reset                         |
 * |       |             |             | 5 = freeze                             |
 * |       |             |             | 6 = water                              |
 * |       |             |             | 8 = temperature                        |
 * |-------|-------------|-------------|----------------------------------------|
 * | 137   |             |             | system reset                           |
 * |-------|-------------|-------------|----------------------------------------|
 * | 138   |             | 1-n         | message P2 logged                      |
 * |-------|-------------|-------------|----------------------------------------|
 * | 140   | 1-n         | 1-n         | access granted to user P1 at reader P2 |
 * |-------|-------------|-------------|----------------------------------------|
 * | 141   | 1-n         | 1-n         | access denied to user P1 at reader P2  |
 * ------------------------------------------------------------------------------
 *
 * OMNI SERIES EVENT LOG EVENT TYPES
 *
 * ------------------------------------------------------------------------------
 * | Event | Parameter 1 | Parameter 2 |                                         |
 * | Type  |     P1      |     P2      |              Description                |
 * |-------|-------------|-------------|-----------------------------------------|
 * | 4     | 1-n         | 1-n         | zone P2 bypassed with code P1           |
 * |-------|-------------|-------------|-----------------------------------------|
 * | 5     | 1-n         | 1-n         | zone P2 restored with code P1           |
 * |-------|-------------|-------------|-----------------------------------------|
 * | 6     | 1-n         | 0-n         | all area P2 zones restored with code P1 |
 * |       |             |             | P2 = 0 means all areas/zones            |
 * |-------|-------------|-------------|-----------------------------------------|
 * | 48+m  | 1           | 0-n         | area P2 armed in mode m with code P1    |
 * |       |             |             | P2 = 0 means all areas                  |
 * |       |             |             | m = security mode:                      |
 * |       |             |             | 0 = disarm                              |
 * |       |             |             | 1 = day mode                            |
 * |       |             |             | 2 = night mode                          |
 * |       |             |             | 3 = away mode                           |
 * |       |             |             | 4 = vacation mode                       |
 * |       |             |             | 5 = day instant mode                    |
 * |       |             |             | 6 = night delayed mode                  |
 * |-------|-------------|-------------|-----------------------------------------|
 * | 128   |             | 1-n         | zone P2 tripped                         |
 * |-------|-------------|-------------|-----------------------------------------|
 * | 129   |             | 1-n         | zone P2 trouble                         |
 * |-------|-------------|-------------|-----------------------------------------|
 * | 130   | 1-n         |             | remote phone access with code P1        |
 * |-------|-------------|-------------|-----------------------------------------|
 * | 131   |             |             | remote phone lockout                    |
 * |-------|-------------|-------------|-----------------------------------------|
 * | 132   |             | 1-n         | zone P2 auto bypassed                   |
 * |-------|-------------|-------------|-----------------------------------------|
 * | 133   |             | 1-n         | zone P2 trouble cleared                 |
 * |-------|-------------|-------------|-----------------------------------------|
 * | 134   | 1-n         |             | PC access with code P1                  |
 * |-------|-------------|-------------|-----------------------------------------|
 * | 135   | 1-n         | 1-n         | alarm P1 activated in area P2           |
 * |       |             |             | 1 = burglary                            |
 * |       |             |             | 2 = fire                                |
 * |       |             |             | 3 = gas                                 |
 * |       |             |             | 4 = auxiliary                           |
 * |       |             |             | 5 = freeze                              |
 * |       |             |             | 6 = water                               |
 * |       |             |             | 7 = duress                              |
 * |       |             |             | 8 = temperature                         |
 * |-------|-------------|-------------|-----------------------------------------|
 * | 135   | 1-n         | 1-n         | alarm P1 reset in area P2               |
 * |       |             |             | 1 = burglary                            |
 * |       |             |             | 2 = fire                                |
 * |       |             |             | 3 = gas                                 |
 * |       |             |             | 4 = auxiliary                           |
 * |       |             |             | 5 = freeze                              |
 * |       |             |             | 6 = water                               |
 * |       |             |             | 7 = duress                              |
 * |       |             |             | 8 = temperature                         |
 * |-------|-------------|-------------|-----------------------------------------|
 * | 137   |             |             | system reset                            |
 * |-------|-------------|-------------|-----------------------------------------|
 * | 138   |             | 1-n         | message P2 logged                       |
 * |-------|-------------|-------------|-----------------------------------------|
 * | 140   | 1-n         | 1-n         | access granted to user P1 at reader P2  |
 * |-------|-------------|-------------|-----------------------------------------|
 * | 141   | 1-n         | 1-n         | access denied to user P1 at reader P2   |
 * -------------------------------------------------------------------------------
 */
@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EventLogData implements Message {
	private final int eventNumber;
	private final boolean timeDataValid;
	private final int month;
	private final int day;
	private final int hour;
	private final int minute;
	private final int eventType;
	private final int parameter1;
	private final int parameter2;

	@Override
	public int getMessageType() {
		return MESG_TYPE_EVENT_LOG_DATA;
	}
}
