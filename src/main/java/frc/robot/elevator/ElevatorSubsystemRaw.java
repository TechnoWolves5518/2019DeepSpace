package frc.robot.elevator;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class ElevatorSubsystemRaw extends Subsystem {

    private VictorSP winch1 = new VictorSP(RobotMap.winch1);
    private VictorSP winch2 = new VictorSP(RobotMap.winch2);
    private Encoder elevatorRawEnc;

    public ElevatorSubsystemRaw() {
        winch2.setInverted(true);
        elevatorRawEnc = new Encoder(RobotMap.ELEVATOR_ENC_A, RobotMap.ELEVATOR_ENC_B, false, EncodingType.k4X);
    }

    public void runElevator(double s) {
        winch1.set(s);
        winch2.set(s);
    }

    public int getElevatorRawEnc() {
        return elevatorRawEnc.get();
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new ElevatorPositionRaw());
    }

}
