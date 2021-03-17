package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpiutil.math.MathUtil;
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

  public double reverseDrive = 1;

  public DriveSub() {
    resetEncoders();
    zeroHeading();
  }

  public double getAverageEncoderDistance(){
    // if(RobotContainer.dsL.get() == Value.kForward){
    //   return ((RobotContainer.l1.getEncoder().getPosition()/ Constants.TICKS_PER_REVOLUTION_LOW) * Constants.WHEEL_CIRCUMFERENCE_METERS
    //   + (-RobotContainer.r2.getEncoder().getPosition() / Constants.TICKS_PER_REVOLUTION_LOW) * Constants.WHEEL_CIRCUMFERENCE_METERS) / 2.0;
    // }else{
    //   return ((RobotContainer.l1.getEncoder().getPosition()/ Constants.TICKS_PER_REVOLUTION_HIGH) * Constants.WHEEL_CIRCUMFERENCE_METERS
    //   + (-RobotContainer.r2.getEncoder().getPosition() / Constants.TICKS_PER_REVOLUTION_HIGH) * Constants.WHEEL_CIRCUMFERENCE_METERS) / 2.0;
    // }
    if(RobotContainer.dsL.get() == Value.kReverse){
      return ((RobotContainer.l1.getEncoder().getPosition()/ Constants.TICKS_PER_REVOLUTION_LOW) * Constants.WHEEL_CIRCUMFERENCE_METERS
      + (-RobotContainer.r2.getEncoder().getPosition() / Constants.TICKS_PER_REVOLUTION_LOW) * Constants.WHEEL_CIRCUMFERENCE_METERS) / 2.0;
    }else{
      return ((RobotContainer.l1.getEncoder().getPosition()/ Constants.TICKS_PER_REVOLUTION_LOW) * Constants.WHEEL_CIRCUMFERENCE_METERS
      + (-RobotContainer.r2.getEncoder().getPosition() / Constants.TICKS_PER_REVOLUTION_LOW) * Constants.WHEEL_CIRCUMFERENCE_METERS) / 2.0;
    }
  }

  public double getTurnRate(){
    return -gyroReversed*RobotContainer.navX.getRate();
  }

  public void teleDrive(){

    // if(RobotContainer.dback.get()){//high
    //   RobotContainer.dsL.set(Value.kForward);
    // }else if(RobotContainer.dstart.get()){//low
    //   RobotContainer.dsL.set(Value.kReverse);
    // }
    if((RobotContainer.driver.getRawAxis(2) > 0.5) && (RobotContainer.driver.getRawAxis(3) > 0.5)){
      reverseDrive = -1;
    }else{
      reverseDrive = 1;
    }

   if(RobotContainer.dbumperLeft.get()){
    // RobotContainer.diffDrive.arcadeDrive(RobotContainer.driver.getRawAxis(1) * 0.5 * reverseDrive, (RobotContainer.driver.getRawAxis(4) * 0.5));
    RobotContainer.diffDrive.tankDrive(RobotContainer.driver.getRawAxis(5) * 0.5, (RobotContainer.driver.getRawAxis(1) * 0.5));
  }else if(RobotContainer.dbumperRight.get()){
      RobotContainer.diffDrive.arcadeDrive(RobotContainer.driver.getRawAxis(1) * reverseDrive, (RobotContainer.driver.getRawAxis(4)));
  }else{
    // RobotContainer.diffDrive.arcadeDrive(RobotContainer.driver.getRawAxis(1) * reverseDrive, RobotContainer.driver.getRawAxis(4) * 0.8);
    RobotContainer.diffDrive.tankDrive(RobotContainer.driver.getRawAxis(5), RobotContainer.driver.getRawAxis(1));
  }
  // if(RobotContainer.dbumperLeft.get()){
  //   RobotContainer.diffDrive.tankDrive(RobotContainer.driver.getRawAxis(5) * 0.5 * reverseDrive, (RobotContainer.driver.getRawAxis(1) * 0.5));
  // }else if(RobotContainer.dbumperRight.get()){
  //   RobotContainer.diffDrive.tankDrive(RobotContainer.driver.getRawAxis(5) * reverseDrive, (RobotContainer.driver.getRawAxis(1)));
  // }else{
  //   RobotContainer.diffDrive.tankDrive(RobotContainer.driver.getRawAxis(5) * reverseDrive, RobotContainer.driver.getRawAxis(1) * 0.8);
  // }
  }

  public void drive(double xSpeed, double rotation){
    RobotContainer.diffDrive.arcadeDrive(xSpeed, rotation);
  }

  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    // if(DriveConstants.reverse){
    //   return new DifferentialDriveWheelSpeeds(
    //     ((RobotContainer.r1.getEncoder().getVelocity()/60) / Constants.TICKS_PER_REVOLUTION_LOW) * Constants.WHEEL_CIRCUMFERENCE_METERS, // (RobotContainer.dsL.get() == Value.kForward ? Constants.TICKS_PER_REVOLUTION_LOW : Constants.TICKS_PER_REVOLUTION_HIGH) ) * Constants.WHEEL_CIRCUMFERENCE_METERS, 
    //     ((-RobotContainer.l1.getEncoder().getVelocity()/60)/ Constants.TICKS_PER_REVOLUTION_LOW) * Constants.WHEEL_CIRCUMFERENCE_METERS); //(RobotContainer.dsL.get() == Value.kForward ? Constants.TICKS_PER_REVOLUTION_LOW : Constants.TICKS_PER_REVOLUTION_HIGH)) * Constants.WHEEL_CIRCUMFERENCE_METERS);
    // }
    // return new DifferentialDriveWheelSpeeds(
    //   ((RobotContainer.l1.getEncoder().getVelocity()/60) / Constants.TICKS_PER_REVOLUTION_HIGH) * Constants.WHEEL_CIRCUMFERENCE_METERS, // (RobotContainer.dsL.get() == Value.kForward ? Constants.TICKS_PER_REVOLUTION_LOW : Constants.TICKS_PER_REVOLUTION_HIGH) ) * Constants.WHEEL_CIRCUMFERENCE_METERS, 
    //   ((-RobotContainer.r1.getEncoder().getVelocity()/60)/ Constants.TICKS_PER_REVOLUTION_HIGH) * Constants.WHEEL_CIRCUMFERENCE_METERS); //(RobotContainer.dsL.get() == Value.kForward ? Constants.TICKS_PER_REVOLUTION_LOW : Constants.TICKS_PER_REVOLUTION_HIGH)) * Constants.WHEEL_CIRCUMFERENCE_METERS);
    if(DriveConstants.reverse){
      return new DifferentialDriveWheelSpeeds(
        ((RobotContainer.r1.getEncoder().getVelocity()/60) / Constants.TICKS_PER_REVOLUTION_LOW) * Constants.WHEEL_CIRCUMFERENCE_METERS, // (RobotContainer.dsL.get() == Value.kForward ? Constants.TICKS_PER_REVOLUTION_LOW : Constants.TICKS_PER_REVOLUTION_HIGH) ) * Constants.WHEEL_CIRCUMFERENCE_METERS, 
        ((-RobotContainer.l1.getEncoder().getVelocity()/60)/ Constants.TICKS_PER_REVOLUTION_LOW) * Constants.WHEEL_CIRCUMFERENCE_METERS); //(RobotContainer.dsL.get() == Value.kForward ? Constants.TICKS_PER_REVOLUTION_LOW : Constants.TICKS_PER_REVOLUTION_HIGH)) * Constants.WHEEL_CIRCUMFERENCE_METERS);
    }
    return new DifferentialDriveWheelSpeeds(
      ((RobotContainer.l1.getEncoder().getVelocity()/60) / Constants.TICKS_PER_REVOLUTION_LOW) * Constants.WHEEL_CIRCUMFERENCE_METERS, // (RobotContainer.dsL.get() == Value.kForward ? Constants.TICKS_PER_REVOLUTION_LOW : Constants.TICKS_PER_REVOLUTION_HIGH) ) * Constants.WHEEL_CIRCUMFERENCE_METERS, 
      ((-RobotContainer.r1.getEncoder().getVelocity()/60)/ Constants.TICKS_PER_REVOLUTION_LOW) * Constants.WHEEL_CIRCUMFERENCE_METERS); //(RobotContainer.dsL.get() == Value.kForward ? Constants.TICKS_PER_REVOLUTION_LOW : Constants.TICKS_PER_REVOLUTION_HIGH)) * Constants.WHEEL_CIRCUMFERENCE_METERS);
  }

  public double getWheelVelocity() {
    if(DriveConstants.reverse){
      return
        (((RobotContainer.r1.getEncoder().getVelocity()/60) / Constants.TICKS_PER_REVOLUTION_LOW) * Constants.WHEEL_CIRCUMFERENCE_METERS // (RobotContainer.dsL.get() == Value.kForward ? Constants.TICKS_PER_REVOLUTION_LOW : Constants.TICKS_PER_REVOLUTION_HIGH) ) * Constants.WHEEL_CIRCUMFERENCE_METERS, 
        + ((-RobotContainer.l1.getEncoder().getVelocity()/60)/ Constants.TICKS_PER_REVOLUTION_LOW) * Constants.WHEEL_CIRCUMFERENCE_METERS)/2; //(RobotContainer.dsL.get() == Value.kForward ? Constants.TICKS_PER_REVOLUTION_LOW : Constants.TICKS_PER_REVOLUTION_HIGH)) * Constants.WHEEL_CIRCUMFERENCE_METERS);
    }
    return
      (((RobotContainer.r1.getEncoder().getVelocity()/60) / Constants.TICKS_PER_REVOLUTION_LOW) * Constants.WHEEL_CIRCUMFERENCE_METERS // (RobotContainer.dsL.get() == Value.kForward ? Constants.TICKS_PER_REVOLUTION_LOW : Constants.TICKS_PER_REVOLUTION_HIGH) ) * Constants.WHEEL_CIRCUMFERENCE_METERS, 
      + ((-RobotContainer.l1.getEncoder().getVelocity()/60)/ Constants.TICKS_PER_REVOLUTION_LOW) * Constants.WHEEL_CIRCUMFERENCE_METERS)/2; //(RobotContainer.dsL.get() == Value.kForward ? Constants.TICKS_PER_REVOLUTION_LOW : Constants.TICKS_PER_REVOLUTION_HIGH)) * Constants.WHEEL_CIRCUMFERENCE_METERS);
    // if(DriveConstants.reverse){
    //   return
    //     (((-RobotContainer.r1.getEncoder().getVelocity()/60) / Constants.TICKS_PER_REVOLUTION_LOW) * Constants.WHEEL_CIRCUMFERENCE_METERS // (RobotContainer.dsL.get() == Value.kForward ? Constants.TICKS_PER_REVOLUTION_LOW : Constants.TICKS_PER_REVOLUTION_HIGH) ) * Constants.WHEEL_CIRCUMFERENCE_METERS, 
    //     + ((-RobotContainer.l1.getEncoder().getVelocity()/60)/ Constants.TICKS_PER_REVOLUTION_LOW) * Constants.WHEEL_CIRCUMFERENCE_METERS)/2; //(RobotContainer.dsL.get() == Value.kForward ? Constants.TICKS_PER_REVOLUTION_LOW : Constants.TICKS_PER_REVOLUTION_HIGH)) * Constants.WHEEL_CIRCUMFERENCE_METERS);
    // }
    // return
    //   (((-RobotContainer.r1.getEncoder().getVelocity()/60) / Constants.TICKS_PER_REVOLUTION_HIGH) * Constants.WHEEL_CIRCUMFERENCE_METERS // (RobotContainer.dsL.get() == Value.kForward ? Constants.TICKS_PER_REVOLUTION_LOW : Constants.TICKS_PER_REVOLUTION_HIGH) ) * Constants.WHEEL_CIRCUMFERENCE_METERS, 
    //   + ((-RobotContainer.l1.getEncoder().getVelocity()/60)/ Constants.TICKS_PER_REVOLUTION_HIGH) * Constants.WHEEL_CIRCUMFERENCE_METERS)/2; //(RobotContainer.dsL.get() == Value.kForward ? Constants.TICKS_PER_REVOLUTION_LOW : Constants.TICKS_PER_REVOLUTION_HIGH)) * Constants.WHEEL_CIRCUMFERENCE_METERS);

  }

  public double getRotationSpeed(){
    if(RobotContainer.dsL.get() == Value.kReverse){
      return .5*Math.PI*(Math.abs(RobotContainer.r1.getEncoder().getVelocity()/RATIO_GEAR2)+Math.abs(RobotContainer.l1.getEncoder().getVelocity()/RATIO_GEAR2))/2/60;
    }return .5*Math.PI*(Math.abs(RobotContainer.r1.getEncoder().getVelocity()/RATIO_GEAR1)+Math.abs(RobotContainer.l1.getEncoder().getVelocity()/RATIO_GEAR1))/2/60;
  }

  double maxVolt = 12;
  public void tankDriveVolts(double leftVolts,double rightVolts) {
    if(DriveConstants.reverse){
      RobotContainer.left.setVoltage(MathUtil.clamp(rightVolts, -maxVolt, maxVolt));
      RobotContainer.right.setVoltage(MathUtil.clamp(-leftVolts, -maxVolt, maxVolt));
    }else{
      RobotContainer.left.setVoltage(MathUtil.clamp(rightVolts, -maxVolt, maxVolt));
      RobotContainer.right.setVoltage(MathUtil.clamp(-leftVolts, -maxVolt, maxVolt));
    }
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

  // public void shift() {
  //   if (RobotContainer.dsL.get() == Value.kForward) {
  //     RobotContainer.dsL.set(Value.kReverse);
  //   } else if(RobotContainer.dsL.get() == Value.kReverse) {
  //     RobotContainer.dsL.set(Value.kForward);
  //   }
  // }

  public int getCurrentGear() {
    if (RobotContainer.dsL.get() == Value.kForward) return GEAR_WHEN_FORWARD;
    else return GEAR_WHEN_REVERSE;
  }

  // returns rotation speed of wheel in rotations per minute
  // public double getRotationSpeed(double currentGear) {
  //   if (currentGear == 1) {
  //     return encoder.getVelocity() / (RATIO_GEAR1);
  //   } else{
  //     return encoder.getVelocity() / (RATIO_GEAR2) ;
  //   }

  // }

}