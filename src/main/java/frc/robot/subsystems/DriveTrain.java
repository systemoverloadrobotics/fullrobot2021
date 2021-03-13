package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.CONSTANTS.DRIVE;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.ctre.phoenix.sensors.PigeonIMU;
import com.revrobotics.CANEncoder;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;

import com.revrobotics.EncoderType;
import frc.robot.CONSTANTS;

import java.io.Console;
import java.util.function.BooleanSupplier;

public class DriveTrain extends SubsystemBase {

    // DriveBase is divided into 2 sections, a right and left section.
    private CANSparkMax rightMaster = new CANSparkMax(DRIVE.RIGHT_MASTER_ID, MotorType.kBrushless);
    private CANSparkMax rightFollower1 = new CANSparkMax(DRIVE.RIGHT_FOLLOWER_1_ID, MotorType.kBrushless);
    private CANSparkMax rightFollower2 = new CANSparkMax(DRIVE.RIGHT_FOLLOWER_2_ID, MotorType.kBrushless);
    private CANSparkMax leftMaster = new CANSparkMax(DRIVE.LEFT_MASTER_ID, MotorType.kBrushless);
    private CANSparkMax leftFollower1 = new CANSparkMax(DRIVE.LEFT_FOLLOWER_1_ID, MotorType.kBrushless);
    private CANSparkMax leftFollower2 = new CANSparkMax(DRIVE.LEFT_FOLLOWER_2_ID, MotorType.kBrushless);

    private CANEncoder leftMasterEncoder = leftMaster.getEncoder(EncoderType.kQuadrature, 4069);

    private CANEncoder rightMasterEncoder = rightMaster.getEncoder(EncoderType.kQuadrature, 4069);

    private DoubleSolenoid shifter = new DoubleSolenoid(CONSTANTS.PCM_ID, DRIVE.FORWARD_CHANNEL_ID,
            DRIVE.REVERSE_CHANNEL_ID);

    private SpeedControllerGroup rightGroup = new SpeedControllerGroup(rightMaster, rightFollower1, rightFollower2);
    private SpeedControllerGroup leftGroup = new SpeedControllerGroup(leftMaster, leftFollower1, leftFollower2);

    public DifferentialDrive robotDrive = new DifferentialDrive(leftGroup, rightGroup);

    private double[] ypr = new double[3];
    private double yaw;
    private double pitch;
    private double roll;
    private PigeonIMU pidgey = new PigeonIMU(CONSTANTS.PIGEON_PORT);

    private DifferentialDriveOdometry odometry;

    public static final DifferentialDriveKinematics DRIVE_KINEMATICS = new DifferentialDriveKinematics(
            CONSTANTS.TRACK_WIDTH_METERS);

    public DriveTrain() {
        rightMaster.restoreFactoryDefaults();
        rightFollower1.restoreFactoryDefaults();
        rightFollower2.restoreFactoryDefaults();
        leftMaster.restoreFactoryDefaults();
        leftFollower1.restoreFactoryDefaults();
        leftFollower2.restoreFactoryDefaults();

        odometry = new DifferentialDriveOdometry(getRotation());

    }

    @Override
    public void periodic() {
        odometry.update(getRotation(), leftMasterEncoder.getPosition(), rightMasterEncoder.getPosition());
        pidgey.getYawPitchRoll(ypr);
        yaw = ypr[0];
        pitch = ypr[1];
        roll = ypr[3];

    }

    public Pose2d getPos() {
        return odometry.getPoseMeters();
    }

    public void resetOdometry(Pose2d position) {
        resetEncoder();
        odometry.resetPosition(position, getRotation());
    }

    public void resetEncoder() {
        leftMasterEncoder.setPosition(0);
        rightMasterEncoder.setPosition(0);
    }

    public Rotation2d getRotation() {
        return new Rotation2d(yaw);
    }

    public void driveArcade(double speed, double turn) {
        // incase we need arcade drive
        robotDrive.arcadeDrive(-speed * DRIVE.SPEED_MULTIPLIER, -turn * DRIVE.TURN_MULTIPLIER);
    }

    // Pushes the solenoid forward
    public void shiftUp() {
        shifter.set(Value.kForward);
    }

    // Pushes the solenoid backward
    public void shiftDown() {
        shifter.set(Value.kReverse);
    }

    // Gets the encoder value for the left encoder
    public double getLeftMasterEncoderValue() {
        return leftMasterEncoder.getPosition();
    }

    // Gets the econder value for the right encoder
    public double getRightMasterEncoderValue() {
        return rightMasterEncoder.getPosition();
    }

}
