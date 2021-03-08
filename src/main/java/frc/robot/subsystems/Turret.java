package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;


import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.CONSTANTS;
import frc.robot.RobotContainer;

public class Turret extends SubsystemBase {

    private WPI_TalonSRX turret = new WPI_TalonSRX(2); // config id later

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

    public void updateError(double error){
        if(found()){
            aim(turret.getSelectedSensorPosition() + error);
        }
        else if(onTarget()){
            stop();
        }
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

    public boolean found(){
        return RobotContainer.limelight.canSeeTarget() && RobotContainer.limelight.getTargetArea() > 0.8;
    }

    public boolean onTarget(){
        return found() && RobotContainer.limelight.getHorizontalAngle() < 1.5;
    }

    //found online
    public double encoderToAngle(double encoder){
        return map(encoder, 0, 2 * CONSTANTS.TURRET_ENCODER_LIMIT/360 , -CONSTANTS.TURRET_ENCODER_LIMIT, CONSTANTS.TURRET_ENCODER_LIMIT);
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
