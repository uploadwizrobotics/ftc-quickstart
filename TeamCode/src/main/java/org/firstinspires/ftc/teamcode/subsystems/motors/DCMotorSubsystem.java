package org.firstinspires.ftc.teamcode.subsystems.motors;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DCMotorSubsystem {
    private DcMotor dcMotor;
    private double power = 1.0;

    public DCMotorSubsystem(HardwareMap hardwareMap){
        dcMotor = hardwareMap.get(DcMotor.class, "Name of DcMotor on Phone");
        dcMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        dcMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        dcMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void turnToPosition(boolean run, int ticks){
        if(run){
            dcMotor.setTargetPosition(ticks);
            dcMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            dcMotor.setPower(power);
        }
    }

    public void turnToPositionPower(boolean run, int ticks, double customPower){
        if(run){
            dcMotor.setTargetPosition(ticks);
            dcMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            dcMotor.setPower(customPower);
        }
    }

    public void Stop(boolean run){
        if(run){
            dcMotor.setPower(0);
        }
    }

    public boolean isBusy(){
        if(dcMotor.isBusy()){
            return true;
        }else{
            return false;
        }
    }

    public int getPosition(){
        return dcMotor.getCurrentPosition();
    }
}
