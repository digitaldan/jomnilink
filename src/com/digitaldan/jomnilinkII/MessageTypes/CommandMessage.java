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

public class CommandMessage implements Message {

	private int command;
	private int parameter1;
	private int parameter2;
	
	/*
	 *COMMAND MESSAGES
Command message are used to send immediate control commands to an HAI controller or connected security
system.
        •    Controller commands
        •    Connected security system commands
        •    Set time command
        •    Activate keypad emergency
CONTROLLER COMMAND
The CONTROLLER COMMAND message is used to send an immediate control command to the HAI controller.
Commands are provided to control lights, appliances, temperatures, security, messaging, and audio. Each command
follows the same format: a single byte command, followed by a single byte parameter, and then a two byte
secondary parameter. The command message is formatted as follows:
          Start character           0x21
          Message length            0x05
          Message type              0x14
          Data 1                    command (0-255)
          Data 2                    parameter 1 (0-255)
          Data 3                    parameter 2 (MSB)
          Data 4                    parameter 2 (LSB)
          CRC 1                     varies
          CRC 2                     varies
          Expected reply            ACKNOWLEDGE
Command             Parameter 1     Parameter 2         Description
                         P1              P2
     0                  0                1-n            unit P2 off
     0                  1-99             1-n            unit P2 off for P1 seconds
     0                  101-199          1-n            unit P2 off for P1-100 minutes
                        201-218          1-n            unit P2 off for P1-200 hours
     0
     1                  0                1-n            unit P2 on
     1                  1-99             1-n            unit P2 on for P1 seconds
     1                  101-199          1-n            unit P2 on for P1-100 minutes
     1                  201-218          1-n            unit P2 on for P1-200 hours
                                         0-n            area P2 all off (0=all areas)
     2
     3                                   0-n            area P2 all on (0=all areas)
     9                  0-100            1-n            unit P2 lighting level to P1 percent
     101                2-99             1-n            unit Lo9(P2) level Hi7(P2) for P1 seconds
     101                101-199          1-n            unit Lo9(P2) level Hi7(P2) for P1-100 minutes
     101                201-218          1-n            unit Lo9(P2) level Hi7(P2) for P1-200 hours
     10                                  1-n            decrement counter P2
     11                                  1-n            increment counter P2
     12                 0-255            1-n            set counter P2 to P1
                                     Copyright © 2008 Home Automation, Inc.
                                               All Rights Reserved
                                                     Page 36
Note: For ALC extended ramp commands, the unit is stored in the low 9 bits of P2. The level to ramp to (0-100%)
is stored in the high 7 bits of P2. The rate specifies the full excursion (0% to 100% or 100% to 0%) ramp rate.
Smaller excursions will reach the desired level in less time.
Command            Parameter 1      Parameter 2           Description
                        P1              P2
     13                2-99             1-n               unit Lo9(P2) ramp to Hi7(P2) at P1 seconds
     13                101-199          1-n               unit Lo9(P2) ramp to Hi7(P2) at P1-100 minutes
     13                201-210          1-n               unit Lo9(P2) ramp to Hi7(P2) at P1-200 hours
     14                0                1-n               Lightolier Compose unit P2 off
     14                1                1-n               Lightolier Compose unit P2 on
     14                2-13             1-n               Lightolier Compose unit P2 scene A-L, respectively
     15                                 1-n               send request status message to UPB unit P2
     16+s              0                1-n               unit P2 dim s steps (s=1-9)
     16+s              1-99             1-n               unit P2 dim s steps (s=1-9) for P1 seconds
     16+s              101-199          1-n               unit P2 dim s steps (s=1-9) for P1-100 minutes
     16+s              201-218          1-n               unit P2 dim s steps (s=1-9) for P1-200 hours
     32+s              0                1-n               unit P2 brighten s steps (s=1-9)
                                        1-n               unit P2 brighten s steps (s=1-9) for P1 sec
     32+s              1-99
     32+s              101-199          1-n               unit P2 brighten s steps (s=1-9) for P1-100 minutes
     32+s              201-218          1-n               unit P2 brighten s steps (s=1-9) for P1-200 hours
     26                0                1-n               UPB unit Lo9(P2) blink at Hi7(P2)
     26                2-99             1-n               UPB unit Lo9(P2) blink at Hi7(P2) for P1 seconds
     26                101-199          1-n               UPB unit Lo9(P2) blink at Hi7(P2) for P1-100 minutes
     26                201-210          1-n               UPB unit Lo9(P2) blink at Hi7(P2) for P1-200 hours
                                                          Hi7(P2) = blink rate:
                                                            0 = 0.25 s
                                                            1 = 0.50 s
                                                            2 = 1.00 s
                                                            3 = 2.00 s
     27                0                1-n               stop blinking UPB unit P2
     28                                 1-n               UPB link P2 off (deactivate)
     29                                 1-n               UPB link P2 on (activate)
     30                                 1-n               UPB link P2 set (store preset)
     42                                 1-n               CentraLite Scene off
     43                                 1-n               CentraLite Scene on
     44                1-8              1-n               UPB unit P2 LED P1 off
     45                1-8              1-n               UPB unit P2 LED P1 on
     46                                 1-n               RadioRA Phantom Button off
     47                                 1-n               RadioRA Phantom Button on
                                        1-n               scene P2 off (Leviton Scene off command)
     60
     61                                 1-n               scene P2 on (Leviton Scene on command)
     62                                 1-n               scene P2 set (Leviton Scene set command)
                                    Copyright © 2008 Home Automation, Inc.
                                                 All Rights Reserved
                                                        Page 37
For security commands, the code specified must be the user code number rather than the actual four digit security
code. That is, send a 0x05 as the code if user code 5 is being used.
Command           Parameter 1       Parameter 2          Description
                        P1               P2
    48+m               1-n               0-n             arm area P2 in mode m with code P1
                                                         P2 = 0 means all areas
                                                         m = security mode:
                                                            0 = disarm
                                                            1 = day mode
                                                            2 = night mode
                                                            3 = away mode
                                                            4 = vacation mode
                                                            5 = day instant mode
                                                            6 = night delayed mode
    4                  1-n               1-n             bypass zone P2 with code P1
                       1-n               1-n             restore zone P2 with code P1
    5
    6                  1-n               0-n             restore all area P2 zones with code P1
                                                         P2 = 0 means all areas/zones
For Lumina mode commands, the code specified must be the user code number rather than the actual four digit
code. That is, send a 0x05 as the code if user code 5 is being used.
Command           Parameter 1       Parameter 2          Description
                        P1               P2
    48+m               1-n               1               set mode m with code P1
                                                         m = mode:
                                                            1 = home mode
                                                            2 = sleep mode
                                                            3 = away mode
                                                            4 = vacation mode
                                                            5 = party mode
                                                            6 = special mode
    7                                    1-n             execute macro button P2
    8                  0-3                               set energy cost to P1
                                                            0 = low
                                                            1 = mid
                                                            2 = high
                                                            3 = critical
    64                 0                 1-n             energy saver P2 off
    64                 1-99              1-n             energy saver P2 off for P1 seconds
    64                 101-199           1-n             energy saver P2 off for P1-100 minutes
    64                 201-218           1-n             energy saver P2 off for P1-200 hours
                       0                 1-n             energy saver P2 on
    65
    65                 1-99              1-n             energy saver P2 on for P1 seconds
    65                 101-199           1-n             energy saver P2 on for P1-100 minutes
    65                 201-218           1-n             energy saver P2 on for P1-200 hours
                                    Copyright © 2008 Home Automation, Inc.
                                                 All Rights Reserved
                                                       Page 38
For commands 66-70, P2 may be set to zero to indicate "all thermostats" in those controllers that support this
capability.
For the following two commands, temperatures are stored in the Omni temperature format (see Appendix C) where
0 = -40 degC and 255 = 87.5 degC. Thus, 44-180 corresponds to 0 to 122 degF or -18 to 50 degC.
Command           Parameter 1     Parameter 2         Description
                       P1            P2
     66               44-180         0-n             set temp zone P2 low/heat setpoint to P1
     67               44-180         0-n             set temp zone P2 high/cool setpoint to P1
     68               0-3            0-n             set thermostat P2 system mode to P1
                                                         0 = off
                                                         1 = heat
                                                         2 = cool
                                                         3 = auto
     69               0-1            0-n             set thermostat P2 fan mode to P1
                                                         0 = auto
                                                         1 = on
For the following two commands, temperatures are stored in the Omni temperature format (see Appendix C).
Command           Parameter 1     Parameter 2         Description
                       P1            P2
     71               -50 to 50      0-n             raise/lower temp P2 low/heat setting by P1
                                                      P2 = 0 means all thermostats
     72               -50 to 50      0-n             raise/lower temp P2 high/cool setting by P1
                                                      P2 = 0 means all thermostats
                      0/255          0-n             set thermostat P2 hold mode to P1
     70
                                                         0 = off
                                                         255 = hold
     80                              1-n             show message P2 (with beep and LED)
     86               0-2            1-n             show message P2
                                                      P1 = mode
                                                         1 = no beep
                                                         2 = no beep or LED
     81                              1-n             log message P2
     82               0-n            0-n             clear message P2 (0=all)
                                                      if clear all messages, P1 = area (0=all)
                                     1-n             say message P2
     83
     84               1-n            1-n             phone number P1 and say message P2
     85               1-n            1-n             send message P2 out serial port P1
                                  Copyright © 2008 Home Automation, Inc.
                                             All Rights Reserved
                                                    Page 39
Command Parameter 1 Parameter 2       Description
            P1         P2
   102     0-255       0-16           enable/disable console beeper P2
                                      P2 = 0 means all consoles
                                      P1 = mode
                                        0 = disabled
                                        1 = enabled
   103     0-6         0-16           console P2 (0=all consoles) beep P1
                                        0 = off
                                        1 = indefinitely
                                        2 = 1 time
                                        3 = 2 times
                                        4 = 3 times
                                        5 = 4 times
                                        6 = 5 times
   112     0-3         0-n            set audio zone P2 (0=all zones) to P1
                                        0 = off
                                        1 = on
                                        2 = mute off
                                        3 = mute on
   113     0-100       1-n            set audio zone P2 volume to P1 percent
   114     1-n         1-n            set audio zone P2 to audio source P1
   115     1-40        1-n            audio zone P2 select key P1 (see audio key codes table)
                    Copyright © 2008 Home Automation, Inc.
                              All Rights Reserved
                                    Page 40
                                                   Audio Key Codes
                           Russound 1        NuVo 2                                          Xantech 3         Speakercraft 4
  Code      HAI Hi-Fi                                      NuVo Grand Concerto
     1                                                    Power                        Power                  Source select 1
           Power         Power            Power
     2                                                    Source step                  Source select 1        Source select 2
           Source step   Source step      Source step
     3                                                    Vol up                       Source select 2        Source select 3
           Vol up        Vol up           Vol up
     4                                                    Vol down                     Source select 3        Source select 4
           Vol down      Vol down         Vol down
     5                                                    Mute                         Source select 4        Source select 5
           Mute          Mute             Mute
     6                                                    Play / Pause                 Source select 5        Source select 6
                         Play             Play
     7                                                    Stop (not used)              Source select 6        Source select 7
                         Stop             Stop
     8                                                    Pause (not used)             Source select 7        Source select 8
                         Pause            Pause
     9                                                    Previous                     Source select 8
                         Minus            Rewind
    10                                                    Next                         Channel up             Mute
                         Plus             Forward
    11                                                                                 Channel down           Vol Up
                         Previous         Fast rewind     Favorite 1
    12                                                                                 Mute                   Power
                         Next             Fast forward    Favorite 2
    13                                                                                 Play                   Vol Down
                         Record           Continuous      Favorite 3
    14                                                                                 Stop
                         Channel up       Shuffle         Favorite 4
    15                                                                                 Pause
                         Channel down     Group           Favorite 5
    16                                                                                 Rewind
                         Zero             Disc            Favorite 6
    17                                                                                 Forward
                         One              Zero            Favorite 7                                          One
    18                                                                                 Vol up
                         Two              One             Favorite 8                                          Two
    19                                                                                 Vol down
                         Three            Two             Favorite 9                                          Three
    20                   Four             Three                                        Tier 2 power           Four
                                                          Favorite 10
                                                                                       Tier 2 source select 1
    21                   Five             Four            Favorite 11                                         Five
                                                                                       Tier 2 source select 2
    22                   Six              Five            Favorite 12                                         Six
                                                          Ok button down               Tier 2 source select 3
    23                   Seven            Six                                                                 Seven
                                                          Ok button up                 Tier 2 source select 4
    24                   Eight            Seven                                                               Eight
                                                          Play / Pause button down     Tier 2 source select 5
    25                   Nine             Eight                                                               Nine
                                                          Play / Pause button up       Tier 2 source select 6 Track
    26                   Plus ten         Nine
                                                          Previous button down         Tier 2 source select 7 Zero
    27                   Enter            Plus ten
                                                          Previous button up           Tier 2 source select 8 Disc
    28                   Last             Enter
                                                          Next button down             Tier 2 channel up      Random
    29                   Sleep            Hotkey zero
                                                          Next button up               Tier 2 channel down    Repeat
    30                   Guide            Hotkey one
                                                          Power / Mute button down     Tier 2 mute            Bass
    31                   Exit             Hotkey two
                                                          Power / Mute button up       Tier 2 play            Treble
    32                   Info             Hotkey three
                                                          Menu button down             Tier 2 stop            Guide
    33                   Menu             Hotkey four
                                                          Menu button up               Tier 2 pause           Menu
    34                   Menu up          Hotkey five
                                                          Up button down               Tier 2 rewind          Up
    35                   Menu right       Hotkey six
                                                          Up button up                 Tier 2 forward         Left
    36                   Menu down        Hotkey seven
                                                          Down button down                                    Select
    37                   Menu left        Hotkey eight
                                                          Down button up                                      Right
    38                   Select           Hotkey nine
                                                                                                              Down
    39                   Favorite 1
    40                   Favorite 2                                                                           Escape
    41                                                                                                        Info
    42                                                                                                        Rewind
    43                                                                                                        Forward
    44                                                                                                        Pause
    45                                                                                                        Play
    46                                                                                                        Stop
1: applies to Russound CAM and CAV audio systems.
2: applies to NuVo Concerto, Essentia, and Simplese audio systems (Essentia and Simplese responds to Key Codes 1-5 only).
3: applies to Xantech MRC-88 audio system.
4: applies to Speakercraft MZC and Proficient M4 and M6 audio systems.

*/

