/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class HoodSub extends SubsystemBase {

  private static final double ticksPerRev = 0;
  private static final double degreesPerEncoder = 0;

  public HoodSub() {

  }

  public void angleHood(double speed){
    RobotContainer.hoodMotor.set(speed);
  }

  public double getHoodAngle() {
    return RobotContainer.hoodMotor.getSensorCollection().getQuadraturePosition() * degreesPerEncoder;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
