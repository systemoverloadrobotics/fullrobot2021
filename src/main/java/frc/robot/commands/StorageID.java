package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Storage;

public class StorageID extends CommandBase {

    private Storage storage;

    public StorageID() {
        addRequirements(storage);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        boolean isTop = false; // check if the top has been used yet
        double delay = 0.0;

        if (storage.getSensor(0)) {
            if (!(storage.getSensor(4) && storage.getSensor(3))) {
                if (!storage.getSensor(1)) {
                    storage.spinLowerMotor();
                } else if (storage.getSensor(1) && !storage.getSensor(2)) {

                }

            }

        }
    }

}
