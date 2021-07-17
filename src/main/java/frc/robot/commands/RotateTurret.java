package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Turret;

public class RotateTurret extends CommandBase {

    private Turret turret;
    private Limelight lime;

    public RotateTurret() {
        addRequirements(turret, lime);
    }

    @Override
    public void execute() {
        lime.turnOn();
        if (lime.canSeeTarget() && lime.getHorizontalAngle() > 1.5) {
            turret.set(lime.getHorizontalAngle() > 0 ? 1 : -1);
        }
    }

    @Override
    public void end(boolean interrupted) {
        turret.stop();
    }

}
