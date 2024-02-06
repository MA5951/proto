// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

// import frc.robot.commands.setIntake;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import frc.robot.commands.setDrivetrain;

public class RobotContainer {

  public static final CommandJoystick leftJoystick = new CommandJoystick(0);
  public static final CommandJoystick rightJoystick = new CommandJoystick(1);
  public RobotContainer() {
    new setDrivetrain();
    configureBindings();
  }

  private void configureBindings() {
  }

  public Command getAutonomousCommand() {
    return null;
  }
}