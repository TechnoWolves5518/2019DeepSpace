package frc.robot.elevator;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class ElevatorNoPID extends Subsystem {

    public VictorSP winch1 = new VictorSP(RobotMap.winch1);
    public VictorSP winch2 = new VictorSP(RobotMap.winch2);

    public ElevatorNoPID() {
        winch1.setInverted(true);
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new ElevatorPosition());
    }

    public void setelevator(double speed) {
        if (Math.abs(speed) < 0.1)
            speed = 0;
        winch1.set(speed);
        winch2.set(speed);
    }

}