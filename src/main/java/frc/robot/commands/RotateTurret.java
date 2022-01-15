package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Turret;

public class RotateTurret extends CommandBase {

    private Turret turret;
    private Limelight lime;

    public RotateTurret(Turret turret, Limelight lime) {
        addRequirements(turret, lime);
    }

    @Override
    public void initialize(){
    }
    @Override
    public void execute() {
        /*
        if (lime.canSeeTarget() && lime.getHorizontalAngle() > 1.5) {
            turret.set(lime.getHorizontalAngle() > 0 ? 0.5 : -0.5);
        }
        if(lime.canSeeTarget()){
            double eps = lime.getHorizontalAngle();
            if(eps != 0 && !Util.eps(eps, 0, 1.5)){
                turret.set(lime.getHorizontalAngle() > 0 ? 0.3 : -0.3);
            }else{
                turret.stop();
            }
        }else{
            turret.stop();
        }*/
        
    }

    @Override
    public void end(boolean interrupted) {
        turret.stop();
    }

}
