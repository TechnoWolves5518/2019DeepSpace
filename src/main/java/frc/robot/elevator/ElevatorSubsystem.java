package frc.robot.elevator;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;


public class ElevatorSubsystem extends Subsystem {

    private VictorSP elevator = new VictorSP(RobotMap.elevatorId);

    private Encoder elevatorEnc;

    private double kGearDiameter = 0;
    private double kGearDistancePerRev = kGearDiameter * Math.PI;
    private double kPulsesPerRev = 360;
    private double kDistancePerPulse = kGearDistancePerRev / kPulsesPerRev;

    public ElevatorSubsystem() {
        elevatorEnc = new Encoder(RobotMap.ELEVATOR_ENC_A, RobotMap.ELEVATOR_ENC_B, true, EncodingType.k4X);
        elevatorEnc.setDistancePerPulse(kDistancePerPulse);
        elevatorEnc.setMaxPeriod(0.1);
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

    }

}