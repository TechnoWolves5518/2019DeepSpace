package frc.robot.sarlacc;

import frc.robot.CommandBase;
import frc.robot.OI;

public class SarlaccCom extends CommandBase {

    boolean active;

    public SarlaccCom() {
        requires(sarlaccSub); //Tells it that it needs its specific subsystem
        active = false;
    }

    @Override
    protected void initialize() {
        sarlaccSub.armsOff(); // Makes sure that when the class is initialized that the solenoid is off.
    }

    @Override
    protected void execute() { //This section runs continuously during operation
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