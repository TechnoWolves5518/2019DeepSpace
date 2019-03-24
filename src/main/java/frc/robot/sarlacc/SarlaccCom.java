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

    int counter = 0;

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
            System.out.println("---------------FRONT PRESSED, SLOW MODE ACTIVE------------");
            sarlacc.liftFront(frontActive);
        }
        if (OI.driver.getRawButtonPressed(OI.XBOX_BBTN)) {
            backActive = !backActive;
            System.out.println("---------------BACK PRESSED, SLOW MODE ACTIVE------------");
            sarlacc.liftBack(backActive);
        }

        // if (sarlaccSub.frontActive || sarlaccSub.backActive) {
        //     RobotMap.maxSpeed = RobotMap.limitedSpeed;
        //     RobotMap.maxTurn = RobotMap.limitedTurn;
        // }
        // if (!sarlaccSub.frontActive && !sarlaccSub.backActive) {
        //     RobotMap.maxSpeed = RobotMap.defaultSpeed;
        //     RobotMap.maxTurn = RobotMap.defaultTurn;
        // }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}