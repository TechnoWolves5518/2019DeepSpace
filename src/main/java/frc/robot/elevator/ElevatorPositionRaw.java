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
        if (Robot.oi.sf.getRawAxis(Robot.oi.XBOX_RTRIGGER) > 0.2)
            elevatorRaw.runElevator(1);
        else if (Robot.oi.sf.getRawAxis(Robot.oi.XBOX_LTRIGGER) > 0.2)
            elevatorRaw.runElevator(-1);
        else
            elevatorRaw.runElevator(0);

        
        // if (Robot.oi.sf.getRawButton(Robot.oi.XBOX_RBUMPER))
        //     elevatorRaw.runElevator(1);
        // else if (Robot.oi.sf.getRawButton(Robot.oi.XBOX_LBUMPER))
        //     elevatorRaw.runElevator(-1);
        // else
        //     elevatorRaw.runElevator(0);
        
        if (RobotMap.debugElevator)
                // System.out.println("Elevator Position = " + active);
                System.out.println("Elevator Encoders = " + elevatorRaw.getElevatorRawEnc());
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}