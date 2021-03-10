package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class FlywheelSub extends SubsystemBase {

  public static final double FLY_WHEEL_RADIUS = .25;//feet
  public static final double FLY_WHEEL_SPEED = -1;

  public FlywheelSub() {

  }

  public void spinFlywheels(double speed) {
    RobotContainer.flywheelMotor.set(speed);
    RobotContainer.flywheelMotor2.set(speed);
  }

  public void delivery(double speed){
    RobotContainer.delivery.set(-speed);
  }

  // returns RPM of flywheel
  public double getFlywheelSpeed() {
    return (RobotContainer.flywheelMotor.getEncoder().getVelocity()*3);

  }

  @Override
  public void periodic() {
  }
}
