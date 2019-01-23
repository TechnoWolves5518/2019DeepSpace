package frc.robot.drive;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.*;

public class PID extends Subsystem { //This creates the P.I.D. class.

    //Dear God Help Me with Derivitives
    public long timeNow;
    public boolean InitialRun = true;
    public double errorLast = 0.0;
    public double timeLast = 0.0;
    public double errorNow = 0.0;


    //Proportionality Constants
    public double kProp = 0.0; //This is our basic proportionality constant.
    public double kInt = 0.0; // This is our integral proportionality constant.
    public double kDeriv = 0.0; //This is our derivitive proportionality constant.

    //Encoder Data
    public Encoder leftEnc, rightEnc, eleEnc;
    public int targetLocation = 0; //This is our desired distance in pulses.
    
    PID() {
        leftEnc = new Encoder(RobotMap.LEFT_ENC_A, RobotMap.LEFT_ENC_B, true, EncodingType.k4X);
        leftEnc = new Encoder(RobotMap.RIGHT_ENC_A, RobotMap.RIGHT_ENC_B, true, EncodingType.k4X);
    }
    public int currentLocationLeft = getLeftEncoder(); //This is our left-side's current location in pulses.
    public int currentLocationRight = getRightEncoder(); //This is our right-side's current location in pulses.
    public int currentLocationAverage = (currentLocationLeft + currentLocationRight) / 2; //This is our average current location in pulses.
    public int distanceErrorLeft = targetLocation - currentLocationLeft; //This gives us our left-side 'error' in pulses.
    public int distanceErrorRight = targetLocation - currentLocationRight; //This gives us our right-side 'error' in pulses.
    public int distanceErrorAverage = (targetLocation - currentLocationAverage); //This is our average 'error' in pulses.

    public int getLeftEncoder() {
        return leftEnc.get();
    }

    public int getRightEncoder() {
        return rightEnc.get();
    }

    //Speed Inputs and Outputs
    public double rawSpeed = 0.0; //This is the raw speed value.
    public double PIDSpeed = 0.0; //This is our PID output.


    //I and D Calculated Values
    public double runningTotalError = 0.0; //Sets our intial INT value to 0.
    public double RateOfChangeError = 0.0; //Derivitive
    public double DerivitiveFinal = 0.0;
    public double IntegralFinal = 0.0;

    //public double createInt2(double i) {
    //runningTotalError =+ distanceErrorAverage;
    //    return i;

    //}
    public double createInt() {
        return runningTotalError += distanceErrorAverage; //Maintains a running total of the error; a sum of all error thus far.
        }
    
    public double createDeriv() { //Fancy derivitive function yay me
        if (InitialRun = false) { //If this isn't the first instance...
            timeNow = System.currentTimeMillis(); //Gets the NOW time.
            RateOfChangeError = (errorNow - errorLast) / (timeNow - timeLast); //Change in error over change in time.
            timeLast = timeNow; //Sets up the just-used time for the next instance. Out with the old, in with the new.
            errorLast = errorNow; //Sets up the just-used error for the next instance. Out with the old, in with the new.
            InitialRun = false;
       }    
        if (InitialRun = true) { //If this is the first instance...
            errorLast = distanceErrorAverage; //Used for non-initial instances.
            timeLast = 0;  //Since no time has passed yet.
            InitialRun = false; //Since after this, it is no longer the initial run.
            timeLast = System.currentTimeMillis(); //Gets the time at the moment this is run.
            RateOfChangeError = 0; //Sets the 'Initial D' hehe to zero.
        }
        return RateOfChangeError; //Spits it out
    }

    public double PIDMagic(double P, double I, double D, int Target, int Now, int Error) { //This creates our PID function.
        DerivitiveFinal = createDeriv(); //Generates current Derivitive Value
        IntegralFinal = createInt(); //Generates current Integral Value

        P = kProp; //see above
        I = kInt; //see above
        D = kDeriv; //see above

        targetLocation = Target; //Duh
        Now = currentLocationAverage; //Duh
        Error = distanceErrorAverage; //Duh

        runningTotalError = runningTotalError; //Pulls our Integral Value; Total Error over time, times Time.
        RateOfChangeError = RateOfChangeError; //Pulls our Derivitive value; change in error over change in time. 
        //ALSO
        //IM
        //FLEXING
        //and wanted to make sure they initialized

        PIDSpeed = ((P*Error) + (I * IntegralFinal) + (D * DerivitiveFinal)); //The Head Honcho himself. Lvl 100 boss.
        return PIDSpeed; //This gives us our 'Output' speed.

    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new DriveCom());
    }
}
//To use this PID system, use this command:
//PIDMagic(kPropVal, kIntVal, kDerivVal, TargetPulseVal, CurrentPulse Val, (Target-Current)Val);
//It should do the rest!

//We can also add ONE PARAMETER to the above function to allow for user-operated PID.

