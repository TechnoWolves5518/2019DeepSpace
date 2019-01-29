package frc.robot.sarlacc;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class sarlaccSubsystem extends Subsystem {

    //Pneumatic Components\\
    public Compressor sarlaccComp;
    public DoubleSolenoid sarlaccToggle;

    public sarlaccSubsystem() { //constructor

        sarlaccComp = new Compressor(RobotMap.Compressor); //assigns the compressor to something
        sarlaccToggle = new DoubleSolenoid(RobotMap.FowardChannel, RobotMap.ReverseChannel); //assigns DoubleSolenoid to something

        sarlaccComp.setClosedLoopControl(true); //Refills compressor automatically
        sarlaccComp.start(); //Starts compressor.
    }


    @Override
    protected void initDefaultCommand() {

    }

}