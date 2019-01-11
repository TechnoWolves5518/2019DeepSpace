package frc.robot.drive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;

public class DriveTrainSubsystem extends Subsystem {
    
    private TalonSRX leftMaster = new TalonSRX(RobotMap.leftMasterId);
    private TalonSRX leftSlave = new TalonSRX(RobotMap.leftSlaveId);
    SpeedControllerGroup difLeft = new SpeedControllerGroup((SpeedController)leftMaster, (SpeedController)leftSlave);
    //Creates a compact way to refer to the left motors.

    private TalonSRX rightMaster = new TalonSRX(RobotMap.rightMasterId);
    private TalonSRX rightSlave = new TalonSRX(RobotMap.rightSlaveId);
    SpeedControllerGroup difRight = new SpeedControllerGroup((SpeedController)rightMaster, (SpeedController)rightSlave);
    //Creates a compact way to refer to the right motors.

    DifferentialDrive difDrive = new DifferentialDrive(difLeft, difRight);
    //This creates the Differential Drive Method.

    public DriveTrainSubsystem() {
        leftSlave.follow(leftMaster);
        rightSlave.follow(rightMaster);
        //This tells the leftSlave motor to 'mimic' the leftMaster motor. Same for the right motors.
        leftMaster.setInverted(true);
        leftSlave.setInverted(true);
        //This inverts the left motors so they are in sync with the right motors.
    }

    @Override
    protected void initDefaultCommand() {
        new DriveCom();
        //This starts the 'DriveCom' file so it can be seen.
    }

    public void tankDrive(double left, double right) {
        leftMaster.set(ControlMode.PercentOutput, left);
        rightMaster.set(ControlMode.PercentOutput, right);
    }

    public void curveDrive(double v, double r, boolean quickturn) {
        difDrive.curvatureDrive(v, r, quickturn);
     //This section of code creates the curveDrive method, which subsequently calls the curvatureDrive function.    
        
        // if (v > 0) {
        //     leftMaster.set(ControlMode.PercentOutput, (v + r));
        //     rightMaster.set(ControlMode.PercentOutput, (v - r));
        // } else if (v < 0) {
        //     leftMaster.set(ControlMode.PercentOutput, (-v - r));
        //     rightMaster.set(ControlMode.PercentOutput, (-v + r));
        // }
    }

}