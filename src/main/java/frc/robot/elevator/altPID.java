package frc.robot.elevator;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.OI;
import frc.robot.RobotMap;
import frc.robot.elevator.ElevatorPosition;

public class altPID extends Subsystem {

    private VictorSP altPIDElevator = new VictorSP(RobotMap.winch);
    
    //UNIVERSAL VALUES
        public int targetPos = 0; {

    //Where is our target?
    if (OI.right1Down.get()) {
        targetPos = RobotMap.bottomPosition;
    }
    else if (OI.right2Down.get()) {
        targetPos = RobotMap.middlePosition;
    } 
    else if (OI.right3Down.get()) {
        targetPos = RobotMap.topPosition;}
    }
    //sets target value when certain buttons are pressed.
    

    //Where am I?
    public Encoder altElevatorEnc = new Encoder(RobotMap.ELEVATOR_ENC_A, RobotMap.ELEVATOR_ENC_B, true, EncodingType.k4X);

    public int whereAmI() {
        return altElevatorEnc.get();
    }
    //Defines our encoder and then defines a function that finds our position.


    //DERIV VARIABLES
    public long timeNow;
    public boolean InitialRun = true;
    public double errorLast;
    public double timeLast;
    public double errorNow;
    public double DerivitiveFinal;


    //DERIV CALCULATION
    public double calcDeriv() {
        if (InitialRun == false) {
            timeNow = System.currentTimeMillis();
            errorNow = (targetPos - whereAmI());
            DerivitiveFinal = ((errorNow - errorLast) / (timeNow - timeLast));
            errorLast = errorNow;
            timeLast = timeNow;
        if (InitialRun == true);
            timeLast = System.currentTimeMillis();
            timeNow = 0;
            errorLast = 0;
            errorNow = 0;
            InitialRun = false;
            DerivitiveFinal = 0;
        }
        return DerivitiveFinal;
    }

    //INT VARIABLES
    public double IntegralFinal;

    //INT CALCULATION
    public double calcInt() {
        return IntegralFinal += (targetPos - whereAmI());
    }

    public double PIDMagic(double targetPos) { //This creates our PID function.
        DerivitiveFinal = calcDeriv(); //Generates current Derivitive Value
        IntegralFinal = calcInt(); //Generates current Integral Value

        double P = 0.005; // see above
        double I = 0.003; // see above
        double D = 0.005; // see above

        double PIDSpeed;

        PIDSpeed = ((P*errorNow) + (I * IntegralFinal) + (D * DerivitiveFinal)); //Finds our modified speed value.
        return PIDSpeed; //This gives us our 'Output' speed.

    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new ElevatorPosition());
    }
}
