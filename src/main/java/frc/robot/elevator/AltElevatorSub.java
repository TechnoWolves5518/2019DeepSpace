package frc.robot.elevator;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.OI;
import frc.robot.RobotMap;

public class AltElevatorSub extends Subsystem {

    private VictorSP altPIDElevator = new VictorSP(RobotMap.winch);

    private Encoder altElevatorEnc;

    private double kGearDiameter = 0;
    private double kGearDistancePerRev = kGearDiameter * Math.PI;
    private double kPulsesPerRev = 360;
    private double kDistancePerPulse = kGearDistancePerRev / kPulsesPerRev;

    public AltElevatorSub() {
        altElevatorEnc = new Encoder(RobotMap.ELEVATOR_ENC_A, RobotMap.ELEVATOR_ENC_B, true, EncodingType.k4X);
        altElevatorEnc.setDistancePerPulse(kDistancePerPulse);
        altElevatorEnc.setMaxPeriod(0.1);
    }

    // DERIV VARIABLES
    public long timeNow;
    public boolean InitialRun = true;
    public double errorLast;
    public double timeLast;
    public double errorNow;
    public double DerivitiveFinal;

    // DERIV CALCULATION
    public double calcDeriv(int targetPos) {
        if (InitialRun == false) {
            timeNow = System.currentTimeMillis();
            errorNow = (targetPos - getAltElevatorEnc());
            DerivitiveFinal = ((errorNow - errorLast) / (timeNow - timeLast));
            errorLast = errorNow;
            timeLast = timeNow;
        } else if (InitialRun == true) {
            timeLast = System.currentTimeMillis();
            timeNow = System.currentTimeMillis();
            errorLast = 0;
            errorNow = (targetPos - getAltElevatorEnc());
            InitialRun = false;
            DerivitiveFinal = 0;
        }
        return DerivitiveFinal;
    }

    // INT VARIABLES
    public double IntegralFinal;

    // INT CALCULATION
    public double calcInt(int targetPos) {
        return IntegralFinal += (targetPos - getAltElevatorEnc());
    }

    public double PIDMagic(int targetPos) { // This creates our PID function.
        DerivitiveFinal = calcDeriv(targetPos); // Generates current Derivitive Value
        IntegralFinal = calcInt(targetPos); // Generates current Integral Value

        // double P = 0.003; // see above
        // double I = 0.0005; // see above
        // double D = 0.0007; // see above

        double P = (OI.stick.getRawAxis(OI.leftJoyY) + 1.000001) * 0.009;
        double I = (OI.stick.getRawAxis(OI.leftJoyX) + 1.000001) * 0.009;
        double D = (-OI.stick.getRawAxis(OI.leftJoyZ) + 1.000001) * 0.009;

        double PIDSpeed;

        PIDSpeed = ((P * errorNow) + (I * IntegralFinal) + (D * DerivitiveFinal)); // Finds our modified speed value.

        altPIDElevator.set(PIDSpeed);
        System.out.println("  P: " + P + "  I: " + I + "  D: "+ D + "     Enc: " + getAltElevatorEnc());
        return PIDSpeed; // This gives us our 'Output' speed.
    }

    public int getAltElevatorEnc() {
        return altElevatorEnc.get();
    }

    public double getAltElevatorPos() {
        return altElevatorEnc.getDistance();
    }

    public void resetAltElevatorEnc() {
        altElevatorEnc.reset();
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new altPID());
    }

}
