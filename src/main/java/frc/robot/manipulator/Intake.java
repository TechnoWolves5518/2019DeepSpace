package frc.robot.manipulator;

import frc.robot.CommandBase;
import frc.robot.RobotMap;

public class Intake extends CommandBase {

    public Intake() {
        if (RobotMap.debugIntake) {
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
