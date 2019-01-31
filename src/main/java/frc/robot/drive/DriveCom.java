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

    // deadzone of controller joystick
    private double deadzone = 0.05;

    private boolean quickturn;
    private boolean xbox;

    public DriveCom() {
        requires(driveTrain);
        xbox = true;
    }
    
    @Override
    protected void initialize() {
        h = 0;
        v = 0;
    }

    @Override
    protected void execute() {
        if (xbox) {
            h = -controller.getRawAxis(OI.XBOX_LSTICKX);
            v = controller.getRawAxis(OI.XBOX_LSTICKY);
            quickturn = !OI.driver.getAButton();
        } else {
            h = stick.getRawAxis(OI.rightJoyX);
            v = stick.getRawAxis(OI.rightJoyY);
            quickturn = !OI.rightMainTrigger.get();
        }

        curvy(true);
    }

    public void tank(boolean config) {
    }

    public void curvy(boolean config) {
        if (config)
            driveTrain.curvyDrive(configSpeed(v), configSpeed(h), quickturn);
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