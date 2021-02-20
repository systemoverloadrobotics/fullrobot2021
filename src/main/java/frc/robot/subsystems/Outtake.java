package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.CANifier;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.RemoteLimitSwitchSource;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;


public class Outtake extends SubsystemBase{

    final int kUnitsPerRevolution = 2048; /* this is constant for Talon FX */
 
    private TalonSRX shooter = new TalonSRX(1); // config id later
    private TalonSRX turret = new TalonSRX(2); // config id later


    /* apparently the falcon 500 acts as hardware limit switch that we can use to maipulate,
    which will com in use for shooting the ball*/
    private CANifier limitSwitch = new CANifier(0); // config id later

    public Outtake(){
        //configuration of the motors
        shooter.configFactoryDefault();
        turret.configFactoryDefault();
        limitSwitch.configFactoryDefault();


        //retrieves the encoders
        shooter.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        turret.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    }

    public double getShooterVel(){
        return shooter.getSelectedSensorVelocity();
    }

    public double getTurretVel(){
        return turret.getSelectedSensorVelocity();
    }

    public void setShooter(int rpm){
        shooter.set(ControlMode.PercentOutput, rpm);
    }

    public void setTurret(int rpm){
        turret.set(ControlMode.PercentOutput, rpm);
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







    NetworkTable networkTable = NetworkTableInstance.getDefault().getTable("Limelight");
    NetworkTableEntry tv = networkTable.getEntry("tv"); //Whether the limelight has any valid targets (0 or 1)
    NetworkTableEntry tx = networkTable.getEntry("tx"); //Horizontal Offset From Crosshair To Target (-27 degrees to 27 degrees)
    NetworkTableEntry ty = networkTable.getEntry("ty"); //Vertical Offset From Crosshair To Target (-20.5 degrees to 20.5 degrees)
    NetworkTableEntry ta = networkTable.getEntry("ta"); //Target Area (0% of image to 100% of image)


    public double x = tx.getDouble(0.0); 
    public double y = ty.getDouble(0.0); 
    public double area = ta.getDouble(0.0);


    public void periodic(){
        x = tx.getDouble(0.0);
        y = ty.getDouble(0.0);
        area = ta.getDouble(0.0);
    }


}
