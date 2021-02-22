package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Limelight extends SubsystemBase {

    NetworkTable networkTable;
    private NetworkTableEntry targetEntry;
    private NetworkTableEntry horizontalAngleOffSet;
    private NetworkTableEntry verticalAngleOffSet;
    private NetworkTableEntry ta;

    private double x;
    private double y;
    private double area;

    public Limelight() {

        networkTable = NetworkTableInstance.getDefault().getTable("Limelight");

        targetEntry = networkTable.getEntry("tv");// Whether the limelight has any valid targets (0 or 1)
        horizontalAngleOffSet = networkTable.getEntry("tx"); // Horizontal Offset From Crosshair To Target (-27 degrees
                                                             // to 27 degrees)
        verticalAngleOffSet = networkTable.getEntry("ty"); // Vertical Offset From Crosshair To Target (-20.5 degrees to
                                                           // 20.5 degrees)
        ta = networkTable.getEntry("ta"); // Target Area (0% of image to 100% of image)

        x = horizontalAngleOffSet.getDouble(0.0);
        y = verticalAngleOffSet.getDouble(0.0);
        area = ta.getDouble(0.0);
    }

    public void periodic() {
        x = horizontalAngleOffSet.getDouble(0.0);
        y = verticalAngleOffSet.getDouble(0.0);
        area = ta.getDouble(0.0);
    }

}
