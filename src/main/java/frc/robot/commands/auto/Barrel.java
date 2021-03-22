package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveTrain;

public class Barrel extends Auto {

    public Barrel(DriveTrain driveTrain, DifferentialDriveKinematics kinematics) {
        super(driveTrain, kinematics);
        new SequentialCommandGroup(generate("D5"), generate("B8"), generate("D10"));

    }

}
