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
            arcade(true);
        } else {
            h = stick.getRawAxis(OI.rightJoyX);
            v = stick.getRawAxis(OI.rightJoyY);
            quickturn = !stick.getRawButton(OI.rightMainTrigger);
            curvy(true);
        }
    }

    public void tank(boolean config) {
    }

    public void arcade(boolean config) {
        if (config)
            driveTrain.arcadeDrive(configSpeed(ly), configSpeed(lx));
        else
            driveTrain.arcadeDrive(ly, lx);
    }

    public void curvy(boolean config) {
        if (config)
            driveTrain.curvyDrive(configSpeed(v), h, quickturn);
        else
            driveTrain.curvyDrive(v, h, quickturn);
    }

    public double configSpeed(double s) {
        // applies a deadzone to the raw controller speeds
        if (Math.abs(s) <= deadzone)
            s = 0;
        // cubes the speeds for sensitivity control
        s = Math.pow(s, 3);
        s *= RobotMap.topSpeed;
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