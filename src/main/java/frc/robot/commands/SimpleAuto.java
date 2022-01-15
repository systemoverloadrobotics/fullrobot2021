package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Storage;
import frc.robot.subsystems.Turret;

public class SimpleAuto extends CommandBase {
    
    private Shooter shooter;
    private DriveTrain drive;
    private Turret turret;
    private Storage storage;
    private double startTime;

    public SimpleAuto (Shooter s, Turret t, DriveTrain drive){
        shooter = s;
        turret = t;
        this.drive = drive;
        addRequirements(shooter, turret, drive);
    }

    @Override
    public void initialize(){
        startTime = Timer.getFPGATimestamp();
    }

    @Override
    public void execute() {

       double time = Timer.getFPGATimestamp();

       if(time - startTime < 2.0){
           drive.driveArcade(-0.8, 0); 
       }else{
           drive.driveArcade(0, 0);
       }
       

    }

    @Override
    public void end(boolean interrupted) {
        drive.driveArcade(0, 0);
    }


}
