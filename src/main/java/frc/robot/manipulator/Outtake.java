package frc.robot.manipulator;

import frc.robot.CommandBase;

public class Outtake extends CommandBase {

    public Outtake() {
        requires(intake);
    }

    @Override
    protected void execute() {
        intake.outtake();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        intake.stop();
    }

}