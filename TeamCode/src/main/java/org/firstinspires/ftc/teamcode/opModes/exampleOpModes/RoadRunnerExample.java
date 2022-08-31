package org.firstinspires.ftc.teamcode.opModes.exampleOpModes;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.commands.TrajectoryFollowerCommandRoadRunner;
import org.firstinspires.ftc.teamcode.commands.TurnCommandRoadRunner;
import org.firstinspires.ftc.teamcode.roadRunnerClasses.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.drive.MecanumDriveSubsystemRoadRunner;


@Autonomous(name = "Road Runner Example", group = "Autonomous")
public class RoadRunnerExample extends CommandOpMode {

    private MecanumDriveSubsystemRoadRunner mecanumDriveSubsystem;

    @Override
    public void initialize(){
        mecanumDriveSubsystem = new MecanumDriveSubsystemRoadRunner(new SampleMecanumDrive(hardwareMap),false);

        SequentialCommandGroup movement = new SequentialCommandGroup();

        // --- Trajectories --//

        //Make a trajectory forward 50 inches
        Trajectory trajectory1 = mecanumDriveSubsystem.trajectoryBuilder(new Pose2d()).forward(50).build();
        //Make trajectory backward 50 inches
        Trajectory trajectory2 = mecanumDriveSubsystem.trajectoryBuilder(trajectory1.end()).back(50).build();
        // Make trajectory left 5 inches
        Trajectory trajectory3 = mecanumDriveSubsystem.trajectoryBuilder(trajectory2.end()).strafeLeft(5).build();
        //turn left to 180 degrees
        Trajectory trajectory4 = mecanumDriveSubsystem.trajectoryBuilder(trajectory3.end()
                .plus(new Pose2d(0,0,Math.toRadians(180)))).back(7).build();


        // --- Add Trajectories --//

        movement.addCommands(new TrajectoryFollowerCommandRoadRunner(mecanumDriveSubsystem,trajectory1));
        movement.addCommands(new TrajectoryFollowerCommandRoadRunner(mecanumDriveSubsystem,trajectory2));
        movement.addCommands(new TrajectoryFollowerCommandRoadRunner(mecanumDriveSubsystem,trajectory3));
        movement.addCommands(new TurnCommandRoadRunner(mecanumDriveSubsystem,180));
        movement.addCommands(new TrajectoryFollowerCommandRoadRunner(mecanumDriveSubsystem,trajectory4));

        //--- Schedule Movements --//

        movement.schedule();

    }
}
