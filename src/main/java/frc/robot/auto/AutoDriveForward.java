package frc.robot.auto;

import frc.robot.CommandBase;

public class AutoDriveForward extends CommandBase {

    private double timeout;

    public AutoDriveForward(double timeout) {
	    requires(driveTrain);
        this.timeout = timeout;
    }

    @Override
    protected void initialize() {
        setTimeout(timeout);
    }

    @Override
    protected void execute() {
        driveTrain.arcadeDrive(RobotMap.autoClimbSpeed, RobotMap.autoClimbTurn);
    }

    @Override
    protected boolean isFinished() {
        return isTimedOut();
    }

}
