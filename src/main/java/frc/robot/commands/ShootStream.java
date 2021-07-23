package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Storage;

public class ShootStream extends CommandBase {

    private Shooter shooter;
    private Storage storage;
    private Limelight lime;

    public ShootStream(Shooter shooter, Storage storage, Limelight lime){
        this.shooter = shooter;
        this.storage = storage;
        this.lime = lime;
        addRequirements(shooter, storage, lime);
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
