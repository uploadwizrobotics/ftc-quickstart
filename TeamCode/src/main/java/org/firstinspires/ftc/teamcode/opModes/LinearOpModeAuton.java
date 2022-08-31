package org.firstinspires.ftc.teamcode.opModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "Linear OpMode Auton", group = " ")
public class LinearOpModeAuton extends LinearOpMode {
    //declare subsystems here

    @Override
    public void runOpMode() throws InterruptedException{
        //code that runs when init is pressed
        //set your subsystems here

        waitForStart();
        //code that runs after start is pressed


        while(opModeIsActive()){
        //code that repeats when stop isn't pressed
            //insert commands for the subsystems
        }

    }
}
