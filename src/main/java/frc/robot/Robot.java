/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();
    RobotContainer.diffDrive.setSafetyEnabled(false);
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    RobotContainer.dsL.set(Value.kForward);

    RobotContainer.intake1.enableCurrentLimit(true);
    RobotContainer.intake1.configPeakCurrentDuration(0,0);
    RobotContainer.intake1.configPeakCurrentLimit(30,0);

    RobotContainer.intake2.enableCurrentLimit(true);
    RobotContainer.intake2.configPeakCurrentDuration(0,0);
    RobotContainer.intake2.configPeakCurrentLimit(30,0);   
  
    RobotContainer.intake3.enableCurrentLimit(true);
    RobotContainer.intake3.configPeakCurrentDuration(0,0);
    RobotContainer.intake3.configPeakCurrentLimit(30,0);

    RobotContainer.r1.setInverted(true);
    RobotContainer.r2.setInverted(true);
    RobotContainer.r1.setIdleMode(IdleMode.kBrake);
    RobotContainer.r2.setIdleMode(IdleMode.kBrake);
    RobotContainer.l1.setIdleMode(IdleMode.kBrake);
    RobotContainer.l2.setIdleMode(IdleMode.kBrake);
    RobotContainer.flywheelMotor.setIdleMode(IdleMode.kBrake);
    RobotContainer.flywheelMotor2.setIdleMode(IdleMode.kBrake);
    RobotContainer.l1.setInverted(true);
    RobotContainer.l2.setInverted(true);
    final double ramprate = .25;
    final int current = 40;
    RobotContainer.l1.setSmartCurrentLimit(current);
    RobotContainer.l1.setClosedLoopRampRate(ramprate);
    RobotContainer.l1.setOpenLoopRampRate(ramprate);
    RobotContainer.l2.setSmartCurrentLimit(current);
    RobotContainer.l2.setClosedLoopRampRate(ramprate);
    RobotContainer.l2.setOpenLoopRampRate(ramprate);
    RobotContainer.r1.setSmartCurrentLimit(current);
    RobotContainer.r1.setClosedLoopRampRate(ramprate);
    RobotContainer.r1.setOpenLoopRampRate(ramprate);
    RobotContainer.r2.setSmartCurrentLimit(current);
    RobotContainer.r2.setClosedLoopRampRate(ramprate);
    RobotContainer.r2.setOpenLoopRampRate(ramprate);
    RobotContainer.flywheelMotor.setSmartCurrentLimit(current);
    RobotContainer.flywheelMotor.setClosedLoopRampRate(ramprate);
    RobotContainer.flywheelMotor.setOpenLoopRampRate(ramprate);
    RobotContainer.flywheelMotor2.setSmartCurrentLimit(current);
    RobotContainer.flywheelMotor2.setClosedLoopRampRate(ramprate);
    RobotContainer.flywheelMotor2.setOpenLoopRampRate(ramprate);

    // RobotContainer.turretMotor.overrideLimitSwitchesEnable(false);
    // RobotContainer.turretMotor.overrideSoftLimitsEnable(false);

  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
    Smartdashboarding();
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  /**
   * This autonomous runs the autonomous command selected by your {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {

    // RobotContainer.turretMotor.overrideLimitSwitchesEnable(false);
    // RobotContainer.turretMotor.overrideSoftLimitsEnable(false);

    if(RobotContainer.mback.get()){
      RobotContainer.intakePistons.set(Value.kForward);
    }

    if(RobotContainer.mstart.get()){
      RobotContainer.intakePistons.set(Value.kReverse);
    }

    if(RobotContainer.manipulator.getPOV() == 90){
      RobotContainer.CPMshift.set(Value.kForward);
    }

    if(RobotContainer.manipulator.getPOV() == 270){
      RobotContainer.CPMshift.set(Value.kReverse);
    }

    if(RobotContainer.ma.get()){
      RobotContainer.intake1.set(0.5);
    }else{
      RobotContainer.intake1.set(0);
    }

    if(RobotContainer.mb.get()){
      RobotContainer.intake2.set(0.4);
    }else{
      RobotContainer.intake2.set(0);
    }

    if(RobotContainer.mx.get()){
      RobotContainer.intake3.set(0.4);
    }else{
      RobotContainer.intake3.set(0);
    }

    // if(RobotContainer.mx.get() && RobotContainer.lift.getSensorCollection().getQuadraturePosition() > -16000){ //-14300
    //   RobotContainer.lift.set(.3);
    // }else if(RobotContainer.ma.get() && RobotContainer.lift.getSensorCollection().getQuadraturePosition() > -16000){
    //   RobotContainer.lift.set(-.3);
    // }else{
    //   RobotContainer.lift.set(0);
    // }

    // && RobotContainer.lift.getSensorCollection().getQuadraturePosition() > -16000
    // if(RobotContainer.mx.get()){ //-14300
    //   RobotContainer.lift.set(-.3);
    // }else{
    //   RobotContainer.lift.set(0);
    // }

    if(RobotContainer.mbumperLeft.get()){
      RobotContainer.lift.set(.3);
    }else if(RobotContainer.mbumperRight.get() && RobotContainer.lift.getSensorCollection().getQuadraturePosition() > -16000){
      RobotContainer.lift.set(-.3);
    }else{
      RobotContainer.lift.set(0);
    }
    
    RobotContainer.flywheelMotor.set(RobotContainer.manipulator.getRawAxis(2));
    RobotContainer.flywheelMotor2.set(RobotContainer.manipulator.getRawAxis(2));

    SmartDashboard.putNumber("raw mani 5", RobotContainer.manipulator.getRawAxis(1));
    RobotContainer.hoodMotor.set(RobotContainer.manipulator.getRawAxis(1));
    RobotContainer.turretMotor.set(RobotContainer.manipulator.getRawAxis(4));

    if(RobotContainer.dx.get()){
      RobotContainer.turretMotor.getSensorCollection().setQuadraturePosition(0, 10);
      // RobotContainer.hoodEncoder.resetAccumulator();
      RobotContainer.lift.getSensorCollection().setQuadraturePosition(0, 10);
    }

  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();

  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }

  public void Smartdashboarding(){
    // SmartDashboard.putNumber("Current Gear", RobotContainer.driveSub.getCurrentGear());

    // SmartDashboard.putString("Wheel Speeds", RobotContainer.driveSub.getWheelSpeeds().toString());
    // SmartDashboard.putNumber("l1",RobotContainer.l1.getEncoder().getPosition());
    // SmartDashboard.putNumber("l2",RobotContainer.l2.getEncoder().getPosition());
    // SmartDashboard.putNumber("r1",-RobotContainer.r1.getEncoder().getPosition());
    // SmartDashboard.putNumber("r2",-RobotContainer.r2.getEncoder().getPosition());
    // SmartDashboard.putNumber("Gyro Heading", RobotContainer.driveSub.getHeading());
    // SmartDashboard.putNumber("Raw gyro", RobotContainer.navX.getAngle());
    // SmartDashboard.putString("Pose", RobotContainer.driveSub.getPose().toString());
    // SmartDashboard.putString("Pose Rot", RobotContainer.driveSub.getPose().getRotation().toString());
    // SmartDashboard.putString("Pose Dist", RobotContainer.driveSub.getPose().getTranslation().toString());
    // SmartDashboard.putNumber("Average encoder distance", RobotContainer.driveSub.getAverageEncoderDistance());
    // if(RobotContainer.dsL.get() == Value.kForward){
      SmartDashboard.putNumber("R Dist", (-RobotContainer.r1.getEncoder().getPosition() / Constants.ticksPerRevolutionLow) * Constants.wheelCircumferenceMeters);
    //   SmartDashboard.putNumber("L Dist", (RobotContainer.l1.getEncoder().getPosition() / Constants.ticksPerRevolutionLow) * Constants.wheelCircumferenceMeters);
      SmartDashboard.putNumber("R Vel", (-RobotContainer.r1.getEncoder().getVelocity()/60 / Constants.ticksPerRevolutionLow) * Constants.wheelCircumferenceMeters);
    //   SmartDashboard.putNumber("L Vel", (RobotContainer.l1.getEncoder().getVelocity()/60 / Constants.ticksPerRevolutionLow) * Constants.wheelCircumferenceMeters);
    // }else{
    //   SmartDashboard.putNumber("R Dist", (-RobotContainer.r1.getEncoder().getPosition() / Constants.ticksPerRevolutionHigh) * Constants.wheelCircumferenceMeters);
    //   SmartDashboard.putNumber("L Dist", (RobotContainer.l1.getEncoder().getPosition() / Constants.ticksPerRevolutionHigh) * Constants.wheelCircumferenceMeters);
    //   SmartDashboard.putNumber("R Vel", (-RobotContainer.r1.getEncoder().getVelocity()/60 / Constants.ticksPerRevolutionHigh) * Constants.wheelCircumferenceMeters);
    //   SmartDashboard.putNumber("L Vel", (RobotContainer.l1.getEncoder().getVelocity()/60 / Constants.ticksPerRevolutionHigh) * Constants.wheelCircumferenceMeters);  
    // }
    SmartDashboard.putNumber("R Dist", (RobotContainer.r2.getEncoder().getPosition() / Constants.ticksPerRevolutionLow) * Constants.wheelCircumferenceMeters);
    SmartDashboard.putNumber("R Vel", (RobotContainer.r2.getEncoder().getVelocity()/60 / Constants.ticksPerRevolutionLow) * Constants.wheelCircumferenceMeters);

    // SmartDashboard.putNumber("axis 1", RobotContainer.driver.getRawAxis(1));
    // SmartDashboard.putNumber("axis 4", RobotContainer.driver.getRawAxis(4));
    // SmartDashboard.putString("Sol", RobotContainer.dsL.get().toString());
    SmartDashboard.putNumber("hood position",  RobotContainer.hoodMotor.getSensorCollection().getQuadraturePosition());
    SmartDashboard.putNumber("turret position", RobotContainer.turretMotor.getSensorCollection().getQuadraturePosition());
    SmartDashboard.putNumber("lift position", RobotContainer.lift.getSensorCollection().getQuadraturePosition());

    SmartDashboard.putNumber("mani POV", RobotContainer.manipulator.getPOV());

    SmartDashboard.putBoolean("lift and x", RobotContainer.mx.get() && RobotContainer.lift.getSensorCollection().getQuadraturePosition() > -16000);
    SmartDashboard.putBoolean("mx", RobotContainer.mx.get());
    SmartDashboard.putBoolean("ma", RobotContainer.ma.get());
    SmartDashboard.putBoolean("quad pos", RobotContainer.lift.getSensorCollection().getQuadraturePosition() > -16000);
    // SmartDashboard.putNumber("Rotation Speed of Wheel", RobotContainer.driveSub.getRotationSpeed(RobotContainer.driveSub.getCurrentGear()));
    // SmartDashboard.putNumber("RPM limit gear one", DriveSub.limitRotSpdGear1);
    // SmartDashboard.putNumber("FtPerSecondOfRobot", RobotContainer.driveSub.getRotationSpeed(RobotContainer.driveSub.getCurrentGear()) * (DriveSub.wheelDiameter * Math.PI) / 60);
    // SmartDashboard.putNumber("EncoderVelocity", DriveSub.encoder.getVelocity());
    // SmartDashboard.putBoolean("Exceeded RPM limit gear one", RobotContainer.driveSub.getRotationSpeed(RobotContainer.driveSub.getCurrentGear()) > DriveSub.limitRotSpdGear1 ? true : false);
    
    SmartDashboard.putBoolean("Turret limit 1", RobotContainer.turretMotor.getSensorCollection().isFwdLimitSwitchClosed());
    SmartDashboard.putBoolean("Turret limit 2", RobotContainer.turretMotor.getSensorCollection().isRevLimitSwitchClosed());

    SmartDashboard.putBoolean("Digital 0", RobotContainer.touch0.get());
    SmartDashboard.putBoolean("Digital 1", RobotContainer.touch1.get());
    SmartDashboard.putBoolean("Digital 2", RobotContainer.touch2.get());
    SmartDashboard.putBoolean("Digital 3", RobotContainer.touch3.get());
    SmartDashboard.putBoolean("Digital 4", RobotContainer.touch4.get());
    SmartDashboard.putBoolean("Digital 5", RobotContainer.touch5.get());
    SmartDashboard.putBoolean("Digital 6", RobotContainer.touch6.get());
    SmartDashboard.putBoolean("Digital 7", RobotContainer.touch7.get());
    SmartDashboard.putBoolean("Digital 8", RobotContainer.touch8.get());
    SmartDashboard.putBoolean("Digital 9", RobotContainer.touch9.get());

    SmartDashboard.putNumber("analog 0 voltage", RobotContainer.touchani0.getVoltage());
    SmartDashboard.putNumber("analog 1 voltage", RobotContainer.touchani1.getVoltage());
    SmartDashboard.putNumber("analog 2 voltage", RobotContainer.touchani2.getVoltage());
    SmartDashboard.putNumber("analog 3 voltage", RobotContainer.touchani3.getVoltage());

    SmartDashboard.putNumber("analog 0 a", RobotContainer.touchani0.getValue());
    SmartDashboard.putNumber("analog 1 a", RobotContainer.touchani1.getValue());
    SmartDashboard.putNumber("analog 2 a", RobotContainer.touchani2.getValue());
    SmartDashboard.putNumber("analog 3 a", RobotContainer.touchani3.getValue());

    //hood is 4 
    //position three is ball 3 is digi 1intake 2 is digi two digi three is intake one

  }

}