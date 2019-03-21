/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

public class RobotMap {

  //Debugging Control
  public static final boolean debugSarlacc = false;
  public static final boolean debugDriveSpeed = false;
  public static final boolean debugDrivePosition = false;
  public static final boolean debugElevator = false;
  /* Drive Train */
    // Motor Controllers
  public static final int leftMasterId = 1;
  public static final int leftSlaveId = 2; // 5
  public static final int rightMasterId = 3;
  public static final int rightSlaveId = 0; // 4

    // Constants
  public static double defaultSpeed = 0.75;
  public static double defaultTurn = 1.0;

  public static double limitedSpeed = 0.4;
  public static double limitedTurn = 0.4;

  // public static double maxSpeed = defaultSpeed;
  // public static double maxTurn = defaultTurn;
  
  public static double autoClimbSpeed = -0.4;
  public static double autoClimbTurn = 0.0;

  public static final int backUpDistance = 100;

  public static final int LEFT_ENC_A = 2;
  public static final int LEFT_ENC_B = 3;
  public static final int RIGHT_ENC_A = 0;
  public static final int RIGHT_ENC_B = 1;


  /* Elevator */
    // Motor Controllers
  public static final int winch1 = 0;
  public static final int winch2 = 1;
  public static final int winchEnc = 6;
  public static final int limitSwitch = 9;

    // Constants
  public static final double kP = 0.01;
  public static final double kI = 0.003;
  public static final double kD = 0.005;
  
    // measured in encoder pulses
  public static final int startingPosition = 0;
  public static final int bottomPosition = 860;
  public static final int middlePosition = 2800;
  public static final int topPosition = 4630;

  public static final int dropDistance = 30;

  public static final int maxOffsetRateController = 20;
  public static final int maxOffsetController = 500;
  public static final int maxOffsetStick = 500;

  public static final int ELEVATOR_ENC_A = 4;
  public static final int ELEVATOR_ENC_B = 5;
  

  /* Pneumatics Constants */
  public static final int compressor = 0;
  public static final int forwardChannel = 0;
  public static final int reverseChannel = 1;
  public static final int frontSolenoidF = 3;
  public static final int frontSolenoidR = 2;
  public static final int backSolenoidF = 5;
  public static final int backSolenoidR = 4;

  /* Manipulator Constants */
  public static final int leftManipulator = 2;
  public static final int rightManipulator = 3;
}
