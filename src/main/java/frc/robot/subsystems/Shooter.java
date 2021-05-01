package frc.robot.subsystems;

import java.util.logging.Logger;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {

    private Logger logger = Logger.getLogger(this.getClass().getName());
    private double motorSetPoint;
    // private int numberOfBallsFired;

    private WPI_TalonSRX shooter = new WPI_TalonSRX(1); // config id later

    private boolean subsystemActive = false;

    public Shooter() {
        // configuration of the motors
        shooter.configFactoryDefault();

        // Coast
        shooter.setNeutralMode(NeutralMode.Coast);

        /* Config sensor used for Primary PID [Velocity] */
        shooter.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,
                Constants.TALON_PRIMARY_CLOSED_LOOP, Constants.TIMEOUT_MS);

        /* Config the peak and nominal outputs */
        shooter.configNominalOutputForward(0, Constants.TIMEOUT_MS);
        shooter.configNominalOutputReverse(0, Constants.TIMEOUT_MS);
        shooter.configPeakOutputForward(1, Constants.TIMEOUT_MS);
        shooter.configPeakOutputReverse(-1, Constants.TIMEOUT_MS);

        shooter.configOpenloopRamp(0.4);

        subsystemActive = true;
    }

    public double calculateVelocity(double distance) {
        double height = Constants.PORT_HEIGHT - Constants.HEIGHT_ABOVE_GROUND;
        double angleRad = Math.toRadians(Constants.SHOOTER_ANGLE);
        double velocity = distance / (Math.cos(angleRad)
                * Math.sqrt((2 * (height - (distance * Math.tan(angleRad))) / Constants.GRAVITY)));
        return velocity; // in m/s
    }

    public void spin(double speed) {
        motorSetPoint = speed;
        shooter.set(ControlMode.Velocity, motorSetPoint);

        shooter.set(ControlMode.PercentOutput, motorSetPoint / Constants.UNITS_PER_REVOLUTION);

        logger.info("Shooter trying to spin at " + motorSetPoint);
        SmartDashboard.putNumber("Shooter Motor 1 RPM ", shooter.getSelectedSensorVelocity());
    }

    public double getVel() {
        return shooter.getSelectedSensorVelocity();
    }

    public void set(double rpm) {
        if (isActive()) {
            spin(rpm);
        }
    }

    public boolean shooterAtSetpoint() {
        if (shooter.getSelectedSensorVelocity() >= motorSetPoint * (1 - Constants.SHOOTER_DEADBAND)
                && shooter.getSelectedSensorVelocity() <= motorSetPoint * (1 + Constants.SHOOTER_DEADBAND)) {
            return true;
        }
        return false;
    }

    public void stop() {
        shooter.stopMotor();
    }

    public boolean isActive() {
        return this.subsystemActive;
    }

}
