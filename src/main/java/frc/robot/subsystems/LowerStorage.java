package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class LowerStorage extends SubsystemBase{
    /*Calculate distance from sensor 1
    Use sensor 2 to verify distance
    Each ball would be in an arrayList by a double
    that will continuosly update. This double represents
    the distance from sensor 1 
    */

    //Declare motor
    private WPI_VictorSPX lowerMotor = new WPI_VictorSPX(0);

    public LowerStorage (){

    }
    private int x = 5;
    //placeholder text

    public void spinDistanceInInches (double distance){
        //If speed = distance/time, distance = speed * time. 
        //Maybe set to a constant speed and control time to get distance.
        double move = distance;
        double time = move * Constants.STORAGE_SPIN_SPEED;
        while (move > x)
            lowerMotor.set(ControlMode.PercentOutput, Constants.STORAGE_SPIN);

    }


}
