package org.firstinspires.ftc.teamcode.subsystems.sensors;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

public class TouchSensorSubsystem {

    private TouchSensor touchSensor;

    public enum SensorPosition{
        front,
        back,
        left,
        right
    }

    public TouchSensorSubsystem(HardwareMap hardwareMap, SensorPosition sensorPosition){
        if(sensorPosition == SensorPosition.front){
            touchSensor = hardwareMap.get(TouchSensor.class, "Front Touch Sensor Name in Phone");
        }
        else if (sensorPosition == SensorPosition.back){
            touchSensor = hardwareMap.get(TouchSensor.class, "Back Touch Sensor Name in Phone");
        }
        else if (sensorPosition == SensorPosition.left){
            touchSensor = hardwareMap.get(TouchSensor.class, "Left Touch Sensor Name in Phone");
        }
        else if (sensorPosition == SensorPosition.right){
            touchSensor = hardwareMap.get(TouchSensor.class, "Right Touch Sensor Name in Phone");
        }
    }

    public boolean IsPressed(){
        return touchSensor.isPressed();
    }
}
