package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Turret extends SubsystemBase{

    final int kUnitsPerRevolution = 2048; /* this is constant for Talon FX */

    private WPI_TalonSRX turret = new WPI_TalonSRX(2); // config id later

    public Turret(){

        turret.configFactoryDefault();
        //retrieves the encoders   
        turret.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);

    }

    public double getTurretVel(){
        return turret.getSelectedSensorVelocity();
    }

    public void setTurret(int rpm){
        turret.set(ControlMode.PercentOutput, rpm);
    }

   
    public void stopTurret(){
        turret.stopMotor();
    }

    
}
