package frc.robot;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.drive.DriveTrainSubsystem;
import frc.robot.elevator.ElevatorSubsystem;

public abstract class CommandBase extends Command {

    public static DriveTrainSubsystem driveTrain;
    public static ElevatorSubsystem elevator;

    public static void init() {
        driveTrain = new DriveTrainSubsystem();
        elevator = new ElevatorSubsystem();
    }

}