	public static int CMD_BUTTON = 7;
	public static int CMD_UNIT_OFF = 0;
	public static int CMD_UNIT_ON = 1;
	public static int CMD_UNIT_AREA_ALL_OFF = 2;
	public static int CMD_UNIT_AREA_ALL_ON = 3;
	public static int CMD_UNIT_PERCENT = 9;
	public static int CMD_UNIT_LO9_LEVEL_HIGH7 = 101;
	public static int CMD_UNIT_DECREMENT_COUNTER = 10;
	public static int CMD_UNIT_INCREMENT_COUNTER = 11;
	public static int CMD_UNIT_SET_COUNTER = 12;
	public static int CMD_UNIT_LO9_RAMP_HIGH7 = 13;
	public static int CMD_UNIT_LIGHTOLIER = 14;
	public static int CMD_UNIT_UPB_REQ_STATUS = 15;
	public static int CMD_UNIT_UPB_DIM_STEP_1 = 17;
	public static int CMD_UNIT_UPB_DIM_STEP_2 = 18;
	public static int CMD_UNIT_UPB_DIM_STEP_3 = 19;
	public static int CMD_UNIT_UPB_DIM_STEP_4 = 20;
	public static int CMD_UNIT_UPB_DIM_STEP_5 = 21;
	public static int CMD_UNIT_UPB_DIM_STEP_6 = 22;
	public static int CMD_UNIT_UPB_DIM_STEP_7 = 23;
	public static int CMD_UNIT_UPB_DIM_STEP_8 = 24;
	public static int CMD_UNIT_UPB_DIM_STEP_9 = 25;
	public static int CMD_UNIT_UPB_BRIGHTEN_STEP_1 = 33;
	public static int CMD_UNIT_UPB_BRIGHTEN_STEP_2 = 34;
	public static int CMD_UNIT_UPB_BRIGHTEN_STEP_3 = 35;
	public static int CMD_UNIT_UPB_BRIGHTEN_STEP_4 = 36;
	public static int CMD_UNIT_UPB_BRIGHTEN_STEP_5 = 37;
	public static int CMD_UNIT_UPB_BRIGHTEN_STEP_6 = 38;
	public static int CMD_UNIT_UPB_BRIGHTEN_STEP_7 = 39;
	public static int CMD_UNIT_UPB_BRIGHTEN_STEP_8 = 40;
	public static int CMD_UNIT_UPB_BRIGHTEN_STEP_9 = 41;
	public static int CMD_UNIT_UPB_LO9_BLINK_HIGH7 = 26;
	public static int CMD_UNIT_UPB_STOP_BLINK = 27;
	public static int CMD_UNIT_UPB_LINK_OFF = 28;
	public static int CMD_UNIT_UPB_LINK_ON = 29;
	public static int CMD_UNIT_UPB_LINK_SET = 30;
	public static int CMD_UNIT_CENTRALITE_SCENE_OFF = 42;
	public static int CMD_UNIT_CENTRALITE_SCENE_ON = 43;
	public static int CMD_UNIT_UPB_LED_OFF = 44;
	public static int CMD_UNIT_UPB_LED_ON = 45;
	public static int CMD_UNIT_RADIORA_PHANTOM_BUTTON_OFF = 46;
	public static int CMD_UNIT_RADIORA_PHANTOM_BUTTON_ON = 46;
	public static int CMD_UNIT_LEVITON_SCENE_OFF = 60;
	public static int CMD_UNIT_LEVITON_SCENE_ON = 60;
	public static int CMD_UNIT_LEVITON_SCENE_SET = 60;
	
