package frc.robot.pullout;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotMap;

public class PullOut extends CommandGroup {

    public PullOut() {
        addSequential(new DropWinch(RobotMap.dropDistance));
        addSequential(new DriveBack(RobotMap.backUpDistance));
    }

}