/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSub extends SubsystemBase {
  public static final int gearWhenForward = 1;
  public static final int gearWhenReverse = 2;
  public static final double ratioGear1 = 18.86;
  public static final double ratioGear2 = 6;
  public static final double eTicksPerRev = 42; //ticks of 

  public static final double wheelDiameter = 0.1524; //in meters

  public static final double limitMeterPerSecGear1 = 1.8288; //in meters per second
  public static final double limitRotSpdGear1 = 
    (limitMeterPerSecGear1 / (wheelDiameter * Math.PI)) /* M/s  /  M/rot  = rot/s */   * 60; // rotations per minute

  public static final DoubleSolenoid dsL = new DoubleSolenoid(1, 0, 1);
  // public static final DoubleSolenoid dsR = new DoubleSolenoid(0, 2);
  public static final CANSparkMax l1 = new CANSparkMax(10, MotorType.kBrushless);
  public static final CANSparkMax l2 = new CANSparkMax(11, MotorType.kBrushless);
  public static final CANSparkMax r1 = new CANSparkMax(12, MotorType.kBrushless);
  public static final CANSparkMax r2 = new CANSparkMax(13, MotorType.kBrushless);
  public static final SpeedControllerGroup l = new SpeedControllerGroup(l1, l2);
  public static final SpeedControllerGroup r = new SpeedControllerGroup(r1, r2);
  public static final DifferentialDrive driveTrain = new DifferentialDrive(l, r);

  public static final CANEncoder encoder = l1.getEncoder();

  public DriveSub() {
  }

  public void drive(double fwd, double rot){
    driveTrain.arcadeDrive(fwd, rot);
  }

  public void shift() {
    if (dsL.get() == Value.kForward) {
      dsL.set(Value.kReverse);
    } else if(dsL.get() == Value.kReverse) {
      dsL.set(Value.kForward);
    }
  }

  public int getCurrentGear() {
    if (dsL.get() == Value.kForward) {
      return gearWhenForward;
    } else {
      return gearWhenReverse;
    }
  }

  // returns rotation speed of wheel in rotations per minute
  public double getRotationSpeed(double currentGear) {
    if (currentGear == 1) {
      return encoder.getVelocity() / (ratioGear1);
    } else{
      return encoder.getVelocity() / (ratioGear2) ;
    }

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}