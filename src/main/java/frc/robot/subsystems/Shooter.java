package frc.robot.subsystems;

import com.ctre.phoenix.CANifier;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.RemoteLimitSwitchSource;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANDigitalInput.LimitSwitch;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.CONSTANTS;

public class Shooter extends SubsystemBase{

    final int kUnitsPerRevolution = 2048; /* this is constant for Talon FX */
    
    private WPI_TalonSRX shooter = new WPI_TalonSRX(1); // config id later

    /* String for output */
    StringBuilder sb = new StringBuilder();

    /* Loop tracker for prints */
	int _loops = 0;

    /* apparently the falcon 500 acts as hardware limit switch that we can use to maipulate,
    which will come in use for shooting the ball*/
    private CANifier limitSwitch = new CANifier(0); // config id later

    public Shooter(){
        //configuration of the motors
        shooter.configFactoryDefault();
        limitSwitch.configFactoryDefault();

        /* Config sensor used for Primary PID [Velocity] */
        shooter.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, CONSTANTS.kPIDLoopIdx, CONSTANTS.kTimeoutMs);

        /* Config the peak and nominal outputs */
		shooter.configNominalOutputForward(0, CONSTANTS.kTimeoutMs);
		shooter.configNominalOutputReverse(0, CONSTANTS.kTimeoutMs);
		shooter.configPeakOutputForward(1, CONSTANTS.kTimeoutMs);
		shooter.configPeakOutputReverse(-1, CONSTANTS.kTimeoutMs);
    }

    public double getShooterVel(){
        return shooter.getSelectedSensorVelocity();
    }

    public void setShooter(int rpm){
        shooter.set(ControlMode.PercentOutput, rpm);
    }

    public void stopShooter(){
        shooter.stopMotor();
    }


    public void limitSwitchState(int state){
        //I think the shooter would act as the best limit swithc, but we can change it later if the turret seems better
        if(state == 0){
            /* use feedback connector but disable feature, use-webdash to reenable */
            shooter.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.Disabled, 30);
            shooter.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.Disabled, 30);
            System.out.println("Limit Switches disabled.");
        }else if(state == 1){
            /* use feedback connector - use three functions */
            shooter.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, 30);
            shooter.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, 30);
            System.out.println("Limit Switches locally enabled.");
        }else if(state == 2){
            /* use remote CANifier - use four param functions */
            shooter.configForwardLimitSwitchSource( RemoteLimitSwitchSource.RemoteCANifier,  LimitSwitchNormal.NormallyOpen, limitSwitch.getDeviceID(), 30);
            shooter.configReverseLimitSwitchSource( RemoteLimitSwitchSource.RemoteCANifier,  LimitSwitchNormal.NormallyOpen, limitSwitch.getDeviceID(), 30);
            System.out.println("Remote Limit Switches enabled using CANifier.");
        }
    }
    
}
