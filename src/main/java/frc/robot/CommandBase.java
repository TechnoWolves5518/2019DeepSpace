package frc.robot;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.drive.DriveTrainSubsystem;
import frc.robot.elevator.ElevatorSubsystem;
import frc.robot.sarlacc.SarlaccSubsystem;
import frc.robot.elevator.AltElevatorSub;

public abstract class CommandBase extends Command {

    public static DriveTrainSubsystem driveTrain;
    public static ElevatorSubsystem elevator;
    public static AltElevatorSub altElevator;
    public static SarlaccSubsystem sarlaccSub;

    public static void init() {
        driveTrain = new DriveTrainSubsystem();
        elevator = new ElevatorSubsystem();
        // altElevator = new AltElevatorSub();
        sarlaccSub = new SarlaccSubsystem();
    }

}
