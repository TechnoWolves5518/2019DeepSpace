package frc.robot.pullout;

import frc.robot.CommandBase;

public class DriveBack extends CommandBase {

    private int distance;

    public DriveBack(int distance) {
        this.distance = distance;
    }

    @Override
    protected void initialize() {
        driveTrain.setPIDenabled(true);
        driveTrain.driveToSetpoint(distance);
    }

    @Override
    protected boolean isFinished() {
        return driveTrain.leftDrivePID.onTarget() && driveTrain.rightDrivePID.onTarget();
    }

    @Override
    protected void end() {
        driveTrain.setPIDenabled(false);
        driveTrain.resetEncoders();
    }
}