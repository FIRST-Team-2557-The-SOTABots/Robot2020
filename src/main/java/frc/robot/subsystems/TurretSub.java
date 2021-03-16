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
  // private static final double ANGLEATLOWLIMIT = -45;
  // private static final double ANGLEATHIGHLIMIT = 45;

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

  // rotate as fast as possible! still limit position and speed, but now speed limit adapts based on
  // direction and position
  public void rotateAFAP(double speed) {
    // check whether either limit switch is activated
    // the turret may only move to the right if the left limit is activated
    // the turret may only move to the left if the right limit is activated
    if (RobotContainer.leftTurretLimit.get()) {
      speed = Math.min(speed, 0);
    } else if (RobotContainer.rightTurretLimit.get()) {
      speed = Math.max(speed, 0);
    }
    // plug the speed into a function that calculates the max value based on encoder value
    // function fit parameters
    double a = 1000;
    double b = 0.2;
    double c = a / (MAX_SPEED - b);
    IntFunction<Double> maxSpeedFunction = (int encoderValue) -> {
      return a / (encoderValue + c) + b;
    };
    double calculatedMaxSpeed = 0;
    int currentEncoderPosition = this.getEncoderValue();
    if (speed > 0) {
      calculatedMaxSpeed = maxSpeedFunction.apply(currentEncoderPosition);
      if (speed > calculatedMaxSpeed) speed = calculatedMaxSpeed;
    } else {
      calculatedMaxSpeed = -maxSpeedFunction.apply(-ENCODER_HIGH_LIMIT - currentEncoderPosition);
      if (speed < calculatedMaxSpeed) speed = calculatedMaxSpeed;
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

    if (RobotContainer.rightTurretLimit.get()) {
      RobotContainer.turret.getSensorCollection().setQuadraturePosition(0, 10);
    }
    
    if (RobotContainer.leftTurretLimit.get()) {

      RobotContainer.turret.getSensorCollection().setQuadraturePosition(ENCODER_HIGH_LIMIT, 10);
    }
    // This method will be called once per scheduler run
  }
}
