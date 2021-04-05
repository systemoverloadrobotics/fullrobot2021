package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveTrain;

public class Bounce extends Auto {

    public Bounce(DriveTrain driveTrain, DifferentialDriveKinematics kinematics) {
        super(driveTrain, kinematics);
//        new SequentialCommandGroup(generate("A6"), generate("A9"), generate("A3"), generate("Finish"));
    }

}
