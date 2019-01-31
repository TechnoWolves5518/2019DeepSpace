/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {

  public static XboxController driver = new XboxController(0);
  public static Joystick stick = new Joystick(1);

  public static JoystickButton rightMainTrigger = new JoystickButton(stick, 1);
  public static JoystickButton rightFunButton = new JoystickButton(stick, 2);
  public static JoystickButton rightTopRightButton = new JoystickButton(stick, 3);
  public static JoystickButton rightMidRightButton = new JoystickButton(stick, 4);
  public static JoystickButton rightMidLeftButton = new JoystickButton(stick, 5);
  public static JoystickButton rightBottomTrigger = new JoystickButton(stick, 6);
  public static int rightBottomTriggerInt = 6;
  public static JoystickButton leftThumb = new JoystickButton(stick, 7);
  public static JoystickButton leftEButtonright1Up = new JoystickButton(stick, 8);
  public static JoystickButton right1Up = new JoystickButton(stick, 9);
  public static JoystickButton right1Down = new JoystickButton(stick, 10);
  public static JoystickButton right2Up = new JoystickButton(stick, 11);
  public static JoystickButton right2Down = new JoystickButton(stick, 12);
  public static JoystickButton right3Up = new JoystickButton(stick, 13);
  public static JoystickButton right3Down = new JoystickButton(stick, 14);
  public static JoystickButton rightMainTriggerHard = new JoystickButton(stick, 15);
  public static JoystickButton rightTopLeftUp = new JoystickButton(stick, 16);

  public static int rightJoyX = 0;
  public static int rightJoyY = 1;
  public static int leftJoyZ = 2;
  public static int leftJoyX = 3;
  public static int leftJoyY = 4;
  public static int rightJoyZ = 5;
  public static int leftJoySlider = 6;

  
  public static int XBOX_LSTICKX = 0;
	public static int XBOX_LSTICKY = 1; 
	public static int XBOX_RSTICKX = 4;
	public static int XBOX_RSTICKY = 5;
	public static int XBOX_LTRIGGER = 2;
	public static int XBOX_RTRIGGER = 3;
	
	public static int XBOX_YBTN = 4;
	public static int XBOX_XBTN = 3;
	public static int XBOX_ABTN = 1;
	public static int XBOX_BBTN = 2; 
	public static int XBOX_RBUMPER = 6;
	public static int XBOX_LBUMPER = 5;
	public static int XBOX_LSTICK = 9;
	public static int XBOX_RSTICK = 10;
	public static int XBOX_START = 8;
	public static int XBOX_BACK = 7;

  public OI() {
  }

}
