package frc.robot.sarlacc;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.CommandBase;
import frc.robot.OI;
import frc.robot.sarlacc.sarlaccSubsystem;

public class sarlaccCom extends CommandBase {


    public sarlaccCom() {
        requires(sarlaccSub); //Tells it that it needs its specific subsystem.
    }

    @Override
    protected void initialize() {
        sarlaccSub.sarlaccToggle.set(Value.kOff); //Makes sure that when the class is initialized that the solenoid is off.
    }

    @Override
    protected void execute() { //This section runs continuously during operation
        if (OI.rightMainTrigger.get()) {
            sarlaccSub.sarlaccToggle.set(Value.kForward); //If main trigger pressed, turn on solenoid (close claws)
        }
        if (OI.rightBottomTrigger.get()) {
            sarlaccSub.sarlaccToggle.set(Value.kReverse); //If bottom trigger pressed, reverse solenoid (open claws)
        }
        if (OI.rightMidLeftButton.get()) {
            sarlaccSub.sarlaccToggle.set(Value.kOff); //If black button pressed, reset/disable/etc solenoid. (stop claw)
        }
        
    }
    @Override
    protected boolean isFinished() {
        return false;
    }

}