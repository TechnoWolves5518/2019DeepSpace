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

        leftEnc = new Encoder(RobotMap.LEFT_ENC_A, RobotMap.LEFT_ENC_B, true, EncodingType.k4X);
	rightEnc = new Encoder(RobotMap.RIGHT_ENC_A, RobotMap.RIGHT_ENC_B, false, EncodingType.k4X);

	leftEnc.setDistancePerPulse(kDistancePerPulse);
	rightEnc.setDistancePerPulse(kDistancePerPulse);
	leftEnc.setMaxPeriod(0.1);
	rightEnc.setMaxPeriod(0.1);

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

    private Encoder leftEnc, rightEnc;
    private double kWheelCircumference = MEASURE VALUE
    private double kDistancePerRevolution = kWheelCircumference * Math.PI
    private double kPulsesPerRevolution = 256;
    private double kDistancePerPulse = kDistancePerRevolution / kPulsesPerRevolution

    public int getLeftEncoderPos() {
        return leftEnc.get();
    }
    public int getRightEncoderPos() {
	return rightEnc.get();
    }
    public double getWheelCircumference() {
	return kWheelCircumference * 0.0254;
    }
        }
    }

}
