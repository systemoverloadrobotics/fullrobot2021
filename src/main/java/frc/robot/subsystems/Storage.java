package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Storage extends SubsystemBase {
    private WPI_VictorSPX lowerMotor = new WPI_VictorSPX(0);// dummy test
    private WPI_VictorSPX upperMotor = new WPI_VictorSPX(1);
    private DigitalInput[] sensors = new DigitalInput[5];

    public Storage() {
        // config
        lowerMotor.configFactoryDefault();
        upperMotor.configFactoryDefault();
        lowerMotor.setNeutralMode(NeutralMode.Brake);
        upperMotor.setNeutralMode(NeutralMode.Brake);
        for (int i = 0; i < 5; i++) {
            sensors[i] = new DigitalInput(i);
        }
        // encoder retrieval
        lowerMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        upperMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    }

    public void spinLowerMotor() {
        lowerMotor.set(ControlMode.PercentOutput, 0.5);
    }

    public void spinUpperMotor() {
        upperMotor.set(ControlMode.PercentOutput, 0.5);
    }

    public void stopLowerMotor() {
        lowerMotor.stopMotor();
    }

    public void stopUpperMotor() {
        upperMotor.stopMotor();
    }

    public boolean getSensor(int index) {
        return sensors[index].get();
    }

    @Override
    public void periodic() {

    }

}