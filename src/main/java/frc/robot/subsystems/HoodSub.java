 
package frc.robot.subsystems;

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
  }
}
