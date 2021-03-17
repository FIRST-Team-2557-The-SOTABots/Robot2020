package frc.robot.subsystems;

import java.util.function.IntFunction;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class TurretSub extends SubsystemBase {

  // assume counter clockwise is more +, that encoder counts become more positive when turning clockwise, etc.
 // actual 
 //4825 tickers per revers

  private static final int ENCODER_HIGH_LIMIT = -9960;
  private static final double DEGREES_OF_FREEDOM = 90;
  private static final double MAX_SPEED = 0.6;

  public TurretSub() {
  }

  // positive is ccw
  public void rotate(double speed){
    // check whether either limit switch is activated
    // the turret may only move away from the limit switch if it's activated
    if (RobotContainer.leftTurretLimit.get()) speed = Math.min(speed, 0);
    else if (RobotContainer.rightTurretLimit.get()) speed = Math.max(speed, 0);
    // clamp speed to within the allowed speed range
    speed = Math.max(-MAX_SPEED, Math.min(MAX_SPEED, speed));
    // set the turret motor to the speed
    RobotContainer.turret.set(speed);
  }

  // rotate as fast as possible! still limit position and speed, but now speed limit adapts based on
  // direction and position
  public void rotateMax(double speed) {
    // check whether either limit switch is activated
    // the turret may only move away from the limit switch if it's activated
    if (RobotContainer.leftTurretLimit.get()) speed = Math.min(speed, 0);
    else if (RobotContainer.rightTurretLimit.get()) speed = Math.max(speed, 0);
    
    // plug the speed into a function that calculates the max value based on encoder value
    // function fit parameters
    double a = 1000;
    double b = 0.2;
    double c = a / (MAX_SPEED - b);
    
    IntFunction<Double> findMaxSpeed = (int encoderValue) -> a / (encoderValue + c) + b;
    
    double maxSpeed = 0;
    int encoderPos = this.getEncoderValue();
    
    if (speed > 0) {
      maxSpeed = findMaxSpeed.apply(encoderPos);
      if (speed > maxSpeed) speed = maxSpeed;
    } else {
      maxSpeed = -findMaxSpeed.apply(-ENCODER_HIGH_LIMIT - encoderPos);
      if (speed < maxSpeed) speed = maxSpeed;
    }
    
    RobotContainer.turret.set(speed);
  }

  // encoder value of turret motor
  // this flips the encoder so that ccw is positive for encoder values too
  public int getEncoderValue() {
    return -1 * RobotContainer.turret.getSensorCollection().getQuadraturePosition();
  }

  public double getAngle() {
    return 0; //(RobotContainer.turret.getSensorCollection().getQuadraturePosition() - (ENCODER_HIGH_LIMIT / 2)) * DEGREESPERTICK;
  }

  @Override
  public void periodic() {

    if (RobotContainer.rightTurretLimit.get()) RobotContainer.turret.getSensorCollection().setQuadraturePosition(0, 10);
    
    
    if (RobotContainer.leftTurretLimit.get()) RobotContainer.turret.getSensorCollection().setQuadraturePosition(ENCODER_HIGH_LIMIT, 10);
  }
}
