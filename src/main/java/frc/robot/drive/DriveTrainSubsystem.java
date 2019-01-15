package frc.robot.drive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class DriveTrainSubsystem extends Subsystem {

    private TalonSRX leftMaster = new TalonSRX(RobotMap.leftMasterId);
    // private TalonSRX leftSlave = new TalonSRX(RobotMap.leftSlaveId);
    private VictorSP leftSlave = new VictorSP(RobotMap.leftSlaveId);
    private TalonSRX rightMaster = new TalonSRX(RobotMap.rightMasterId);
    // private TalonSRX rightSlave = new TalonSRX(RobotMap.rightSlaveId);
    private VictorSP rightSlave = new VictorSP(RobotMap.rightSlaveId);

    private Encoder leftEnc, rightEnc;
    private double kWheelDiameter = 8;
    private double kDistancePerRevolution = kWheelDiameter * Math.PI;
    private double kPulsesPerRevolution = 360;
    private double kDistancePerPulse = kDistancePerRevolution / kPulsesPerRevolution;

    public DriveTrainSubsystem() {
        // leftSlave.follow(leftMaster);
        // rightSlave.follow(rightMaster);
        
        leftMaster.setInverted(true);
        leftSlave.setInverted(true);

        leftEnc = new Encoder(RobotMap.LEFT_ENC_A, RobotMap.LEFT_ENC_B, true, EncodingType.k4X);
        leftEnc.setDistancePerPulse(kDistancePerPulse);
        leftEnc.setMaxPeriod(0.1);

        rightEnc = new Encoder(RobotMap.RIGHT_ENC_A, RobotMap.RIGHT_ENC_B, false, EncodingType.k4X);
        rightEnc.setDistancePerPulse(kDistancePerPulse);
        rightEnc.setMaxPeriod(0.1);
    }

    @Override
    protected void initDefaultCommand() {
        System.out.println("setup command");
        // new DriveCom();
        setDefaultCommand(Robot.drive);
    }

    public void tankDrive(double left, double right) {
        leftMaster.set(ControlMode.PercentOutput, left);
        leftSlave.set(-left);
        rightMaster.set(ControlMode.PercentOutput, right);
        rightSlave.set(-right);
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

    public int getLeftEncoder() {
        return leftEnc.get();
    }
    public double getLeftEncoderPos() {
        return leftEnc.getDistance();
    }

    public int getRightEncoder() {
        return rightEnc.get();
    }
    public double getRightEncoderPos() {
        return rightEnc.getDistance();
    }

    public int getAvgEncoder() {
        return (leftEnc.get() + rightEnc.get()) / 2;
    }
    public double getAvgEncoderPos() {
        return (rightEnc.getDistance() + leftEnc.getDistance()) / 2;
    }
    
    public void resetEncoders() {
        leftEnc.reset();
        rightEnc.reset();
    }

    public double getWheelDiameter() {
        return kWheelDiameter * 0.0254;
    }

}
