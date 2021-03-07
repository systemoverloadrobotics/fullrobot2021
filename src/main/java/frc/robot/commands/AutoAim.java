package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;

public class AutoAim extends CommandBase {
    
    Turret m_turret;
    Limelight m_limLimelight;

    public AutoAim(Turret turret, Limelight limelight){
        m_turret = turret;
        m_limLimelight = limelight;

        addRequirements(turret);
        addRequirements(limelight);
    }

    @Override
    public void execute(){
        if(m_turret.found()){
            m_turret.aim(m_limLimelight.getHorizontalAngle());
            m_turret.set(0.8);
        }
        else if(m_turret.onTarget()){
            m_turret.stop();
        }
    }
}
