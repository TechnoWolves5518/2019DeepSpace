package frc.robot.sarlacc;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class SarlaccSubsystem extends Subsystem {

    //  Pneumatic Components \\
    public Compressor compressor;
    public DoubleSolenoid arms;
    public DoubleSolenoid front, back;


    public SarlaccSubsystem() {
        compressor = new Compressor(RobotMap.compressor);
        arms = new DoubleSolenoid(RobotMap.forwardChannel, RobotMap.reverseChannel);

        compressor.setClosedLoopControl(true); // Refills compressor automatically
        compressor.start(); // Starts compressor.
        back = new DoubleSolenoid(RobotMap.backSolenoidF, RobotMap.backSolenoidR);
        front = new DoubleSolenoid(RobotMap.frontSolenoidF, RobotMap.frontSolenoidR);
        liftFront(false);
        liftBack(false);
        arms.set(Value.kReverse);
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new SarlaccCom());
    }

    public void toggleArms(boolean active) {
        if (active)
            arms.set(Value.kForward);
        else
            arms.set(Value.kReverse);
    }

    public void liftFront(boolean active) {
        if (active) {
            front.set(Value.kForward);
	    if (RobotMap.debugSarlacc)  {
            	System.out.println("FRONT PISTON ACTIVE");
	    }
        } else {
            front.set(Value.kReverse);
            if (RobotMap.debugSarlacc) {
		System.out.println("FRONT PISTON INACTIVE");
	    }        
	}
    }

    public void liftBack(boolean active) {
        if (active) {
            back.set(Value.kForward);
            if (RobotMap.debugSarlacc) {
                System.out.println("BACK PISTON ACTIVE");
            }
        } else {
            back.set(Value.kReverse);
            if (RobotMap.debugSarlacc) {
                System.out.println("BACK PISTON INACTIVE");
            }
        }
    }
