package frc.robot.auto;

import frc.robot.CommandBase;
import frc.robot.RobotMap;

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
	if ((RobotMap.debugAuto) && (System.currentTimeMillis % RobotMap.debugRefreshRate = 0)) {
		System.out.println("Automatically driving forward...");
    	}
    }

    @Override
    protected boolean isFinished() {
        return isTimedOut();
    }

}
