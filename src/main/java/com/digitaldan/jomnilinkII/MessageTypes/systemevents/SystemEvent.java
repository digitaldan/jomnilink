package com.digitaldan.jomnilinkII.MessageTypes.systemevents;

import java.lang.reflect.InvocationTargetException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class SystemEvent {
	private static final Logger logger = LoggerFactory.getLogger(SystemEvent.class);

	protected final int event;
	protected final SystemEventType type;

	protected SystemEvent(int event, SystemEventType type) {
		this.event = event;
		this.type = type;
	}

	public int getEvent() {
		return event;
	}

	public SystemEventType getType() {
		return type;
	}

	public static SystemEvent fromEvent(int number) {
		SystemEventType st = SystemEventType.fromInt(number);
		if (st != null) {
			try {
				return (SystemEvent) st.getSystemEventClass().getDeclaredConstructor(int.class).newInstance(number);
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				logger.warn("Could not create systemEvent from number " + number, e);
			}
		}
		return null;
	}
}