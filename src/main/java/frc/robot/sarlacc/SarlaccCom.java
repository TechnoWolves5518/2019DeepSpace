package frc.robot.sarlacc;

import javax.lang.model.util.ElementScanner6;

import frc.robot.CommandBase;
import frc.robot.OI;

public class SarlaccCom extends CommandBase {

    boolean active;
    boolean frontActive;
    boolean backActive;
    

    public SarlaccCom() {
        requires(sarlaccSub);
        active = false;
        frontActive = false;
        backActive = false;
    }

    @Override
    protected void initialize() {
        sarlaccSub.armsOff();
    }

    @Override
    protected void execute() { 
        if (OI.controllerToggle) { 
            if (OI.driver.getRawButtonPressed(OI.XBOX_XBTN))
                active = !active;
            if (OI.driver.getRawButtonPressed(OI.XBOX_YBTN))
                frontActive = !frontActive;
            if (OI.driver.getRawButtonPressed(OI.XBOX_ABTN))
                backActive = !backActive;
        } else {
            if (OI.stick.getRawButtonPressed(OI.leftThumb)) 
                active = !active;
        }

        if (active)
            sarlaccSub.openArms(); // If main trigger pressed, turn on solenoid (open claws)
        else
            sarlaccSub.closeArms(); // If bottom trigger pressed, reverse solenoid (close claws)

        sarlaccSub.liftFront(frontActive);
        sarlaccSub.liftBack(backActive);
    }


    @Override
    protected boolean isFinished() {
        return false;
    }

}