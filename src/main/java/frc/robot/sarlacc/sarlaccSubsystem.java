package frc.robot.sarlacc;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class SarlaccSubsystem extends Subsystem {

    //  Pneumatic Components \\
    public Compressor sarlaccComp;
    public DoubleSolenoid sarlaccToggle;

    public SarlaccSubsystem() { // constructor
        sarlaccComp = new Compressor(8); // assigns the compressor to something
        // sarlaccComp = new Compressor(RobotMap.compressor); // assigns the compressor to something
        sarlaccToggle = new DoubleSolenoid(RobotMap.forwardChannel, RobotMap.reverseChannel); // assigns DoubleSolenoid to something

        sarlaccComp.setClosedLoopControl(true); // Refills compressor automatically
        sarlaccComp.start(); // Starts compressor.
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new SarlaccCom());
    }

}