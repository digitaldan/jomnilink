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

public class Camera3TriggerEvent extends SystemEvent {

	/**
	 * CAMERA 3 TRIGGER 0000 0011 0001 0000
	 */
	public Camera3TriggerEvent(int event) {
		super(event, SystemEventType.CAMERA_3_TRIGGER);
	}
}
