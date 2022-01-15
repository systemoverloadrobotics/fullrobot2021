package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;

public class Shoot extends CommandBase {

    private Shooter shooter;
    private Turret turret;

    public Shoot (Shooter s, Turret t){
        shooter = s;
        turret = t;
    }

    @Override
    public void initialize(){
        
    }

    @Override
    public void execute() {
        shooter.spin(-0.8);
        turret.spinFeeder();
    }

    @Override
    public void end(boolean interrupted) {
        shooter.stop();
        turret.stopFeeder();
       
    }



    
}
