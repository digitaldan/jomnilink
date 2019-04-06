package com.digitaldan.jomnilinkII.MessageTypes.systemevents;

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
	public UPBLinkEvent(int event) {
		super(event, SystemEventType.UPB_LINK);
	}

	public int getLinkCommand() {
		return event >> 8 & 0x03;
	}

	public int getLinkNumber() {
		return event & 0xff;
	}
}
