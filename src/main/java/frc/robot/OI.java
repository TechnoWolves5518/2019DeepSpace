/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.auto.ClimbGroup;
import frc.robot.auto.PullOut;
import frc.robot.manipulator.Intake;
import frc.robot.manipulator.Outtake;

public class OI {
  public static boolean controllerToggle = true;
  
  public static XboxController driver = new XboxController(0);
  public static XboxController sf = new XboxController(1);

  public static int XBOX_LSTICKX = 0;
	public static int XBOX_LSTICKY = 1; 
	public static int XBOX_RSTICKX = 4;
	public static int XBOX_RSTICKY = 5;
	public static int XBOX_LTRIGGER = 2;
	public static int XBOX_RTRIGGER = 3;
  
  public static int XBOX_ABTN = 1;
	public static int XBOX_BBTN = 2;
	public static int XBOX_YBTN = 4;
	public static int XBOX_XBTN = 3;
	public static int XBOX_RBUMPER = 6;
	public static int XBOX_LBUMPER = 5;
	public static int XBOX_LSTICK = 9;
	public static int XBOX_RSTICK = 10;
	public static int XBOX_START = 8;
	public static int XBOX_BACK = 7;

	JoystickButton d_b = new JoystickButton(driver, XBOX_BBTN);

	JoystickButton sf_a = new JoystickButton(sf, XBOX_ABTN);
	JoystickButton sf_x = new JoystickButton(sf, XBOX_XBTN);
	JoystickButton sf_lstick = new JoystickButton(sf, XBOX_LSTICK);

  public OI() {
    d_b.whenPressed(new ClimbGroup());

    sf_a.whileHeld(new Outtake());
		sf_x.whileHeld(new Intake());
		sf_lstick.whenPressed(new PullOut());
  }

}
