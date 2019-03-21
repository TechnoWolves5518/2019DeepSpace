package frc.robot.drive;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.CommandBase;
import frc.robot.OI;
import frc.robot.RobotMap;

public class DriveCom extends CommandBase {

    private static Joystick stick = OI.stick;
    private static XboxController controller = OI.driver;

    // speed values taken in from controllers
    private double h, v;
    private double lx, ly, rx, ry;

    // deadzone of controller joystick
    private double deadzone = 0.05;

    private boolean quickturn;
    private boolean reverse;
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
        if (OI.controllerToggle) {
            lx = controller.getRawAxis(OI.XBOX_LSTICKX);
            ly = controller.getRawAxis(OI.XBOX_LSTICKY);
            rx = controller.getRawAxis(OI.XBOX_RSTICKX);
            ry = controller.getRawAxis(OI.XBOX_RSTICKY);
            quickturn = !OI.driver.getAButton();
            reverse = OI.driver.getYButtonPressed();
        
            slow = OI.driver.getRawButton(OI.XBOX_RBUMPER);
            arcade(true);
        } else {
            h = stick.getRawAxis(OI.rightJoyX);
            v = stick.getRawAxis(OI.rightJoyY);
            quickturn = !stick.getRawButton(OI.rightMainTrigger);
            // curvy(false);
        }
    }

    public void tank(boolean config) {
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

        if (reverse)
            driveTrain.reverseMotors();
    }

    // public void curvy(boolean config) {
    //     if (config)
    //         driveTrain.curvyDrive(configSpeed(v), h, quickturn);
    //     else {
    //         if (slow)
    //             driveTrain.curvyDrive(v * RobotMap.limitedSpeed, h * RobotMap.limitedTurn, quickturn);
    //         else
    //             driveTrain.curvyDrive(v * RobotMap.maxSpeed, h * RobotMap.maxTurn, quickturn);
    //     }
    // }

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