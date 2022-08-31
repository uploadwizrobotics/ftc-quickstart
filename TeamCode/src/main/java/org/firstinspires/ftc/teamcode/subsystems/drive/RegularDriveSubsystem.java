package org.firstinspires.ftc.teamcode.subsystems.drive;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class RegularDriveSubsystem {

    private DcMotor frontLeftMotor;
    private DcMotor frontRightMotor;
    private DcMotor backLeftMotor;
    private DcMotor backRightMotor;

    private double power = 1;

    public RegularDriveSubsystem(HardwareMap hardwareMap){
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


    public void move(boolean run, double left, double right){
        if (run){
            frontRightMotor.setPower(right);
            backRightMotor.setPower(right);
            frontLeftMotor.setPower(left);
            backLeftMotor.setPower(left);
        }
    }

    public void MoveVertical(boolean run, int verticalDistance){

        if(run) {
            int tickTarget = verticalDistance;

            backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            backLeftMotor.setTargetPosition(tickTarget);
            backRightMotor.setTargetPosition(tickTarget);
            frontLeftMotor.setTargetPosition(tickTarget);
            frontRightMotor.setTargetPosition(tickTarget);

            backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            backLeftMotor.setPower(power);
            backRightMotor.setPower(power);
            frontLeftMotor.setPower(power);
            frontRightMotor.setPower(power);

            while(backLeftMotor.isBusy() && backRightMotor.isBusy() &&frontLeftMotor.isBusy()&&frontRightMotor.isBusy());
        }

    }

    public void MoveVerticalTime(boolean run,long ms){

        if(run) {
            backLeftMotor.setPower(power);
            backRightMotor.setPower(power);
            frontLeftMotor.setPower(power);
            frontRightMotor.setPower(power);

            sleep(ms);

            Stop(run);
        }

    }

    public void sleep(long milliseconds){
        try{
            Thread.sleep(milliseconds);
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
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
