package com.digitaldan.jomnilinkII.MessageTypes;

/**
*  Copyright (C) 2009  Dan Cunningham
*
* This program is free software; you can redistribute it and/or
* modify it under the terms of the GNU General Public License
* as published by the Free Software Foundation, version 2
* of the License, or (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
*/

import com.digitaldan.jomnilinkII.Message;
import lombok.*;

import java.util.List;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SystemFeatures implements Message {

	@Singular private final List<Integer> features;

	/*
	 * 
	SYSTEM FEATURES
	This message is sent by the HAI controller in reply to a REQUEST SYSTEM FEATURES message. The controller
	reports any enabled features. If multiple features are enabled, each feature is reported in s separate data byte.
	     Start character            0x21
	     Message length             number of features + 1
	     Message type               0x1D
	     Data 1                     first feature
	     ....
	     Data n                     last feature
	     CRC 1                      varies
	     CRC 2                      varies
	The available system features are as follows:
	Feature Byte           System Feature
	    1         NuVo Concerto
	    2         NuVo Essentia/Simplese
	    3         NuVo Grand Concerto
	    4         Russound
	    5         HAI Hi-Fi
	    6         Xantech
	    7         Speakercraft
	    8         Proficient
	
	 */

	@Override
	public int getMessageType() {
		return MESG_TYPE_SYS_FEATURES;
	}

}
