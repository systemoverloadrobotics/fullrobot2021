package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Storage;

public class StorageID extends CommandBase {

    private Storage storage;

    public StorageID(Storage storage) {
        this.storage = storage;
        addRequirements(storage);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {

        storage.setBallCount(0);

        for(int i = 0; i < storage.getAllSensors().length; i++){
            if(storage.getSensorValue(i)){
                storage.incBallCount();
            }
        }
        
        if(storage.getSensorValue(0)) {
            if(storage.getBallCount() == 0){
                storage.spinLowerMotor();
                if(storage.getSensorValue(1)){
                    storage.stopLowerMotor();
                }
                storage.incBallCount();
            }
    
            if(storage.getBallCount() == 2 && storage.getSensorValue(1) && storage.getSensorValue(2)){
                storage.spinBoth();
                if(storage.getSensorValue(3) && storage.getSensorValue(4)){
                    storage.stopBoth();
                }
            }
    
            if(storage.getBallCount() >= 2 && storage.getSensorValue(3) && storage.getSensorValue(4)){
                int i = 0;
                if(storage.getSensorValue(i)){
                    storage.spinLowerMotor();
                    if(storage.getSensorValue(i++)){
                        storage.stopLowerMotor();
                    }
                }
                storage.incBallCount();
            }
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        storage.stopBoth();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }

}
