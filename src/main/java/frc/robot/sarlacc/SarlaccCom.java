package frc.robot.sarlacc;

import javax.lang.model.util.ElementScanner6;

import frc.robot.CommandBase;
import frc.robot.OI;

public class SarlaccCom extends CommandBase {

    public boolean active;


    public SarlaccCom() {
        requires(sarlaccSub);
        active = false;
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
            if (OI.driver.getRawButtonPressed(OI.XBOX_XBTN))
                active = !active;
            if (OI.driver.getRawButtonPressed(OI.XBOX_YBTN))
                sarlaccSub.frontActive = !sarlaccSub.frontActive;
            if (OI.driver.getRawButtonPressed(OI.XBOX_ABTN))
                sarlaccSub.backActive = !sarlaccSub.backActive;
        } else {
            if (OI.stick.getRawButtonPressed(OI.leftThumb)) 
                active = !active;
        }

        if (active)
            sarlaccSub.openArms(); // If main trigger pressed, turn on solenoid (open claws)
        else
            sarlaccSub.closeArms(); // If bottom trigger pressed, reverse solenoid (close claws)

        sarlaccSub.liftFront(sarlaccSub.frontActive);
        sarlaccSub.liftBack(!sarlaccSub.backActive);
        System.out.println("Front: "  + sarlaccSub.frontActive + "  Back: " + sarlaccSub.backActive);
    }


    @Override
    protected boolean isFinished() {
        return false;
    }

}