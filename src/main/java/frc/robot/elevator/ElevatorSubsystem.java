package frc.robot.elevator;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import frc.robot.RobotMap;

public class ElevatorSubsystem extends PIDSubsystem {

    private Spark winch = new Spark(RobotMap.winch);

    private Encoder elevatorEnc;

    private double kGearDiameter = 0;
    private double kGearDistancePerRev = kGearDiameter * Math.PI;
    private double kPulsesPerRev = 360;
    private double kDistancePerPulse = kGearDistancePerRev / kPulsesPerRev;

    public ElevatorSubsystem() {
        super("Elevator", 0.005, 0.001, 0.005);
        // super("Elevator", 0.005, 0.0, 0.0);
        setAbsoluteTolerance(0.5);
        getPIDController().setContinuous(false);
        getPIDController().setOutputRange(-0.25, 0.25);

        elevatorEnc = new Encoder(RobotMap.ELEVATOR_ENC_A, RobotMap.ELEVATOR_ENC_B, false, EncodingType.k4X);
        elevatorEnc.setDistancePerPulse(kDistancePerPulse);
        elevatorEnc.setMaxPeriod(0.1);

        setSetpoint(RobotMap.startingPosition);
        enable();
    }

    @Override
    protected double returnPIDInput() {
        return getElevatorEnc();
    }

    @Override
    protected void usePIDOutput(double output) {
        winch.set(output);
        System.out.println("Value: " + getElevatorEnc() + "  Setpoint: " + getSetpoint() + "  Output: " + winch.get());
    }

    public int getElevatorEnc() {
        return elevatorEnc.get();
    }

    public double getElevatorPos() {
        return elevatorEnc.getDistance();
    }

    public void resetElevatorEnc() {
        elevatorEnc.reset();
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new ElevatorPosition());
    }

}
