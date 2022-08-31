package org.firstinspires.ftc.teamcode.subsystems.drive;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class MecanumDriveSubsystem {

    private DcMotor frontLeftMotor;
    private DcMotor frontRightMotor;
    private DcMotor backLeftMotor;
    private DcMotor backRightMotor;

    private double power = 1;

    double y1;
    double y2;
    double x1;
    double x2;

    public MecanumDriveSubsystem(HardwareMap hardwareMap){
        frontLeftMotor = hardwareMap.get(DcMotor.class, "Name on Phone of Front Left Motor");
        frontRightMotor = hardwareMap.get(DcMotor.class, "Name on Phone of Front Right Motor");
        backLeftMotor = hardwareMap.get(DcMotor.class, "Name on Phone of Back Left Motor");
        backRightMotor = hardwareMap.get(DcMotor.class, "Name on Phone of Back Right Motor");

        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        frontLeftMotor.setPower(0);
        backLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backRightMotor.setPower(0);

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

            frontRightMotor.setPower(-x1);
            backLeftMotor.setPower(-x2);
            backRightMotor.setPower(-y1);
            frontLeftMotor.setPower(-y2);
        }
    }

    public void Stop(boolean run){
        if (run){
            frontRightMotor.setPower(0);
            backRightMotor.setPower(0);
            frontLeftMotor.setPower(0);
            backLeftMotor.setPower(0);
        }
    }


}
