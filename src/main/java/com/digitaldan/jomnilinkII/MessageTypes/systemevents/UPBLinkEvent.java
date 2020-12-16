/**
 * Copyright (c) 2009-2020 Dan Cunningham
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package com.digitaldan.jomnilinkII.MessageTypes.systemevents;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
 * UPB LINK                  1111 11cc nnnn nnnn     c = link command
 *                                                       0 = off (deactivate)
 *                                                       1 = on (activate)
 *                                                       2 = set (store preset)
 *                                                       3 = fade stop
 *                                                   n = link number
 */
public class UPBLinkEvent extends SystemEvent {
	public enum Command {
		DEACTIVATED(0), ACTIVATED(1), SET(2), FADE_STOP(3);

		private final int commandValue;

		private int getCommandValue() {
			return commandValue;
		}

		private static Map<Integer, Command> lookup = Arrays.stream(values())
				.collect(Collectors.toMap(Command::getCommandValue, Function.identity()));

		Command(int commandValue) {
			this.commandValue = commandValue;
		}
	}

	public UPBLinkEvent(int event) {
		super(event, SystemEventType.UPB_LINK);
	}

	public Command getLinkCommand() {
		// 0x03 = 3 = 11
		return Command.lookup.get(event >> 8 & 0x03);
	}

	public int getLinkNumber() {
		// 0xFF = 255 = 11111111
		return event & 0xff;
	}
}
