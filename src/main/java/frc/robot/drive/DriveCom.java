package frc.robot.drive;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import frc.robot.CommandBase;
import frc.robot.OI;
import frc.robot.RobotMap;

public class DriveCom extends CommandBase {

    private static XboxController driver = OI.driver;
    private static Joystick stick = OI.stick;

    // speed values taken in from controllers
    private double leftX, leftY, rightX, rightY;
    private double leftJoyZ, leftJoyX, leftJoyY, rightJoyX, rightJoyY, rightJoyZ, leftJoySlider;

    // deadzone of controller joystick
    private double deadzone = 0.05;

    private boolean isMoving;
    private boolean reverseMotors;
    private boolean slowMode;
    private boolean quickturn;

    public DriveCom() {
        requires(driveTrain);
    }

    @Override
    protected void initialize() {
        leftX = 0;
        leftY = 0;
        rightX = 0;
        rightY = 0;

        leftJoyX = 0;
        leftJoyY = 0;
        leftJoyZ = 0;
        rightJoyX = 0;
        rightJoyY = 0;
        rightJoyZ = 0;
        
        quickturn = false;
        reverseMotors = false;
    }

    @Override
    protected void execute() {
        // gets speeds from joysticks
        leftX = driver.getX(Hand.kLeft);
        leftY = -driver.getY(Hand.kLeft);
        rightX = driver.getX(Hand.kRight);
        rightY = -driver.getY(Hand.kRight);

        quickturn = driver.getXButton();

        rightJoyX = stick.getRawAxis(0);
        rightJoyY = -stick.getRawAxis(1);
        rightJoyZ = stick.getRawAxis(5);
        leftJoyX = stick.getRawAxis(3);
        leftJoyY = stick.getRawAxis(4);
        leftJoyZ = -stick.getRawAxis(2);

        tank(false);
        
        // if (leftJoyX == -1) {
        //     driveTrain.arcadeDrive(leftJoyZ, rightJoyX);
        //     slowMode = true;
        // } else {
        //     driveTrain.arcadeDrive(configSpeed(leftJoyZ), rightJoyX);
        //     slowMode = false;
        // }
    }

    public void tank(boolean config) {
        isMoving = (leftY != 0 && rightY != 0);
        if (driver.getBumperPressed(Hand.kRight) && !isMoving) {
            driver.setRumble(RumbleType.kRightRumble, 1);
            reverseMotors = !reverseMotors;
            driveTrain.reverseMotors(reverseMotors);
        }

        if (config)
            driveTrain.tankDrive(configSpeed(leftY), configSpeed(rightY));
        else
            driveTrain.tankDrive(leftY, rightY);
    }

    public void curvy(boolean config) {
        isMoving = (rightJoyY != 0 && rightJoyX != 0);
        if (driver.getBumperPressed(Hand.kRight) && !isMoving) {
            driver.setRumble(RumbleType.kRightRumble, 1);
            reverseMotors = !reverseMotors;
            driveTrain.reverseMotors(reverseMotors);
        }

        if (config)
            driveTrain.curvyDrive(configSpeed(rightJoyY), configSpeed(rightJoyX), quickturn);
        else
            driveTrain.curvyDrive(leftY, leftX, quickturn);
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