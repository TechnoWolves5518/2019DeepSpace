// package frc.robot.auto;

// import frc.robot.OI;
// import frc.robot.RobotMap;
// import edu.wpi.first.wpilibj.command.TimedCommand;
// import frc.robot.CommandBase;

// public class HabClimb extends CommandBase {

//     public boolean Active;
//     public boolean Kill;
//     public int counter = 0;

    
//     public HabClimb() {
//         // requires(driveTrain);
//         requires(sarlaccSub);
//     }

//     @Override
//     protected void execute() {
//         System.out.println("RUNNING HAB CLIMB");
//         if (OI.driver.getRawButtonPressed(OI.XBOX_XBTN)) {
//             if (counter == 0) {
//                 // RobotMap.maxSpeed = RobotMap.limitedSpeed;
//                 // RobotMap.maxTurn = RobotMap.limitedTurn;
//                 sarlaccSub.frontActive = true;
//                 counter++;        
//             }
//             if (counter == 1) {
//                 // RobotMap.maxSpeed = RobotMap.limitedSpeed;
//                 // RobotMap.maxTurn = RobotMap.limitedTurn;
//                 sarlaccSub.frontActive = false;
//                 sarlaccSub.backActive = true;
//                 counter++;
//             }
//             if (counter == 2) {
//                 sarlaccSub.backActive = false;
//                 counter++;
//                 // RobotMap.maxSpeed = RobotMap.limitedSpeed;
//                 // RobotMap.maxTurn = RobotMap.limitedTurn;
//             }
//             if (counter == 3) {
//                 sarlaccSub.backActive = false;
//                 sarlaccSub.frontActive = false;
//                 // RobotMap.maxSpeed = RobotMap.defaultSpeed;
//                 // RobotMap.maxTurn = RobotMap.defaultTurn;
//                 counter = 0;
//             }
//         }
//     }

//     @Override
//     protected boolean isFinished() {
//         return false;
//     }

// }