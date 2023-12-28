// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystem.intake;

import com.ma5951.utils.MAShuffleboard;
import com.ma5951.utils.subsystem.MotorSubsystem;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase implements MotorSubsystem{

  private static Intake instance;
  private CANSparkMax UpperMotor;
  private CANSparkMax LMotor;


  private double IntakeSpeed;
  private double EjectSpeed;
  private double speedadj;

  private MAShuffleboard board;

  private Intake() {
    UpperMotor = new CANSparkMax(3, MotorType.kBrushless);
    LMotor= new CANSparkMax(13, MotorType.kBrushless);

    board = new MAShuffleboard("IntakeFootball");
    
    board.addNum("Intake Speed", IntakeSpeed);
    board.addNum("Eject Speed", EjectSpeed);


  }

  public void setSpeed (double speed) {
    UpperMotor.set(speed);
    LMotor.set(speed);
  }

  public double getIntakeSpeed() {
    return board.getNum("Intake Speed");
  }

  public double getEjectSpeed() {
    return board.getNum("Eject Speed");
  }

  public void stop () {
    UpperMotor.set(0);
    LMotor.set(0);

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
    board.addNum("Left motor Speed", LMotor.getEncoder().getVelocity());
    board.addNum("Right motor Speed", UpperMotor.getEncoder().getVelocity());

  }


  @Override
  public boolean canMove() {
    return true;
  }


  @Override
  public void setVoltage(double voltage) {
    UpperMotor.set(voltage/12d);
    LMotor.set(voltage/12d);
  }

  
}
