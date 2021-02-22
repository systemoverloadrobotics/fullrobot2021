package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.CONSTANTS;

public class Turret extends SubsystemBase {

    final int kUnitsPerRevolution = 2048; /* this is constant for Talon FX */

    private WPI_TalonSRX turret = new WPI_TalonSRX(2); // config id later

    double turretP = 0.1;
    double turretI = 0.0;
    double turretD = 0.0;
    double turretF = 0.0;
    double turretIzone = 100;

    public Turret() {

        turret.configFactoryDefault();
        // retrieves the encoders
        turret.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);

        // PID
        turret.config_kF(CONSTANTS.TURRET_PORT, turretF);
        turret.config_kP(CONSTANTS.TURRET_PORT, turretP);
        turret.config_kI(CONSTANTS.TURRET_PORT, turretI);
        turret.config_kD(CONSTANTS.TURRET_PORT, turretD);
        turret.config_IntegralZone(CONSTANTS.TURRET_PORT, turretIzone);

    }

    /**
     * Moves the turret to an angle
     * 
     * @param position to move turret to
     */
    public void aim(double position) {
        if (position <= CONSTANTS.turretEncoderLeftSoftLimit && position >= CONSTANTS.turretEncoderRightSoftLimit) {
            // figure something out here
        }
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
