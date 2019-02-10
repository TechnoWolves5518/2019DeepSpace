// package frc.robot.vbasedpid;

// import edu.wpi.first.wpilibj.command.Command;
// import frc.robot.CommandBase;
// import frc.robot.OI;
// import frc.robot.vbasedpid.*;

// public class VBasedPIDCom extends CommandBase {

//     public double targetV;

//     public VBasedPIDCom() {
//         requires(vPidSub);
//     }

//     @Override
//     protected void execute() {
//         if (OI.controllerToggle) {
//             targetV = OI.XBOX_LSTICK;
//             vPidSub.VPIDMagic(targetV);
//         } else {
//             targetV = OI.leftJoyZ;
//             vPidSub.VPIDMagic(targetV);
//         }
//     }

//     @Override
//     protected boolean isFinished() {
//         return false;
//     }

// }