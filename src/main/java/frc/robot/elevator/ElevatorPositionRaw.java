package frc.robot.elevator;

import frc.robot.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class ElevatorPositionRaw extends CommandBase {

    public ElevatorPositionRaw() {
        requires(elevatorRaw);
    }

    @Override
    protected void execute() {
        if (Robot.oi.sf.getRawAxis(Robot.oi.XBOX_RTRIGGER) > RobotMap.triggerDeadZone)
            elevatorRaw.runElevator(1);
        else if (Robot.oi.sf.getRawAxis(Robot.oi.XBOX_LTRIGGER) > RobotMap.triggerDeadZone)
            elevatorRaw.runElevator(-1);
        else
            elevatorRaw.runElevator(0);       
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}
