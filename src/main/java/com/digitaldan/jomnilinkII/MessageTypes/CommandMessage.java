/**
 * Copyright (c) 2009-2020 Dan Cunningham
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package com.digitaldan.jomnilinkII.MessageTypes;

import com.digitaldan.jomnilinkII.Message;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

/*
 * CONTROLLER COMMAND
 *
 * The CONTROLLER COMMAND message is used to send an immediate control command
 * to the HAI controller. Commands are provided to control lights, appliances,
 * temperatures, security, messaging, and audio. Each command follows the same
 * format: a single byte command, followed by a single byte parameter, and then
 * a two byte secondary parameter. The command message is formatted as follows:
 *
 *     Start character      0x21
 *     Message length       0x05
 *     Message Type         0x14
 *     Data 1               command (0-255)
 *     Data 2               parameter 1 (0-255)
 *     Data 3               parameter 2 (MSB)
 *     Data 4               parameter 2 (LSB)
 *     CRC 1                varies
 *     CRC 2                varies
 *
 *     Expected reply       ACKNOWLEDGE
 *
 * --------------------------------------------------------------------------------------------
 * |         | Parameter 1 | Parameter 2 |                                                    |
 * | Command |     P1      |     P2      |                    Description                     |
 * |---------|-------------|-------------|----------------------------------------------------|
 * | 0       | 0           | 1-n         | unit P2 off                                        |
 * |---------|-------------|-------------|----------------------------------------------------|
 * | 0       | 1-99        | 1-n         | unit P2 off for P1 seconds                         |
 * |---------|-------------|-------------|----------------------------------------------------|
 * | 0       | 101-199     | 1-n         | unit P2 off for P1-100 minutes                     |
 * |---------|-------------|-------------|----------------------------------------------------|
 * | 0       | 201-218     | 1-n         | unit P2 off for P1-200 hours                       |
 * |---------|-------------|-------------|----------------------------------------------------|
 * | 0       | 219-up      | 1-n         | unit P2 off for user setting P1-218                |
 * |---------|-------------|-------------|----------------------------------------------------|
 * | 1       | 0           | 1-n         | unit P2 on                                         |
 * |---------|-------------|-------------|----------------------------------------------------|
 * | 1       | 1-99        | 1-n         | unit P2 on for P1 seconds                          |
 * |---------|-------------|-------------|----------------------------------------------------|
 * | 1       | 101-199     | 1-n         | unit P2 on for P1-100 minutes                      |
 * |---------|-------------|-------------|----------------------------------------------------|
 * | 1       | 201-218     | 1-n         | unit P2 on for P1-200 hours                        |
 * |---------|-------------|-------------|----------------------------------------------------|
 * | 1       | 219-up      | 1-n         | unit P2 on for user setting P1-218                 |
 * |---------|-------------|-------------|----------------------------------------------------|
 * | 2       |             | 0-n         | area P2 all off (0=all areas)                      |
 * |---------|-------------|-------------|----------------------------------------------------|
 * | 3       |             | 0-n         | area P2 all on (0=all areas)                       |
 * |---------|-------------|-------------|----------------------------------------------------|
 * | 9       | 0-100       | 1-n         | unit P2 lighting level to P1 percent               |
 * |---------|-------------|-------------|----------------------------------------------------|
 * | 9       | 101-up      | 1-n         | unit P2 lighting level to user setting P1-100      |
 * |---------|-------------|-------------|----------------------------------------------------|
 * | 101     | 2-99        | 1-n         | unit Lo9(P2) level Hi7(P2) for P1 seconds          |
 * |---------|-------------|-------------|----------------------------------------------------|
 * | 101     | 101-199     | 1-n         | unit Lo9(P2) level Hi7(P2) for P1-100 minutes      |
 * |---------|-------------|-------------|----------------------------------------------------|
 * | 101     | 201-218     | 1-n         | unit Lo9(P2) level Hi7(P2) for P1-200 hours        |
 * |---------|-------------|-------------|----------------------------------------------------|
 * | 101     | 219-up      | 1-n         | unit Lo9(P2) level Hi7(P2) for user setting P1-218 |
 * |---------|-------------|-------------|----------------------------------------------------|
 * | 10      |             | 1-n         | decrement counter P2                               |
 * |---------|-------------|-------------|----------------------------------------------------|
 * | 11      |             | 1-n         | increment counter P2                               |
 * |---------|-------------|-------------|----------------------------------------------------|
 * | 12      | 0-255       | 1-n         | decrement counter P2                               |
 * --------------------------------------------------------------------------------------------
 *
 * Note: For ALC extended ramp commands, the unit is stored in the low 9 bits of
 * P2. The level to ramp to (0-100%) is stored in the high 7 bits of P2. The
 * rate specifies the full excursion (0% to 100% or 100% to 0%) ramp rate.
 * Smaller excursions will reach the desired level in less time.
 *
 * --------------------------------------------------------------------------------------------------------
 * |         | Parameter 1 | Parameter 2 |                                                                |
 * | Command |     P1      |     P2      |                           Description                          |
 * |---------|-------------|-------------|----------------------------------------------------------------|
 * | 13      | 2-99        | 1-n         | unit Lo9(P2) ramp to Hi7(P2) at P1 seconds                     |
 * |---------|-------------|-------------|----------------------------------------------------------------|
 * | 13      | 101-199     | 1-n         | unit Lo9(P2) ramp to Hi7(P2) at P1-100 minutes                 |
 * |---------|-------------|-------------|----------------------------------------------------------------|
 * | 13      | 201-210     | 1-n         | unit Lo9(P2) ramp to Hi7(P2) at P1-200 hours                   |
 * |---------|-------------|-------------|----------------------------------------------------------------|
 * | 13      | 219-up      | 1-n         | unit Lo9(P2) ramp to Hi7(P2) at user setting P1-218            |
 * |---------|-------------|-------------|----------------------------------------------------------------|
 * | 14      | 0           | 1-n         | HLC Room or Lightolier Compose unit P2 off                     |
 * |---------|-------------|-------------|----------------------------------------------------------------|
 * | 14      | 1           | 1-n         | HLC Room or Lightolier Compose unit P2 on                      |
 * |---------|-------------|-------------|----------------------------------------------------------------|
 * | 14      | 2-13        | 1-n         | HLC Room or Lightolier Compose unit P2 scene A-L, respectively |
 * |---------|-------------|-------------|----------------------------------------------------------------|
 * | 15      |             | 1-n         | send request status message to UPB unit P2                     |
 * |---------|-------------|-------------|----------------------------------------------------------------|
 * | 16+s    | 0           | 1-n         | unit P2 dim s steps (s=1-9)                                    |
 * |---------|-------------|-------------|----------------------------------------------------------------|
 * | 16+s    | 1-99        | 1-n         | unit P2 dim s steps (s=1-9) for P1 seconds                     |
 * |---------|-------------|-------------|----------------------------------------------------------------|
 * | 16+s    | 101-199     | 1-n         | unit P2 dim s steps (s=1-9) for P1-100 minutes                 |
 * |---------|-------------|-------------|----------------------------------------------------------------|
 * | 16+s    | 201-218     | 1-n         | unit P2 dim s steps (s=1-9) for P1-200 hours                   |
 * |---------|-------------|-------------|----------------------------------------------------------------|
 * | 16+s    | 219-up      | 1-n         | unit P2 dim s steps (s=1-9) for user setting P1-218            |
 * |---------|-------------|-------------|----------------------------------------------------------------|
 * | 32+s    | 0           | 1-n         | unit P2 brighten s steps (s=1-9)                               |
 * |---------|-------------|-------------|----------------------------------------------------------------|
 * | 32+s    | 1-99        | 1-n         | unit P2 brighten s steps (s=1-9) for P1 seconds                |
 * |---------|-------------|-------------|----------------------------------------------------------------|
 * | 32+s    | 101-199     | 1-n         | unit P2 brighten s steps (s=1-9) for P1-100 minutes            |
 * |---------|-------------|-------------|----------------------------------------------------------------|
 * | 32+s    | 201-218     | 1-n         | unit P2 brighten s steps (s=1-9) for P1-200 hours              |
 * |---------|-------------|-------------|----------------------------------------------------------------|
 * | 32+s    | 219-up      | 1-n         | unit P2 brighten s steps (s=1-9) for user setting P1-218       |
 * |---------|-------------|-------------|----------------------------------------------------------------|
 * | 26      | 0           | 1-n         | UPB unit Lo9(P2) blink at Hi7(P2)                              |
 * |---------|-------------|-------------|----------------------------------------------------------------|
 * | 26      | 2-99        | 1-n         | UPB unit Lo9(P2) blink at Hi7(P2) for P1 seconds               |
 * |---------|-------------|-------------|----------------------------------------------------------------|
 * | 26      | 101-199     | 1-n         | UPB unit Lo9(P2) blink at Hi7(P2) for P1-100 minutes           |
 * |---------|-------------|-------------|----------------------------------------------------------------|
 * | 26      | 201-218     | 1-n         | UPB unit Lo9(P2) blink at Hi7(P2) for P1-200 hours             |
 * |---------|-------------|-------------|----------------------------------------------------------------|
 * | 26      | 219-up      | 1-n         | UPB unit Lo9(P2) blink at Hi7(P2) for user setting P1-218      |
 * |         |             |             | Hi7(P2) = blink rate:                                          |
 * |         |             |             |   0 = 0.25 s                                                   |
 * |         |             |             |   1 = 0.50 s                                                   |
 * |         |             |             |   2 = 1.00 s                                                   |
 * |         |             |             |   3 = 2.00 s                                                   |
 * |---------|-------------|-------------|----------------------------------------------------------------|
 * | 27      | 0           | 1-n         | stop blinking UPB unit P2                                      |
 * |---------|-------------|-------------|----------------------------------------------------------------|
 * | 28      |             | 1-n         | UPB link P2 off (deactivate)                                   |
 * |---------|-------------|-------------|----------------------------------------------------------------|
 * | 29      |             | 1-n         | UPB link P2 on (activate)                                      |
 * |---------|-------------|-------------|----------------------------------------------------------------|
 * | 30      |             | 1-n         | UPB link P2 set (store preset)                                 |
 * |---------|-------------|-------------|----------------------------------------------------------------|
 * | 42      |             | 1-n         | CentraLite Scene off                                           |
 * |---------|-------------|-------------|----------------------------------------------------------------|
 * | 43      |             | 1-n         | CentraLite Scene on                                            |
 * |---------|-------------|-------------|----------------------------------------------------------------|
 * | 44      | 1-8         | 1-n         | UPB unit P2 LED P1 off                                         |
 * |---------|-------------|-------------|----------------------------------------------------------------|
 * | 45      | 1-8         | 1-n         | UPB unit P2 LED P1 on                                          |
 * |---------|-------------|-------------|----------------------------------------------------------------|
 * | 46      |             | 1-n         | RadioRA Phantom Button off                                     |
 * |---------|-------------|-------------|----------------------------------------------------------------|
 * | 47      |             | 1-n         | RadioRA Phantom Button on                                      |
 * |---------|-------------|-------------|----------------------------------------------------------------|
 * | 60      |             | 1-n         | scene P2 off (Leviton Scene off command)                       |
 * |---------|-------------|-------------|----------------------------------------------------------------|
 * | 61      |             | 1-n         | scene P2 on (Leviton Scene on command)                         |
 * |---------|-------------|-------------|----------------------------------------------------------------|
 * | 62      |             | 1-n         | scene P2 set (Leviton Scene set command)                       |
 * --------------------------------------------------------------------------------------------------------
 *
 * For security commands, the code specified must be the user code number rather
 * than the actual four digit security code. That is, send a 0x05 as the code if
 * user code 5 is being used.
 *
 * --------------------------------------------------------------------------------
 * |         | Parameter 1 | Parameter 2 |                                        |
 * | Command |     P1      |     P2      |              Description               |
 * |---------|-------------|-------------|----------------------------------------|
 * | 48+m    | 1-n         | 0-n         | arm area P2 in mode m with code P1     |
 * |         |             |             | P2 = 0 means all areas                 |
 * |         |             |             | m = security mode:                     |
 * |         |             |             |   0 = disarm                           |
 * |         |             |             |   1 = day mode                         |
 * |         |             |             |   2 = night mode                       |
 * |         |             |             |   3 = away mode                        |
 * |         |             |             |   4 = vacation mode                    |
 * |         |             |             |   5 = day instant mode                 |
 * |         |             |             |   6 = night delayed mode               |
 * |---------|-------------|-------------|----------------------------------------|
 * | 4       | 1-n         | 1-n         | bypass zone P2 with code P1            |
 * |---------|-------------|-------------|----------------------------------------|
 * | 5       | 1-n         | 1-n         | restore zone P2 with code P1           |
 * |---------|-------------|-------------|----------------------------------------|
 * | 6       | 1-n         | 0-n         | restore all area P2 zones with code P1 |
 * |         |             |             | P2 = 0 means all areas/zones           |
 * |---------|-------------|-------------|----------------------------------------|
 *
 * For Lumina mode commands, the code specified must be the user code number
 * rather than the actual four digit code. That is, send a 0x05 as the code if
 * user code 5 is being used.
 *
 * -------------------------------------------------------------------------------------
 * |         | Parameter 1 | Parameter 2 |                                             |
 * | Command |     P1      |     P2      |                 Description                 |
 * |---------|-------------|-------------|---------------------------------------------|
 * | 48+m    | 1-n         | 1           | set mode m with code P1                     |
 * |         |             |             | m = mode:                                   |
 * |         |             |             |   1 = home mode                             |
 * |         |             |             |   2 = sleep mode                            |
 * |         |             |             |   3 = away mode                             |
 * |         |             |             |   4 = vacation mode                         |
 * |         |             |             |   5 = party mode                            |
 * |         |             |             |   6 = special mode                          |
 * |---------|-------------|-------------|---------------------------------------------|
 * | 7       |             | 1-n         | execute macro button P2                     |
 * |---------|-------------|-------------|---------------------------------------------|
 * | 8       | 0-3         |             | set energy cost to P1                       |
 * |         |             |             |   0 = low                                   |
 * |         |             |             |   1 = mid                                   |
 * |         |             |             |   2 = high                                  |
 * |         |             |             |   3 = critical                              |
 * |---------|-------------|-------------|---------------------------------------------|
 * | 64      | 0           | 1-n         | energy saver P2 off                         |
 * |---------|-------------|-------------|---------------------------------------------|
 * | 64      | 1-99        | 1-n         | energy saver P2 off for P1 seconds          |
 * |---------|-------------|-------------|---------------------------------------------|
 * | 64      | 101-199     | 1-n         | energy saver P2 off for P1-100 minutes      |
 * |---------|-------------|-------------|---------------------------------------------|
 * | 64      | 201-218     | 1-n         | energy saver P2 off for P1-200 hours        |
 * |---------|-------------|-------------|---------------------------------------------|
 * | 64      | 219-up      | 1-n         | energy saver P2 off for user setting P1-218 |
 * |---------|-------------|-------------|---------------------------------------------|
 * | 65      | 0           | 1-n         | energy saver P2 on                          |
 * |---------|-------------|-------------|---------------------------------------------|
 * | 65      | 1-99        | 1-n         | energy saver P2 on for P1 seconds           |
 * |---------|-------------|-------------|---------------------------------------------|
 * | 65      | 101-199     | 1-n         | energy saver P2 on for P1-100 minutes       |
 * |---------|-------------|-------------|---------------------------------------------|
 * | 65      | 201-218     | 1-n         | energy saver P2 on for P1-200 hours         |
 * |---------|-------------|-------------|---------------------------------------------|
 * | 65      | 219-up      | 1-n         | energy saver P2 on for user setting P1-218  |
 * -------------------------------------------------------------------------------------
 *
 * For commands 66-74, P2 may be set to zero to indicate "all thermostats" in
 * those controllers that support this capability.
 *
 * For the following two commands, temperatures are stored in the Omni
 * temperature format where 0 = -40 ˚C and 255 = 87.5 ˚C. Thus, 44-180
 * corresponds to 0 to 122 ˚F or -18 to 50 ˚C. 201-up corresponds to user
 * setting 1-n.
 *
 * --------------------------------------------------------------------------------------------
 * |         | Parameter 1 | Parameter 2 |                                                    |
 * | Command |     P1      |     P2      |                    Description                     |
 * |---------|-------------|-------------|----------------------------------------------------|
 * | 66      | 0-180       | 0-n         | set temp zone P2 low/heat setpoint to P1           |
 * |         |             |             | (for thermostats and temperature/humidity sensors) |
 * |---------|-------------|-------------|----------------------------------------------------|
 * | 67      | 0-180       | 0-n         | set temp zone P2 high/cool setpoint to P1          |
 * |         |             |             | (for thermostats and temperature/humidity sensors) |
 * |---------|-------------|-------------|----------------------------------------------------|
 * | 68      | 0-4         | 0-n         | set thermostat P2 system mode to P1                |
 * |         |             |             |   0 = off                                          |
 * |         |             |             |   1 = heat                                         |
 * |         |             |             |   2 = cool                                         |
 * |         |             |             |   3 = auto                                         |
 * |         |             |             |   4 = emergency heat                               |
 * |---------|-------------|-------------|----------------------------------------------------|
 * | 69      | 0-2         | 0-n         | set thermostat P2 fan mode to P1                   |
 * |         |             |             |   0 = auto                                         |
 * |         |             |             |   1 = on                                           |
 * |         |             |             |   2 = cycle                                        |
 * |---------|-------------|-------------|----------------------------------------------------|
 * | 70      | 0/255       | 0-n         | set thermostat P2 hold mode to P1                  |
 * |         |             |             |   0 = off                                          |
 * |         |             |             |   255 = hold                                       |
 * --------------------------------------------------------------------------------------------
 *
 * For the following two commands, temperatures offsets are stored in 2's
 * complement Omni temperature degrees, such that -50 to 50 corresponds to
 * -25 to 25 degrees Celsius (-45 to 45 degrees Fahrenheit) offset.
 *
 * --------------------------------------------------------------------------------------------
 * |         | Parameter 1 | Parameter 2 |                                                    |
 * | Command |     P1      |     P2      |                    Description                     |
 * |---------|-------------|-------------|----------------------------------------------------|
 * | 71      | -50 to 50   | 0-n         | raise/lower temp P2 low/heat setting by P1         |
 * |         |             |             | P2 = 0 means all thermostats                       |
 * |---------|-------------|-------------|----------------------------------------------------|
 * | 72      | -50 to 50   | 0-n         | raise/lower temp P2 high/cool setting by P1        |
 * |         |             |             | P2 = 0 means all thermostats                       |
 * --------------------------------------------------------------------------------------------
 *
 * For the following two commands, humidity is stored in the Omni temperature
 * format. 0=disabled, 44-156 = 0-100%. 201-up corresponds to user setting 1-n.
 *
 * -------------------------------------------------------------------------------------------
 * |         | Parameter 1 | Parameter 2 |                                                   |
 * | Command |     P1      |     P2      |                    Description                    |
 * |---------|-------------|-------------|---------------------------------------------------|
 * | 73      | 0-156       | 0-n         | set temp zone P2 humidify setpoint to P1          |
 * |         |             |             | (for thermostats only)                            |
 * |---------|-------------|-------------|---------------------------------------------------|
 * | 74      | 0-156       | 0-n         | set temp zone P2 dehumidify setpoint to P1        |
 * |         |             |             | (for thermostats only)                            |
 * |---------|-------------|-------------|---------------------------------------------------|
 * | 80      |             | 1-n         | show message P2 (with beep and LED)               |
 * |---------|-------------|-------------|---------------------------------------------------|
 * | 86      | 0-2         | 1-n         | show message P2                                   |
 * |         |             |             | P1 = mode                                         |
 * |         |             |             |   1 = no beep                                     |
 * |         |             |             |   2 = no beep or LED                              |
 * |---------|-------------|-------------|---------------------------------------------------|
 * | 81      |             | 1-n         | log message P2                                    |
 * |---------|-------------|-------------|---------------------------------------------------|
 * | 82      | 0-n         | 0-n         | clear message P2 (0=all)                          |
 * |         |             |             | if clear all messages, P1 = area (0=all)          |
 * |---------|-------------|-------------|---------------------------------------------------|
 * | 83      |             | 1-n         | say message P2                                    |
 * |---------|-------------|-------------|---------------------------------------------------|
 * | 84      | 1-n         | 1-n         | phone number P1 and say message P2                |
 * |---------|-------------|-------------|---------------------------------------------------|
 * | 85      | 1-n         | 1-n         | send message P2 out serial port P1                |
 * |---------|-------------|-------------|---------------------------------------------------|
 * | 100     | 1-6         | 1-n         | display camera P1 on touchscreen P2               |
 * |---------|-------------|-------------|---------------------------------------------------|
 * | 102     | 0-1         | 0-16        | enable/disable console beeper P2 (0=all consoles) |
 * |         |             |             | P1 = mode                                         |
 * |         |             |             |   0 = disabled                                    |
 * |         |             |             |   1 = enabled                                     |
 * |---------|-------------|-------------|---------------------------------------------------|
 * | 103     | 0-6         | 0-16        | console P2 (0=all consoles) beep P1               |
 * |         |             |             |   0 = off                                         |
 * |         |             |             |   1 = indefinitely                                |
 * |         |             |             |   2 = 1 time                                      |
 * |         |             |             |   3 = 2 times                                     |
 * |         |             |             |   4 = 3 times                                     |
 * |         |             |             |   5 = 4 times                                     |
 * |         |             |             |   6 = 5 times                                     |
 * |---------|-------------|-------------|---------------------------------------------------|
 * | 104     | 1-n         | 0-n         | set user setting P1 to P2                         |
 * |---------|-------------|-------------|---------------------------------------------------|
 * | 105     |             | 0-n         | lock door P2 (0=all doors)                        |
 * |---------|-------------|-------------|---------------------------------------------------|
 * | 106     | 0           | 0-n         | unlock door P2 (0=all doors)                      |
 * |---------|-------------|-------------|---------------------------------------------------|
 * | 106     | 1-99        | 0-n         | unlock door P2 for P1 seconds                     |
 * |---------|-------------|-------------|---------------------------------------------------|
 * | 106     | 101-199     | 0-n         | unlock door P2 for P1-100 minutes                 |
 * |---------|-------------|-------------|---------------------------------------------------|
 * | 106     | 201-218     | 0-n         | unlock door P2 for P1-200 hours                   |
 * |---------|-------------|-------------|---------------------------------------------------|
 * | 106     | 219-up      | 0-n         | unlock door P2 for user setting P1-218            |
 * |---------|-------------|-------------|---------------------------------------------------|
 * | 112     | 0-3         | 0-n         | set audio zone P2 (0=all zones) to P1             |
 * |         |             |             |   0 = off                                         |
 * |         |             |             |   1 = on                                          |
 * |         |             |             |   2 = mute off                                    |
 * |         |             |             |   3 = mute on                                     |
 * |---------|-------------|-------------|---------------------------------------------------|
 * | 113     | 0-100       | 1-n         | set audio zone P2 volume to P1 percent            |
 * |---------|-------------|-------------|---------------------------------------------------|
 * | 114     | 1-n         | 1-n         | set audio zone P2 to audio source P1              |
 * |---------|-------------|-------------|---------------------------------------------------|
 * | 115     | 1-46        | 1-n         | audio zone P2 select key P1                       |
 * |         |             |             | (see doc/AudioKeyCodes.pdf)                       |
 * -------------------------------------------------------------------------------------------
 */
