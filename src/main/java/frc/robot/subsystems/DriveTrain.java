package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DRIVE;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import javax.annotation.OverridingMethodsMustInvokeSuper;

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
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.EncoderType;
import frc.robot.Constants;

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

    private CANEncoder rightFolloer1Encoder;
    private CANEncoder rightFollower2Encoder;
    private CANEncoder leftFollower1Encoder;
    private CANEncoder leftFOllower2Encoder;

    private CANEncoder leftMasterEncoder = leftMaster.getEncoder(EncoderType.kQuadrature, 4069);

    private CANEncoder rightMasterEncoder = rightMaster.getEncoder(EncoderType.kQuadrature, 4069);

    private DoubleSolenoid shifter = new DoubleSolenoid(Constants.PCM_ID, DRIVE.FORWARD_CHANNEL_ID, DRIVE.REVERSE_CHANNEL_ID);

    private SpeedControllerGroup rightGroup = new SpeedControllerGroup(rightMaster, rightFollower1, rightFollower2);
    private SpeedControllerGroup leftGroup = new SpeedControllerGroup(leftMaster, leftFollower1, leftFollower2);

    public DifferentialDrive robotDrive = new DifferentialDrive(leftGroup, rightGroup);

    private double yaw;
    private double pitch;
    private double roll;
    //private PigeonIMU pidgey = new PigeonIMU(Constants.PIGEON_PORT);

    private DifferentialDriveOdometry odometry;

    public DifferentialDriveKinematics DRIVE_KINEMATICS = new DifferentialDriveKinematics(Constants.TRACK_WIDTH_METERS);

    public DriveTrain() {

        rightFolloer1Encoder = rightFollower1.getEncoder();
        rightFollower2Encoder = rightFollower2.getEncoder();
        leftFollower1Encoder = leftFollower1.getEncoder();
        leftFOllower2Encoder = leftFollower2.getEncoder();



        odometry = new DifferentialDriveOdometry(getRotation());

    }

    @Override
    public void periodic() {
        double[] ypr = new double[3];
        odometry.update(getRotation(), leftMasterEncoder.getPosition(), rightMasterEncoder.getPosition());
        //pidgey.getYawPitchRoll(ypr);
        yaw = ypr[0];
        pitch = ypr[1];
        roll = ypr[2];
        SmartDashboard.putNumber("Voltagee Output", getLeftMasterEncoderValue());
        SmartDashboard.putNumber("ID 7 drive", leftFollower1.getAppliedOutput());
        SmartDashboard.putNumber("ID 6 drive", leftFollower2.getAppliedOutput());
        SmartDashboard.putNumber("ID 4 Drive", rightFollower1.getAppliedOutput());

    }

    public Pose2d getPose() {
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
        robotDrive.arcadeDrive(speed * DRIVE.SPEED_MULTIPLIER, -turn * DRIVE.TURN_MULTIPLIER);
    }

    public void tankDriveVolts(double leftVolts, double rightVolts) {
        rightGroup.setVoltage(-rightVolts);
        leftGroup.setVoltage(leftVolts);
    }

    public DifferentialDriveWheelSpeeds getWheelSpeeds() {
        return new DifferentialDriveWheelSpeeds(leftMasterEncoder.getVelocity(), rightMasterEncoder.getVelocity());
    }

    // Pushes the solenoid forward
    public void shiftUp() {
        shifter.set(Value.kForward);
    }

    // Pushes the solenoid backward
    public void shiftDown() {
        shifter.set(Value.kReverse);
    }

    // Gets the encoder value for thre left encoder
    public double getLeftMasterEncoderValue() {
        return leftMasterEncoder.getPosition();
    }

    // Gets the econder value for the right encoder
    public double getRightMasterEncoderValue() {
        return rightMasterEncoder.getPosition();
    }


}
