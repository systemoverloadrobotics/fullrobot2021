package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveTrain;

public class Slalom extends Auto {

    public Slalom(DriveTrain driveTrain, DifferentialDriveKinematics kinematics) {
        super(driveTrain, kinematics);
        new SequentialCommandGroup(generate("TopHalf"), generate("BottomHalf"));

    }

}
