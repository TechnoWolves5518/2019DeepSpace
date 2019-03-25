package frc.robot.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.manipulator.Intake;
import frc.robot.manipulator.Stop;

public class PullOut extends CommandGroup {

    public PullOut() {
	if (RobotMap.debugAuto) {
        	System.out.println("Pulling Out");
	}
	addParallel(new Intake());
        addSequential(new AutoDriveForward(0.7));
        addSequential(new Stop());
    }

}
