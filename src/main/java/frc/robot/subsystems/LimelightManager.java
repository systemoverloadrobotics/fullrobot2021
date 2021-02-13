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

    @Override
    public double setX() {
        return 0;
    }

    @Override
    public double setY() {
        return 0;
    }

    @Override
    public double setA() {
        return 0;
    }

    @Override
    public void ConvertPixelsToAngles() {
        double nx = (1/160) * (getX() - 159.5); 
        double ny = (1/120) * (119.5 - getY());

        
    }

}
