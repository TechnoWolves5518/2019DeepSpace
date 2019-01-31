package frc.robot.vbasedpid;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.OI;
import frc.robot.RobotMap;
import frc.robot.drive.DriveTrainSubsystem;

public class VBasedPIDSub extends Subsystem {

   private Encoder leftSideEnc;
   private Encoder rightSideEnc;
   private double kDistancePerPulse;

    public VBasedPIDSub() {
        leftSideEnc  = new Encoder(RobotMap.LEFT_ENC_A, RobotMap.LEFT_ENC_B);
        rightSideEnc = new Encoder(RobotMap.RIGHT_ENC_A, RobotMap.RIGHT_ENC_B);
        rightSideEnc.setDistancePerPulse(kDistancePerPulse);
        leftSideEnc.setDistancePerPulse(kDistancePerPulse);
        rightSideEnc.setMaxPeriod(0.1);
        leftSideEnc.setMaxPeriod(0.1);
    }

    public double getAverage() {
        return ((leftSideEnc.getRate() + rightSideEnc.getRate()) / 2);
    }
    // DERIV VARIABLES
    public long timeNow;
    public boolean InitialRun = true;
    public double errorLast;
    public double timeLast;
    public double errorNow;
    public double DerivitiveFinal;

    // DERIV CALCULATION
    public double calcDeriv(Double targetV) {
        if (InitialRun == false) {
            timeNow = System.currentTimeMillis();
            errorNow = (targetV - getAverage());
            DerivitiveFinal = ((errorNow - errorLast) / (timeNow - timeLast));
            errorLast = errorNow;
            timeLast = timeNow;
        } else if (InitialRun == true) {
            timeLast = System.currentTimeMillis();
            timeNow = System.currentTimeMillis();
            errorLast = 0;
            errorNow = (targetV - getAverage());
            InitialRun = false;
            DerivitiveFinal = 0;
        }
        return DerivitiveFinal;
    }

    // INT VARIABLES
    public double IntegralFinal;
    public long IntTimeNow;
    public long IntTimeLast;
    public boolean InitialRun2 = true;
    public long dTime;

    // INT CALCULATION
    public double calcInt(Double targetV) {
        if (InitialRun2 == false) {
            IntTimeNow = System.currentTimeMillis();
            dTime = (IntTimeNow - IntTimeLast);
            IntegralFinal = ((targetV - getAverage()) * dTime);
            IntTimeLast = IntTimeNow;
        }
        if (InitialRun2 == true) {
            IntTimeNow = System.currentTimeMillis();
            IntTimeLast = System.currentTimeMillis();
            dTime = (IntTimeNow - IntTimeLast);
            IntegralFinal = ((targetV - getAverage()) * dTime);
            InitialRun2 = false;
        }
        return IntegralFinal;
    }

    public double VPIDMagic(Double targetV) { // This creates our PID function.
        DerivitiveFinal = calcDeriv(targetV); // Generates current Derivitive Value
        IntegralFinal = calcInt(targetV); // Generates current Integral Value

        // double P = 0.003; // see above
        // double I = 0.0005; // see above
        // double D = 0.0007; // see above

        double P = (OI.stick.getRawAxis(OI.leftJoyY) + 1.000001) * 0.0045;
        double I = (OI.stick.getRawAxis(OI.leftJoyX) + 1.000001) * 0.0002;
        double D = (-OI.stick.getRawAxis(OI.leftJoySlider) + 1.000001) * 0.1;

        double OutV;

        OutV = ((P * errorNow) + (I * IntegralFinal) + (D * DerivitiveFinal)); // Finds our modified speed value.
        System.out.println("  P: " + P + "  I: " + I + "  D: "+ D + "     AvgVel: " + getAverage()  +  "  Deriv : " + DerivitiveFinal + "  Int  : " + IntegralFinal);
        return OutV; // This gives us our 'Output' speed.
    }

    public int getLeftSideEnc() {
        return leftSideEnc.get();
    }

    public double getRightSideEnc() {
        return rightSideEnc.getDistance();
    }

    public void resetAllEncs() {
        rightSideEnc.reset();
        leftSideEnc.reset();
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new VBasedPIDCom());
    }

}
