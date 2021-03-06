package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class FlywheelSub extends SubsystemBase {

  public static final double flywheelRadius = .25;//feet
  public static final double flywheelSpeed = -1;

  public FlywheelSub() {

  }

  // returns RPM the flywheel should spin at to hit target
  public static double getRequiredRPM(){
    double velocity = Projectile.getProjectileVelocity(); // required velocity of projectile
    return (120 * velocity) / (2 * Math.PI * flywheelRadius);
  }

  public void spinFlywheels(double speed) {
    RobotContainer.flywheelMotor.set(speed);
    RobotContainer.flywheelMotor2.set(speed);
  }

  // returns RPM of flywheel
  public double getFlywheelSpeed() {
    return (RobotContainer.flywheelMotor.getEncoder().getVelocity()*3);

  }

  @Override
  public void periodic() {
  }
}
