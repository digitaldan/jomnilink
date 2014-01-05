package com.digitaldan.jomnilinkII.MessageTypes;

public class ReqExtenedObjectStatus extends ReqObjectStatus {

	public ReqExtenedObjectStatus(int objectType, int startObject, int endObject) {
		super(objectType, startObject, endObject);
	}

	public int getMessageType() {
		return MESG_TYPE_REQ_EXT_OBJ_STATUS;
	}
}
