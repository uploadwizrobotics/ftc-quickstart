package org.firstinspires.ftc.teamcode.commands;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.subsystems.drive.MecanumDriveSubsystemRoadRunner;

import java.util.function.DoubleSupplier;

public class MecanumDriveCommandRoadRunner extends CommandBase {
    private final MecanumDriveSubsystemRoadRunner drive;
    private final DoubleSupplier leftY, leftX, rightX;

    public MecanumDriveCommandRoadRunner(MecanumDriveSubsystemRoadRunner drive, DoubleSupplier leftY,
                               DoubleSupplier leftX, DoubleSupplier rightX) {
        this.drive = drive;
        this.leftX = leftX;
        this.leftY = leftY;
        this.rightX = rightX;

        addRequirements((Subsystem) drive);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void execute() {
        drive.drive(leftY.getAsDouble(), leftX.getAsDouble(), rightX.getAsDouble());
    }
}
