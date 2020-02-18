/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class HoodSub extends SubsystemBase {

  public static WPI_TalonSRX hoodMotor = new WPI_TalonSRX(3);
  private static final double radiansPerEncoder = 0;

  public HoodSub() {

  }

  public void angleHood(double speed){
    hoodMotor.set(speed);
  }

  public double getHoodAngle() {
    return hoodMotor.getSensorCollection().getQuadraturePosition() * radiansPerEncoder;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}