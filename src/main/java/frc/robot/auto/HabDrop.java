package frc.robot.auto;

import frc.robot.CommandBase;

public class HabDrop extends CommandBase {

    public HabDrop() {
        requires(driveTrain);
    }

    @Override
    protected void initialize() {
        driveTrain.setPIDenabled(true);
        driveTrain.driveToSetpoint(2000);
    }

    @Override
    protected void execute() {
        driveTrain.driveToSetpoint(2000);
    }

	@Override
	protected boolean isFinished() {
		return false;
    }
}