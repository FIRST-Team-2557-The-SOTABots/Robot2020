
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.Constants.DriveConstants;

public class DriveSub extends SubsystemBase {

  DifferentialDriveOdometry m_odometry;
  private double gyroReversed = 1;
  public static final double limitMeterPerSecGear1 = 1.8288; //in meters per second
  public static final double limitRotSpdGear1 = 
    (limitMeterPerSecGear1/Constants.wheelCircumferenceMeters) /* ft/s / ft/rot = rot/s */ * 60; // rotations per minute
  public static final int gearWhenForward = 1;
  public static final int gearWhenReverse = 2;
  public static final double ratioGear1 = 18.86;
  public static final double ratioGear2 = 6;
  public static final double eTicksPerRev = 42; //ticks of 

  public static final double wheelDiameter = 0.1524; //in meters

  public DriveSub() {
    m_odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(getHeading()));

    resetEncoders();
    zeroHeading();
    resetOdometry(new Pose2d());
  }

  public double getAverageEncoderDistance(){
    if(RobotContainer.dsL.get() == Value.kForward){
      return ((RobotContainer.l1.getEncoder().getPosition()/ Constants.ticksPerRevolutionLow) * Constants.wheelCircumferenceMeters
      + (-RobotContainer.r2.getEncoder().getPosition() / Constants.ticksPerRevolutionLow) * Constants.wheelCircumferenceMeters) / 2.0;
    }else{
      return ((RobotContainer.l1.getEncoder().getPosition()/ Constants.ticksPerRevolutionHigh) * Constants.wheelCircumferenceMeters
      + (-RobotContainer.r2.getEncoder().getPosition() / Constants.ticksPerRevolutionHigh) * Constants.wheelCircumferenceMeters) / 2.0;
    }
  }

  public double getTurnRate(){
    return gyroReversed*RobotContainer.navX.getRate();
  }

  public void teleDrive(){

    if(RobotContainer.dback.get()){//high
      RobotContainer.dsL.set(Value.kForward);
    }else if(RobotContainer.dstart.get()){//low
      RobotContainer.dsL.set(Value.kReverse);
    }

   if(RobotContainer.dbumperLeft.get()){
      RobotContainer.diffDrive.arcadeDrive(RobotContainer.driver.getRawAxis(1) * 0.5, (-RobotContainer.driver.getRawAxis(4) * 0.5));
    } else {
      RobotContainer.diffDrive.arcadeDrive(RobotContainer.driver.getRawAxis(1), -RobotContainer.driver.getRawAxis(4) * 0.8);
    }

}

  public void drive(double xSpeed, double rotation){
    RobotContainer.diffDrive.arcadeDrive(xSpeed, rotation);
  }

  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    if(DriveConstants.reverse){
      return new DifferentialDriveWheelSpeeds(
        ((RobotContainer.r1.getEncoder().getVelocity()/60) / Constants.ticksPerRevolutionLow) * Constants.wheelCircumferenceMeters, // (RobotContainer.dsL.get() == Value.kForward ? Constants.ticksPerRevolutionLow : Constants.ticksPerRevolutionHigh) ) * Constants.wheelCircumferenceMeters, 
        ((-RobotContainer.l1.getEncoder().getVelocity()/60)/ Constants.ticksPerRevolutionLow) * Constants.wheelCircumferenceMeters); //(RobotContainer.dsL.get() == Value.kForward ? Constants.ticksPerRevolutionLow : Constants.ticksPerRevolutionHigh)) * Constants.wheelCircumferenceMeters);
    }
    return new DifferentialDriveWheelSpeeds(
      ((RobotContainer.l1.getEncoder().getVelocity()/60) / Constants.ticksPerRevolutionLow) * Constants.wheelCircumferenceMeters, // (RobotContainer.dsL.get() == Value.kForward ? Constants.ticksPerRevolutionLow : Constants.ticksPerRevolutionHigh) ) * Constants.wheelCircumferenceMeters, 
      ((-RobotContainer.r1.getEncoder().getVelocity()/60)/ Constants.ticksPerRevolutionLow) * Constants.wheelCircumferenceMeters); //(RobotContainer.dsL.get() == Value.kForward ? Constants.ticksPerRevolutionLow : Constants.ticksPerRevolutionHigh)) * Constants.wheelCircumferenceMeters);
  }

  public double getWheelVelocity() {
    if(DriveConstants.reverse){
      return
        (((RobotContainer.r1.getEncoder().getVelocity()/60) / Constants.ticksPerRevolutionLow) * Constants.wheelCircumferenceMeters // (RobotContainer.dsL.get() == Value.kForward ? Constants.ticksPerRevolutionLow : Constants.ticksPerRevolutionHigh) ) * Constants.wheelCircumferenceMeters, 
        + ((-RobotContainer.l1.getEncoder().getVelocity()/60)/ Constants.ticksPerRevolutionLow) * Constants.wheelCircumferenceMeters)/2; //(RobotContainer.dsL.get() == Value.kForward ? Constants.ticksPerRevolutionLow : Constants.ticksPerRevolutionHigh)) * Constants.wheelCircumferenceMeters);
    }
      return
        (((RobotContainer.r1.getEncoder().getVelocity()/60) / Constants.ticksPerRevolutionHigh) * Constants.wheelCircumferenceMeters // (RobotContainer.dsL.get() == Value.kForward ? Constants.ticksPerRevolutionLow : Constants.ticksPerRevolutionHigh) ) * Constants.wheelCircumferenceMeters, 
        + ((-RobotContainer.l1.getEncoder().getVelocity()/60)/ Constants.ticksPerRevolutionHigh) * Constants.wheelCircumferenceMeters)/2; //(RobotContainer.dsL.get() == Value.kForward ? Constants.ticksPerRevolutionLow : Constants.ticksPerRevolutionHigh)) * Constants.wheelCircumferenceMeters);
    }

  double maxVolt = 12;
  public void tankDriveVolts(double leftVolts,double rightVolts) {
    if(DriveConstants.reverse){
      RobotContainer.left.setVoltage(MathUtil.clamp(-rightVolts, -maxVolt, maxVolt));
      RobotContainer.right.setVoltage(MathUtil.clamp(leftVolts, -maxVolt, maxVolt));
    }else{
      RobotContainer.left.setVoltage(MathUtil.clamp(rightVolts, -maxVolt, maxVolt));
      RobotContainer.right.setVoltage(MathUtil.clamp(-leftVolts, -maxVolt, maxVolt));
    }
  }

  public Pose2d getPose(){
    return m_odometry.getPoseMeters();
  }

  public void resetOdometry(Pose2d pose){
    m_odometry.resetPosition(pose, getAngle());
  }


  public Rotation2d getAngle(){
    return Rotation2d.fromDegrees(gyroReversed*RobotContainer.navX.getAngle());
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
    if(DriveConstants.reverse){
      if(RobotContainer.dsL.get() == Value.kForward){
        m_odometry.update(Rotation2d.fromDegrees(-getHeading()), 
        (RobotContainer.r1.getEncoder().getPosition()/ Constants.ticksPerRevolutionLow) * Constants.wheelCircumferenceMeters, //encoder * rev/ticks = rev * circ = dist
        (-RobotContainer.l1.getEncoder().getPosition() / Constants.ticksPerRevolutionLow) * Constants.wheelCircumferenceMeters);
      }else{
        m_odometry.update(Rotation2d.fromDegrees(getHeading()), 
        (RobotContainer.r1.getEncoder().getPosition()/ Constants.ticksPerRevolutionHigh) * Constants.wheelCircumferenceMeters, 
        (-RobotContainer.l1.getEncoder().getPosition() / Constants.ticksPerRevolutionHigh) * Constants.wheelCircumferenceMeters);
      }
    }else{
      if(RobotContainer.dsL.get() == Value.kForward){
        m_odometry.update(Rotation2d.fromDegrees(getHeading()), 
        (RobotContainer.l1.getEncoder().getPosition()/ Constants.ticksPerRevolutionLow) * Constants.wheelCircumferenceMeters, //encoder * rev/ticks = rev * circ = dist
        (-RobotContainer.r1.getEncoder().getPosition() / Constants.ticksPerRevolutionLow) * Constants.wheelCircumferenceMeters);
      }else{
        m_odometry.update(Rotation2d.fromDegrees(getHeading()), 
        (RobotContainer.l1.getEncoder().getPosition()/ Constants.ticksPerRevolutionHigh) * Constants.wheelCircumferenceMeters, 
        (-RobotContainer.r1.getEncoder().getPosition() / Constants.ticksPerRevolutionHigh) * Constants.wheelCircumferenceMeters);
      }
    }
  }

  public void shift() {
    if (RobotContainer.dsL.get() == Value.kForward) {
      RobotContainer.dsL.set(Value.kReverse);
    } else if(RobotContainer.dsL.get() == Value.kReverse) {
      RobotContainer.dsL.set(Value.kForward);
    }
  }

  public int getCurrentGear() {
    if (RobotContainer.dsL.get() == Value.kForward) {
      return gearWhenForward;
    } else {
      return gearWhenReverse;
    }
  }

  // returns rotation speed of wheel in rotations per minute
  // public double getRotationSpeed(double currentGear) {
  //   if (currentGear == 1) {
  //     return encoder.getVelocity() / (ratioGear1);
  //   } else{
  //     return encoder.getVelocity() / (ratioGear2) ;
  //   }

  // }

}