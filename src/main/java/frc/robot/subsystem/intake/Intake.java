// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystem.intake;

import com.ma5951.utils.MAShuffleboard;
import com.ma5951.utils.subsystem.MotorSubsystem;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase implements MotorSubsystem{

  private static Intake instance;
  private CANSparkMax UpperMotor;
  private CANSparkMax BouttomMotor;

  private double upperPower;
  private double lowerPower;

  private MAShuffleboard board;

  private Intake() {
    UpperMotor = new CANSparkMax(4, MotorType.kBrushless);
    BouttomMotor = new CANSparkMax(13, MotorType.kBrushless);

    board = new MAShuffleboard("IntakeFootball");
    board.addNum("UpperSpeed", upperPower);
    board.addNum("LowerSpeed", lowerPower);
  }

  public void serUpper () {
    UpperMotor.set(board.getNum("UpperSpeed"));
  }

  public void setBouttom () {
    BouttomMotor.set(board.getNum("LowerSpeed"));
  }

  public void stop () {
    BouttomMotor.set(0);
    UpperMotor.set(0);
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

  }


  @Override
  public boolean canMove() {
    return true;
  }


  @Override
  public void setVoltage(double voltage) {
    UpperMotor.set(voltage/12d);
    BouttomMotor.set(voltage/12d);
  }

  
}
