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

public class AudioSourceStatus implements Message {
	private int sourceNumber;
	private int sequenceNumber;
	private int position;
	private int fieldId;
	private String sourceData;


	/*
	 * AUDIO SOURCE STATUS
This message is sent by the HAI controller in response to a REQUEST AUDIO SOURCE STATUS message.
        Start character           0x21
        Message length            source data length (exclusive of terminating zero) + 7
        Message type              0x31
        Data 1                    source number (MSB)
        Data 1                    source number (LSB)
        Data 2                    sequence number (0-255)
        Data 3                    position (1-6)
        Data 4                    field ID
        Data 5                    first byte of source data
        Data 6                    second byte of source data
        ...
        Data n-1                  last byte of source data
        Data n                    terminating zero
        CRC 1                     varies
        CRC 2                     varies
	 */

	public AudioSourceStatus(int sourceNumber, int sequenceNumber,
			int position, int fieldId, String sourceData) {
		super();
		this.sourceNumber = sourceNumber;
		this.sequenceNumber = sequenceNumber;
		this.position = position;
		this.fieldId = fieldId;
		this.sourceData = sourceData;
	}
	
	public int getSourceNumber() {
		return sourceNumber;
	}


	public int getSequenceNumber() {
		return sequenceNumber;
	}


	public int getPosition() {
		return position;
	}


	public int getFieldId() {
		return fieldId;
	}


	public String getSourceData() {
		return sourceData;
	}
	
	public int getMessageType() {
		return MESG_TYPE_AUDIO_SOURCE_STATUS;
	}

	public String toString() {
	    final String TAB = "    ";
	    String retValue = "";
	    
	    retValue = "AudioSourceStatus ( "
	        + "sourceNumber = " + this.sourceNumber + TAB
	        + "sequenceNumber = " + this.sequenceNumber + TAB
	        + "position = " + this.position + TAB
	        + "fieldId = " + this.fieldId + TAB
	        + "sourceData = " + this.sourceData + TAB
	        + " )";
	
	    return retValue;
	}
}
