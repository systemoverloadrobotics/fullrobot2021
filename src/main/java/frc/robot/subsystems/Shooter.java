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

    private WPI_TalonSRX shooter = new WPI_TalonSRX(7); // config id later
    private WPI_TalonSRX shooterOne = new WPI_TalonSRX(2);

    private boolean subsystemActive = false;

    public Shooter() {
        // configuration of the motors
        shooter.configFactoryDefault();
        shooterOne.configFactoryDefault();
        // Coast
        shooter.setNeutralMode(NeutralMode.Coast);
        shooter.setNeutralMode(NeutralMode.Coast);

        /* Config sensor used for Primary PID [Velocity] */
        shooter.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,
                Constants.TALON_PRIMARY_CLOSED_LOOP, Constants.TIMEOUT_MS);

        /* Config the peak and nominal outputs */
        shooter.configNominalOutputForward(0, Constants.TIMEOUT_MS);
        shooter.configNominalOutputReverse(0, Constants.TIMEOUT_MS);
        shooter.configPeakOutputReverse(-0.9, Constants.TIMEOUT_MS);

        shooter.configOpenloopRamp(0.4);
        //shooterOne.configOpenloopRamp(0.4);

        subsystemActive = true;
    }

    public double calculateVelocity(double distance) {
        double height = Constants.PORT_HEIGHT - Constants.HEIGHT_ABOVE_GROUND;
        double angleRad = Math.toRadians(Constants.SHOOTER_ANGLE);
        double velocity = distance / (Math.cos(angleRad)
                * Math.sqrt((2 * (height - (distance * Math.tan(angleRad))) / Constants.GRAVITY)));
        return velocity; // in m/s
    }

    public void spin(double percent) {
        motorSetPoint = percent;
        shooter.set(ControlMode.PercentOutput, motorSetPoint);
        shooterOne.set(ControlMode.PercentOutput, -motorSetPoint);

        //shooter.set(ControlMode.PercentOutput, motorSetPoint / Constants.UNITS_PER_REVOLUTION);
        SmartDashboard.putNumber("Shooter Motor RPM ", shooter.getSelectedSensorVelocity());
    }

    public double getOutput() {
        return shooter.getMotorOutputPercent();
    }

    public void set(double rpm) {
        if (isActive()) {
            spin(rpm);
        }
    }

    public boolean atSetpoint() {
        if (shooter.getMotorOutputPercent() >= motorSetPoint * (1 - Constants.SHOOTER_DEADBAND)
                && shooter.getMotorOutputPercent() <= motorSetPoint * (1 + Constants.SHOOTER_DEADBAND)) {
            return true;
        }
        return false;
    }

    public void stop() {
        shooter.stopMotor();
        shooterOne.stopMotor();
    }

    public boolean isActive() {
        return this.subsystemActive;
    }

    @Override
    public void periodic(){
        SmartDashboard.putNumber("Shooter Output", getOutput());
    }


}
