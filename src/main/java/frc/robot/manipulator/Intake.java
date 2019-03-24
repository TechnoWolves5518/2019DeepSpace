package frc.robot.manipulator;

import frc.robot.CommandBase;

public class Intake extends CommandBase {

    public Intake() {
        System.out.println("intake");
        requires(intake);
    }

    @Override
    protected void execute() {
        intake.intake();
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