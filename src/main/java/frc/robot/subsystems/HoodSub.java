package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class HoodSub extends SubsystemBase {


  public HoodSub() {

  }
//10.3 - -1734
//68.8 - 0
//58.5
//1734 / 58.5 = 29.641
  public void angleHood(double speed){
    // RobotContainer.hood.set(speed);
  }

  public double getHoodPos() {
    return -RobotContainer.hood.getSensorCollection().getQuadraturePosition();
  }

  @Override
  public void periodic() {
  }
}
