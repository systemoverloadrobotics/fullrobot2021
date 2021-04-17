package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class Turret extends SubsystemBase {

    private WPI_TalonSRX turret = new WPI_TalonSRX(2); // config id later

    public Turret() {

        turret.configFactoryDefault();
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
