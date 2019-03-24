package frc.robot;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.drive.DriveTrainSubsystem;
// import frc.robot.elevator.ElevatorSubsystem;
import frc.robot.elevator.ElevatorSubsystemRaw;
import frc.robot.manipulator.IntakeSubsystem;
import frc.robot.sarlacc.SarlaccSubsystem;

public abstract class CommandBase extends Command {

    public static DriveTrainSubsystem driveTrain;
    // public static ElevatorSubsystem elevator;
    public static ElevatorSubsystemRaw elevatorRaw;
    public static SarlaccSubsystem sarlacc;
    public static IntakeSubsystem intake;

    public static void init() {
        driveTrain = new DriveTrainSubsystem();
        // elevator = new ElevatorSubsystem();
        elevatorRaw = new ElevatorSubsystemRaw();
        sarlacc = new SarlaccSubsystem();
        intake = new IntakeSubsystem();
    }

}
