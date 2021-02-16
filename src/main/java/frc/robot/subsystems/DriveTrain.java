package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpiutil.math.VecBuilder;
import edu.wpi.first.wpilibj.SpeedController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANEncoder;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.revrobotics.EncoderType;

import frc.robot.Constants;
import frc.robot.Constants.*;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.simulation.ADXRS450_GyroSim;
import edu.wpi.first.wpilibj.simulation.AnalogGyroSim;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim;
import edu.wpi.first.wpilibj.simulation.EncoderSim;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.system.plant.DCMotor;
import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


import java.io.Console;
import java.util.function.BooleanSupplier;

public class DriveTrain extends SubsystemBase {
    
    //DriveBase is divided into 2 sections, a right and left section.
    //private CANSparkMax rightMaster = new CANSparkMax(DRIVE.RIGHT_MASTER_ID, MotorType.kBrushless);
    //private CANSparkMax rightFollower1 = new CANSparkMax(DRIVE.RIGHT_FOLLOWER_1_ID, MotorType.kBrushless);
    //private CANSparkMax rightFollower2 = new CANSparkMax(DRIVE.RIGHT_FOLLOWER_2_ID, MotorType.kBrushless);
    //private CANSparkMax leftMaster = new CANSparkMax(DRIVE.LEFT_MASTER_ID, MotorType.kBrushless);
    //private CANSparkMax leftFollower1 = new CANSparkMax(DRIVE.LEFT_FOLLOWER_1_ID, MotorType.kBrushless);
    //private CANSparkMax leftFollower2 = new CANSparkMax(DRIVE.LEFT_FOLLOWER_2_ID, MotorType.kBrushless);
    
    /*private CANEncoder leftMasterEncoder = new CANEncoder(leftMaster, EncoderType.kQuadrature, 4069);
    private CANEncoder rightMasterEncoder = new CANEncoder(rightMaster, EncoderType.kQuadrature, 4069);

    private DoubleSolenoid shifter = new DoubleSolenoid(Constants.PCM_ID, DRIVE.FORWARD_CHANNEL_ID, DRIVE.REVERSE_CHANNEL_ID);

    private SpeedControllerGroup rightGroup = new SpeedControllerGroup(rightMaster, rightFollower1, rightFollower2);
    private SpeedControllerGroup leftGroup = new SpeedControllerGroup(leftMaster, leftFollower1, leftFollower2);

    public DifferentialDrive robotDrive = new DifferentialDrive(leftGroup, rightGroup);*/




    /*public DriveTrain() {
        rightMaster.restoreFactoryDefaults();
        rightFollower1.restoreFactoryDefaults();
        rightFollower2.restoreFactoryDefaults();
        leftMaster.restoreFactoryDefaults();
        leftFollower1.restoreFactoryDefaults();
        leftFollower2.restoreFactoryDefaults();
    }*/

    /*public void driveArcade(double speed, double turn) {
        //incase we need arcade drive
        robotDrive.arcadeDrive(-speed * DRIVE.SPEED_MULTIPLIER, -turn * DRIVE.TURN_MULTIPLIER);
    }

    //Pushes the solenoid forward
    public void shiftUp() {
        shifter.set(Value.kForward);
    }
    //Pushes the solenoid backward
    public void shiftDown() {
        shifter.set(Value.kReverse);
    }

    //Gets the encoder value for the left encoder
    public double getLeftMasterEncoderValue() {
        return leftMasterEncoder.getPosition();
    }

    //Gets the econder value for the right encoder
    public double getRightMasterEncoderValue() {
        return rightMasterEncoder.getPosition();
    }*/

    // The motors on the left side of the drive.
    private final SpeedControllerGroup m_leftMotors =
    new SpeedControllerGroup(new PWMVictorSPX(Constants.kLeftMotor1Port),
                           new PWMVictorSPX(Constants.kLeftMotor2Port));

    // The motors on the right side of the drive.
    private final SpeedControllerGroup m_rightMotors =
    new SpeedControllerGroup(new PWMVictorSPX(Constants.kRightMotor1Port),
                           new PWMVictorSPX(Constants.kRightMotor2Port));

    // The robot's drive
    private final DifferentialDrive m_drive = new DifferentialDrive(m_leftMotors, m_rightMotors);

    // The left-side drive encoder
    private final Encoder m_leftEncoder =
        new Encoder(2, 3,
        true, Encoder.EncodingType.k4X);

    // The right-side drive encoder
    private final Encoder m_rightEncoder =
        new Encoder(0, 1,
              true, Encoder.EncodingType.k4X);

    private EncoderSim m_leftEncoderSim = new EncoderSim(m_leftEncoder);
    private EncoderSim m_rightEncoderSim = new EncoderSim(m_rightEncoder);

    // The gyro sensor
    private final ADXRS450_Gyro m_gyro = new ADXRS450_Gyro();

    private ADXRS450_GyroSim m_gyroSim = new ADXRS450_GyroSim(m_gyro);

    // Odometry class for tracking robot pose
    private final DifferentialDriveOdometry m_odometry;

