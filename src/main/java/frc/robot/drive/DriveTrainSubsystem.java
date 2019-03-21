package frc.robot.drive;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
// import frc.robot.auto.HabDrop;
import frc.robot.CommandBase;

public class DriveTrainSubsystem extends Subsystem {

    private WPI_TalonSRX leftMaster = new WPI_TalonSRX(RobotMap.leftMasterId);
    private WPI_TalonSRX leftSlave = new WPI_TalonSRX(RobotMap.leftSlaveId);
    private WPI_TalonSRX rightMaster = new WPI_TalonSRX(RobotMap.rightMasterId);
    private WPI_TalonSRX rightSlave = new WPI_TalonSRX(RobotMap.rightSlaveId);

    private SpeedControllerGroup rightSide = new SpeedControllerGroup(rightMaster, rightSlave);
    private SpeedControllerGroup leftSide = new SpeedControllerGroup(leftMaster, leftSlave);

    private DifferentialDrive driveTrain = new DifferentialDrive(leftSide, rightSide);

    public Encoder leftEnc, rightEnc;
    private double kWheelDiameter = 8;
    private double kDistancePerRevolution = kWheelDiameter * Math.PI;
    private double kPulsesPerRevolution = 360;
    private double kDistancePerPulse = kDistancePerRevolution / kPulsesPerRevolution;

    // public PIDController leftDrivePID = null;
    // public PIDController rightDrivePID = null;

    public DriveTrainSubsystem() {
        leftEnc = new Encoder(RobotMap.LEFT_ENC_A, RobotMap.LEFT_ENC_B, false, EncodingType.k4X);
        leftEnc.setDistancePerPulse(kDistancePerPulse);
        leftEnc.setMaxPeriod(0.1);

        rightEnc = new Encoder(RobotMap.RIGHT_ENC_A, RobotMap.RIGHT_ENC_B, true, EncodingType.k4X);
        rightEnc.setDistancePerPulse(kDistancePerPulse);
        rightEnc.setMaxPeriod(0.1);

        // PIDController leftDrivePID = new PIDController(0.05, 0.0, 0.0, leftEnc, leftSide);
        // leftDrivePID.setAbsoluteTolerance(5);
        // leftDrivePID.setContinuous(false);
        // leftDrivePID.setOutputRange(-0.5, 0.5);
        // leftDrivePID.disable();

        // PIDController rightDrivePID = new PIDController(0.05, 0.0, 0.0, rightEnc, rightSide);
        // rightDrivePID.setAbsoluteTolerance(5);
        // rightDrivePID.setContinuous(false);
        // rightDrivePID.setOutputRange(-0.5, 0.5);
        // rightDrivePID.disable();

        driveTrain.setSafetyEnabled(true);
        driveTrain.setExpiration(0.08);
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new DriveCom());
    }

    public void arcadeDrive(double speed, double rotation) {
        driveTrain.arcadeDrive(speed, -rotation);
        if (RobotMap.debugDriveSpeed); {
            // System.out.println("Speed Value : " + speed + " (Cap is " + RobotMap.defaultSpeed + ")" + "      Turn Value : " + -rotation + " (Cap is " + RobotMap.defaultTurn + ")");
        }
        if (RobotMap.debugDrivePosition) {
            System.out.println("Left: " + getLeftEncoder() + "  Right: " + getRightEncoder());
        }
    }

    public void tankDrive(double left, double right) {
        driveTrain.tankDrive(left, right);
    }

    public void curvyDrive(double speed, double rotation, boolean quickTurn) {
        driveTrain.curvatureDrive(speed, rotation, quickTurn);
    }

    // public void driveToSetpoint(double left, double right) {
    //     resetEncoders();
    //     leftSide.set(left);
    //     rightSide.set(right);

        

        // leftDrivePID.setSetpoint(-setpoint);
        // rightDrivePID.setSetpoint(setpoint);
    // }

    public void reverseMotors() {
        leftMaster.setInverted(!leftMaster.getInverted());
        leftSlave.setInverted(!leftSlave.getInverted());
        rightMaster.setInverted(!rightMaster.getInverted());
        rightSlave.setInverted(!rightSlave.getInverted());
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

    public void logEncoders() {
        System.out.println("Left: " + getLeftEncoder() + "\tRight: " + getRightEncoder());
    }

    // public void setPIDenabled(boolean enabled) {
    //     if (enabled) {
    //         leftDrivePID.enable();
    //         rightDrivePID.enable();
    //     } else {
    //         leftDrivePID.disable();
    //         rightDrivePID.disable();
    //     }
    // }

}
