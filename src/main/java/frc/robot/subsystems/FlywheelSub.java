package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class FlywheelSub extends SubsystemBase {

  public static final double FLY_WHEEL_RADIUS = .25;//feet
  public static final double FLY_WHEEL_SPEED = -1;
  private static final double GEAR_RATIO = 3.0; // three flywheel rotations for 1 motor rotation
  private static final double MAX_FLYWHEEL_SPEED = 0.5;

  public FlywheelSub() {
  }

  public void spinFlywheels(double speed) {
    speed = Math.max(-MAX_FLYWHEEL_SPEED, Math.min(MAX_FLYWHEEL_SPEED, speed));
    RobotContainer.flywheelMotor.set(speed);
    RobotContainer.flywheelMotor2.set(speed);
  }

  // returns RPM of flywheel
  public double getFlywheelSpeed() {
    return (RobotContainer.flywheelMotor.getEncoder().getVelocity()*GEAR_RATIO);
  }

  @Override
  public void periodic() {
  }
}
