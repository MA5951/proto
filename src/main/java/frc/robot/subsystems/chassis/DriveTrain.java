// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.chassis;

import com.ma5951.utils.MAShuffleboard;
import com.ma5951.utils.MAShuffleboard.pidControllerGainSupplier;
import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;

import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {
  /** Creates a new driveTrain. */
  private CANSparkMax leftMotor;
  private CANSparkMax rightMotor;

  private RelativeEncoder leftEncoder;
  private RelativeEncoder rightEncoder;

  private static DriveTrain instance;

  private double setersetPoint;

  private SparkPIDController PIDcontrollerLeft;
  private SparkPIDController PIDcontrollerRight;

  private pidControllerGainSupplier leftPidGainSupplier;
  private pidControllerGainSupplier rightPidGainSupplier;

  private SimpleMotorFeedforward feedforwardleft;
  private SimpleMotorFeedforward feedforwardright;

  private MAShuffleboard boardleft;
  private MAShuffleboard boardright;

  private DriveTrain() {
    leftMotor = new CANSparkMax(14, CANSparkLowLevel.MotorType.kBrushless);
    rightMotor = new CANSparkMax(18, CANSparkLowLevel.MotorType.kBrushless);

    leftMotor.setIdleMode(IdleMode.kBrake);
    rightMotor.setIdleMode(IdleMode.kBrake);
    
    leftMotor.setInverted(true);
    rightMotor.setInverted(true);

    leftEncoder = leftMotor.getEncoder();
    rightEncoder = rightMotor.getEncoder();

    PIDcontrollerLeft = leftMotor.getPIDController();
    PIDcontrollerLeft.setFeedbackDevice(leftEncoder);
    PIDcontrollerLeft.setP(drivetrainconstants.leftKP);
    PIDcontrollerLeft.setI(drivetrainconstants.leftKI);
    PIDcontrollerLeft.setD(drivetrainconstants.leftKD);
    feedforwardleft = new SimpleMotorFeedforward(0, drivetrainconstants.leftKV);

    PIDcontrollerRight = rightMotor.getPIDController();
    PIDcontrollerRight.setFeedbackDevice(rightEncoder);
    PIDcontrollerRight.setP(drivetrainconstants.rightKP);
    PIDcontrollerRight.setI(drivetrainconstants.rightKI);
    PIDcontrollerRight.setD(drivetrainconstants.rightKD);
    // feedforwardright = new SimpleMotorFeedforward(0, drivetrainconstants.rightKV);
    feedforwardright = new SimpleMotorFeedforward(0, 5000 / 5600);

    boardleft = new MAShuffleboard("drivetrain left");
    boardright = new MAShuffleboard("drivetrain right");
    boardleft.addNum("set setPoint", setersetPoint);
    boardright.addNum("set setPoint", setersetPoint);
    leftPidGainSupplier = boardleft.getPidControllerGainSupplier(drivetrainconstants.leftKP, drivetrainconstants.leftKI, drivetrainconstants.leftKD);
    rightPidGainSupplier = boardright.getPidControllerGainSupplier(drivetrainconstants.rightKP, drivetrainconstants.rightKI, drivetrainconstants.rightKD);
  }

  public void calculateFront() {
    PIDcontrollerLeft.setReference(boardleft.getNum("set setPoint"), ControlType.kVelocity, 0, feedforwardleft.calculate(boardleft.getNum("set setPoint")));
    PIDcontrollerRight.setReference(-boardleft.getNum("set setPoint"), ControlType.kVelocity, 0, feedforwardright.calculate(boardleft.getNum("set setPoint")));
  }

  public void calculateReverse() {
    PIDcontrollerLeft.setReference(-boardleft.getNum("set setPoint"), ControlType.kVelocity, 0, feedforwardleft.calculate(boardleft.getNum("set setPoint")));
    PIDcontrollerRight.setReference(boardleft.getNum("set setPoint"), ControlType.kVelocity, 0, feedforwardright.calculate(boardleft.getNum("set setPoint")));
  }

  public double getSetPoint() {
    return setersetPoint;   
  }

  public void resetSetPoint() {
    this.setersetPoint = 0;
  }

  public void setLeft(double power) {
    leftMotor.set(power);
  }

  public void setRight(double power) {
    rightMotor.set(power);
  }

  public boolean canMove() {
    return true;
  }

  public static DriveTrain getInstance() {
    if (instance == null) {
      instance = new DriveTrain();
    }
    return instance;
  }

  @Override
  public void periodic() {
    boardleft.addNum("left encoder", leftEncoder.getVelocity());
    boardright.addNum("right encoder", rightEncoder.getVelocity());

    PIDcontrollerLeft.setP(leftPidGainSupplier.getKP());
    PIDcontrollerLeft.setI(leftPidGainSupplier.getKI());
    PIDcontrollerLeft.setD(leftPidGainSupplier.getKD());

    PIDcontrollerRight.setP(rightPidGainSupplier.getKP());
    PIDcontrollerRight.setI(rightPidGainSupplier.getKI());
    PIDcontrollerRight.setD(rightPidGainSupplier.getKD());
  }
}
