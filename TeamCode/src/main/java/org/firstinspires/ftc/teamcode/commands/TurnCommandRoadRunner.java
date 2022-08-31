package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.subsystems.drive.MecanumDriveSubsystemRoadRunner;


public class TurnCommandRoadRunner extends CommandBase {
    private final MecanumDriveSubsystemRoadRunner drive;
    private final double angle;

    public TurnCommandRoadRunner(MecanumDriveSubsystemRoadRunner drive, double angle) {
        this.drive = drive;
        this.angle = angle;

        addRequirements((Subsystem) drive);
    }

    @Override
    public void initialize() {
        drive.turn(Math.toRadians(angle));
    }

    @Override
    public void execute() {
        drive.update();
    }

    @Override
    public void end(boolean interrupted) {
        if (interrupted) {
            drive.stop();
        }
    }

    @Override
    public boolean isFinished() {
        return Thread.currentThread().isInterrupted() || !drive.isBusy();
    }


}
