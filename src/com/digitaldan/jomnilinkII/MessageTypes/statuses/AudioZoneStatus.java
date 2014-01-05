package com.digitaldan.jomnilinkII.MessageTypes.statuses;

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



public class AudioZoneStatus extends Status{
	
	private boolean power;
	private int source;
	private int volume;
	private boolean mute;
	
	/*
	 *AUDIO ZONE STATUS
The controller reports the status of an audio zone object or a group of audio zone objects. The status
reported for each audio zone includes the audio zone number, on/off status of the zone, the selected source
for the zone, the volume, and whether the zone is muted.
          Start character          0x21
         Message length            (6 * number of audio zones) + 2
          Message type             0x23
          Data 1                   0x0A
                                   audio zone number for first audio zone (MSB)
          Data 2
          Data 3                   audio zone number for first audio zone (LSB)
         Data 4                    power on/off for first audio zone (0-1)
         Data 5                    selected source for first audio zone (1-n)
         Data 6                    volume for first audio zone (0-100)
         Data 7                    mute status for first audio zone (0-1)
          Data 8                   audio zone number for second audio zone (MSB)
          Data 9                   audio zone number for second audio zone (LSB)
         Data 10                   power on/off for second audio zone (0-1)
         Data 11                   selected source for second audio zone (1-n)
         Data 12                   volume for second audio zone (0-100)
         Data 13                   mute status for second audio zone (0-1)
         ...
          Data n-5                 audio zone number for last audio zone (MSB)
          Data n-4                 audio zone number for last audio zone (LSB)
         Data n-3                  power on/off for last audio zone (0-1)
         Data n-2                  selected source for last audio zone (1-n)
         Data n-1                  volume for last audio zone (0-100)
         Data n                    mute status for last audio zone (0-1)
         CRC 1                     varies
         CRC 2                     varies
	 */
	public AudioZoneStatus(int number, boolean power, int source,
			int volume, boolean mute) {
		super(number);
		this.power = power;
		this.source = source;
		this.volume = volume;
		this.mute = mute;
	}
	public boolean isPower() {
		return power;
	}
	public int getSource() {
		return source;
	}
	public int getVolume() {
		return volume;
	}
	public boolean isMute() {
		return mute;
	}
	
	public void setPower(boolean power) {
      this.power = power;
   }
   public void setSource(int source) {
      this.source = source;
   }
   public void setVolume(int volume) {
      this.volume = volume;
   }
   public void setMute(boolean mute) {
      this.mute = mute;
   }
   public String toString() {
	    final String TAB = "    ";
	    String retValue = "";
	    
	    retValue = "AudioZoneStatus ( "
	    	+ "number = " + this.number + TAB
	        + "power = " + this.power + TAB
	        + "source = " + this.source + TAB
	        + "volume = " + this.volume + TAB
	        + "mute = " + this.mute + TAB
	        + " )";
	
	    return retValue;
	}
}
