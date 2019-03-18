package frc.robot.auto;

import frc.robot.CommandBase;

public class AutoDriveForward extends CommandBase {

    private double timeout;

    public AutoDriveForward(double timeout) {
        this.timeout = timeout;
    }

    @Override
    protected void initialize() {
        setTimeout(timeout);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}