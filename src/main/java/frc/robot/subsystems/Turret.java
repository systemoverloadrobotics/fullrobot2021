package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.CONSTANTS;

public class Turret extends SubsystemBase{

    final int kUnitsPerRevolution = 2048; /* this is constant for Talon FX */

    private WPI_TalonSRX turret = new WPI_TalonSRX(2); // config id later

    double kP_turret = 0.1;
    double kI_turret = 0.0;
    double kD_turret = 0.0;
    double kF_turret = 0.0;
    double kIzone_turrent = 100;

    public Turret(){

        turret.configFactoryDefault();
        //retrieves the encoders   
        turret.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);

        //PID
        turret.config_kF(2, kF_turret);
        turret.config_kP(2, kP_turret);
        turret.config_kI(2, kI_turret);
        turret.config_kD(2, kD_turret);
        turret.config_IntegralZone(2, kIzone_turrent);      
        
    }

    /**
    * Moves the turret to an angle
    * @param position to move turret to
    */
    public void aimTurret(double position){
        if(position <= CONSTANTS.turretEncoderLeftSoftLimit && position >= CONSTANTS.turretEncoderRightSoftLimit){
            //figure something out here
        }
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

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }


    
}
