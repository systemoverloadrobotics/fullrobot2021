package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
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

    public void GetLimeLightData(){
        
    }

}
