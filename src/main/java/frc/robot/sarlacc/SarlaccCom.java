package frc.robot.sarlacc;

import frc.robot.CommandBase;
import frc.robot.OI;

public class SarlaccCom extends CommandBase {

    boolean active;

    public SarlaccCom() {
        requires(sarlaccSub);
        active = false;
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
        } else {
            if (OI.stick.getRawButtonPressed(OI.leftThumb)) 
                active = !active;
        }

        if (active)
            sarlaccSub.openArms(); // If main trigger pressed, turn on solenoid (open claws)
        else
            sarlaccSub.closeArms(); // If bottom trigger pressed, reverse solenoid (close claws)
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}