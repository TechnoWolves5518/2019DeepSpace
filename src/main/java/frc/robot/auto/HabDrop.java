// // package frc.robot.auto;

// // import frc.robot.CommandBase;

// // public class HabDrop extends CommandBase {

// //     public HabDrop() {
// //         requires(driveTrain);
// //     }

// //     @Override
// //     protected void initialize() {
// //         driveTrain.setPIDenabled(true);
// //         driveTrain.driveToSetpoint(2000);
// //     }

// //     @Override
// //     protected void execute() {
// //         driveTrain.driveToSetpoint(2000);
// //     }

// // 	@Override
// // 	protected boolean isFinished() {
// // 		return false;
// //     }
// // }

// package frc.robot.auto;

// import frc.robot.CommandBase;

// public class HabDrop extends CommandBase {

//     public int rightEnc;
//     public int leftEnc;
//     public double P = 0.05;
//     public int target = 1000;
//     public int leftError;
//     public int rightError;
//     public double speedLeft;
//     public double speedRight;

//     public HabDrop() {
//         requires(driveTrain);
//     }

//     @Override
//     protected void initialize() {
//         target = driveTrain.getLeftEncoder() + 1000;
//     }

//     @Override
//     protected void execute() {
//         rightEnc = driveTrain.getRightEncoder();
//         leftEnc = driveTrain.getLeftEncoder();
        
//         leftError = errorLeft();
//         rightError = errorRight();

//         speedLeft = autoP(leftError);
//         speedRight = autoP(rightError);

//         driveTrain.driveToSetpoint(speedLeft, speedRight);
//         System.out.println("wack");
//     }
//     public int errorLeft() {
//         return target - leftEnc;
//     }
//     public int errorRight() {
//         return target - rightEnc;
//     }

//     public double autoP(int i) {
//         return Math.min(.7, i * P);
//     }

//     public boolean onTarget(double i) {
//         if (i == target) {
//             return true;
//         } else {
//             return false;
//         }
//     }
        
//         @Override
//     protected boolean isFinished() {
//         return false;
//     }

// }