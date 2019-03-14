package frc.robot.auto;

import frc.robot.OI;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.CommandBase;

public class HabClimb extends CommandBase {
    
    public boolean climbing = false;
    public boolean gate1 = false;
    public boolean gate2 = false;
    public boolean gate3 = false;
    public boolean gate4 = false;

    public HabClimb() {
        requires(driveTrain);
        requires(sarlaccSub);
    }


    @Override
    protected void execute() {
        if (OI.driver.getRawButtonPressed(OI.XBOX_XBTN)) {
	          public startTime = System.currentTimeMillis();
            climbing = !climbing;
	          sarlaccSub.liftFront();
	      }
        
    	while (!climbing) { //This 'while' statement makes it possible to climb again; either if we failed, or if we did it on accident.
        gate1 = false;
        gate2 = false;
        gate3 = false;
        gate4 = false;
        endCondition = false;
      }  
    
	    
      while (climbing) {
	    
          RobotMap.maxSpeed = RobotMap.LimitedSpeed;
	        RobotMap.maxTurn = RobotMap.LimitedTurn;
	    
      
      
      
      
             //The following will move the robot forward if all of the following are true:
             //1. 'X' milliseconds have passed, 
             //2. It hasn't been true yet.
             
          if (((startTime - getTime()) < -1000) && (gate1 == false)) { //time value needs to be tested
  	    	    startTime = System.currentTimeMillis(); //the time this command was run
              gate1 = true; //make it so this command can't run again
		          //PID TARGET = TESTED AMOUNT (amount that gets our front on without killing our piston)
	        }
	    
      
      
      
      
              //The following will extend the back piston and retract the front piston if all of the following are true:
              //1. 'X' milliseconds have passed since the last action, 
              //2. It hasn't been true yet,
              //3. The last action was performed.
             
          if ((((startTime - getTime()) < -1000) && (gate2 == false) && (gate1 == true)) { //time value needs to be tested
              startTime = System.currentTimeMillis(); //the time this command was run
	    	      gate2 = true; //make it so this command can't run again
	          	sarlaccSub.liftFront(); //retract front
		          sarlaccSub.liftBack(); //extend back
	        }
	        
          
          
          
          
              //The following will move the robot forward if all 3 of the following are true:
              //1. 'X' milliseconds have passed since the last action, 
              //2. It hasn't been true before,
              //3. The last command was run.
              
          if ((((startTime - getTime()) < -1000) && (gate3 == false) && (gate2 == true)) { //time value needs to be tested
              startTime = System.currentTimeMillis(); //the time this command was run
              gate3 = true; //make it so this command can't run again
		          //PID TARGET = TESTED AMOUNT (amount that gets our back on without killing our piston
	        }
	    
      
      
      
      
              //The following 'if' will retract the back piston if all of the following are true:
              //1. 'X' milliseconds have passed since the last action, 
              //2. It hasn't been true before,
              //3. The last action was performed.
          
          if ((((startTime - getTime()) < -1000) && (gate4 == false) && (gate3 == true)) { //time value needs to be tested
              startTime = System.currentTimeMillis(); //the time this command was run
              gate4 = true; //make it so this command can't run again
		          sarlaccSub.liftBack(); //retract back
          }
	    
      
      
      
      
              //The following 'if' will set 'climbing' to false if the following are both true:
              //1. 'X' milliseconds have passed since the last action, and,
              //2. The last action was performed.
              
          if ((((startTime - getTime()) < -1000) && (gate4 = true;)) { //time value needs to be tested
              climbing = false;
	        }
	  
    }

    @Override
    protected boolean isFinished() {
        return false;
    }




    public long getTime() {
        return System.currentTimeMillis();
    }


}
