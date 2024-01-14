// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.chassis;

import com.ma5951.utils.MAShuffleboard;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {
  /** Creates a new driveTrain. */
  private CANSparkMax leftMotor;
  private CANSparkMax rightMotor;

  private static DriveTrain instance;

  private double leftpower = 0;
  private double rightpower = 0;

  private MAShuffleboard board;

  private DriveTrain() {
    leftMotor = new CANSparkMax(13, CANSparkLowLevel.MotorType.kBrushless);
    rightMotor = new CANSparkMax(17, CANSparkLowLevel.MotorType.kBrushless);

    leftMotor.setIdleMode(IdleMode.kBrake);
    rightMotor.setIdleMode(IdleMode.kBrake);
    
    leftMotor.setInverted(true);
    rightMotor.setInverted(true);

    board = new MAShuffleboard("drivetrain");
  }

  public void setLeft(double power) {
    leftMotor.set(power);
    leftpower = power;
  }

  public void setRight(double power) {
    rightMotor.set(power);
    rightpower = power;
  }

  public static DriveTrain getInstance() {
    if (instance == null) {
      instance = new DriveTrain();
    }
    return instance;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    board.addNum("left motor", leftMotor.get());
    board.addNum("right motor", rightMotor.get());

    board.addNum("left motor power", leftpower);
    board.addNum("right motor power", rightpower);
  }
}
