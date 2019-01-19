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
  public static JoystickButton rightFunButton = new JoystickButton(stick, 1);
  public static JoystickButton rightTopRightButton = new JoystickButton(stick, 1);
  public static JoystickButton rightMidRightButton = new JoystickButton(stick, 1);
  public static JoystickButton rightMidLeftButton = new JoystickButton(stick, 1);
  public static JoystickButton rightBottomTrigger = new JoystickButton(stick, 1);
  public static JoystickButton leftThumb = new JoystickButton(stick, 1);
  public static JoystickButton leftEButtonright1Up = new JoystickButton(stick, 1);
  public static JoystickButton right1Down = new JoystickButton(stick, 1);
  public static JoystickButton right2Up = new JoystickButton(stick, 1);
  public static JoystickButton right2Down = new JoystickButton(stick, 1);
  public static JoystickButton right3Up = new JoystickButton(stick, 1);
  public static JoystickButton right3Down = new JoystickButton(stick, 1);
  public static JoystickButton rightMainTriggerHard = new JoystickButton(stick, 1);
  public static JoystickButton rightTopLeftUp = new JoystickButton(stick, 1);
  public static JoystickButton fastMode = new JoystickButton(stick, 1);

  public OI() {

  }

}
