package frc.robot.sarlacc;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.CommandBase;
import frc.robot.OI;
import frc.robot.RobotMap;

public class SarlaccCom extends CommandBase {

    boolean active;

    public SarlaccCom() {
        requires(sarlaccSub); //Tells it that it needs its specific subsystem
        active = false;
    }

    @Override
    protected void initialize() {
        // sarlaccSub.sarlaccToggle.set(Value.kOff); //Makes sure that when the class is initialized that the solenoid is off.
    }

    @Override
    protected void execute() { //This section runs continuously during operation
        
        // if (OI.stick.getRawButtonPressed(OI.rightBottomTriggerInt)) {
        //     active = !active;
        // }

        // System.out.println(OI.stick.getRawButton(OI.rightBottomTriggerInt));

        // if (OI.stick.getRawButton(OI.rightBottomTriggerInt)) {
        //     System.out.println("pressed");
        //     sarlaccSub.sarlaccToggle.set(Value.kForward); //If main trigger pressed, turn on solenoid (close claws)
        // } else {
        //     System.out.println("not");
        //     sarlaccSub.sarlaccToggle.set(Value.kReverse); //If bottom trigger pressed, reverse solenoid (open claws)
        // }

        if (OI.controllerToggle) { 
            OI.driver.getXButtonPressed();
            active = !active;
        } else {
            if ((OI.rightMainTrigger).get() == true) {
                active = true;
            } else {
                active = false;
            }
        }

        if (active) {
            // System.out.println("pressed");
            sarlaccSub.sarlaccToggle.set(Value.kForward); //If main trigger pressed, turn on solenoid (close claws)
        } else {
            // System.out.println("not");
            sarlaccSub.sarlaccToggle.set(Value.kReverse); //If bottom trigger pressed, reverse solenoid (open claws)
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}