	public static int CMD_SECURITY_OMNI_DISARM = 48;
	public static int CMD_SECURITY_OMNI_DAY_MODE = 49;
	public static int CMD_SECURITY_OMNI_NIGHT_MODE = 50;
	public static int CMD_SECURITY_OMNI_AWAY_MODE = 51;
	public static int CMD_SECURITY_OMNI_VACATION_MODE = 52;
	public static int CMD_SECURITY_OMNI_DAY_INSTANCE_MODE = 53;
	public static int CMD_SECURITY_OMNI_NIGHT_DELAYED_MODE = 54;
	public static int CMD_SECURITY_BYPASS_ZONE = 4;
	public static int CMD_SECURITY_RESTORE_ZONE = 5;
	public static int CMD_SECURITY_RESTORE_ALL_ZONES;
	public static int CMD_SECURITY_LUMINA_HOME_MODE = 49;
	public static int CMD_SECURITY_LUMINA_SLEEP_MODE = 50;
	public static int CMD_SECURITY_LUMINA_AWAY_MODE = 51;
	public static int CMD_SECURITY_LUMINA_VACATION_MODE = 52;
	public static int CMD_SECURITY_LUMINA_PARTY_MODE = 53;
	public static int CMD_SECURITY_LUMINA_SPECIAL_MODE = 54; 
	
