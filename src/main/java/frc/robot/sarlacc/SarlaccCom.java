package frc.robot.sarlacc;

import frc.robot.CommandBase;
import frc.robot.OI;
import frc.robot.Robot;

public class SarlaccCom extends CommandBase {

    boolean active = false;
    boolean climbA = false;
    boolean climbB = false;

    boolean frontActive = false;
    boolean backActive= false;

    public SarlaccCom() {
        requires(sarlacc);
    }

    @Override
    protected void initialize() {
        sarlacc.liftFront(false);
        sarlacc.liftBack(false);
    }

    @Override
    protected void execute() { 

        if (Robot.oi.sf.getRawButtonPressed(OI.XBOX_BBTN)) {
            active = !active;
            sarlacc.toggleArms(active);
        }

        if (OI.driver.getRawButtonPressed(OI.XBOX_ABTN)) {
            frontActive = !frontActive;
            if (System.currentTimeMillis % RobotMap.debugRefreshRate = 0) {
		System.out.println("---------------FRONT PRESSED, SLOW MODE ACTIVE------------");
	    }
            sarlacc.liftFront(frontActive);
        }
        if (OI.driver.getRawButtonPressed(OI.XBOX_BBTN)) {
            backActive = !backActive;
            if (System.currentTimeMillis % RobotMap.debugRefreshRate = 0) {
		System.out.println("---------------BACK PRESSED, SLOW MODE ACTIVE------------");
            }
	    sarlacc.liftBack(backActive);
        }


    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}
