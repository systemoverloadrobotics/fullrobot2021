package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

public class ArcadeDrive extends CommandBase {
    private final DriveTrain m_driveTrain;
    private final DoubleSupplier m_speed;
    private final DoubleSupplier m_turn;
    private final BooleanSupplier m_shiftDown;
    private final BooleanSupplier m_shiftUp;
    private final BooleanSupplier m_slowDown;
    private final BooleanSupplier m_slowUp;

    private double prev_val = 0;
    private double slowed = 1;

    public ArcadeDrive(DriveTrain driveTrain, DoubleSupplier speed, DoubleSupplier turn, BooleanSupplier shiftDown, BooleanSupplier shiftUp, BooleanSupplier slowDown, BooleanSupplier slowUp) {
        m_driveTrain = driveTrain;
        m_speed = speed;
        m_turn = turn;
        m_shiftDown = shiftDown;
        m_shiftUp = shiftUp;
        m_slowDown = slowDown;
        m_slowUp = slowUp;
        addRequirements(driveTrain);
    }

    @Override
    public void execute() {
        prev_val = (m_speed.getAsDouble() - prev_val) * Constants.DRIVE.DECELERATION_MULTIPLIER + prev_val;

        m_driveTrain.driveArcade(prev_val * slowed, m_turn.getAsDouble());

        if (m_shiftDown.getAsBoolean()) {
            m_driveTrain.shiftDown();
        } else if (m_shiftUp.getAsBoolean()) {
            m_driveTrain.shiftUp();
        }

        if (m_slowDown.getAsBoolean()) {
            slowed = Constants.DRIVE.SLOWDOWN_MULTIPLIER;
        }
        else if (m_slowDown.getAsBoolean()) {
            slowed = 1;
        }
    }

    @Override
    public void end(boolean interrupted) {
        m_driveTrain.driveArcade(0, 0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
