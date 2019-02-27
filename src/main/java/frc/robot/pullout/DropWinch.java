package frc.robot.pullout;

import frc.robot.CommandBase;

public class DropWinch extends CommandBase  {

    private int dropDistance;

    public DropWinch(int dropDistance) {
        this.dropDistance = dropDistance;
    }

    @Override
    protected void initialize() {
        elevator.setSetpoint(elevator.getElevatorPos() - dropDistance);
    }

    @Override
    protected boolean isFinished() {
        return elevator.onTarget();
    }

}