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

public class Camera4TriggerEvent extends SystemEvent {

	/**
	 * CAMERA 4 TRIGGER 0000 0011 0001 0001
	 */
	public Camera4TriggerEvent(int event) {
		super(event, SystemEventType.CAMERA_4_TRIGGER);
	}
}
