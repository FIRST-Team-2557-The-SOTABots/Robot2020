package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class HoodSub extends SubsystemBase {

  private final DigitalInput hallEffect = new DigitalInput(0); // we don't know yet

  public HoodSub() {

  }

  public void angleHood(double speed){
    if(RobotContainer.hoodLock.get() == Value.kForward){
      RobotContainer.hood.set(speed);
    }else RobotContainer.hood.set(0);
  }

  public boolean isClosed() {
    return hallEffect.get();
  }

  public double getHoodPos() {
    return RobotContainer.hood.getSensorCollection().getQuadraturePosition();
  }

  @Override
  public void periodic() {
    SmartDashboard.putString("Hood Lock", RobotContainer.hoodLock.get().toString());
  }
}
