package org.firstinspires.ftc.teamcode.subsystems.util;

import com.qualcomm.robotcore.util.ElapsedTime;

public class PIDController {

    private double Kp;
    private double Ki;
    private double Kd;

    private ElapsedTime timer;
    private double integralSum = 0;
    private double lastError = 0;

    public PIDController(ElapsedTime timer){
        this.timer = timer;
        Kp = 0.35;
        Kd = 0.05;
        Ki = 0.00001;
    }

    public double PIDOutput(double setPoint, double feedback) {

        double error = setPoint - feedback;
        integralSum += error * timer.time();
        double derivative = (error - lastError) / timer.time();
        lastError = error;
        timer.reset();

        return (error * Kp) + (derivative * Kd) + (integralSum * Ki);
    }


}
