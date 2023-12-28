// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ma5951.utils.commands.MotorCommand;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandPS4Controller;
import frc.robot.commands.Eject;
import frc.robot.commands.setIntake;
public class RobotContainer {

  public static final CommandJoystick leftJoystick = new CommandJoystick(0);
  public static final CommandJoystick rightJoystick = new CommandJoystick(1);
  public static final CommandPS4Controller joystick = new CommandPS4Controller(2);

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    leftJoystick.button(1).whileTrue(new setIntake());
    rightJoystick.button(1).whileTrue(new Eject());
    

  }

  public Command getAutonomousCommand() {
    return null;
  }
}
