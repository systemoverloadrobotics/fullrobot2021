package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.CONSTANTS;
import frc.robot.subsystems.*;

public class AutoAim extends CommandBase {
    
    Turret m_turret;
    Limelight m_limelight;

    public AutoAim(Turret turret, Limelight limelight){
        m_turret = turret;
        m_limelight = limelight;
        addRequirements(turret);
        addRequirements(limelight);
    }

    @Override
    public void execute(){

        if(m_turret.found()){
            m_turret.aim(m_turret.getAngle() + m_limelight.getHorizontalAngle());
        }
        else if(m_turret.onTarget()){
            m_turret.stop();
        }
    }
}
