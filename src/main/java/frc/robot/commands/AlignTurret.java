package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Turret;

public class AlignTurret extends CommandBase {
    

    private Turret turret;
    DoubleSupplier right;
    DoubleSupplier left;

    public AlignTurret (DoubleSupplier right, DoubleSupplier left, Turret turret){
        this.turret = turret;
        this.right = right;
        this.left = left;
        addRequirements(turret);
    }

    @Override
    public void initialize(){
        
    }

    @Override
    public void execute() {
        
    }

    @Override
    public void end(boolean interrupted) {
        turret.stop();
       
    }

}
