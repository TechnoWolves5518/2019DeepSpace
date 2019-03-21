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
        // sarlaccSub.active = false;
        sarlacc.frontActive = false;
        sarlacc.backActive = false;
    }

    @Override
    protected void initialize() {
        sarlacc.armsOff();
    }

    @Override
    protected void execute() { 
        if (OI.controllerToggle) { 

            if (OI.sf.getRawButtonPressed(OI.XBOX_BBTN)) {
                active = !active;
                // if (active)
                //     sarlaccSub.openArms(); // If main trigger pressed, turn on solenoid (open claws)
                // else
                //     sarlaccSub.closeArms(); // If bottom trigger pressed, reverse solenoid (close claws)
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
        } else {
            if (OI.stick.getRawButtonPressed(OI.leftThumb)) 
                active = !active;
        }

        // if (sarlaccSub.frontActive || sarlaccSub.backActive) {
        //     RobotMap.maxSpeed = RobotMap.limitedSpeed;
        //     RobotMap.maxTurn = RobotMap.limitedTurn;
        // }
        // if (!sarlaccSub.frontActive && !sarlaccSub.backActive) {
        //     RobotMap.maxSpeed = RobotMap.defaultSpeed;
        //     RobotMap.maxTurn = RobotMap.defaultTurn;
        // }

        // if (OI.driver.getRawButtonPressed(OI.XBOX_XBTN)) {
        //     if (counter == 0) {
        //         sarlacc.liftFront(true);
        //         counter++;        
        //     }
        //     if (counter == 1) {
        //         sarlacc.liftFront(false);
        //         sarlacc.liftBack(true);
        //         counter++;
        //     }
        //     if (counter == 2) {
        //         sarlacc.liftBack(false);
        //         counter++;
        //     }
        //     if (counter == 3) {
        //         sarlacc.liftFront(false);
        //         sarlacc.liftBack(false);
        //         counter = 0;
        //     }
        // }
    }


    @Override
    protected boolean isFinished() {
        return false;
    }

}