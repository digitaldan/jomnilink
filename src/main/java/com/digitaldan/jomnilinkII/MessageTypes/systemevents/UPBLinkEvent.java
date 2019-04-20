package com.digitaldan.jomnilinkII.MessageTypes.systemevents;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UPBLinkEvent extends SystemEvent {

	/**
	 * UPB LINK
	 * 1111 11cc nnnn nnnn
	 * c= link command
	 *   0 = off (deactivate) 1 = on (activate)
	 *   2 = set (store preset) 3 = fade stop
	 * n = link number
	 *
	 * @param event
	 */

	public enum Command {
		DEACTIVATED(0), ACTIVATED(1), SET(2), FADE_STOP(3);

		private static Map<Integer, Command> lookup = Arrays.stream(values()).collect(Collectors.toMap(Command::getCommandValue, Function.identity()));

		private final int commandValue;

		private int getCommandValue() {
			return commandValue;
		}

		Command(int commandValue) {
			this.commandValue = commandValue;
		}
	}

	public UPBLinkEvent(int event) {
		super(event, SystemEventType.UPB_LINK);
	}

	public Command getLinkCommand() {
		return Command.lookup.get(event >> 8 & 0x03);
	}

	public int getLinkNumber() {
		return event & 0xff;
	}
}
