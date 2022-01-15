package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Storage;

public class StorageManager extends CommandBase {

    private Storage storage;

    public StorageManager(Storage storage) {
        this.storage = storage;
        addRequirements(storage);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        
        /*if(storage.getSensor(0).get()) {
            if(storage.getBallCount() == 0){
                storage.spinLowerMotor();
                if(storage.getSensor(1).get()){
                    storage.stopLowerMotor();
                }
                storage.incBallCount();
            }
    
            if(storage.getBallCount() == 2 && storage.getSensor(1).get() && storage.getSensor(2).get()){
                storage.spinBoth();
                if(storage.getSensor(3).get() && storage.getSensor(4).get()){
                    storage.stopBoth();
                }
            }
    
            if(storage.getBallCount() >= 2 && storage.getSensor(3).get() && storage.getSensor(4).get()){
                int i = 0;
                if(storage.getSensor(i).get()){
                    storage.spinLowerMotor]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]
                    if(storage.getSensor(i++).get()){
                        storage.stopLowerMotor();
                    }
                }
                storage.incBallCount();
            }
        }*/
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
