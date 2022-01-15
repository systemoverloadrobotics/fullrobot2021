<<<<<<< Updated upstream
=======
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Storage extends SubsystemBase {
    private WPI_VictorSPX lowerMotor = new WPI_VictorSPX(6);// dummy test
    private WPI_VictorSPX upperMotor = new WPI_VictorSPX(1);
    //private DigitalInput[] sensors = new DigitalInput[5];
    private int ballCount = 0;

    public Storage() {
        // config
        lowerMotor.configFactoryDefault();
        upperMotor.configFactoryDefault();
        lowerMotor.setNeutralMode(NeutralMode.Coast);
        upperMotor.setNeutralMode(NeutralMode.Coast);
        //DIO 0-4 sensors are for the balls
        for (int i = 0; i < 5; i++) {
            //sensors[i] = new DigitalInput(i);
        }
        // encoder retrieval
        lowerMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        upperMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    }

    public void spinBoth(){
        spinLowerMotor();
        spinUpperMotor();
    }

    public void stopBoth(){
        stopUpperMotor();
        stopLowerMotor();
    }

    public void spinLowerMotor() {
        lowerMotor.set(ControlMode.PercentOutput, -0.6);
    }

    public void spinUpperMotor() {
        upperMotor.set(ControlMode.PercentOutput, 0.85);
    }

    public void stopLowerMotor() {
        lowerMotor.stopMotor();
    }

    public void stopUpperMotor() {
        upperMotor.stopMotor();
    }


    public int getBallCount(){
        return this.ballCount;
    }

    public void setBallCount(int ball){
        this.ballCount = ball;
    }

    public void incBallCount(){
        ballCount++;
    }

    @Override
    public void periodic() {

    }

}
>>>>>>> Stashed changes
