package frc.robot.manipulator;

import frc.robot.CommandBase;
import frc.robot.RobotMap;

public class Stop extends CommandBase {

    public Stop() {
        if (RobotMap.debugIntake) {
            System.out.println("Stop");
        }
        requires(intake);
    }

    @Override
    protected void initialize() {
        intake.stop();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }

    @Override
    protected void end() {
        intake.stop();
    }

}
