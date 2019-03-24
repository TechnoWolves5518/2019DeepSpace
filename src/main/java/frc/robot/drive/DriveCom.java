package frc.robot.drive;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.CommandBase;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class DriveCom extends CommandBase {

    private static XboxController controller = Robot.oi.driver;

    // speed values taken in from controllers
    private double ly, rx;

    // deadzone of controller joystick
    private double deadzone = 0.05;

    private boolean slow, climb;

    public DriveCom() {
        requires(driveTrain);
    }
    
    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        ly = controller.getRawAxis(OI.XBOX_LSTICKY);
        rx = controller.getRawAxis(OI.XBOX_RSTICKX);
    
        slow = controller.getRawButton(OI.XBOX_RBUMPER);
        climb = controller.getRawButton(OI.XBOX_LBUMPER);
        arcade(true);
    }

    public void arcade(boolean config) {
        if (config) {
            if (slow)
                driveTrain.arcadeDrive(configSpeed(ly, RobotMap.limitedSpeed), configSpeed(rx, RobotMap.limitedTurn));
            else if (climb)
                driveTrain.arcadeDrive(configSpeed(ly, RobotMap.climbSpeed), configSpeed(rx, RobotMap.climbTurn));
            else
                driveTrain.arcadeDrive(configSpeed(ly, RobotMap.defaultSpeed), configSpeed(rx, RobotMap.defaultTurn));
        }
        else {
            driveTrain.arcadeDrive(ly * RobotMap.defaultSpeed, rx * RobotMap.defaultTurn);
        }
    }

    public double configSpeed(double s, double max) {
        // applies a deadzone to the raw controller speeds
        if (Math.abs(s) <= deadzone)
            s = 0;
        // cubes the speeds for sensitivity control
        s = Math.pow(s, 3);
        s *= max;
        return s;
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
    }

}