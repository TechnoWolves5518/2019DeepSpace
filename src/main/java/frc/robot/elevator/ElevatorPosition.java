// package frc.robot.elevator;

// import edu.wpi.first.wpilibj.DigitalInput;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import frc.robot.CommandBase;
// import frc.robot.OI;
// import frc.robot.Robot;
// import frc.robot.RobotMap;

// public class ElevatorPosition extends CommandBase {

//     private String[] locations = { "floor", "bottom", "middle", "top" };

//     int active = -1;
//     int setpoint = 0;
//     double adjust = 0;
//     int offset = 0;
//     public long startTime;

//     private DigitalInput limitSwitch = new DigitalInput(RobotMap.limitSwitch);
//     private boolean limit = false;
//     private boolean lastLimit = false;

//     public ElevatorPosition() {
//         requires(elevator);
//         elevator.setSetpoint(RobotMap.startingPosition);
//     }

//     @Override
//     protected void execute() {
//         xboxControls();
//         if (RobotMap.debugElevator)
//                 System.out.println("Elevator Position = " + active);
//                 System.out.println("Elevator Encoders = " + elevator.getElevatorEnc());
//     }

//     public void xboxControls() {
//         limit = !limitSwitch.get();
//         boolean reset = Robot.oi.sf.getRawButtonPressed(Robot.oi.XBOX_LSTICK);
//         // if (limit && !lastLimit) {
//         if (reset) {
//             elevator.resetElevatorEnc();
//             offset = 0;
//             elevator.setSetpoint(RobotMap.startingPosition);
//             active = 0;
//         }
//         lastLimit = limit;

//         adjust = Robot.oi.sf.getRawAxis(OI.XBOX_RTRIGGER) - OI.sf.getRawAxis(OI.XBOX_LTRIGGER);
//         offset += (int)(adjust * RobotMap.maxOffsetRateController);

//         if (Robot.oi.sf.getRawButtonPressed(OI.XBOX_RBUMPER)) {
//             startTime = System.currentTimeMillis();
//             active++; offset = 0;
            
//             if (RobotMap.debugElevator)
//                 System.out.println("Elevator Position = " + active);
//                 System.out.println("Elevator Encoders = " + elevator.getElevatorEnc());
                
//         } else if (Robot.oi.sf.getRawButtonPressed(OI.XBOX_LBUMPER)) {
//             startTime = System.currentTimeMillis();
//             active--; offset = 0;

//             if (RobotMap.debugElevator)
//                 System.out.println("Elevator Position = " + active);

//         } else if (Robot.oi.sf.getRawButtonPressed(OI.XBOX_RSTICK)) {
//             active = -2; offset = 0;
//             if (RobotMap.debugElevator)
//                 System.out.println("Elevator is resetting...");
//         }
        
//         //The following kills the motors under certain conditions.
//         // if ((elevator.getElevatorEnc() != setpoint) && ((startTime - elevator.getTime()) < -2500)) {
//         //     setpoint = elevator.getElevatorEnc();
//         // }
        
//         if (active == -2) {
//             // nothing
//         } else if (active < 0) {
//             active = 0;
//         } else if (active > 3) {
//             active = 3;
//         }

//         switch (active) {
//             case 0:
//                 setpoint = RobotMap.startingPosition;
//                 setDisplayLocation(locations[0]);
//                 break;
//             case 1:
//                 setpoint = RobotMap.bottomPosition;
//                 setDisplayLocation(locations[1]);
//                 break;
//             case 2:
//                 setpoint = RobotMap.middlePosition;
//                 setDisplayLocation(locations[2]);
//                 break;
//             case 3:
//                 setpoint = RobotMap.topPosition;
//                 setDisplayLocation(locations[3]);
//                 break;
//             case -2:
//                 setpoint = -10000;
//                 break;
//         }

//         setpoint += offset;

//         if (setpoint > RobotMap.topPosition)
//             setpoint = RobotMap.topPosition;

//         elevator.setSetpoint(setpoint);

//         if (RobotMap.debugElevator)
//             elevator.logPID();
//     }

//     public void xboxControlsRaw() {
        
//     }

//     public void setDisplayLocation(String loc) {
//         for (String location : locations) {
//             if (loc.equals(location)) {
//                 SmartDashboard.putBoolean(loc, true);
//             } else {
//                 SmartDashboard.putBoolean(loc, false);
//             }
//         }
//     }

//     @Override
//     protected boolean isFinished() {
//         return false;
//     }

// }