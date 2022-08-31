package org.firstinspires.ftc.teamcode.opModes.exampleOpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.drive.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.motors.DCMotorSubsystem;

@TeleOp(name = "Example OpMode TeleOp")
public class RegularOpModeTeleOpExample extends OpMode {

    //Declare subsystems
    private MotorDriveSubsystem drive;
    private DCMotorSubsystem dcMotorSubsystem;
    private MecanumDriveSubsystem mecanumDriveSubsystem;

    private double sensitivity = 0.5;

    @Override
    public void init(){
        //code that happens once
        //set your subsystems here
        drive = new MotorDriveSubsystem();
        dcMotorSubsystem = new DCMotorSubsystem(hardwareMap);
        mecanumDriveSubsystem = new MecanumDriveSubsystem(hardwareMap);
    }

    @Override
    public void loop(){
        //code that repeats
        //insert commands for the subsystems here
        //these commands should be in a control if statement

        if(gamepad1.a){
            drive.moveForward();
        }
        if(gamepad1.left_bumper){
            dcMotorSubsystem.turnToPosition(true, 1500);
        }
        if(gamepad1.right_trigger != 0){

            dcMotorSubsystem.turnToPositionPower(true, 1500, gamepad1.right_trigger);
        }
        mecanumDriveSubsystem.move(true, gamepad1.left_stick_y*sensitivity, gamepad1.left_stick_x*sensitivity, gamepad1.right_stick_x*sensitivity);
    }
}
