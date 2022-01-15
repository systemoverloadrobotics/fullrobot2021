package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Storage;

public class IntakeBall extends CommandBase {
    
    private Intake intake = new Intake();
    private Storage st = new Storage();

    public IntakeBall(Intake intake, Storage st) {
        addRequirements(intake, st);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {}

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        intake.spinInBar();
        st.spinBoth();
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        intake.stop();
        st.stopBoth();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }

}
