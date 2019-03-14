package frc.robot.sarlacc;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class SarlaccSubsystem extends Subsystem {

    //  Pneumatic Components \\
    public Compressor compressor;
    public DoubleSolenoid solenoid;
    public DoubleSolenoid front, back;
    public boolean frontActive;
    public boolean backActive;
    public boolean frontActiveAuto;
    public boolean backActiveAuto;

    // public boolean active;

    public SarlaccSubsystem() {
        compressor = new Compressor(RobotMap.compressor);
        solenoid = new DoubleSolenoid(RobotMap.forwardChannel, RobotMap.reverseChannel);

        compressor.setClosedLoopControl(true); // Refills compressor automatically
        compressor.start(); // Starts compressor.
        back = new DoubleSolenoid(RobotMap.backSolenoidF, RobotMap.backSolenoidR);
        front = new DoubleSolenoid(RobotMap.frontSolenoidF, RobotMap.frontSolenoidR);
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new SarlaccCom());
    }

    public void liftFront(boolean active) {
        if (active) {
            front.set(Value.kForward);
            System.out.println("FRONT PISTON ACTIVE");
        } else {
            front.set(Value.kReverse);
            System.out.println("FRONT PISTON INACTIVE");
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

    public void backPistonToggle() {
        backActiveAuto = !backActiveAuto;
        if (backActiveAuto) {
            back.set(Value.kForward);
        } else {
            back.set(Value.kReverse);
        }
    }

    public void frontPistonToggle() {
        frontActiveAuto = !frontActiveAuto;
        if (frontActiveAuto) {
            back.set(Value.kForward);
        } else {
            back.set(Value.kReverse);
        }
    }

    public void openArms() {
        solenoid.set(Value.kReverse);
        if (RobotMap.debugSarlacc) {
            System.out.println("ARMS OPEN");
        }
    }


    public void closeArms() {
        solenoid.set(Value.kForward);
        if (RobotMap.debugSarlacc) {
            System.out.println("ARMS CLOSED");
        }
    }

    public void armsOff() {
        solenoid.set(Value.kOff);
        if (RobotMap.debugSarlacc) {
            System.out.println("ARMS OFF");
        }
    }

}