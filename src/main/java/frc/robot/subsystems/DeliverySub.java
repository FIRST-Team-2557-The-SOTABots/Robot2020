// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class DeliverySub extends SubsystemBase {
  /** Creates a new DeliverySub. */
  public DeliverySub() {}

  // runs both delivery systems
  public void runDelivery(double speed) {
    if (RobotContainer.flywheelSub.getFlywheelSpeed() > Constants.FLYWHEEL_SHOOT_RPM) {
      RobotContainer.delivery.set(-speed);
      RobotContainer.deliveryStar.set(speed * 0.3);
    } else {
      RobotContainer.delivery.set(0);
      RobotContainer.deliveryStar.set(0);
    }
  }

  // runs just star wheel delivery system
  public void runStarDelivery(double speed) {
    RobotContainer.deliveryStar.set(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
