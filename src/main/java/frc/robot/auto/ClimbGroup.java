package frc.robot.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ClimbGroup extends CommandGroup {

    public ClimbGroup() {
        System.out.println("Climb Group");
        addSequential(new ActivateClimbPistons(true, false));   // Extends front pistons, retracts back piston
        addSequential(new AutoDriveForward(2));                 // THE TWO IS AN UNTESTED NUMBER THAT NEEDS TO BE TESTED
        addSequential(new ActivateClimbPistons(false, true));   // Retracts front piston, extends back piston
        addSequential(new AutoDriveForward(2));                 // THIS TWO IS ALSO UNTESTED
        addSequential(new ActivateClimbPistons(false, false));  // Retracts both pistons
        addSequential(new AutoDriveForward(2));                 // yeah this one too
    }

}
