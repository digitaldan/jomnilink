package com.digitaldan.jomnilinkII.MessageTypes;

import com.digitaldan.jomnilinkII.Message;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DownloadNames implements Message {

	private final int objectType;
	private final int objectNumber;
	private final String name;

	@Override
	public int getMessageType() {
		return MESG_TYPE_DOWNLOAD_NAMES;
	}

}
