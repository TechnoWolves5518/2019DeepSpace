package frc.robot.elevator;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.CommandBase;
import frc.robot.OI;
import frc.robot.RobotMap;

public class ElevatorPosition extends CommandBase {

    private String[] locations = { "floor", "bottom", "middle", "top" };

    int active = -1;
    int setpoint = 0;
    double adjust = 0;
    int offset = 0;

    private static Joystick stick = OI.stick;
    private static XboxController driver = OI.driver;

    private DigitalInput limit = new DigitalInput(RobotMap.limitSwitch);
    private boolean lastLimit = false;

    public ElevatorPosition() {
        requires(elevator);
        elevator.setSetpoint(RobotMap.startingPosition);
    }

    @Override
    protected void execute() {
        if (OI.controllerToggle) {
            xboxControls();
        } else {
            joystickControls();
        }
    }

    public void xboxControls() {
        if (driver.getBButtonPressed()) {
        // if (limit.get() && !lastLimit) {
            elevator.resetElevatorEnc();
            offset = 0;
            elevator.setSetpoint(RobotMap.startingPosition);
        }
        lastLimit = limit.get();

        adjust = driver.getRawAxis(OI.XBOX_RTRIGGER) - driver.getRawAxis(OI.XBOX_LTRIGGER);
        offset += (int)(adjust * RobotMap.maxOffsetRateController);

        System.out.println(offset);

        if (offset > RobotMap.maxOffsetController)
            offset = RobotMap.maxOffsetController;
        else if (offset < -RobotMap.maxOffsetController)
            offset = -RobotMap.maxOffsetController;

        if (driver.getRawButtonPressed(OI.XBOX_RBUMPER)) {
            active++; offset = 0;
        } else if (driver.getRawButtonPressed(OI.XBOX_LBUMPER)) {
            active--; offset = 0;
        }
        
        if (active < 0)
            active = 0;
        else if (active > 3)
            active = 3;

        switch (active) {
            case 0:
                setpoint = RobotMap.startingPosition;
                setDisplayLocation("floor");
                break;
            case 1:
                setpoint = RobotMap.bottomPosition;
                setDisplayLocation("bottom");
                break;
            case 2:
                setpoint = RobotMap.middlePosition;
                setDisplayLocation("middle");
                break;
            case 3:
                setpoint = RobotMap.topPosition;
                setDisplayLocation("top");
                break;
        }

        setpoint += offset;

        if (setpoint > RobotMap.topPosition)
            setpoint = RobotMap.topPosition;

        elevator.setSetpoint(setpoint);
        elevator.logPID();
    }

    public void joystickControls() {
        if (stick.getRawButtonPressed(OI.rightFunButton)) {
            elevator.resetElevatorEnc();
            offset = 0;
            elevator.setSetpoint(RobotMap.startingPosition);
        }
        
        adjust = stick.getRawAxis(OI.leftJoyY);
        offset = (int)(adjust * RobotMap.maxOffsetStick);

        if (stick.getRawButton(OI.right1Down)) {
            setpoint = RobotMap.bottomPosition;
        } else if (stick.getRawButton(OI.right2Down)) {
            setpoint = RobotMap.middlePosition;
        } else if (stick.getRawButton(OI.right3Down)) {
            setpoint = RobotMap.topPosition;
        }

        elevator.setSetpoint(setpoint + offset);
        elevator.logPID();
    }

    public void setDisplayLocation(String loc) {
        for (String location : locations) {
            if (loc.equals(location)) {
                SmartDashboard.putBoolean(loc, true);
            } else {
                SmartDashboard.putBoolean(loc, false);
            }
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}