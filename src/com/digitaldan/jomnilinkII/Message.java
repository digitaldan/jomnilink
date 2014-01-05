
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


package com.digitaldan.jomnilinkII;

public interface Message {
	public static int  MESG_START                      = 0x21; 
	public static int  MESG_TYPE_ACK                   = 0x01;
	public static int  MESG_TYPE_NEG_ACK               = 0x02;
	public static int  MESG_TYPE_END_OF_DATA           = 0x03 ;
	public static int  MESG_TYPE_CLEAR_NAMES        	= 0x0B ;
	public static int  MESG_TYPE_DOWNLOAD_NAMES        = 0x0C ;
	public static int  MESG_TYPE_UPLOAD_NAMES          = 0x0D ;
	public static int  MESG_TYPE_NAME_DATA             = 0x0E ;
	public static int  MESG_TYPE_CLEAR_VOICES         	= 0x0F ;
	public static int  MESG_TYPE_DOWNLOAD_VOICES       = 0x10 ;
	public static int  MESG_TYPE_UPLOAD_VOICES         = 0x11 ;
	public static int  MESG_TYPE_VOICE_DATA            = 0x12 ;
	public static int  MESG_TYPE_SET_TIME              = 0x13 ;
	public static int  MESG_TYPE_COMMAND               = 0x14 ;
	public static int  MESG_TYPE_ENABLE_NOTIFICATIONS	= 0x15 ;
	public static int  MESG_TYPE_REQ_SYS_INFO          = 0x16 ;
	public static int  MESG_TYPE_SYS_INFO              = 0x17 ;
	public static int  MESG_TYPE_REQ_SYS_STATUS        = 0x18 ;
	public static int  MESG_TYPE_SYS_STATUS            = 0x19 ;
	public static int  MESG_TYPE_REQ_SYS_TROUBLES      = 0x1A ;
	public static int  MESG_TYPE_SYS_TROUBLES        	= 0x1B ;
	public static int  MESG_TYPE_REQ_SYS_FEATURES      = 0x1C ;
	public static int  MESG_TYPE_SYS_FEATURES			= 0x1D ;
	public static int  MESG_TYPE_REQ_OBJ_CAPACITY		= 0x1E ;
	public static int  MESG_TYPE_OBJ_CAPACITY			= 0x1F ;
	public static int  MESG_TYPE_REQ_OBJ_PROP			= 0x20 ;
	public static int  MESG_TYPE_OBJ_PROP        		= 0x21 ;
	public static int  MESG_TYPE_REQ_OBJ_STATUS        = 0x22 ;
	public static int  MESG_TYPE_OBJ_STATUS        		= 0x23 ;
	public static int  MESG_TYPE_UPLOAD_EVENT_LOG      = 0x24 ;
	public static int  MESG_TYPE_EVENT_LOG_DATA        = 0x25 ;
	public static int  MESG_TYPE_REQ_SEC_CODE_VALID    = 0x26 ;
	public static int  MESG_TYPE_SEC_CODE_VALID        = 0x27 ;
	public static int  MESG_TYPE_REQ_SYS_FORMATS      	= 0x28 ;
	public static int  MESG_TYPE_SYS_FORMATS          	= 0x29 ;
	public static int  MESG_TYPE_ACT_KEYPAD_EMERGENCY  = 0x2C ;
	public static int  MESG_TYPE_REQ_CONN_SEC_STATUS	= 0x2D ;
	public static int  MESG_TYPE_CONN_SEC_STATUS		= 0x2E ;
	public static int  MESG_TYPE_CONN_SEC_COMMAND		= 0x2F ;
	public static int  MESG_TYPE_REQ_AUDIO_SOURCE_STATUS = 0x30 ;
	public static int  MESG_TYPE_AUDIO_SOURCE_STATUS    = 0x31 ;
	public static int  MESG_TYPE_REQ_EXT_OBJ_STATUS     = 0x3A ;
	public static int  MESG_TYPE_EXT_OBJ_STATUS        	= 0x3B ;
	public static int  MESG_TYPE_OTHER_EVENT_NOTIFY    = 0x37 ;
	public static int  MESG_TYPE_REQ_ZONE_READY        = 0x38 ;
	public static int  MESG_TYPE_ZONE_READY            = 0x39 ;
		
	public static int OBJ_TYPE_ZONE 			= 0x01; 
	public static int OBJ_TYPE_UNIT 			= 0x02;
	public static int OBJ_TYPE_BUTTON 			= 0x03;
	public static int OBJ_TYPE_CODE 			= 0x04; 
	public static int OBJ_TYPE_AREA 			= 0x05;
	public static int OBJ_TYPE_THERMO			= 0x06; 
	public static int OBJ_TYPE_MESG 			= 0x07; 
	public static int OBJ_TYPE_AUX_SENSOR		= 0x08; 
	public static int OBJ_TYPE_AUDIO_SOURCE 	= 0x09; 
	public static int OBJ_TYPE_AUDIO_ZONE 		= 0x0A;
	public static int OBJ_TYPE_EXP		 		= 0x0B;
	public static int OBJ_TYPE_USER_SETTING		= 0x0D;
	public static int OBJ_TYPE_CONTROL_READER	= 0x0E;
	public static int OBJ_TYPE_CONTROL_LOCK		= 0x0F;
	
	public int getMessageType();
}
