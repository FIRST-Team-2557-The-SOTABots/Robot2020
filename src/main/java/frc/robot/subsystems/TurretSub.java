package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class TurretSub extends SubsystemBase {

  // assume counter clockwise is more +, that encoder counts become more positive when turning clockwise, etc.
 // actual 
 //4825 tickers per revers

  private static final int ENCODER_LOW_LIMIT = 10000;
  private static final int ENCODER_HIGH_LIMIT = 0;
  private static final double DEGREES_OF_FREEDOM = 90;
  private static final double MAX_SPEED = 0.2;
  // private static final double ANGLEATLOWLIMIT = -45;
  // private static final double ANGLEATHIGHLIMIT = 45;
  private static final double DEGREESPERTICK = DEGREES_OF_FREEDOM / ENCODER_LOW_LIMIT;

  public TurretSub() {
  }

  // positive is ccw
  public void rotate(double speed){
    // check whether either limit switch is activated
    // the turret may only move to the right if the left limit is activated
    // the turret may only move to the left if the right limit is activated
    if (RobotContainer.leftTurretLimit.get()) {
      speed = Math.min(speed, 0);
    } else if (RobotContainer.rightTurretLimit.get()) {
      speed = Math.max(speed, 0);
    }
    // clamp speed to within the allowed speed range
    // speed can't be less than the -max, or greater than the max
    if (speed > MAX_SPEED) speed = MAX_SPEED;
    else if (speed < -MAX_SPEED) speed = -MAX_SPEED;
    // set the turret motor to the speed
    RobotContainer.turret.set(speed);
  }

  public double getAngle() {
    return 0; //(RobotContainer.turret.getSensorCollection().getQuadraturePosition() - (ENCODER_HIGH_LIMIT / 2)) * DEGREESPERTICK;
  }

  @Override
  public void periodic() {
    // if (RobotContainer.touchHigh.get()) {
    //   RobotContainer.turretMotor.getSensorCollection().setQuadraturePosition(encoderHighLimit, 10);
    // }
    
    // if (RobotContainer.touchLow.get()) {
    //   RobotContainer.turretMotor.getSensorCollection().setQuadraturePosition(encoderLowLimit, 10);
    // }
    // This method will be called once per scheduler run
  }
}
