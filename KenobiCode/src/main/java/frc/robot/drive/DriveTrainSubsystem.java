package frc.robot.drive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class DriveTrainSubsystem extends Subsystem {

    private TalonSRX leftMaster = new TalonSRX(RobotMap.leftMasterId);
    private TalonSRX leftSlave = new TalonSRX(RobotMap.leftSlaveId);
    private TalonSRX rightMaster = new TalonSRX(RobotMap.rightMasterId);
    private TalonSRX rightSlave = new TalonSRX(RobotMap.rightSlaveId);

    public DriveTrainSubsystem() {
        leftSlave.follow(leftMaster);
        rightSlave.follow(rightMaster);
        
        leftMaster.setInverted(true);
        leftSlave.setInverted(true);
    }

    @Override
    protected void initDefaultCommand() {
        new DriveCom();
    }

    public void tankDrive(double left, double right) {
        leftMaster.set(ControlMode.PercentOutput, left);
        rightMaster.set(ControlMode.PercentOutput, right);
    }

    public void curveDrive(double v, double h) {
        if (v > 0) {
            leftMaster.set(ControlMode.PercentOutput, (v + h));
            rightMaster.set(ControlMode.PercentOutput, (v - h));
        } else if (v < 0) {
            leftMaster.set(ControlMode.PercentOutput, (-v - h));
            rightMaster.set(ControlMode.PercentOutput, (-v + h));
        }
    }

}