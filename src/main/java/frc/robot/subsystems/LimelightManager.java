package frc.robot.subsystems;

public class LimelightManager implements ILimelightManager{

    private double tx;
    private double ty;
    private double ta;

    public LimelightManager(double tx, double ty, double ta){
        
    }

    @Override
    public double getX() {
        return tx;
    }

    @Override
    public double getY() {
        return ty;
    }

    @Override
    public double getA() {
        return ta;
    }
}
