package frc.robot.elevator;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.CommandBase;
import frc.robot.OI;
import frc.robot.RobotMap;

public class ElevatorPosition extends CommandBase {

    int active = -1;

    int setpoint = 0;

    private static Joystick stick = OI.stick;
    double adjust = 0;

    public ElevatorPosition() {
        requires(elevator);
        // elevator.setSetpoint(RobotMap.startingPosition);
    }

    @Override
    protected void execute() {
        /**
         * This line gets the raw values from the Y axis of the left throttle stick of the flight controller joysticks that our good friend nick was kind enough to lend to us this season. The stick is a litttle less sensitive that the right flight stick so we will not be using it for driving. Instead, it is used to elevate our status and balls, which brings me back to the topic at hand.
         * This value is used to slightly adjust the height of the elevator in case our PID goofs.
         */
        adjust = stick.getRawAxis(OI.leftJoyY);
        int offset = (int)(adjust * RobotMap.maxOffset);

        // if (active == 0) {
        //     bottom += offset;
        // } else if (active == 1) {
        //     middle += offset;
        // } else if (active == 2) {
        //     top += offset;
        // }

        if (OI.right1Down.get()) {
            setpoint = RobotMap.bottomPosition;
            active = 0;
        } else if (OI.right2Down.get()) {
            setpoint = RobotMap.middlePosition;
            active = 1;
        } else if (OI.right3Down.get()) {
            setpoint = RobotMap.topPosition;
            active = 2;
        }

        elevator.setSetpoint(setpoint + offset);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}