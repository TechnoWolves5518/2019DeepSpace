/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;

import edu.wpi.cscore.CameraServerJNI;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Robot extends TimedRobot {
  public CANSparkMax winch;
  public CANPIDController winchPID;
  public CANEncoder winchENCODER;
  public static UsbCamera camera;
  public static CameraServerJNI camera1 = new CameraServerJNI();
  
  public long timeAnchor;

  @Override
  public void robotInit() {
    CameraServer.getInstance().startAutomaticCapture();
    CommandBase.init();
    timeAnchor = System.currentTimeMillis() / 1000;

    // camera.setResolution(320, 240);
    // camera1.setSourceResolution(0, 320, 240);
  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void autonomousInit() {
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    CommandBase.driveTrain.resetEncoders();
    // CommandBase.elevator.resetElevatorEnc();
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

  public long timeStamp() {
    return (timeAnchor - (System.currentTimeMillis() / 1000));
  }

}
