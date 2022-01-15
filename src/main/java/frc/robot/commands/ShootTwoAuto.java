package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Storage;
import frc.robot.subsystems.Turret;

public class ShootTwoAuto extends CommandBase{

    private Shooter shooter;
    private DriveTrain drive;
    private Storage storage;
    private Turret turret;
    private double startTime;

    public ShootTwoAuto (Shooter s, DriveTrain drive, Storage storage, Turret turret){
        shooter = s;
        this.drive = drive;
        this.turret = turret;
        this.storage = storage;
        addRequirements(turret, shooter, drive, storage);
    }

    @Override
    public void initialize(){
        startTime = Timer.getFPGATimestamp();
    }

    @Override
    public void execute() {

       double time = Timer.getFPGATimestamp();

       if(time - startTime < 8.0){
           shooter.spin(-0.8);
           turret.spinFeeder();
           storage.spinBoth();
       }else{
           shooter.stop();
           turret.stop();
           storage.stopBoth();
       }

       drive.driveArcade(-0.3, 0);
       

    }

    @Override
    public void end(boolean interrupted) {
        drive.driveArcade(0, 0);
    }
    
}
