package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase{
    private WPI_VictorSPX intakeBar = new WPI_VictorSPX(0);

    public Intake(){

    }


    //One of these needs to be negative
    public void spinInBar(){
        intakeBar.set(ControlMode.PercentOutput, Constants.INTAKE_SPEED_IN);
    }

    public void spinOutBar(){
        intakeBar.set(ControlMode.PercentOutput, Constants.INTAKE_SPEED_OUT);
    }

    public void stop(){
        intakeBar.set(ControlMode.PercentOutput, 0);
    }
}
