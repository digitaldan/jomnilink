package com.digitaldan.jomnilinkII;

import com.digitaldan.jomnilinkII.MessageTypes.ObjectStatus;
import com.digitaldan.jomnilinkII.MessageTypes.OtherEventNotifications;

public interface NotificationListener {

	public void objectStausNotification(ObjectStatus status);
	public void otherEventNotification(OtherEventNotifications other);
}