    DifferentialDrivetrainSim m_driveSim = new DifferentialDrivetrainSim(
    DCMotor.getNEO(2),       // 2 NEO motors on each side of the drivetrain.
    7.29,                    // 7.29:1 gearing reduction.
    7.5,                     // MOI of 7.5 kg m^2 (from CAD model).
    60.0,                    // The mass of the robot is 60 kg.
    Units.inchesToMeters(3), // The robot uses 3" radius wheels.
    0.7112,                  // The track width is 0.7112 meters.

    VecBuilder.fill(0.001, 0.001, 0.001, 0.1, 0.1, 0.005, 0.005));

    private Field2d m_field = new Field2d();

/**
* Creates a new DriveSubsystem.
*/
    public DriveTrain() {
        // Sets the distance per pulse for the encoders
        m_leftEncoder.setDistancePerPulse(Constants.kEncoderDistancePerPulse);
        m_rightEncoder.setDistancePerPulse(Constants.kEncoderDistancePerPulse);

        resetEncoders();
        m_odometry = new DifferentialDriveOdometry(m_gyro.getRotation2d());
        SmartDashboard.putData("Field", m_field);
    }

    @Override
    public void periodic() {
        // Update the odometry in the periodic block
        m_odometry.update(m_gyro.getRotation2d(), m_leftEncoder.getDistance(),
                  m_rightEncoder.getDistance());
        m_field.setRobotPose(m_odometry.getPoseMeters());          
    }

    /**
    * Returns the currently-estimated pose of the robot.
    *
    * @return The pose.
    */
    public Pose2d getPose() {
        return m_odometry.getPoseMeters();
    }

    /**
    * Returns the current wheel speeds of the robot.
    *
        * @return The current wheel speeds.
*/
    public DifferentialDriveWheelSpeeds getWheelSpeeds() {
        return new DifferentialDriveWheelSpeeds(m_leftEncoder.getRate(), m_rightEncoder.getRate());
    }

    /**
    * Resets the odometry to the specified pose.
    *
    * @param pose The pose to which to set the odometry.
    */
    public void resetOdometry(Pose2d pose) {
        resetEncoders();
        m_odometry.resetPosition(pose, m_gyro.getRotation2d());
    }

    /**
    * Drives the robot using arcade controls.
    *
    * @param fwd the commanded forward movement
    * @param rot the commanded rotation
    */
    public void arcadeDrive(double fwd, double rot) {
        m_drive.arcadeDrive(fwd, rot);
    }

    /**
    * Controls the left and right sides of the drive directly with voltages.
    *
    * @param leftVolts  the commanded left output
    * @param rightVolts the commanded right output
    */
    public void tankDriveVolts(double leftVolts, double rightVolts) {
        m_leftMotors.setVoltage(leftVolts);
        m_rightMotors.setVoltage(-rightVolts);
        m_drive.feed();
    }

    /**
    * Resets the drive encoders to currently read a position of 0.
    */
    public void resetEncoders() {
        m_leftEncoder.reset();
        m_rightEncoder.reset();
    }

    /**
    * Gets the average distance of the two encoders.
    *
    * @return the average of the two encoder readings
    */
    public double getAverageEncoderDistance() {
        return (m_leftEncoder.getDistance() + m_rightEncoder.getDistance()) / 2.0;
    }       

    /**
    * Gets the left drive encoder.
    *
    * @return the left drive encoder
    */
    public Encoder getLeftEncoder() {
        return m_leftEncoder;
    }

    /**
    * Gets the right drive encoder.
    *
    * @return the right drive encoder
    */
    public Encoder getRightEncoder() {
        return m_rightEncoder;
    }

    /**
    * Sets the max output of the drive.  Useful for scaling the drive to drive more slowly.
    *
    * @param maxOutput the maximum output to which the drive will be constrained
    */
    public void setMaxOutput(double maxOutput) {
        m_drive.setMaxOutput(maxOutput);
    }

    /**
    * Zeroes the heading of the robot.
    */
    public void zeroHeading() {
        m_gyro.reset();
    }

    /**
    * Returns the heading of the robot.
    *
    * @return the robot's heading in degrees, from -180 to 180
    */
    public double getHeading() {
        return m_gyro.getRotation2d().getDegrees();
    }

    /**
    * Returns the turn rate of the robot.
    *
    * @return The turn rate of the robot, in degrees per second
    */
    public double getTurnRate() {
        return -m_gyro.getRate();
    }

    public void simulationPeriodic() {
        // Set the inputs to the system. Note that we need to convert
        // the [-1, 1] PWM signal to voltage by multiplying it by the
        // robot controller voltage.
        m_driveSim.setInputs(m_leftMotors.get() * RobotController.getInputVoltage(),
                             m_rightMotors.get() * RobotController.getInputVoltage());
      
        // Advance the model by 20 ms. Note that if you are running this
        // subsystem in a separate thread or have changed the nominal timestep
        // of TimedRobot, this value needs to match it.
        m_driveSim.update(0.020);
      
        // Update all of our sensors.
        m_leftEncoderSim.setDistance(m_driveSim.getLeftPositionMeters());
        m_leftEncoderSim.setRate(m_driveSim.getLeftVelocityMetersPerSecond());
        m_rightEncoderSim.setDistance(m_driveSim.getRightPositionMeters());
        m_rightEncoderSim.setRate(m_driveSim.getRightVelocityMetersPerSecond());
        m_gyroSim.setAngle(-m_driveSim.getHeading().getDegrees());
      }



}
