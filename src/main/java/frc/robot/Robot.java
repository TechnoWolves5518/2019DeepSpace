/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.auto.HabDrop;

public class Robot extends TimedRobot {
  
  public static UsbCamera camera;
  
  public long timeAnchor;

  Command autoCom = null;

  @Override
  public void robotInit() {
    camera = CameraServer.getInstance().startAutomaticCapture();
    CommandBase.init();

    camera.setResolution(480, 320);
  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void autonomousInit() {
    CommandBase.driveTrain.resetEncoders();
    autoCom = new HabDrop();
    autoCom.start();
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    if (autoCom != null)
      autoCom.cancel();
    CommandBase.driveTrain.resetEncoders();
    CommandBase.elevator.setSetpoint(RobotMap.startingPosition);
    CommandBase.sarlaccSub.closeArms();
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void testPeriodic() {
  }

}
