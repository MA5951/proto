// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.intake;

import com.ma5951.utils.MAShuffleboard;
import com.ma5951.utils.subsystem.MotorSubsystem;
import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase implements MotorSubsystem{

  private static Intake instance;
  private CANSparkMax MotorOne;
  private CANSparkMax MotorTwo;


  private double MotorOneSpeed;
  private double MotorTwoSpeed;

  private MAShuffleboard board;

  private Intake() {
    MotorOne = new CANSparkMax(14, CANSparkLowLevel.MotorType.kBrushless);
    MotorTwo = new CANSparkMax(18, CANSparkLowLevel.MotorType.kBrushless);

    board = new MAShuffleboard("IntakeFootball");
    
    board.addNum("Motor 1 Speed", MotorOneSpeed);
    board.addNum("Motor 2 Speed", MotorTwoSpeed);
  }

  public void setMotorOneSpeed (double speed) {
    MotorOne.set(speed);
  }

  public void setMotorTwoSpeed (double speed) {
    MotorTwo.set(speed);
  }

  public double getMotorOneSpeed() {
    return board.getNum("Motor 1 Speed");
  }

  public double getMotorTwoSpeed() {
    return board.getNum("Motor 2 Speed");
  }

  public void stop () {
    MotorOne.set(0);
    MotorTwo.set(0);
  }

  public static Intake getInstance() {
    if (instance == null) {
      instance = new Intake();
    }
    return instance;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    board.addNum("Left motor Speed", MotorTwo.getEncoder().getVelocity());
    board.addNum("Right motor Speed", MotorOne.getEncoder().getVelocity());

  }


  @Override
  public boolean canMove() {
    return true;
  }


  @Override
  public void setVoltage(double voltage) {
    MotorOne.set(voltage/12d);
    MotorTwo.set(voltage/12d);
  }
}
