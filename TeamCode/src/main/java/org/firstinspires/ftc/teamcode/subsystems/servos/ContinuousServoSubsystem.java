package org.firstinspires.ftc.teamcode.subsystems.servos;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ContinuousServoSubsystem {

    private CRServo continuousServo;
    private double power = 1;

    public ContinuousServoSubsystem(HardwareMap hardwareMap){
        this.continuousServo = hardwareMap.get(CRServo.class, "Servo Name on Phone");
        continuousServo.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public void ServoRotationForward(boolean run){
        if(run){
            continuousServo.setPower(power);
        }
    }

    public void ServoRotationBackward(boolean run){
        if (run){
            continuousServo.setPower(-power);
        }
    }

    public void Stop(boolean run){
        if(run){
            continuousServo.setPower(0);
        }
    }

    public void ServoRotationCustomSpeed(boolean run, double customPower){
        if(run){
            continuousServo.setPower(customPower);
        }
    }

    public void TimeBasedRotation(boolean run, long ms ){
        if(run){
            ServoRotationForward(true);
            sleep(ms);
            Stop(true);
        }
    }

    public void sleep(long milliseconds){
        try{
            Thread.sleep(milliseconds);
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
}
