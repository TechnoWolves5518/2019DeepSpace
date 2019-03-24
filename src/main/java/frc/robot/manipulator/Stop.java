package frc.robot.manipulator;

import frc.robot.CommandBase;

public class Stop extends CommandBase {

    public Stop() {
        System.out.println("stop");
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