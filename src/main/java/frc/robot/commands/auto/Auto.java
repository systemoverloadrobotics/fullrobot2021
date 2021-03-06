package frc.robot.commands.auto;

import java.io.IOException;
import java.nio.file.Path;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj2.command.*;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;

public class Auto extends SequentialCommandGroup {

    protected DriveTrain driveTrain;
    protected DifferentialDriveKinematics kinematics;

    public Auto(DriveTrain driveTrain, DifferentialDriveKinematics kinematics) {
        this.driveTrain = driveTrain;
        this.kinematics = kinematics;
    }

    public Command generate(String path) {
        String trajectoryJSON = "paths/" + path + ".wpilib.json";
        Trajectory trajectory = new Trajectory();
        try {
            Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON);
            trajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
        } catch (IOException ex) {
            DriverStation.reportError("Unable to open trajectory: " + trajectoryJSON, ex.getStackTrace());
        }

        RamseteCommand commandD5 = new RamseteCommand(
            trajectory, 
            driveTrain::getPose,
            new RamseteController(Constants.RAMSETE_B, Constants.RAMSETE_ZETA),
            new SimpleMotorFeedforward(Constants.S_DRIVE, Constants.V_DRIVE, Constants.A_DRIVE), 
            kinematics,
            driveTrain::getWheelSpeeds, 
            new PIDController(Constants.P_DRIVE, 0, 0), // left
            new PIDController(Constants.P_DRIVE, 0, 0), // right
            driveTrain::tankDriveVolts, 
            driveTrain);

        // Reset odometry to the starting pose of the trajectory.
        driveTrain.resetOdometry(trajectory.getInitialPose());
        
        return commandD5.andThen(() -> driveTrain.tankDriveVolts(0, 0));
    }

}
