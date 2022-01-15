package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class Turret extends SubsystemBase {

    private WPI_VictorSPX turret = new WPI_VictorSPX(11); // config id later

    private WPI_VictorSPX feederOne = new WPI_VictorSPX(4);
    private WPI_VictorSPX feederTwo = new WPI_VictorSPX(5);
    private double speed;

    public Turret() {

        turret.configFactoryDefault();
        feederOne.configFactoryDefault();
        feederTwo.configFactoryDefault();
        // retrieves the encoders
        turret.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);

        // PID
        turret.config_kF(Constants.TURRET_PID_SLOT, Constants.F_TURRET);
        turret.config_kP(Constants.TURRET_PID_SLOT, Constants.P_TURRET);
        turret.config_kI(Constants.TURRET_PID_SLOT, Constants.I_TURRET);
        turret.config_kD(Constants.TURRET_PID_SLOT, Constants.D_TURRET);
        turret.config_IntegralZone(Constants.TURRET_PID_SLOT, Constants.IZONE_TURRET);

        // Soft Limit
        turret.configForwardSoftLimitEnable(true, Constants.TURRET_ENCODER_LIMIT);

    }

    // merge conflict
    /*
     * public void aim(double angle) { turret.set(ControlMode.Position,
     * encoderToAngle(angle)); }
     */
    public void spinFeeder(){
        feederOne.set(ControlMode.PercentOutput, 0.5);
        feederTwo.set(ControlMode.PercentOutput, 0.5);
    }

    public void stopFeeder(){
        feederOne.stopMotor();
        feederTwo.stopMotor();
    }

    public double getVel() {
        return turret.getSelectedSensorVelocity();
    }

    public void stop() {
        turret.stopMotor();
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run

    }

}
