package frc.robot;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.drive.DriveTrainSubsystem;
import frc.robot.vision.ImplementVision;

public abstract class CommandBase extends Command {

    public static DriveTrainSubsystem driveTrain;
    public static ImplementVision vision;

    public static void init() {
        driveTrain = new DriveTrainSubsystem();
        vision = new ImplementVision();
    }

}
