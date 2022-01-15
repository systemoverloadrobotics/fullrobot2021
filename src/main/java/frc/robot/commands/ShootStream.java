package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Storage;
import frc.robot.subsystems.Turret;

public class ShootStream extends CommandBase {

    private Shooter shooter;
    private Storage storage;
    private Turret turret;
    private Limelight lime;
    private DoubleSupplier speed;

    public ShootStream(Shooter shooter, Storage storage, Limelight lime, Turret turret, DoubleSupplier speed){
        this.turret = turret;
        this.shooter = shooter;
        this.storage = storage;
        this.lime = lime;
        this.speed = speed;
        addRequirements(shooter, storage, lime, turret);
    }    

    @Override
    public void initialize() {

    }

    @Override
    public void execute(){
        double velocity = shooter.calculateVelocity(lime.calculateDistance());
        
        shooter.set(velocity);
        if(shooter.atSetpoint()){
            storage.spinBoth();
        }
    }

    @Override
    public void end(boolean interrupted){
        storage.stopBoth();
        shooter.stop();
    }
    
}
