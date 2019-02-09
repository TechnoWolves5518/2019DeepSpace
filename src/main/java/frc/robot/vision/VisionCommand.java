package frc.robot.vision;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.CommandBase;
import frc.robot.OI;

public class VisionCommand extends CommandBase {

    private XboxController driver = OI.driver;
    private Joystick stick = OI.stick;

    public VisionCommand() {
        requires(vision);
    }

    @Override
    protected void initialize() {
        vision.disable();
    }

    @Override
    protected void execute() {
        boolean on = false;

        if (OI.controllerToggle)
            on = driver.getRawButton(OI.XBOX_YBTN);
        else
            on = stick.getRawButton(OI.leftThumb);

        if (on) {
            vision.enable();
            vision.setSetpoint(0);
        } else {
            vision.disable();
        }
            
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}