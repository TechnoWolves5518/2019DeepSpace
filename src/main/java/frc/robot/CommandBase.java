package frc.robot;

import edu.wpi.first.wpilibj.command.Command;
// import frc.robot.auto.HabDrop;
import frc.robot.drive.DriveTrainSubsystem;
import frc.robot.elevator.ElevatorSubsystem;
import frc.robot.sarlacc.SarlaccSubsystem;
// import frc.robot.auto.HabDrop;

public abstract class CommandBase extends Command {

    public static DriveTrainSubsystem driveTrain;
    public static ElevatorSubsystem elevator;
    public static SarlaccSubsystem sarlaccSub;
    // public static HabDrop habDrop;

    public static void init() {
        // habDrop = new HabDrop();
        driveTrain = new DriveTrainSubsystem();
        elevator = new ElevatorSubsystem();
        sarlaccSub = new SarlaccSubsystem();
    }

}
