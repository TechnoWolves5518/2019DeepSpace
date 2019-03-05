package frc.robot.auto;

import frc.robot.OI;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.CommandBase;

public class HabClimb extends CommandBase {

    public boolean Active;
    public boolean Kill;
    public int counter = 0;

    
    public HabClimb() {
        requires(driveTrain);
        requires(sarlaccSub);
    }


    @Override
    protected void execute() {
        if (OI.sf.getRawButtonPressed(OI.XBOX_XBTN)) {
            if (counter == 0) {
                RobotMap.maxSpeed = 0.5;
                RobotMap.maxTurn = 0.3;
                sarlaccSub.frontActive = true;
                counter++;        
            }
            if (counter == 1) {
                sarlaccSub.frontActive = false;
                sarlaccSub.backActive = true;
                counter++;
            }
            if (counter == 2) {
                sarlaccSub.backActive = false;
                counter = 0;
                RobotMap.maxSpeed = 1.0;
                RobotMap.maxTurn = 1.0;
            }
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}