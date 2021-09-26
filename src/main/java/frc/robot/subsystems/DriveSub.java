package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.Constants.DriveConstants;

public class DriveSub extends SubsystemBase {

  private double gyroReversed = 1;
  public static final double LIMIT_METER_PER_SEC_GEAR1 = 1.8288; //in meters per second
  public static final double LIMIT_ROT_SPD_GEAR1 = 
    (LIMIT_METER_PER_SEC_GEAR1/Constants.WHEEL_CIRCUMFERENCE_METERS) /* ft/s / ft/rot = rot/s */ * 60; // rotations per minute
  public static final int GEAR_WHEN_FORWARD = 1;
  public static final int GEAR_WHEN_REVERSE = 2;
  public static final double RATIO_GEAR1 = 18.86;
  public static final double RATIO_GEAR2 = 6;
  public static final double E_TICKS_PER_REV = 42; //ticks of 
  // forward channel of dsl is low gear

  public double reverseDrive = 1;

  public DriveSub() {
    resetEncoders();
    zeroHeading();
  }

  public double getTurnRate(){
    return -gyroReversed*RobotContainer.navX.getRate();
  }

  public void teleDrive(){

   if(RobotContainer.dbumperLeft.get()){
      RobotContainer.diffDrive.arcadeDrive(RobotContainer.driver.getRawAxis(1) * 0.7 * reverseDrive, (RobotContainer.driver.getRawAxis(4) * 0.5));
   }else if(RobotContainer.dbumperRight.get()){
      RobotContainer.diffDrive.arcadeDrive(RobotContainer.driver.getRawAxis(1) * reverseDrive, (RobotContainer.driver.getRawAxis(4)));
   }else{
      RobotContainer.diffDrive.arcadeDrive(RobotContainer.driver.getRawAxis(1) * reverseDrive, RobotContainer.driver.getRawAxis(4) * 0.8);
    }
  }

  public void drive(double xSpeed, double rotation){
    RobotContainer.diffDrive.arcadeDrive(xSpeed, rotation);
  }

  public double getWheelVelocity() {
    if(DriveConstants.REVERSE){
      return
        (((RobotContainer.r1.getEncoder().getVelocity()/60) / Constants.TICKS_PER_REVOLUTION_LOW) * Constants.WHEEL_CIRCUMFERENCE_METERS // (RobotContainer.dsL.get() == Value.kForward ? Constants.TICKS_PER_REVOLUTION_LOW : Constants.TICKS_PER_REVOLUTION_HIGH) ) * Constants.WHEEL_CIRCUMFERENCE_METERS, 
        + ((-RobotContainer.l1.getEncoder().getVelocity()/60)/ Constants.TICKS_PER_REVOLUTION_LOW) * Constants.WHEEL_CIRCUMFERENCE_METERS)/2; //(RobotContainer.dsL.get() == Value.kForward ? Constants.TICKS_PER_REVOLUTION_LOW : Constants.TICKS_PER_REVOLUTION_HIGH)) * Constants.WHEEL_CIRCUMFERENCE_METERS);
    }
    return
      (((RobotContainer.r1.getEncoder().getVelocity()/60) / Constants.TICKS_PER_REVOLUTION_LOW) * Constants.WHEEL_CIRCUMFERENCE_METERS // (RobotContainer.dsL.get() == Value.kForward ? Constants.TICKS_PER_REVOLUTION_LOW : Constants.TICKS_PER_REVOLUTION_HIGH) ) * Constants.WHEEL_CIRCUMFERENCE_METERS, 
      + ((-RobotContainer.l1.getEncoder().getVelocity()/60)/ Constants.TICKS_PER_REVOLUTION_LOW) * Constants.WHEEL_CIRCUMFERENCE_METERS)/2; //(RobotContainer.dsL.get() == Value.kForward ? Constants.TICKS_PER_REVOLUTION_LOW : Constants.TICKS_PER_REVOLUTION_HIGH)) * Constants.WHEEL_CIRCUMFERENCE_METERS);
  }

  //robot's heading in degrees from -180 to 180
  public double getHeading(){
    return Math.IEEEremainder(gyroReversed*RobotContainer.navX.getAngle(), 360);
  }

  public void zeroHeading(){
    RobotContainer.navX.reset();
  }

  public void resetEncoders(){
    RobotContainer.l1.getEncoder().setPosition(0);
    RobotContainer.l2.getEncoder().setPosition(0);
    RobotContainer.r1.getEncoder().setPosition(0);
    RobotContainer.r2.getEncoder().setPosition(0);
  }

  @Override
  public void periodic() {
  }

  public int getCurrentGear() {
    if (RobotContainer.dsL.get() == Value.kForward) return GEAR_WHEN_FORWARD;
    else return GEAR_WHEN_REVERSE;
  }

}