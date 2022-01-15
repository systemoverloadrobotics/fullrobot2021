package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class ReverseIntake extends CommandBase {
    
    private Intake intake;

    public ReverseIntake (Intake intake){
        this.intake = intake;
        addRequirements(intake);
    }

    @Override
    public void initialize(){
        
    }

    @Override
    public void execute() {
        intake.spinOutBar();
    }

    @Override
    public void end(boolean interrupted) {
        intake.stop();
       
    }

}
