package frc.robot.vision;

import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.vision.VisionThread;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import frc.robot.CommandBase;

public class ImplementVision extends PIDSubsystem {

    public static UsbCamera camera;

    private static final int IMG_WIDTH = 480;
    private static final int IMG_HEIGHT = 320;

    private VisionThread visionThread;
    private double avgCenter = 0.0;
    private double lCenterX = 0.0;
    private double rCenterX = 0.0;

    private final Object imgLock = new Object();

    public ImplementVision() {
        super(0.005, 0.0, 0.0);
        setAbsoluteTolerance(5);
        getPIDController().setContinuous(false);
        getPIDController().setOutputRange(-0.3, 0.3);

        DigitalOutput led = new DigitalOutput(9);
        led.set(true);

        camera = CameraServer.getInstance().startAutomaticCapture();

        camera.setExposureManual(15);
        camera.setBrightness(50);
        camera.setResolution(IMG_WIDTH, IMG_HEIGHT);

        visionThread = new VisionThread(camera, new TapePipeline(), pipeline -> {
            if (pipeline.filterContoursOutput().size() == 2) {
                Rect r1 = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
                Rect r2 = Imgproc.boundingRect(pipeline.filterContoursOutput().get(1));
                synchronized (imgLock) {
                    lCenterX = r1.x + (r1.width / 2);
                    rCenterX = r2.x + (r2.width / 2);
                    avgCenter = (lCenterX + rCenterX) / 2;
                }
            } else {
                System.out.println("Found  " + pipeline.filterContoursOutput().size() + "  targets instead of 2");
                // TODO: remove this log
            }
        });

        visionThread.start();
        setSetpoint(0);
        disable();
    }

    @Override
    protected double returnPIDInput() {
        double avgCenter;
        synchronized (imgLock) {
            avgCenter = this.avgCenter;
        }
        double error = avgCenter - (IMG_WIDTH / 2);
        return error;
    }

    @Override
    protected void usePIDOutput(double output) {
        CommandBase.driveTrain.arcadeDrive(0, output);
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new VisionCommand());
    }
}