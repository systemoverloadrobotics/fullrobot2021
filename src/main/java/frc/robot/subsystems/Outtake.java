package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.CANifier;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.RemoteLimitSwitchSource;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;


public class Outtake extends SubsystemBase{

    public Outtake(){
        
        
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
