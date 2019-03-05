/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
// import frc.robot.auto.HabDrop;

public class OI {
  public static boolean controllerToggle = true;

  
  public static XboxController driver = new XboxController(0);
  public static XboxController sf = new XboxController(1);
  public static Joystick stick = new Joystick(1);


  public static int rightMainTrigger = 1;
  public static int rightFunButton = 2;
  public static int rightTopRightButton = 3;
  public static int rightMidRightButton = 4;
  public static int rightMidLeftButton = 5;
  public static int rightBottomTrigger = 6;
  public static int leftThumb = 7;
  public static int leftEButtonright1Up = 8;
  public static int right1Up = 9;
  public static int right1Down = 10;
  public static int right2Up = 11;
  public static int right2Down = 12;
  public static int right3Up = 13;
  public static int right3Down = 14;
  public static int rightMainTriggerHard = 15;
  public static int rightTopLeftUp = 16;

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
    JoystickButton rightStick = new JoystickButton(driver, XBOX_RSTICK);
    // rightStick.whenPressed(new HabDrop());
  }

}
