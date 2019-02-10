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

        // elevator.setSetpoint(setpoint + offset);
        // elevator.setSetpoint(setpoint += (driver.getRawAxis(OI.XBOX_RSTICKY * 100)));

        // if (OI.controllerToggle) {
        //     newElevator.setelevator(driver.getRawAxis(OI.XBOX_RSTICKY));
        // } else {
        //     newElevator.setelevator(driver.getRawAxis(OI.leftJoyX));
        // }

        elevator.logPID();
    }

    public void xboxControls() {
        if (driver.getBButtonPressed()) {
            elevator.resetElevatorEnc();
            elevator.setSetpoint(RobotMap.startingPosition);
        }

        adjust = -driver.getRawAxis(OI.XBOX_RSTICKY);
        offset = (int)(adjust * RobotMap.maxOffset);

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

        setpoint += offset;
        elevator.setSetpoint(setpoint);
        elevator.logPID();
    }

    public void joystickControls() {
        adjust = stick.getRawAxis(OI.leftJoyY);
        offset = (int)(adjust * RobotMap.maxOffset);

        if (stick.getRawButton(OI.right1Down)) {
            setpoint = RobotMap.bottomPosition;
            active = 0;
        } else if (stick.getRawButton(OI.right2Down)) {
            setpoint = RobotMap.middlePosition;
            active = 1;
        } else if (stick.getRawButton(OI.right3Down)) {
            setpoint = RobotMap.topPosition;
            active = 2;
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}