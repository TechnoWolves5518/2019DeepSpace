package frc.robot.elevator;


import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import frc.robot.RobotMap;

public class ElevatorSubsystem extends PIDSubsystem {

    private VictorSP winch1 = new VictorSP(RobotMap.winch1);
    private VictorSP winch2 = new VictorSP(RobotMap.winch2);
    private Encoder elevatorEnc;

    private double kGearDiameter = 0;
    private double kGearDistancePerRev = kGearDiameter * Math.PI;
    private double kPulsesPerRev = 360;
    private double kDistancePerPulse = kGearDistancePerRev / kPulsesPerRev;

    public ElevatorSubsystem() {
        super("Elevator", RobotMap.kP, RobotMap.kI, RobotMap.kD);
        setAbsoluteTolerance(0.5);
        getPIDController().setContinuous(false);
        getPIDController().setOutputRange(-1, 1);

        elevatorEnc = new Encoder(RobotMap.ELEVATOR_ENC_A, RobotMap.ELEVATOR_ENC_B, false, EncodingType.k4X);
        elevatorEnc.setDistancePerPulse(kDistancePerPulse);
        elevatorEnc.setMaxPeriod(0.1);

        winch2.setInverted(true);

        setSetpoint(RobotMap.startingPosition);
        enable();
    }

    @Override
    protected double returnPIDInput() {
        return getElevatorEnc();
    }

    @Override
    protected void usePIDOutput(double output) {
        winch1.set(output);
        winch2.set(output);
    }

    public long getTime() {
        return System.currentTimeMillis();
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

    public void logPID() {
        System.out.println("Value: " + getElevatorEnc() + "  Setpoint: " + getSetpoint());
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new ElevatorPosition());
    }

}
