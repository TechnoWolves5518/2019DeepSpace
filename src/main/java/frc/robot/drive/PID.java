package frc.robot.drive;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import frc.robot.RobotMap;

public class PID { // This creates the P.I.D. class.

    // Dear God Help Me with Derivitives
    public long timeNow;
    public boolean initialRun = true;
    public double errorLast = 0.0;
    public double timeLast = 0.0;
    public double errorNow = 0.0;


    // Proportionality Constants
    public double kProp = 0.0; // This is our basic proportionality constant.
    public double kInt = 0.0; //  This is our integral proportionality constant.
    public double kDeriv = 0.0; // This is our derivitive proportionality constant.

    // Encoder Data
    public Encoder leftEnc, rightEnc, eleEnc;
    public int targetLocation = 0; // This is our desired distance in pulses.

    public int distanceErrorLeft = 0;
    public int distanceErrorRight = 0;
    public int currentLocationAverage = 0;
    public int distanceErrorAverage = 0;

    public int currentLocationLeft = 0;
    public int currentLocationRight = 0;
    
    public PID(int targetLocation) {
        leftEnc = new Encoder(RobotMap.LEFT_ENC_A, RobotMap.LEFT_ENC_B, true, EncodingType.k4X);
        leftEnc = new Encoder(RobotMap.RIGHT_ENC_A, RobotMap.RIGHT_ENC_B, true, EncodingType.k4X);

        updateErrorValues();
    }

    public void updateErrorValues() {
        distanceErrorLeft = targetLocation - getLeftEncoder(); // This gives us our left-side 'error' in pulses.
        distanceErrorRight = targetLocation - getRightEncoder(); // This gives us our right-side 'error' in pulses.
        currentLocationAverage = (currentLocationLeft + currentLocationRight) / 2; // This is our average current location in pulses.
        distanceErrorAverage = (targetLocation - currentLocationAverage); // This is our average 'error' in pulses.
    }

    public int getLeftEncoder() {
        return leftEnc.get();
    }

    public int getRightEncoder() {
        return rightEnc.get();
    }

    // Speed Inputs and Outputs
    public double rawSpeed = 0.0; // This is the raw speed value.
    public double PIDSpeed = 0.0; // This is our PID output.


    // I and D Calculated Values
    public double runningTotalError = 0.0; // Sets our intial INT value to 0.
    public double rateOfChangeError = 0.0; // Derivative
    public double derivativeFinal = 0.0;
    public double integralFinal = 0.0;

    public double createInt() {
        return (runningTotalError += distanceErrorAverage); // Maintains a running total of the error; a sum of all error thus far.
    }
    
    public double createDeriv() { // Fancy derivitive function yay me
        if (!initialRun) { // If this isn't the first instance...
            errorNow = distanceErrorAverage;
            timeNow = System.currentTimeMillis(); // Gets the NOW time.
            rateOfChangeError = (errorNow - errorLast) / (timeNow - timeLast); // Change in error over change in time.
            timeLast = timeNow; // Sets up the just-used time for the next instance. Out with the old, in with the new.
            errorLast = errorNow; // Sets up the just-used error for the next instance. Out with the old, in with the new.
            initialRun = false;
        } else if (initialRun) { // If this is the first instance...
            errorLast = distanceErrorAverage; // Used for non-initial instances.
            timeLast = 0;  // Since no time has passed yet.
            initialRun = false; // Since after this, it is no longer the initial run.
            timeLast = System.currentTimeMillis(); // Gets the time at the moment this is run.
            rateOfChangeError = 0; // Sets the 'Initial D' hehe to zero.
        }
        return rateOfChangeError; // Spits it out
    }

    public double PIDMagic(double P, double I, double D) { // This creates our PID function.
        updateErrorValues();
        
        derivativeFinal = createDeriv(); // Generates current Derivitive Value
        integralFinal = createInt(); // Generates current Integral Value

        PIDSpeed = ((P*distanceErrorAverage) + (I * integralFinal) + (D * derivativeFinal)); // The Head Honcho himself. Lvl 100 boss.
        return PIDSpeed; // This gives us our 'Output' speed.

    }
}
// To use this PID system, use this command:
// PIDMagic(kPropVal, kIntVal, kDerivVal, TargetPulseVal, CurrentPulse Val, (Target-Current)Val);
// It should do the rest!

// We can also add ONE PARAMETER to the above function to allow for user-operated PID.

