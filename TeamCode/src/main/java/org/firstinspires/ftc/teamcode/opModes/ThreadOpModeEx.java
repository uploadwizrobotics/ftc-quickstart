package org.firstinspires.ftc.teamcode.opModes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.threadopmode.TaskThread;
import org.firstinspires.ftc.teamcode.threadopmode.ThreadOpMode;

@TeleOp(name = "Threaded Op Mode", group = "")
public class ThreadOpModeEx extends ThreadOpMode {
    //declare subsystems here


    @Override
    public void mainInit(){
        //create subsystems here

        //register different subsystems to perform tasks indepedently
        registerThread(new TaskThread(new TaskThread.Actions() {
            @Override
            public void loop() {
                //insert actions you want repeated
            }
        }));


    }

    @Override
    public void mainLoop(){
        //insert code that repeats

    }
}
