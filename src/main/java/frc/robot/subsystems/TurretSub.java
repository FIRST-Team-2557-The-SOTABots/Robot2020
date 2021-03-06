package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class TurretSub extends SubsystemBase {

  // assume counter clockwise is more +, that encoder counts become more positive when turning clockwise, etc.
 // actual 
 //4825 tickers per revers

  private static final int ENCODERLOWLIMIT = 10000;
  private static final int ENCODERHIGHLIMIT = 0;
  private static final double DEGREESOFFREEDOM = 90;
  private static final double ANGLEATLOWLIMIT = -45;
  private static final double ANGLEATHIGHLIMIT = 45;
  private static final double DEGREESPERTICK = DEGREESOFFREEDOM / ENCODERLOWLIMIT;

  public TurretSub() {

  }

  public void rotate(double speed){
    if (Math.abs(RobotContainer.turretMotor.getSensorCollection().getQuadraturePosition()) > ENCODERHIGHLIMIT*.95 && speed < 0 || 
       (Math.abs(RobotContainer.turretMotor.getSensorCollection().getQuadraturePosition()) < ENCODERHIGHLIMIT*.05 && speed > 0)){
      RobotContainer.turretMotor.set(0);
    }
    
    // if (RobotContainer.turretMotor.getSensorCollection().getQuadraturePosition() > encoderHighLimit-10 && speed > 0) {
    //   RobotContainer.turretMotor.set(0);
    // } else if(RobotContainer.turretMotor.getSensorCollection().getQuadraturePosition() <= encoderLowLimit+10 && speed < 0){
    //   RobotContainer.turretMotor.set(0);
    // } else {
      RobotContainer.turretMotor.set(speed);
      //right clockwise, left clockwise, negatived stick
    // }
  }

  public double getAngle() {
    return (RobotContainer.turretMotor.getSensorCollection().getQuadraturePosition() - (ENCODERHIGHLIMIT / 2)) * DEGREESPERTICK;
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
