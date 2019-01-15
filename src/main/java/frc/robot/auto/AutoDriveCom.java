package frc.robot.auto;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class AutoDriveCom extends Command {
    
    private double speed;

    public AutoDriveCom() {
        requires(Robot.autoDrive);
    }

    @Override
    protected void initialize() {
        speed = 0.3;
    }

    @Override
    protected void execute() {
        Robot.autoDrive.autoDriveForward(30, speed);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }


    @Override
    protected void end() {
        super.end();
    }
}