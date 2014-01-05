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

public class ReqAudioSourceStatus implements Message {

	private int src;
	private int pos;
	
/*
 * This message requests the HAI controller to report the status of an audio source. This is used to report any metadata
(album, song, artist, etc.) or other feedback from the specified source.
         Start character               0x21
         Message length                0x05
         Message type                  0x30
         Data 1                        source number (1-8)
         Data 2                        position
         CRC 1                         varies
         CRC 2                         varies
         Expected reply                AUDIO SOURCE STATUS

The source status is sent as a series of zero or more AUDIO SOURCE STATUS messages, followed by an END OF
DATA message. Each AUDIO SOURCE STATUS message contains the data for one field position of metadata
(album, song, artist, etc.) or the overall feedback from the source, such as the frequency of a tuner.

A field ID is sent as part of each AUDIO SOURCE STATUS message to identify the metadata field that is being
reported. If this field ID is zero, it indicates that there is only one field of information, such as a frequency or other
status information. The field IDs reported by the HAI controller are the field IDs reported by the audio system. If
an END OF DATA message is sent back immediately in response to the REQUEST AUDIO SOURCE STATUS
message, no source data is available.

If the source status has not yet been obtained, send a REQUEST AUDIO SOURCE STATUS message with the
position number set to “0”. When an AUDIO SOURCE STATUS message is received from the controller, it
contains a sequence number and position number. Send a second AUDIO SOURCE STATUS message
incrementing the position number contained in the AUDIO SOURCE STATUS message by one to cause the HAI
controller to send the next field of information. If the AUDIO SOURCE STATUS message contains a different
sequence number from the previous message, the metadata in the connected audio system has been updated. Send a
REQUEST AUDIO SOURCE STATUS message with the position number set to “0” to get the updated metadata.

Sending an AUDIO SOURCE STATUS message with the same position number to the HAI controller will cause
the HAI controller to resend its last message.

 */
	public ReqAudioSourceStatus(int source, int position){
		src = source;
		pos = position;
	}
	
	public int source(){
		return src;
	}

	public int position(){
		return pos;
	}
	public int getMessageType() {
		return MESG_TYPE_REQ_AUDIO_SOURCE_STATUS;
	}

	public String toString() {
	    final String TAB = "    ";
	    String retValue = "";
	    
	    retValue = "ReqAudioSourceStatus ( "
	        + "src = " + this.src + TAB
	        + "pos = " + this.pos + TAB
	        + " )";
	
	    return retValue;
	}
}
