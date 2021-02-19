// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.io.IOException;
import java.nio.file.Path;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryUtil;
import frc.robot.commands.ArcadeDrive;
import frc.robot.subsystems.DriveTrain;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.*;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  //Controllers
  //private final Joystick movementJoystick = new Joystick(CONTROLS.JOYSTICK_PORT);
	//private final Joystick arcadeJoystick = new Joystick(CONTROLS.ARCADE_JOYSTICK_PORT);

  //Subsystems
  //private final DriveTrain driveTrain = new DriveTrain();
  private final DriveTrain m_robotDrive = new DriveTrain();


  //Commands
  /*private final ArcadeDrive arcadeDrive = new ArcadeDrive(driveTrain,
			() -> movementJoystick.getY(),
			() -> movementJoystick.getX(),
			() -> movementJoystick.getRawButtonPressed(CONTROLS.JOYSTICK.TRIGGER),
			() -> movementJoystick.getRawButtonReleased(CONTROLS.JOYSTICK.TRIGGER));*/

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings

    //driveTrain.setDefaultCommand(arcadeDrive);

    configureButtonBindings();
    m_robotDrive.resetEncoders();

  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    String trajectoryJSON = "paths/Barrel.wpilib.json";
    Trajectory trajectory = new Trajectory();
    try {
      Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON);
      trajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
    } catch (IOException ex) {
      DriverStation.reportError("Unable to open trajectory: " + trajectoryJSON, ex.getStackTrace());
    }

    /*RamseteCommand ramseteCommand = new RamseteCommand(
    trajectory, 
    m_robotDrive::getPose, 
    new RamseteController(SIMULATION.kRamseteB, SIMULATION.kRamseteZeta), 
    new SimpleMotorFeedforward(SIMULATION.ksVolts, SIMULATION.kvVoltSecondsPerMeter, SIMULATION.kaVoltSecondsSquaredPerMeter),
    SIMULATION.kDriveKinematics, 
    m_robotDrive::getWheelSpeeds, 
    new PIDController(SIMULATION.kPDriveVel, 0, 0, 0, null, null), 
    new PIDController(SIMULATION.kPDriveVel, 0, 0, 0, null, null), 
    m_robotDrive::tankDriveVolts, 
    m_robotDrive);*/

    RamseteController ramseteController = new RamseteController(SIMULATION.kRamseteB, SIMULATION.kRamseteZeta);
    SimpleMotorFeedforward feedforward = new SimpleMotorFeedforward(SIMULATION.ksVolts, SIMULATION.kvVoltSecondsPerMeter,SIMULATION.kaVoltSecondsSquaredPerMeter);

    RamseteCommand ramseteCommand = new RamseteCommand(
      trajectory,
      m_robotDrive::getPose,
      ramseteController,
      feedforward,
      SIMULATION.kDriveKinematics,
      m_robotDrive::getWheelSpeeds,
      new PIDController(SIMULATION.kPDriveVel, 0, 0),
      new PIDController(SIMULATION.kPDriveVel, 0, 0),
      m_robotDrive::tankDriveVolts,
      m_robotDrive
    );


    // Reset odometry to the starting pose of the trajectory.
    m_robotDrive.resetOdometry(trajectory.getInitialPose());

    // Run path following command, then stop at the end.
    return ramseteCommand.andThen(() -> m_robotDrive.tankDriveVolts(0, 0));
  }

}
