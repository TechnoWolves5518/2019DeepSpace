package frc.robot.elevator;

import frc.robot.CommandBase;
import frc.robot.OI;
import frc.robot.RobotMap;

public class ElevatorPosition extends CommandBase {

    public ElevatorPosition() {
        requires(elevator);
        elevator.setSetpoint(RobotMap.startingPosition);
    }

    @Override
    protected void execute() {
        if (OI.right1Down.get()) {
            elevator.setSetpoint(RobotMap.bottomPosition);
        } else if (OI.right2Down.get()) {
            elevator.setSetpoint(RobotMap.middlePosition);
        } else if (OI.right3Down.get()) {
            elevator.setSetpoint(RobotMap.topPosition);
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}