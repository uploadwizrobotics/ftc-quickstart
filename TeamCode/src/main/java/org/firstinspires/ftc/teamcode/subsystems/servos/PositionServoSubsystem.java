package org.firstinspires.ftc.teamcode.subsystems.servos;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class PositionServoSubsystem {
    private Servo positionServo;

    // ROM is the range of motion of the servo in degrees,
    // goBilda servos generally go 300 degrees,
    // Other servos in the shop go 180 degrees
    // Set the appropriate ROM based on the servo being controlled
    int ROM = 300;


    public PositionServoSubsystem(HardwareMap hardwareMap){
        this.positionServo = hardwareMap.get(Servo.class, "ServoNameInPhone");
        positionServo.setDirection(Servo.Direction.FORWARD);
        positionServo.scaleRange(0,1);
    }

    public void ToPosition(boolean run, double position){
        if(run){
            positionServo.setPosition(position);
        }
    }

    public double getPosition(boolean run){
        return positionServo.getPosition();
    }

    public double getAngle(){
        return positionServo.getPosition()*ROM;
    }
}
