package frc.robot.elevator;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import frc.robot.OI;
import frc.robot.RobotMap;
import frc.robot.drive.*;

public class ElevatorSubsystem extends PIDSubsystem {

    private VictorSP PID = new VictorSP(RobotMap.winch);

    private Encoder elevatorEnc;

    private double kGearDiameter = 0;
    private double kGearDistancePerRev = kGearDiameter * Math.PI;
    private double kPulsesPerRev = 360;
    private double kDistancePerPulse = kGearDistancePerRev / kPulsesPerRev;

    public ElevatorSubsystem() {
        super("Elevator", 0.005, 0.001, 0.005);
        setAbsoluteTolerance(0.5);
        getPIDController().setContinuous(false);
        getPIDController().setOutputRange(-0.5, 0.5);

        elevatorEnc = new Encoder(RobotMap.ELEVATOR_ENC_A, RobotMap.ELEVATOR_ENC_B, true, EncodingType.k4X);
        elevatorEnc.setDistancePerPulse(kDistancePerPulse);
        elevatorEnc.setMaxPeriod(0.1);

        setSetpoint(RobotMap.startingPosition);
        enable();

        //Alternate PID stuff
        //this big area that's boxed in green represents the alternate PID, which is an us-made PID that SHOULD
        //perform the same task as the above, EXCEPT WE MADE IT SO IT'S COOLER.
        int targetPos = RobotMap.startingPosition;{ //Sets intial target position to the starting position.
        if (OI.right1Down.get()) {
            targetPos = RobotMap.bottomPosition; //If you press DownBut1, targetPos is set to the BOTTOM position.
        }
        else if (OI.right2Down.get()) {
            targetPos = RobotMap.middlePosition; //If you press DownBut2, targetPos is set to the MIDDLE position.
        } 
        else if (OI.right3Down.get()) {
            targetPos = RobotMap.topPosition;} //If you press DownBut3, targetPos is set to the TOP position.
        }
        altPID altPID = new altPID();
        PID.set(altPID.PIDMagic(targetPos)); //Creates a new object of the altPID class, and makes it work.
    }


    @Override
    protected double returnPIDInput() {
        return getElevatorEnc();
    }

    @Override
    protected void usePIDOutput(double output) {
        PID.set(output);
        System.out.println("Value: " + getElevatorEnc());
    }

    public int getElevatorEnc() {
        return elevatorEnc.get();
    }

    public double getElevatorPos() {
        return elevatorEnc.getDistance();
    }

    public void resetElevatorEnc() {
        elevatorEnc.reset();
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new ElevatorPosition());
    }

}
