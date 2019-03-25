package frc.robot.manipulator;

import frc.robot.CommandBase;

public class Intake extends CommandBase {

    public Intake() {
        if ((RobotMap.debugIntake) && (System.currentTimeMillis % RobotMap.debugRefreshRate)) {
            System.out.println("Intake");
        }
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
