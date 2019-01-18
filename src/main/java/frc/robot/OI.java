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

  public JoystickButton rightMainTrigger, rightFunButton, rightTopRightButton, rightMidRightButton, rightMidLeftButton,
    rightBottomTrigger, leftThumb, leftEButton, right1Up, right1Down, right2Up, right2Down, right3Up, right3Down,
    rightMainTriggerHard, rightTopLeftUp, fastMode;

  public OI() {

  }

}
