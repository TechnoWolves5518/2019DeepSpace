package frc.robot.sarlacc;

import frc.robot.CommandBase;
import frc.robot.OI;

public class SarlaccCom extends CommandBase {

    boolean active = false;
    boolean climbA = false;
    boolean climbB = false;

    int counter = 0;

    public SarlaccCom() {
        requires(sarlacc);
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() { 

        if (OI.sf.getRawButtonPressed(OI.XBOX_BBTN)) {
            active = !active;
            sarlacc.toggleArms(active);
        }

        // if (OI.driver.getRawButtonPressed(OI.XBOX_ABTN)) {
        //     sarlaccSub.frontActive = !sarlaccSub.frontActive;
        //     System.out.println("---------------FRONT PRESSED, SLOW MODE ACTIVE------------");
        //     sarlaccSub.liftFront(sarlaccSub.frontActive);
        // }
        // if (OI.driver.getRawButtonPressed(OI.XBOX_BBTN)) {
        //     sarlaccSub.backActive = !sarlaccSub.backActive;
        //     System.out.println("---------------BACK PRESSED, SLOW MODE ACTIVE------------");
        //     sarlaccSub.liftBack(sarlaccSub.backActive);
        // }

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