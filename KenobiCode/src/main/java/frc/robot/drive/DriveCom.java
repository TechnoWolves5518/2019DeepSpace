package frc.robot.drive;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DriveCom extends Command {

    private double leftSpeed, rightSpeed;

    public DriveCom() {
        requires(Robot.driveTrain);
    }

    @Override
    protected void initialize() {
        leftSpeed = 0;
        rightSpeed = 0;
    }

    @Override
    protected void execute() {
        leftSpeed = Robot.oi.driver.getY(Hand.kLeft);
        rightSpeed = Robot.oi.driver.getY(Hand.kRight);
        Robot.driveTrain.drive(leftSpeed, rightSpeed);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        
    }

}