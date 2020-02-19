package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class TurretSub extends SubsystemBase {

  // assume counter clockwise is more +, that encoder counts become more positive when turning cclockwise, etc.

  private static int encoderLowLimit = 0;
  private static int encoderHighLimit = 1000;
  private static double degreesOfFreedom = 90;
  private static double angleAtLowLimit = -45;
  private static double angleAtHighLimit = 45;
  private static final double degreesPerTick = degreesOfFreedom / encoderHighLimit;



  public TurretSub() {

  }

  public void rotate(double speed){
    if (RobotContainer.turretMotor.getSensorCollection().getQuadraturePosition() > encoderHighLimit-10 && speed > 0) {
      RobotContainer.turretMotor.set(0);
    } else if(RobotContainer.turretMotor.getSensorCollection().getQuadraturePosition() <= encoderLowLimit+10 && speed < 0){
      RobotContainer.turretMotor.set(0);
    } else {
      RobotContainer.turretMotor.set(speed);
    }
  }

  public double getAngle() {
    return (RobotContainer.turretMotor.getSensorCollection().getQuadraturePosition() - (encoderHighLimit / 2)) * degreesPerTick;
  }

  @Override
  public void periodic() {
    if (RobotContainer.touchHigh.get()) {
      RobotContainer.turretMotor.getSensorCollection().setQuadraturePosition(encoderHighLimit, 10);
    }
    
    if (RobotContainer.touchLow.get()) {
      RobotContainer.turretMotor.getSensorCollection().setQuadraturePosition(encoderLowLimit, 10);
    }
    // This method will be called once per scheduler run
  }
}
