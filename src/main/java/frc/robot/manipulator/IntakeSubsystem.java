package frc.robot.manipulator;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class IntakeSubsystem extends Subsystem {

    public static final double manipulatorSpeed = 0.5;

    private VictorSP left = new VictorSP(RobotMap.leftManipulator);
    private VictorSP right = new VictorSP(RobotMap.rightManipulator);

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new IntakeCom());
    }

    public void intake() {
        left.set(-manipulatorSpeed);
        right.set(manipulatorSpeed);
    }

    public void outtake() {
        left.set(manipulatorSpeed);
        right.set(-manipulatorSpeed);
    }

    public void stop() {
        left.set(0);
        right.set(0);
    }

}