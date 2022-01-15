// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import javax.swing.plaf.basic.BasicDirectoryModel;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ArcadeDrive;
import frc.robot.subsystems.DriveTrain;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.IntakeBall;
import frc.robot.commands.ReverseIntake;
import frc.robot.commands.RotateTurret;
import frc.robot.commands.Shoot;
import frc.robot.commands.SimpleAuto;
import frc.robot.commands.auto.Barrel;
import frc.robot.commands.auto.Bounce;
import frc.robot.commands.auto.Slalom;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Storage;
import frc.robot.subsystems.Turret;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
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
  private final Storage storage = new Storage();
  private final Intake intake = new Intake();

  private final Field2d field = new Field2d();

  // Xbox Controller
  XboxController controller = new XboxController(0);

  // Commands

  private final ArcadeDrive arcadeDrive = new ArcadeDrive(driveTrain, () -> controller.getRawAxis(1),
      () -> controller.getRawAxis(0), () -> controller.getRawButtonPressed(5),
      () -> controller.getRawButtonReleased(5));    

  //SequentialCommandGroup omegaShoot = new SequentialCommandGroup(new RotateTurret(), new Shoot(shooter, turret));

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
    SmartDashboard.putData("Field", field);
    SmartDashboard.putNumber("RightPosition", driveTrain.getRightMasterEncoderValue());
    SmartDashboard.putNumber("LeftPosition", driveTrain.getLeftMasterEncoderValue());
    SmartDashboard.putBoolean("Target", limelight.canSeeTarget());
    SmartDashboard.putBoolean("On Target", limelight.onTarget());
    SmartDashboard.putNumber("Y-Offset", limelight.getHorizontalAngle());

    SmartDashboard.putNumber("Shooter Output", shooter.getOutput());

    // System.out.println(controller.getPort());

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
    
    //RotateTurret Biniding
    /*JoystickButton xButton = new JoystickButton(controller, 3);
    xButton.whenHeld(new RotateTurret());*/

    JoystickButton bButton = new JoystickButton(controller, 2);
    bButton.whenHeld(new IntakeBall(intake,storage));

    JoystickButton rbButton = new JoystickButton(controller, 6);
    rbButton.whenHeld(new Shoot(shooter,turret));

    JoystickButton midLButton = new JoystickButton(controller, 7);
    midLButton.whenHeld(new ReverseIntake(intake));

    JoystickButton xButton = new JoystickButton(controller, 3);
    xButton.whenHeld(new RotateTurret(turret, limelight));

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous

    return new SimpleAuto(shooter, turret, driveTrain);
  }
}
