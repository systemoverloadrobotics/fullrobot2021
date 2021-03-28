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

        // Soft Limit
        turret.configForwardSoftLimitEnable(true, CONSTANTS.TURRET_ENCODER_LIMIT);

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
