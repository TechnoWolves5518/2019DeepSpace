package frc.robot.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ClimbGroup extends CommandGroup {

    public ClimbGroup() {
        addSequential(new ActivateClimbPistons(true, false));
    }

}