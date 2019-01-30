package frc.robot.elevator;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import frc.robot.RobotMap;

public class ElevatorSubsystem extends PIDSubsystem {

    private VictorSP PID = new VictorSP(RobotMap.winch);

    private Encoder elevatorEnc;

    private double kGearDiameter = 0;
    private double kGearDistancePerRev = kGearDiameter * Math.PI;
    private double kPulsesPerRev = 360;
    private double kDistancePerPulse = kGearDistancePerRev / kPulsesPerRev;

    public ElevatorSubsystem() {
        super("Elevator", 0.005, 0.001, 0.005);
        setAbsoluteTolerance(0.5);
        getPIDController().setContinuous(false);
        getPIDController().setOutputRange(-0.5, 0.5);

        elevatorEnc = new Encoder(RobotMap.ELEVATOR_ENC_A, RobotMap.ELEVATOR_ENC_B, true, EncodingType.k4X);
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
        PID.set(output);
        // System.out.println("Value: " + getElevatorEnc());
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
