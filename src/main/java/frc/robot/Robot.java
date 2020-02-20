/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

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

    RobotContainer.flywheelMotor.enableCurrentLimit(true);
    RobotContainer.flywheelMotor.configPeakCurrentDuration(0,0);
    RobotContainer.flywheelMotor.configPeakCurrentLimit(30,0);

    RobotContainer.flywheelMotor2.enableCurrentLimit(true);
    RobotContainer.flywheelMotor2.configPeakCurrentDuration(0,0);
    RobotContainer.flywheelMotor2.configPeakCurrentLimit(30,0);

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



    // RobotContainer.flywheelMotor.set(RobotContainer.driver.getRawAxis(1));
    // RobotContainer.flywheelMotor2.set(RobotContainer.driver.getRawAxis(1));

    RobotContainer.flywheelMotor.set(-RobotContainer.driver.getRawAxis(2));
    RobotContainer.flywheelMotor2.set(-RobotContainer.driver.getRawAxis(2));

    RobotContainer.hoodMotor.set(RobotContainer.driver.getRawAxis(5));
    RobotContainer.turretMotor.set(RobotContainer.driver.getRawAxis(4));

    if(RobotContainer.dx.get()){
      RobotContainer.turretMotor.getSensorCollection().setQuadraturePosition(0, 10);
      RobotContainer.flywheelMotor.getSensorCollection().setQuadraturePosition(0, 10);
      // RobotContainer.hoodEncoder.resetAccumulator();
      
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
    //   SmartDashboard.putNumber("R Dist", (-RobotContainer.r1.getEncoder().getPosition() / Constants.ticksPerRevolutionLow) * Constants.wheelCircumferenceMeters);
    //   SmartDashboard.putNumber("L Dist", (RobotContainer.l1.getEncoder().getPosition() / Constants.ticksPerRevolutionLow) * Constants.wheelCircumferenceMeters);
    //   SmartDashboard.putNumber("R Vel", (-RobotContainer.r1.getEncoder().getVelocity()/60 / Constants.ticksPerRevolutionLow) * Constants.wheelCircumferenceMeters);
    //   SmartDashboard.putNumber("L Vel", (RobotContainer.l1.getEncoder().getVelocity()/60 / Constants.ticksPerRevolutionLow) * Constants.wheelCircumferenceMeters);
    // }else{
    //   SmartDashboard.putNumber("R Dist", (-RobotContainer.r1.getEncoder().getPosition() / Constants.ticksPerRevolutionHigh) * Constants.wheelCircumferenceMeters);
    //   SmartDashboard.putNumber("L Dist", (RobotContainer.l1.getEncoder().getPosition() / Constants.ticksPerRevolutionHigh) * Constants.wheelCircumferenceMeters);
    //   SmartDashboard.putNumber("R Vel", (-RobotContainer.r1.getEncoder().getVelocity()/60 / Constants.ticksPerRevolutionHigh) * Constants.wheelCircumferenceMeters);
    //   SmartDashboard.putNumber("L Vel", (RobotContainer.l1.getEncoder().getVelocity()/60 / Constants.ticksPerRevolutionHigh) * Constants.wheelCircumferenceMeters);  
    // }
    // SmartDashboard.putNumber("axis 1", RobotContainer.driver.getRawAxis(1));
    // SmartDashboard.putNumber("axis 4", RobotContainer.driver.getRawAxis(4));
    // SmartDashboard.putString("Sol", RobotContainer.dsL.get().toString());
    SmartDashboard.putNumber("hood position 1", RobotContainer.hoodEncoder.getValue());
    SmartDashboard.putNumber("hood position 2", RobotContainer.hoodEncoder.getVoltage());
    SmartDashboard.putNumber("hood position 3", RobotContainer.hoodEncoder.pidGet());
    SmartDashboard.putNumber("hood position 4", RobotContainer.hoodEncoder.getAccumulatorCount());
    SmartDashboard.putNumber("turret position", RobotContainer.turretMotor.getSensorCollection().getQuadraturePosition());
    SmartDashboard.putNumber("flywheel talon 1 velocity", RobotContainer.flywheelMotor.getSensorCollection().getQuadratureVelocity());
    SmartDashboard.putNumber("flywheel talon 1 position", RobotContainer.flywheelMotor.getSensorCollection().getQuadraturePosition());
    SmartDashboard.putNumber("flywheel talon 1 rev", RobotContainer.flywheelMotor.getSensorCollection().getQuadraturePosition()/ 4825);
    SmartDashboard.putNumber("flywheel RPS", RobotContainer.flywheelMotor.getSensorCollection().getQuadratureVelocity() * 10 / 4825);
    SmartDashboard.putNumber("flywheel RPM", RobotContainer.flywheelMotor.getSensorCollection().getQuadratureVelocity() * 10 * 60 / 4825);



    // SmartDashboard.putNumber("Rotation Speed of Wheel", RobotContainer.driveSub.getRotationSpeed(RobotContainer.driveSub.getCurrentGear()));
    // SmartDashboard.putNumber("RPM limit gear one", DriveSub.limitRotSpdGear1);
    // SmartDashboard.putNumber("FtPerSecondOfRobot", RobotContainer.driveSub.getRotationSpeed(RobotContainer.driveSub.getCurrentGear()) * (DriveSub.wheelDiameter * Math.PI) / 60);
    // SmartDashboard.putNumber("EncoderVelocity", DriveSub.encoder.getVelocity());
    // SmartDashboard.putBoolean("Exceeded RPM limit gear one", RobotContainer.driveSub.getRotationSpeed(RobotContainer.driveSub.getCurrentGear()) > DriveSub.limitRotSpdGear1 ? true : false);
    
  }

}