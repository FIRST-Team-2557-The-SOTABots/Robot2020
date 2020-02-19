/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class FlywheelSub extends SubsystemBase {

  public static WPI_TalonSRX flywheelMotor = new WPI_TalonSRX(1);
  public static WPI_TalonSRX flywheelMotor2 = new WPI_TalonSRX(2);
  public static final double flywheelRadius = 1; //meters
  private static final double encTicksPerRot = 0;

  public FlywheelSub() {

  }

  // returns RPM the flywheel should spin at to hit target
  public static double getRequiredRPM(){
    // double velocity = Projectile.getProjectileVelocity(); // required velocity of projectile
    // return (120 * velocity) / (2 * Math.PI * flywheelRadius);
    return 0;
  }

  public void spinFlywheel(double speed) {
    flywheelMotor.set(speed);
    flywheelMotor2.set(speed);
  }

  // returns RPM of flywheel
  public double getFlywheelSpeed() {
    return (flywheelMotor.getSensorCollection().getQuadratureVelocity() / encTicksPerRot) * 600;

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
