package frc.robot.drive;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.CommandBase;
import frc.robot.OI;
import frc.robot.RobotMap;

public class DriveCom extends CommandBase {

    private static XboxController controller = OI.driver;

    // speed values taken in from controllers
    private double h, v;
    private double lx, ly, rx, ry;

    // deadzone of controller joystick
    private double deadzone = 0.05;

    private boolean slow;

    public DriveCom() {
        requires(driveTrain);
    }
    
    @Override
    protected void initialize() {
        h = 0;
        v = 0;
    }

    @Override
    protected void execute() {
        lx = controller.getRawAxis(OI.XBOX_LSTICKX);
        ly = controller.getRawAxis(OI.XBOX_LSTICKY);
        rx = controller.getRawAxis(OI.XBOX_RSTICKX);
        ry = controller.getRawAxis(OI.XBOX_RSTICKY);
    
        slow = OI.driver.getRawButton(OI.XBOX_RBUMPER);
        arcade(true);
    }

    public void arcade(boolean config) {
        if (config) {
            if (slow)
                driveTrain.arcadeDrive(configSpeed(ly, RobotMap.limitedSpeed), configSpeed(rx, RobotMap.limitedTurn));
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