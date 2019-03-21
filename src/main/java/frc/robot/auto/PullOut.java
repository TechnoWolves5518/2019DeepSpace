package frc.robot.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.manipulator.Outtake;
import frc.robot.manipulator.Stop;

public class PullOut extends CommandGroup {

    public PullOut() {
        addParallel(new Outtake());
        addSequential(new AutoDriveForward(0.7));
        addSequential(new Stop());
    }

}