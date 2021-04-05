// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ArcadeDrive;
import frc.robot.subsystems.DriveTrain;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.auto.Barrel;
import frc.robot.commands.auto.Bounce;
import frc.robot.commands.auto.Slalom;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.*;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  // Controllers
  private final Joystick movementJoystick = new Joystick(CONTROLS.JOYSTICK_PORT);
  private final Joystick arcadeJoystick = new Joystick(CONTROLS.ARCADE_JOYSTICK_PORT);

  // Subsystems
  private final DriveTrain driveTrain = new DriveTrain();
  private final Turret turret = new Turret();
  private final Shooter shooter = new Shooter();
  private final Limelight limelight = new Limelight();

  // Commands
  private final ArcadeDrive arcadeDrive = new ArcadeDrive(driveTrain, () -> movementJoystick.getY(),
      () -> movementJoystick.getX(), 
      () -> movementJoystick.getRawButtonPressed(CONTROLS.JOYSTICK.TRIGGER),
      () -> movementJoystick.getRawButtonReleased(CONTROLS.JOYSTICK.TRIGGER), 
      () -> movementJoystick.getRawButtonPressed(CONTROLS.JOYSTICK.THUMB), 
      () -> movementJoystick.getRawButtonReleased(CONTROLS.JOYSTICK.THUMB));

  private SendableChooser<Command> autoChooser = new SendableChooser<Command>();

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings

    // Autonomous Chooser
    autoChooser.addOption("Bounce Paths", new Bounce(driveTrain, driveTrain.DRIVE_KINEMATICS));
    autoChooser.addOption("Barrel Racing Path", new Barrel(driveTrain, driveTrain.DRIVE_KINEMATICS));
    autoChooser.addOption("Slalom Path", new Slalom(driveTrain, driveTrain.DRIVE_KINEMATICS));
    SmartDashboard.putData("Auton Task", autoChooser);

    driveTrain.setDefaultCommand(arcadeDrive);

    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
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

    return autoChooser.getSelected();
  }
}
