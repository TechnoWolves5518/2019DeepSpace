package frc.robot.elevator;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.CommandBase;
import frc.robot.OI;
import frc.robot.RobotMap;
import frc.robot.sarlacc.SarlaccCom;

public class ElevatorPosition extends CommandBase {

    private String[] locations = { "floor", "bottom", "middle", "top" };

    int active = -1;
    int setpoint = 0;
    double adjust = 0;
    int offset = 0;
    public long startTime;

    private static Joystick stick = OI.stick;
    private static XboxController driver = OI.driver;

    private DigitalInput limitSwitch = new DigitalInput(RobotMap.limitSwitch);
    private boolean limit = false;
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
        limit = !limitSwitch.get();
        if (limit && !lastLimit) {
            elevator.resetElevatorEnc();
            // sarlacc.openArms();
            offset = 0;
            elevator.setSetpoint(RobotMap.startingPosition);
            active = 0;
        }
        lastLimit = limit;

        adjust = OI.sf.getRawAxis(OI.XBOX_RTRIGGER) - OI.sf.getRawAxis(OI.XBOX_LTRIGGER);
        offset += (int)(adjust * RobotMap.maxOffsetRateController);

        // if (offset > RobotMap.maxOffsetController)
        //     offset = RobotMap.maxOffsetController;
        // else if (offset < -RobotMap.maxOffsetController)
        //     offset = -RobotMap.maxOffsetController;

        if (OI.sf.getRawButtonPressed(OI.XBOX_RBUMPER)) {
            startTime = System.currentTimeMillis();
            active++; offset = 0;
            if (RobotMap.debugElevator) {
                System.out.println("Elevator Position = " + active);
            }
            
            //The following kills the motors under certain conditions.
            // if ((elevator.getElevatorEnc() != setpoint) && ((startTime - elevator.getTime()) < -2500)) {
            //     setpoint = elevator.getElevatorEnc();
            // }

        } else if (OI.sf.getRawButtonPressed(OI.XBOX_LBUMPER)) {
            startTime = System.currentTimeMillis();
            active--; offset = 0;
            if (active == 0) {
                // sarlacc.closeArms();
            }
            //The following kills the motors under certain conditions.
            // if ((elevator.getElevatorEnc() != setpoint) && ((startTime - elevator.getTime()) < -2500)) {
            //     setpoint = elevator.getElevatorEnc();
            // }

            if (RobotMap.debugElevator) {
                System.out.println("Elevator Position = " + active);
            }

        } else if (OI.sf.getRawButtonPressed(OI.XBOX_RSTICK)) {
            // sarlacc.closeArms();
            active = -2; offset = 0;
            if (RobotMap.debugElevator) {
                System.out.println("Elevator is resetting...");
            }
        }
        
        //The following kills the motors under certain conditions.
        // if ((elevator.getElevatorEnc() != setpoint) && ((startTime - elevator.getTime()) < -2500)) {
        //     setpoint = elevator.getElevatorEnc();
        // }
        
        if (active == -2) {
            // nothing
        } else if (active < 0)
            active = 0;
        else if (active > 3)
            active = 3;

        switch (active) {
            case 0:
                setpoint = RobotMap.startingPosition;
                setDisplayLocation(locations[0]);
                // sarlaccSub.active = false;
                break;
            case 1:
                setpoint = RobotMap.bottomPosition;
                setDisplayLocation(locations[1]);
                break;
            case 2:
                setpoint = RobotMap.middlePosition;
                setDisplayLocation(locations[2]);
                break;
            case 3:
                setpoint = RobotMap.topPosition;
                setDisplayLocation(locations[3]);
                break;
            case -2:
                // sarlaccSub.active = true;
                setpoint = -10000;
                // sarlaccSub.active = false;
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