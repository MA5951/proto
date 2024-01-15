package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.chassis.DriveTrain;

public class drivetrainpid extends Command {
    private DriveTrain driveTrain;
    /** Creates a new setIntake. */
    public drivetrainpid() {
        driveTrain = DriveTrain.getInstance();
        addRequirements(driveTrain);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {}

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        driveTrain.calculateFront();
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        driveTrain.resetSetPoint();
        driveTrain.setLeft(0);
        driveTrain.setRight(0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}