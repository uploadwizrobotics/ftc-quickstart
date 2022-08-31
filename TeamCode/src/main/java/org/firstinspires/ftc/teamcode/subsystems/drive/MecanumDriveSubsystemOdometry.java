package org.firstinspires.ftc.teamcode.subsystems.drive;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class MecanumDriveSubsystemOdometry {
    private DcMotorEx leftBack;
    private DcMotorEx rightBack;
    private DcMotorEx leftForward;
    private DcMotorEx rightForward;
    private DcMotor leftEncoder;
    private DcMotor rightEncoder;
    private DcMotor backEncoder;

    public double x = 0;
    public double y = 0;
    public double Theta = 0;
    public double headingChange = 0;
    public double dx = 0;
    public double dy = 0;
    public double dTheta = 0;
    private int lEncoderi = 0;
    private int rEncoderi = 0;
    private int bEncoderi = 0;
    private int lEncoderf = 0;
    private int rEncoderf = 0;
    private int bEncoderf = 0;

    //Constants in the calculation of dx, dy, and dTheta
    public final double odometryCir = 3.5*Math.PI;
    public final double odometryTick = 8192;
    public final double SideOdometryToCentre = 10.4785495;
    public final double lengthFromOdometrySideToFront = 16.25;
    public final double sideOdometryAngleFromCentre = Math.atan2(2.4, 10.2);

    private final double dxc = odometryCir/(odometryTick*2);
    private final double dThetac = -odometryCir/(odometryTick*2*SideOdometryToCentre*Math.cos(sideOdometryAngleFromCentre));
    private final double dyc = odometryCir/odometryTick;
    private final double twoPi = 2*Math.PI;
    public double x1 = 0;
    public double y1 = 0;
    public double x2 = 0;
    public double y2 = 0;

    public void OdometryProcess(){

        lEncoderf = -leftEncoder();
        rEncoderf = rightEncoder();
        bEncoderf = backEncoder();
        dx = dxc*((lEncoderf-lEncoderi)+(rEncoderf-rEncoderi));
        dTheta = dThetac*((rEncoderf-rEncoderi)-(lEncoderf-lEncoderi));
        dy = (dyc*(bEncoderf-bEncoderi))+(lengthFromOdometrySideToFront*dTheta);

        x = x+(dx*Math.cos(Theta))+(dy*Math.sin(Theta));
        y = y+(dy*Math.cos(Theta))-(dx*Math.sin(Theta));

        Theta += dTheta/**1.03746397695*/;
        if (Theta > twoPi){
            Theta -= twoPi;
        } else if (Theta < 0){
            Theta += twoPi;
        }
        rEncoderi = rEncoderf;
        lEncoderi = lEncoderf;
        bEncoderi = bEncoderf;
    }


    public MecanumDriveSubsystemOdometry(HardwareMap hardwareMap, boolean test) {

        leftBack = hardwareMap.get(DcMotorEx.class, "leftBack");
        rightBack = hardwareMap.get(DcMotorEx.class, "rightBack");
        leftForward = hardwareMap.get(DcMotorEx.class, "leftForward");
        rightForward = hardwareMap.get(DcMotorEx.class, "rightForward");
        if (!test){
            leftEncoder = hardwareMap.get(DcMotor.class, "Left Encoder Name on Phone");
            rightEncoder = hardwareMap.get(DcMotor.class, "Right Encoder Name on Phone");
            backEncoder = hardwareMap.get(DcMotor.class, "Back Encoder Name on Phone");
            leftEncoder.setDirection(DcMotorSimple.Direction.FORWARD);
            rightEncoder.setDirection(DcMotorSimple.Direction.REVERSE);
            backEncoder.setDirection(DcMotorSimple.Direction.REVERSE);
            leftEncoder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightEncoder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            backEncoder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }
        leftBack.setDirection(DcMotor.Direction.FORWARD);
        leftForward.setDirection(DcMotor.Direction.FORWARD);
        rightBack.setDirection(DcMotor.Direction.REVERSE);
        rightForward.setDirection(DcMotor.Direction.REVERSE);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftForward.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightForward.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftForward.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightForward.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBack.setPower(0);
        leftForward.setPower(0);
        rightBack.setPower(0);
        rightForward.setPower(0);
    }

    public void move(boolean run, double vertical, double horizontal, double rotational){
        if (run){
            y1 = -vertical * Math.cos(Math.toRadians(45)) - horizontal * Math.sin(Math.toRadians(45))
                    + rotational * Math.sin(Math.toRadians(45));
            x1 = horizontal * Math.cos(Math.toRadians(45)) - vertical * Math.sin(Math.toRadians(45))
                    + rotational * Math.sin(Math.toRadians(45));
            y2 = -vertical * Math.cos(Math.toRadians(45)) - horizontal * Math.sin(Math.toRadians(45))
                    - rotational * Math.sin(Math.toRadians(45));
            x2 = horizontal * Math.cos(Math.toRadians(45)) - vertical * Math.sin(Math.toRadians(45))
                    - rotational * Math.sin(Math.toRadians(45));

            rightForward.setPower(-x1);
            leftBack.setPower(-x2);
            rightBack.setPower(-y1);
            leftForward.setPower(-y2);
        }
    }

    public void Reset(){
        rightForward.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftForward.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftEncoder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightEncoder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backEncoder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightForward.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftForward.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftEncoder.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightEncoder.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backEncoder.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void Stop(boolean run){
        if(run) {
            rightForward.setPower(0);
            leftBack.setPower(0);
            rightBack.setPower(-0);
            leftForward.setPower(-0);
        }
    }

    public int backEncoder(){ return backEncoder.getCurrentPosition(); }

    public int leftEncoder(){
        return leftEncoder.getCurrentPosition();
    }

    public int rightEncoder(){
        return rightEncoder.getCurrentPosition();
    }


}
