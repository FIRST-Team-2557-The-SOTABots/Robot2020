package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class FlywheelSub extends SubsystemBase {

  public static final double flywheelRadius = 1; //meters
  private static final double encTicksPerRot = 0;

  public FlywheelSub() {

  }

  // returns RPM the flywheel should spin at to hit target
  public static double getRequiredRPM(){
    double velocity = Projectile.getProjectileVelocity(); // required velocity of projectile
    return (120 * velocity) / (2 * Math.PI * flywheelRadius);
  }

  public void spinFlywheel(double speed) {
    RobotContainer.flywheelMotor.set(-speed);
    RobotContainer.flywheelMotor2.set(-speed);
  }

  // returns RPM of flywheel
  public double getFlywheelSpeed() {
    return (RobotContainer.flywheelMotor.getEncoder().getVelocity());

  }

  @Override
  public void periodic() {
  }
}
