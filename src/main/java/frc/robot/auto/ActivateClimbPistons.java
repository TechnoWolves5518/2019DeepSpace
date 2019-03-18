package frc.robot.auto;

import frc.robot.CommandBase;

public class ActivateClimbPistons extends CommandBase {

    private boolean front;
    private boolean back;

    public ActivateClimbPistons(boolean front, boolean back) {
        requires(sarlaccSub);
        this.front = front;
        this.back = back;
    }

    @Override
    protected void initialize() {
        sarlaccSub.liftFront(front);
        sarlaccSub.liftBack(back);
    }

    @Override
    protected boolean isFinished() {
        return true;
    }

}