package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class ShooterAutoTarget extends CommandBase{
    
    private double target = 0.0;
    private long update;

    public ShooterAutoTarget(double speed){
        target = speed;
    }

    

}
