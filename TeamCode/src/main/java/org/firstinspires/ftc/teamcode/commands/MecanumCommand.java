package org.firstinspires.ftc.teamcode.commands;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystems.drive.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.drive.MecanumDriveSubsystemOdometry;

public class MecanumCommand {

    public MecanumDriveSubsystemOdometry mecanumDriveSubsystemOdometry;
    private LinearOpMode opMode;

    public MecanumCommand(MecanumDriveSubsystemOdometry mecanumSubsystem, LinearOpMode opMode) {
        this.mecanumDriveSubsystemOdometry = mecanumSubsystem;
        this.opMode = opMode;

    }

    public void ToCoord(boolean run, double pow, double xf, double yf){
        if (run){
            double dx = xf - mecanumDriveSubsystemOdometry.x;
            double dy = yf - mecanumDriveSubsystemOdometry.y;
            double theta = mecanumDriveSubsystemOdometry.Theta;
            while (theta > 2*Math.PI && opMode.opModeIsActive()){
                theta = theta - (2*Math.PI);
            }
            while (theta < 0 && opMode.opModeIsActive()){
                theta = theta + (2*Math.PI);
            }
            double angle = Math.atan2(dy, dx)- (2*Math.PI) + theta;
            while (angle < 0 && opMode.opModeIsActive()){
                angle = angle + (2*Math.PI);
            }
            double temp = Math.tan(angle);
//            Log.d("tempLogs", "Current ratio: " + Double.toString(temp));
            Log.d("Auto2Logs", "Angle from endpoint: " + angle);

            if (angle < Math.PI){
                if (angle < Math.PI/2){
                    if (angle <= Math.PI/4){
                        //Works
//                        mecanumSubsystem.move(true, pow, pow*temp, 0);
                        mecanumDriveSubsystemOdometry.move(true, pow, pow*temp, 0);
                    } else {
                        //Works
                        temp = 1/temp;
//                        mecanumSubsystem.move(true, pow*temp, pow, 0);
                        mecanumDriveSubsystemOdometry.move(true, pow*temp, pow, 0);
                    }
                } else {

                    if (angle <= 3*Math.PI/4){
                        //Works
                        temp = 1/temp;
//                        mecanumSubsystem.move(true, pow*temp, pow, 0);
                        mecanumDriveSubsystemOdometry.move(true, pow*temp, pow, 0);
                    } else {
                        //Works
//                        mecanumSubsystem.move(true, -pow, -pow*temp, 0);
                        mecanumDriveSubsystemOdometry.move(true, -pow, -pow*temp, 0);
                    }
                }
            } else {
                if (angle < 3*Math.PI/2){
                    //Works
                    if (angle <= 5*Math.PI/4){
//                        mecanumSubsystem.move(true, -pow, -pow*temp, 0);
                        mecanumDriveSubsystemOdometry.move(true, -pow, -pow*temp, 0);
                    } else {
                        temp = 1/temp;
//                        mecanumSubsystem.move(true, -pow*temp, -pow, 0);
                        mecanumDriveSubsystemOdometry.move(true, -pow*temp, -pow, 0);
                    }
                } else {
                    if (angle <= 7*Math.PI/4){
                        //Works
                        temp = 1/temp;
//                        mecanumSubsystem.move(true, -pow*temp, -pow, 0);
                        mecanumDriveSubsystemOdometry.move(true, -pow*temp, -pow, 0);
                    } else {
//                        mecanumSubsystem.move(true, pow, pow*temp, 0);
                        mecanumDriveSubsystemOdometry.move(true, pow, pow*temp, 0);

                    }
                }
            }
        }
    }
    public void ToShippingHub(double pow) {
        while ( Math.abs(mecanumDriveSubsystemOdometry.x - 160) > 5 && Math.abs(mecanumDriveSubsystemOdometry.y - 50) > 5 && opMode.opModeIsActive()){
            ToCoord(true, pow, 160, 50);
        }
        mecanumDriveSubsystemOdometry.Stop(true);
    }
}
