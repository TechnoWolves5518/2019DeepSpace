package frc.robot.manipulator;

import frc.robot.CommandBase;

public class Stop extends CommandBase {

    public Stop() {
        if ((RobotMap.debugIntake) && (System.currentTimeMillis % RobotMap.debugRefreshRate)) {
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