@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CommandMessage implements Message {
	private final int command;
	private final int parameter1;
	private final int parameter2;

	public final static int CMD_UNIT_OFF = 0;
	public final static int CMD_UNIT_ON = 1;
	public final static int CMD_UNIT_AREA_ALL_OFF = 2;
	public final static int CMD_UNIT_AREA_ALL_ON = 3;
	public final static int CMD_UNIT_PERCENT = 9;
	public final static int CMD_UNIT_LO9_LEVEL_HIGH7 = 101;
	public final static int CMD_UNIT_DECREMENT_COUNTER = 10;
	public final static int CMD_UNIT_INCREMENT_COUNTER = 11;
	public final static int CMD_UNIT_SET_COUNTER = 12;
	public final static int CMD_UNIT_LO9_RAMP_HIGH7 = 13;
	public final static int CMD_UNIT_LIGHTOLIER = 14;
	public final static int CMD_UNIT_UPB_REQ_STATUS = 15;
	public final static int CMD_UNIT_UPB_DIM_STEP_1 = 17;
	public final static int CMD_UNIT_UPB_DIM_STEP_2 = 18;
	public final static int CMD_UNIT_UPB_DIM_STEP_3 = 19;
	public final static int CMD_UNIT_UPB_DIM_STEP_4 = 20;
	public final static int CMD_UNIT_UPB_DIM_STEP_5 = 21;
	public final static int CMD_UNIT_UPB_DIM_STEP_6 = 22;
	public final static int CMD_UNIT_UPB_DIM_STEP_7 = 23;
	public final static int CMD_UNIT_UPB_DIM_STEP_8 = 24;
	public final static int CMD_UNIT_UPB_DIM_STEP_9 = 25;
	public final static int CMD_UNIT_UPB_BRIGHTEN_STEP_1 = 33;
	public final static int CMD_UNIT_UPB_BRIGHTEN_STEP_2 = 34;
	public final static int CMD_UNIT_UPB_BRIGHTEN_STEP_3 = 35;
	public final static int CMD_UNIT_UPB_BRIGHTEN_STEP_4 = 36;
	public final static int CMD_UNIT_UPB_BRIGHTEN_STEP_5 = 37;
	public final static int CMD_UNIT_UPB_BRIGHTEN_STEP_6 = 38;
	public final static int CMD_UNIT_UPB_BRIGHTEN_STEP_7 = 39;
	public final static int CMD_UNIT_UPB_BRIGHTEN_STEP_8 = 40;
	public final static int CMD_UNIT_UPB_BRIGHTEN_STEP_9 = 41;
	public final static int CMD_UNIT_UPB_LO9_BLINK_HIGH7 = 26;
	public final static int CMD_UNIT_UPB_STOP_BLINK = 27;
	public final static int CMD_UNIT_UPB_LINK_OFF = 28;
	public final static int CMD_UNIT_UPB_LINK_ON = 29;
	public final static int CMD_UNIT_UPB_LINK_SET = 30;
	public final static int CMD_UNIT_CENTRALITE_SCENE_OFF = 42;
	public final static int CMD_UNIT_CENTRALITE_SCENE_ON = 43;
	public final static int CMD_UNIT_UPB_LED_OFF = 44;
	public final static int CMD_UNIT_UPB_LED_ON = 45;
	public final static int CMD_UNIT_RADIORA_PHANTOM_BUTTON_OFF = 46;
	public final static int CMD_UNIT_RADIORA_PHANTOM_BUTTON_ON = 46;
	public final static int CMD_UNIT_LEVITON_SCENE_OFF = 60;
	public final static int CMD_UNIT_LEVITON_SCENE_ON = 61;
	public final static int CMD_UNIT_LEVITON_SCENE_SET = 62;

	public final static int CMD_SECURITY_OMNI_DISARM = 48;
	public final static int CMD_SECURITY_OMNI_DAY_MODE = 49;
	public final static int CMD_SECURITY_OMNI_NIGHT_MODE = 50;
	public final static int CMD_SECURITY_OMNI_AWAY_MODE = 51;
	public final static int CMD_SECURITY_OMNI_VACATION_MODE = 52;
	public final static int CMD_SECURITY_OMNI_DAY_INSTANCE_MODE = 53;
	public final static int CMD_SECURITY_OMNI_NIGHT_DELAYED_MODE = 54;
	public final static int CMD_SECURITY_BYPASS_ZONE = 4;
	public final static int CMD_SECURITY_RESTORE_ZONE = 5;
	public final static int CMD_SECURITY_RESTORE_ALL_ZONES = 6;
	public final static int CMD_SECURITY_LUMINA_HOME_MODE = 49;
	public final static int CMD_SECURITY_LUMINA_SLEEP_MODE = 50;
	public final static int CMD_SECURITY_LUMINA_AWAY_MODE = 51;
	public final static int CMD_SECURITY_LUMINA_VACATION_MODE = 52;
	public final static int CMD_SECURITY_LUMINA_PARTY_MODE = 53;
	public final static int CMD_SECURITY_LUMINA_SPECIAL_MODE = 54;

	public final static int CMD_BUTTON = 7;

	public final static int CMD_ENERGY_COST_SET = 8;
	public final static int CMD_ENERGY_SAVER_OFF = 64;
	public final static int CMD_ENERGY_SAVER_ON = 65;

	public final static int CMD_THERMO_SET_HEAT_POINT = 66;
	public final static int CMD_THERMO_SET_COOL_POINT = 67;
	public final static int CMD_THERMO_SET_SYSTEM_MODE = 68;
	public final static int CMD_THERMO_SET_FAN_MODE = 69;
	public final static int CMD_THERMO_RAISE_LOWER_HEAT = 71;
	public final static int CMD_THERMO_RAISE_LOWER_COOL = 72;
	public final static int CMD_THERMO_SET_HOLD_MODE = 70;

	public final static int CMD_MESSAGE_SHOW_MESSAGE_WITH_BEEP_AND_LED = 80;
	public final static int CMD_MESSAGE_SHOW_MESSAGE_WITH_BEEP_OR_LED = 86;
	public final static int CMD_MESSAGE_LOG_MESSAGE = 81;
	public final static int CMD_MESSAGE_CLEAR_MESSAGE = 82;
	public final static int CMD_MESSAGE_SAY_MESSAGE = 83;
	public final static int CMD_MESSAGE_PHONE_AND_SAY_MESSAGE = 84;
	public final static int CMD_MESSAGE_SEND_MESSAGE_TO_SERIAL_PORT = 85;

	public final static int CMD_CONSOLE_ENABLE_DISABLE_BEEPER = 102;
	public final static int CMD_CONSOLE_BEEP = 103;

	public final static int CMD_AUDIO_ZONE_SET_ON_AND_MUTE = 112;
	public final static int CMD_AUDIO_ZONE_SET_VOLUME = 113;
	public final static int CMD_AUDIO_ZONE_SET_SOURCE = 114;
	public final static int CMD_AUDIO_ZONE_SELECT_KEY = 115;

	@Override
	public int getMessageType() {
		return MESG_TYPE_COMMAND;
	}
}
