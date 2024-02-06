package frc.robot.subsystems.chassis;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {
  private TalonSRX leftFront;
  private TalonSRX leftRear;
  private TalonSRX rightFront;
  private TalonSRX rightRear;

  private static DriveTrain instance;

  private DriveTrain() {
    leftFront = new TalonSRX(1);
    leftRear = new TalonSRX(2); 
    rightFront = new TalonSRX(3);
    rightRear = new TalonSRX(4);
  
    leftFront.setInverted(true);
    leftRear.setInverted(true);
    rightFront.setInverted(false);
    rightRear.setInverted(false);

  }

  public void setLeft(double power) {
    leftFront.set(TalonSRXControlMode.PercentOutput, power);
    leftRear.set(TalonSRXControlMode.PercentOutput, power);
  }

  public void setRight(double power) {
    rightFront.set(TalonSRXControlMode.PercentOutput, power);
    rightRear.set(TalonSRXControlMode.PercentOutput, power);
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
  }
}
