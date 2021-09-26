package frc.robot.subsystems;

import java.util.function.IntFunction;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class TurretSub extends SubsystemBase {

  // assume counter clockwise is more +, that encoder counts become more positive when turning clockwise, etc.
 // actual 
 //4825 tickers per revers

  private static final int ENCODER_HIGH_LIMIT = -8471;
  private static final double MAX_SPEED = 0.6;
  private static final double END_SPEED = 0.2;

  public TurretSub() {
  }

  // positive is ccw
  public void rotate(double speed){
    // check whether either limit switch is activated
    // the turret may only move away from the limit switch if it's activated
    if (this.getLeftSwitch() || this.getEncoderValue() > -ENCODER_HIGH_LIMIT) speed = Math.min(speed, 0);
    if (this.getRightSwitch() || this.getEncoderValue() < 0) speed = Math.max(speed, 0);
    // clamp speed to within the allowed speed range
    speed = Math.max(-END_SPEED, Math.min(END_SPEED, speed));
    // set the turret motor to the speed

    SmartDashboard.putNumber("turret Speed", speed);

    if(speed > MAX_SPEED) {
      speed = 0;
      System.out.println("IT DID A BAD");
    }

    RobotContainer.turret.set(0);
    // RobotContainer.turret.set(speed/5);
  }

  // rotate as fast as possible! still limit position and speed, but now speed limit adapts based on
  // direction and position
  public void rotateMax(double speed) {
    // check whether either limit switch is activated
    // the turret may only move away from the limit switch if it's activated
    if (this.getLeftSwitch() || this.getEncoderValue() > -ENCODER_HIGH_LIMIT) speed = Math.min(speed, 0);
    if (this.getRightSwitch() || this.getEncoderValue() < 0) speed = Math.max(speed, 0);
    
    // plug the speed into a function that calculates the max value based on encoder value
    // function fit parameters
    double a = 1000;
    double b = 0.2;
    double c = a / (MAX_SPEED - b);
    
    IntFunction<Double> findMaxSpeed = (int encoderValue) -> a / (encoderValue + c) + b;
    
    double calcMaxSpeed = 0;
    int encoderPos = this.getEncoderValue();
    
    if (speed > 0) {
      calcMaxSpeed = findMaxSpeed.apply(encoderPos);
      if (speed > calcMaxSpeed) speed = calcMaxSpeed;
    } else {
      calcMaxSpeed = -findMaxSpeed.apply(-ENCODER_HIGH_LIMIT - encoderPos);
      if (speed < calcMaxSpeed) speed = calcMaxSpeed;
    }
    
    SmartDashboard.putNumber("turret Speed", speed);
    
    if(Math.abs(speed) > MAX_SPEED) {
      System.out.println("SPEED EXCEEDED ALLOWED VALUE:" + speed);
      speed = 0;
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

  // usually, left switch returns true if not pressed and false if pressed
  // reverse the value of the limit switch so that it is true when pressed
  public boolean getLeftSwitch() {
    return !RobotContainer.leftTurretLimit.get();
  }

  public boolean getRightSwitch() {
    return RobotContainer.rightTurretLimit.get();
  }

  @Override
  public void periodic() {

    if (this.getRightSwitch()) RobotContainer.turret.getSensorCollection().setQuadraturePosition(0, 10);
    
    
    if (this.getLeftSwitch()) RobotContainer.turret.getSensorCollection().setQuadraturePosition(ENCODER_HIGH_LIMIT, 10);
  }
}
