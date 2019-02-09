package frc.robot.sarlacc;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class SarlaccSubsystem extends Subsystem {

    //  Pneumatic Components \\
    public Compressor compressor;
    public DoubleSolenoid solenoid;

    public SarlaccSubsystem() {
        compressor = new Compressor(RobotMap.compressor);
        solenoid = new DoubleSolenoid(RobotMap.forwardChannel, RobotMap.reverseChannel);

        compressor.setClosedLoopControl(true); // Refills compressor automatically
        compressor.start(); // Starts compressor.
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new SarlaccCom());
    }

    public void openArms() {
        solenoid.set(Value.kForward);
    }

    public void closeArms() {
        solenoid.set(Value.kReverse);
    }

    public void armsOff() {
        solenoid.set(Value.kOff);
    }

}