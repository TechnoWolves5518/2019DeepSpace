package frc.robot.elevator;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class ElevatorNoPID extends Subsystem {

    public Spark winch = new Spark(RobotMap.winch);

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new ElevatorPosition());
    }

    public void setelevator(double speed) {
        if (Math.abs(speed) < 0.1) {
            speed = 0;
            winch.set(0);
        }
        winch.set(speed);
    }

}