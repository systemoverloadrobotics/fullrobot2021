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
        m_turret.updateError(m_limelight.getHorizontalAngle());
    }
}
