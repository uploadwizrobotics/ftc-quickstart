package org.firstinspires.ftc.teamcode.opModes.exampleOpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous (name = "Example Linear OpMode Autonmous", group = "")
public class LinearOpModeAutonExample extends LinearOpMode {

    // Declare subsystems
    private MotorDriveSubsystem drive;

    @Override
    public void runOpMode() throws InterruptedException{
        //set the subsystems
        drive = new MotorDriveSubsystem();

        waitForStart();
        //code that runs after start is pressed

        while(opModeIsActive()){
            //code that repeats when stop isn't pressed
            //insert commands for the subsystem

            drive.moveForward();
        }
    }
}
