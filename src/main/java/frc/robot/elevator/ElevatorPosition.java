package frc.robot.elevator;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.CommandBase;
import frc.robot.OI;
import frc.robot.RobotMap;

public class ElevatorPosition extends CommandBase {

    int active = -1;

    int setpoint = 0;

    private static Joystick stick = OI.stick;
    private static XboxController driver = OI.driver;
    double adjust = 0;
    int offset = 0;

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
            elevator.resetElevatorEnc();
            offset = 0;
            elevator.setSetpoint(RobotMap.startingPosition);
        }

        adjust = driver.getRawAxis(OI.XBOX_RTRIGGER) - driver.getRawAxis(OI.XBOX_LTRIGGER);
        offset += (int)(adjust * RobotMap.maxOffsetController);

        if (driver.getRawButtonPressed(OI.XBOX_RBUMPER))
            active++;
        else if (driver.getRawButtonPressed(OI.XBOX_LBUMPER))
            active--;
        
        if (active < 0)
            active = 0;
        else if (active > 3)
            active = 3;

        switch (active) {
            case 0:
                setpoint = RobotMap.startingPosition;
                break;
            case 1:
                setpoint = RobotMap.bottomPosition;
                break;
            case 2:
                setpoint = RobotMap.middlePosition;
                break;
            case 3:
                setpoint = RobotMap.topPosition;
                break;
        }

        elevator.setSetpoint(setpoint + offset);
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

    @Override
    protected boolean isFinished() {
        return false;
    }

}