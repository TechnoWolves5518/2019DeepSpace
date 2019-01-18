package frc.robot.drive;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import frc.robot.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class DriveCom extends CommandBase {

    private XboxController driver = Robot.oi.driver;

    // speed values taken in from controllers
    private double leftX, leftY, rightX, rightY;
    public double leftJoyZ, leftJoyX, leftJoyY, rightJoyX, rightJoyY, rightJoyZ, leftJoySlider;
    private boolean rightMainTrigger, rightFunButton, rightTopRightButton, rightMidRightButton, rightMidLeftButton;
    private boolean rightBottomTrigger, leftThumb, leftEButton, right1Up, right1Down, right2Up, right2Down;
    private boolean right3Up, right3Down, rightMainTriggerHard, rightTopLeftUp;
    public boolean fastMode;
    // deadzone of controller joystick
    private double deadzone = 0.05;

    private boolean isMoving;
    private boolean reverseMotors;
    private boolean quickturn;

    public DriveCom() {
        requires(driveTrain);
        System.out.println("Drive Train Com constructor");
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
        rightMainTrigger = false;
        rightFunButton = false;
        rightTopRightButton = false;
        rightMidRightButton = false;
        rightMidLeftButton = false;
        rightBottomTrigger = false;
        leftThumb = false;
        leftEButton = false;
        right1Up = false;
        right1Down = false;
        right2Up = false;
        right2Down = false;
        right3Up = false;
        right3Down = false;
        rightMainTriggerHard = false;
        rightTopLeftUp = false;
        
        System.out.println("Drive Train Com initialize");
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

        rightJoyX = Robot.oi.stick.getRawAxis(0);
        rightJoyY = -Robot.oi.stick.getRawAxis(1);
        rightJoyZ = Robot.oi.stick.getRawAxis(5);
        leftJoyX = Robot.oi.stick.getRawAxis(3);
        leftJoyY = Robot.oi.stick.getRawAxis(4);
        leftJoyZ = -Robot.oi.stick.getRawAxis(2);

        System.out.println("RightJoyX = " + rightJoyX + "  RightJoyY = " + rightJoyY + "  RightJoyZ = " + rightJoyZ +
            "  LeftJoyX = " + leftJoyX + "  LeftJoyY = " + leftJoyY + "  LeftJoyZ = " + leftJoyZ + "  Fast Mode = " + fastMode);

        curvy();
        
        if (leftJoyX == -1) {
            driveTrain.arcadeDrive(leftJoyZ, rightJoyX);
            fastMode = true;
        } else {
            driveTrain.arcadeDrive(configSpeed(leftJoyZ), rightJoyX);
            fastMode = false;
        }
    }

    public void tank() {
        isMoving = (leftY != 0 && rightY != 0);
        if (driver.getBumperPressed(Hand.kRight) && !isMoving) {
            driver.setRumble(RumbleType.kRightRumble, 1);
            reverseMotors = !reverseMotors;
            driveTrain.reverseMotors(reverseMotors);
        }

        driveTrain.tankDrive(configSpeed(leftY), configSpeed(rightY));
    }

    public void curvy() {
        isMoving = (leftY != 0 && leftX != 0);
        if (driver.getBumperPressed(Hand.kRight) && !isMoving) {
            driver.setRumble(RumbleType.kRightRumble, 1);
            reverseMotors = !reverseMotors;
            driveTrain.reverseMotors(reverseMotors);
        }

        driveTrain.curvyDrive(configSpeed(leftY), configSpeed(leftX), quickturn);
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