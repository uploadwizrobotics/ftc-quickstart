package org.firstinspires.ftc.teamcode.subsystems.sensors;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ColourSensorSubsystem {

    private ColorSensor colourSensor;

    public enum SensorPosition{
        front,
        back,
        left,
        right
    }

    public ColourSensorSubsystem(HardwareMap hardwareMap, SensorPosition sensorPosition) {

        if(sensorPosition == SensorPosition.front){
            colourSensor = hardwareMap.get(ColorSensor.class, "Name of Colour Sensor in phone at front");
        }
        else if(sensorPosition == SensorPosition.back){
            colourSensor = hardwareMap.get(ColorSensor.class, "Name of Colour Sensor in phone at back");
        }
        else if(sensorPosition == SensorPosition.left){
            colourSensor = hardwareMap.get(ColorSensor.class, "Name of Colour Sensor in phone at left");
        }
        else if(sensorPosition == SensorPosition.right){
            colourSensor = hardwareMap.get(ColorSensor.class, "Name of Colour Sensor in phone at right");
        }
    }

    public int getRed() {
        return colourSensor.red();
    }

    public int getBlue() {
        return colourSensor.blue();
    }

    public int getGreen() {
        return colourSensor.green();
    }

    public int getHue() {
        return colourSensor.argb();
    }

    public int getAlpha() {
        return colourSensor.alpha();
    }
}
