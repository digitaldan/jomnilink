package com.digitaldan.jomnilinkII.MessageTypes;

import com.digitaldan.jomnilinkII.Message;

public class DownloadNames implements Message {

	private int objectType;
	private int objectNumber;
	private String name;
	
	public DownloadNames(int objectType, int objectNumber, String name) {
		super();
		this.objectType = objectType;
		this.objectNumber = objectNumber;
		this.name = name;
	}

	public int getMessageType() {
		return MESG_TYPE_DOWNLOAD_NAMES;
	}

	public int getObjectType() {
		return objectType;
	}

	public int getObjectNumber() {
		return objectNumber;
	}

	public String getName() {
		return name;
	}

}
