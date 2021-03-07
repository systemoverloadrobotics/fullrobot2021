// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.AutoAim;
import frc.robot.subsystems.DriveTrain;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ScheduleCommand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.CONSTANTS.*;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  //Controllers
  private final Joystick movementJoystick = new Joystick(CONTROLS.JOYSTICK_PORT);
	private final Joystick arcadeJoystick = new Joystick(CONTROLS.ARCADE_JOYSTICK_PORT);

  //Subsystems
  private final DriveTrain driveTrain = new DriveTrain();
  public static final Turret turret = new Turret();
  public static final Shooter shooter = new Shooter();
  public static final Limelight limelight = new Limelight();

  //Commands
  private final ArcadeDrive arcadeDrive = new ArcadeDrive(driveTrain,
			() -> movementJoystick.getY(),
			() -> movementJoystick.getX(),
			() -> movementJoystick.getRawButtonPressed(CONTROLS.JOYSTICK.TRIGGER),
			() -> movementJoystick.getRawButtonReleased(CONTROLS.JOYSTICK.TRIGGER));
  private final AutoAim autoAim = new AutoAim(turret, limelight);
  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    final JoystickButton autoAimButton = new JoystickButton(movementJoystick, CONSTANTS.CONTROLS.JOYSTICK.TRIGGER);
    driveTrain.setDefaultCommand(arcadeDrive);

    autoAimButton.whenHeld(autoAim);
    configureButtonBindings();

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
    return null;
  }
}
