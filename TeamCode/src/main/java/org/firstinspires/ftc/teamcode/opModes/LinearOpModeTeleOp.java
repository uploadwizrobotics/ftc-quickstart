package org.firstinspires.ftc.teamcode.opModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Linear OpMode TeleOp", group = " ")
public class LinearOpModeTeleOp extends LinearOpMode {

    @Override
    public void runOpMode()throws InterruptedException{
        //code that runs when init is pressed

        waitForStart();
        //code that runs after start is pressed

        while(opModeIsActive()){
        //code that repeats when stop isn't pressed
        }
    }
}
