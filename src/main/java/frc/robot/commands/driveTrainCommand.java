// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
import frc.robot.subsystems.chassis.DriveTrain;

public class driveTrainCommand extends Command {
  private DriveTrain driveTrain;

  public driveTrainCommand() {
    driveTrain = DriveTrain.getInstance();
    addRequirements(driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (Math.abs(RobotContainer.leftJoystick.getY()) > 0.06) {
      driveTrain.setLeft(-RobotContainer.leftJoystick.getY());
    } else {
      driveTrain.setLeft(0);
    }
    if (Math.abs(RobotContainer.rightJoystick.getY()) > 0.06) {
      driveTrain.setRight(RobotContainer.rightJoystick.getY());
    } else {
      driveTrain.setRight(0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