	public static int CMD_ENERGY_SAVER_OFF = 64;
	public static int CMD_ENERGY_SAVER_ON = 65;
	
	public static int CMD_THERMO_SET_HEAT_POINT = 66;
	public static int CMD_THERMO_SET_COOL_POINT = 67;
	public static int CMD_THERMO_SET_SYSTEM_MODE = 68;
	public static int CMD_THERMO_SET_FAN_MODE = 69;
	public static int CMD_THERMO_RAISE_LOWER_HEAT = 71;
	public static int CMD_THERMO_RAISE_LOWER_COOL = 71;
	public static int CMD_THERMO_SET_HOLD_MODE = 70;
	
	public static int CMD_MESSAGE_SHOW_MESSAGE_WITH_BEEP_AND_LED = 80;
	public static int CMD_MESSAGE_SHOW_MESSAGE_WITH_BEEP_OR_LED = 86;
	public static int CMD_MESSAGE_LOG_MESSAGE = 81;
	public static int CMD_MESSAGE_CLEAR_MESSAGE = 82;
	public static int CMD_MESSAGE_SAY_MESSAGE = 83;
	public static int CMD_MESSAGE_PHONE_AND_SAY_MESSAGE = 84;
	public static int CMD_MESSAGE_SEND_MESSAGE_TO_SERIAL_PORT = 85;
	
