package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class HoodSub extends SubsystemBase {

  private final DigitalInput hallEffect = new DigitalInput(0); // we don't know yet

  public HoodSub() {

  }
//10.3 - -1734
//68.8 - 0
//58.5
//1734 / 58.5 = 29.641
  public void angleHood(double speed){
    RobotContainer.hoodMotor.set(speed);
  }

  public boolean isClosed() {
    return hallEffect.get();
  }

  public double getHoodPos() {
    return -RobotContainer.hoodMotor.getSensorCollection().getQuadraturePosition();
  }

  @Override
  public void periodic() {
  }
}
