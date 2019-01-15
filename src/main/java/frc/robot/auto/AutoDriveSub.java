package frc.robot.auto;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;

public class AutoDriveSub extends Subsystem {

    private double allowance = 0.3;

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(Robot.autoDriveCom);
    }

    public AutoDriveSub() {
        Robot.driveTrain.resetEncoders();
    }

    public void autoDriveForward(double target, double speed) {
        double distance = Robot.driveTrain.getAvgEncoderPos();

        if (distance < (target - allowance)) {
            Robot.driveTrain.tankDrive(speed, speed);
        } else if (distance > (target + allowance)) {
            Robot.driveTrain.tankDrive(-speed, -speed);
        } else {
            Robot.driveTrain.tankDrive(0, 0);
        }

        System.out.println(distance);
    }

}