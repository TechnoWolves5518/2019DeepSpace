package frc.robot.sarlacc;

import javax.lang.model.util.ElementScanner6;

import frc.robot.CommandBase;
import frc.robot.OI;
import frc.robot.RobotMap;

public class SarlaccCom extends CommandBase {

    boolean active = false;
    boolean climbA = false;
    boolean climbB = false;

    int counter = 0;

    public SarlaccCom() {
        requires(sarlaccSub);
        // sarlaccSub.active = false;
        sarlaccSub.frontActive = false;
        sarlaccSub.backActive = false;
    }

    @Override
    protected void initialize() {
        sarlaccSub.armsOff();
    }

    @Override
    protected void execute() { 
        if (OI.controllerToggle) { 

            if (OI.sf.getRawButtonPressed(OI.XBOX_XBTN)) {
                active = !active;
                if (active)
                    sarlaccSub.openArms(); // If main trigger pressed, turn on solenoid (open claws)
                else
                    sarlaccSub.closeArms(); // If bottom trigger pressed, reverse solenoid (close claws)
            }
            if (OI.driver.getRawButtonPressed(OI.XBOX_ABTN)) {
                sarlaccSub.frontActive = !sarlaccSub.frontActive;
                System.out.println("---------------FRONT PRESSED, SLOW MODE ACTIVE------------");
                sarlaccSub.liftFront(sarlaccSub.frontActive);
            }
            if (OI.driver.getRawButtonPressed(OI.XBOX_BBTN)) {
                sarlaccSub.backActive = !sarlaccSub.backActive;
                System.out.println("---------------BACK PRESSED, SLOW MODE ACTIVE------------");
                sarlaccSub.liftBack(sarlaccSub.backActive);
            }
        } else {
            if (OI.stick.getRawButtonPressed(OI.leftThumb)) 
                active = !active;
        }

        if (sarlaccSub.frontActive || sarlaccSub.backActive) {
            RobotMap.maxSpeed = RobotMap.limitedSpeed;
            RobotMap.maxTurn = RobotMap.limitedTurn;
        }
        if (!sarlaccSub.frontActive && !sarlaccSub.backActive) {
            RobotMap.maxSpeed = RobotMap.defaultSpeed;
            RobotMap.maxTurn = RobotMap.defaultTurn;
        }

        if (OI.driver.getRawButtonPressed(OI.XBOX_XBTN)) {
            if (counter == 0) {
                sarlaccSub.liftFront(true);
                counter++;        
            }
            if (counter == 1) {
                sarlaccSub.liftFront(false);
                sarlaccSub.liftBack(true);
                counter++;
            }
            if (counter == 2) {
                sarlaccSub.liftBack(false);
                counter++;
            }
            if (counter == 3) {
                sarlaccSub.liftFront(false);
                sarlaccSub.liftBack(false);
                counter = 0;
            }
        }
    }


    @Override
    protected boolean isFinished() {
        return false;
    }

}