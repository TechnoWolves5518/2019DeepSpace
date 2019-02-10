// package frc.robot.custompid;

// import frc.robot.CommandBase;
// import frc.robot.RobotMap;

// public class altPID extends CommandBase {

//     // UNIVERSAL VALUES
//     public int targetPos = 0;

//     public altPID() {
//         requires(altElevator);
//         targetPos = RobotMap.startingPosition;
//     }

//     @Override
//     protected void execute() {
//         // Where is our target?
//         if (OI.right1Down.get()) {
//             targetPos = RobotMap.bottomPosition;
//         } else if (OI.right2Down.get()) {
//             targetPos = RobotMap.middlePosition;
//         } else if (OI.right3Down.get()) {
//             targetPos = RobotMap.topPosition;
//         }
//         // sets target value when certain buttons are pressed.

//         altElevator.PIDMagic(targetPos);
//     }

//     @Override
//     protected boolean isFinished() {
//         return false;
//     }
// }
