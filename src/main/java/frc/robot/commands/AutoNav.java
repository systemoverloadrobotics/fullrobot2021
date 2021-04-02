// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.DriveTrain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class AutoNav extends CommandBase {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private final DriveTrain driveTrain;
  private String currentPath;
  ArrayList<Double> x = new ArrayList<Double>();
  ArrayList<Double> y = new ArrayList<Double>();
  ArrayList<Double> tangent_x = new ArrayList<Double>();
  ArrayList<Double> tangent_y = new ArrayList<Double>();

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public AutoNav(DriveTrain dt, String path) {
    driveTrain = dt;
    currentPath = path;
    loadPath(path);
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(dt);
  }

  private void loadPath(String path) {
    try {
      Scanner fileReader = new Scanner(new File("./PathWeaver/Groups/" + path));
      while (fileReader.hasNextLine()){
        Scanner pathReader = new Scanner(new File("./PathWeaver/Paths/" + fileReader.nextLine()));
        pathReader.nextLine(); // First line has no useful info
        while (pathReader.hasNextLine()){
          String[] data = pathReader.nextLine().split(",");
          x.add(Double.parseDouble(data[0]));
          y.add(Double.parseDouble(data[1]));
          tangent_x.add(Double.parseDouble(data[2]));
          tangent_y.add(Double.parseDouble(data[3]));
        }
      }
      fileReader.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
