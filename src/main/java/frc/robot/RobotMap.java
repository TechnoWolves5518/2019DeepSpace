/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

public class RobotMap {

  /* Drive Train */
    // Motor Controllers
  public static final int leftMasterId = 1;
  public static final int leftSlaveId = 2; // 5
  public static final int rightMasterId = 3;
  public static final int rightSlaveId = 0; // 4

    // Constants
  public static final double topSpeed = 0.5;

  public static final int LEFT_ENC_A = 2;
  public static final int LEFT_ENC_B = 3;
  public static final int RIGHT_ENC_A = 0;
  public static final int RIGHT_ENC_B = 1;


  /* Elevator */
    // Motor Controllers
  public static final int winch1 = 0;
  public static final int winch2 = 1;
  public static int winchEnc = 6;

    // Constants
  public static final double kP = 1.0;
  public static final double kI = 0.0;
  public static final double kD = 0.0;
  
    // measured in encoder pulses
  public static final int startingPosition = 0;
  public static int bottomPosition = 600;
  public static int middlePosition = 1200;
  public static int topPosition = 1800;

  public static final int maxOffset = 200;

  public static final int ELEVATOR_ENC_A = 4;
  public static final int ELEVATOR_ENC_B = 5;
  

  /* Pneumatics Constants */
  public static final int compressor = 0;
  public static final int forwardChannel = 0;
  public static final int reverseChannel = 1;
}
