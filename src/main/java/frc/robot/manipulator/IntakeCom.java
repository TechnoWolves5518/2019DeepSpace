package frc.robot.manipulator;

import frc.robot.CommandBase;
import frc.robot.OI;

public class IntakeCom extends CommandBase {

    public IntakeCom() {
        requires(intake);
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        if (OI.sf.getRawButton(OI.XBOX_XBTN)) {
            intake.intake();
        } else if (OI.sf.getRawButton(OI.XBOX_ABTN)) {
            intake.outtake();
        } else {
            intake.stop();
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}