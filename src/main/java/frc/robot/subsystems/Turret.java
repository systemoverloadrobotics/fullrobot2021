package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.controller.*;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.CONSTANTS;

public class Turret extends SubsystemBase {

    private WPI_TalonSRX turret = new WPI_TalonSRX(2); // config id later
    private PIDController pid = new PIDController(CONSTANTS.KP, CONSTANTS.KI, CONSTANTS.KD); //change these constants as needed;  

    public Turret() {

        turret.configFactoryDefault();
        // retrieves the encoders
        turret.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);

        // PID
        turret.config_kF(CONSTANTS.TURRET_PID_SLOT, CONSTANTS.F_TURRET);
        turret.config_kP(CONSTANTS.TURRET_PID_SLOT, CONSTANTS.P_TURRET);
        turret.config_kI(CONSTANTS.TURRET_PID_SLOT, CONSTANTS.I_TURRET);
        turret.config_kD(CONSTANTS.TURRET_PID_SLOT, CONSTANTS.D_TURRET);
        turret.config_IntegralZone(CONSTANTS.TURRET_PID_SLOT, CONSTANTS.IZONE_TURRET);
        
        //Soft Limit
        turret.configForwardSoftLimitEnable(true, CONSTANTS.TURRET_ENCODER_LIMIT);

    }

    public void set(double power){
        turret.set(ControlMode.PercentOutput, power);
    }

    public void aim(double angle){
        turret.set(ControlMode.Position, encoderToAngle(angle));
    }

    public double getVel() {
        return turret.getSelectedSensorVelocity();
    }


    public void stop() {
        turret.stopMotor();
    }   

    public void setPos(int position){
        turret.setSelectedSensorPosition(position);
    }

    public void home(){
        turret.setSelectedSensorPosition(0);
    }

    public double encoderToAngle(double encoder){
        return map(encoder, 0, 2*CONSTANTS.TURRET_ENCODER_LIMIT , -CONSTANTS.TURRET_ENCODER_LIMIT, CONSTANTS.TURRET_ENCODER_LIMIT);
    }

    //math I found online
    public static double map(double x, double inputMin, double inputMax, double outputMin, double outputMax) {
        return ((outputMax - outputMin) / (inputMax - inputMin)) * (x - inputMin) + outputMin;
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

}
