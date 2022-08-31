package org.firstinspires.ftc.teamcode.subsystems.sensors;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class DistanceSensorSubsystem {

    private DistanceSensor distanceSensor;
    private Rev2mDistanceSensor rev2mDistanceSensor;

    public enum SensorPosition{
        front,
        back,
        left,
        right
    }

    public DistanceSensorSubsystem(HardwareMap hardwareMap, SensorPosition sensorPosition){
        if(sensorPosition == SensorPosition.front){
            distanceSensor = hardwareMap.get(DistanceSensor.class, "Name of Distance Sensor in phone at front");
        }
        else if(sensorPosition == SensorPosition.back){
            distanceSensor = hardwareMap.get(DistanceSensor.class, "Name of Distance Sensor in phone at back");
        }
        else if(sensorPosition == SensorPosition.left){
            distanceSensor = hardwareMap.get(DistanceSensor.class, "Name of Distance Sensor in phone at left");
        }
        else if(sensorPosition == SensorPosition.right){
            distanceSensor = hardwareMap.get(DistanceSensor.class, "Name of Distance Sensor in phone at right");
        }

        rev2mDistanceSensor = (Rev2mDistanceSensor) distanceSensor;
    }

    public double getDistance(){
        return distanceSensor.getDistance(DistanceUnit.CM);
    }
    public boolean getTimeOut(){
        return rev2mDistanceSensor.didTimeoutOccur();
    }

}