	public static int CMD_CONSOLE_ENABLE_DISABLE_BEEPER = 102;
	public static int CMD_CONSOLE_BEEP = 102;
	
	public static int CMD_AUDIO_ZONE_SET_ON_AND_MUTE = 112;
	public static int CMD_AUDIO_ZONE_SET_VOLUME = 113;
	public static int CMD_AUDIO_ZONE_SET_SOURCE = 114;
	public static int CMD_AUDIO_ZONE_SELECT_KEY = 115;
	
	
	
	
	
	
	
	
	public CommandMessage(int command, int parameter1, int parameter2) {
		super();
		this.command = command;
		this.parameter1 = parameter1;
		this.parameter2 = parameter2;
	}

	public int getCommand() {
		return command;
	}

	public int getParameter1() {
		return parameter1;
	}

	public int getParameter2() {
		return parameter2;
	}

	public int getMessageType() {
		return MESG_TYPE_COMMAND;
	}

	public String toString() {
	    final String TAB = "    ";
	    String retValue = "";
	    
	    retValue = "CommandMessage ( "
	        + "command = " + this.command + TAB
	        + "parameter1 = " + this.parameter1 + TAB
	        + "parameter2 = " + this.parameter2 + TAB
	        + " )";
	
	    return retValue;
	}
}
