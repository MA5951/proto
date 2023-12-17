// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ma5951.utils.commands.MotorCommand;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandPS4Controller;
import frc.robot.subsystem.intake.Intake;
public class RobotContainer {

  public static final Joystick leftJoystick = new Joystick(0);
  public static final Joystick rightJoystick = new Joystick(1);
  public static final CommandPS4Controller joystick = new CommandPS4Controller(2);

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    joystick.triangle().whileTrue(new MotorCommand(
      Intake.getInstance(), 0.5, 0));

    joystick.cross().whileTrue(new MotorCommand(
      Intake.getInstance(), -0.5, 0));
  }

  public Command getAutonomousCommand() {
    return null;
  }
}
