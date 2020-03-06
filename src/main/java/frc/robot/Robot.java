/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.io.IOException;

import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.PIDHood;
import frc.robot.commands.PIDTurret;
import frc.robot.commands.auto.paths.BasicTurn;
import frc.robot.commands.auto.paths.Chain;
import frc.robot.commands.auto.paths.Example;
import frc.robot.commands.auto.paths.Fiveball;
import frc.robot.commands.auto.paths.Forward;
import frc.robot.commands.auto.paths.Secondary;
import frc.robot.commands.auto.paths.Smiles;
import frc.robot.commands.auto.paths.TurnReverse;
import frc.robot.subsystems.CPMSub;
import frc.robot.subsystems.IntakeSub;
import frc.robot.subsystems.LidarSub;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  SendableChooser<Command> m_chooser;

  private RobotContainer m_robotContainer;

  public static PIDTurret pt = new PIDTurret();
  public static PIDHood ph = new PIDHood(Constants.hoodFromTrench);

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();
    configRobot();

    m_chooser = new SendableChooser<>();
    m_chooser.addOption("forward", new Forward(1));
    m_chooser.addOption("reverse turn", new TurnReverse());
    m_chooser.addOption("basic turning", new BasicTurn());
    try {
      m_chooser.addOption("example", new Example());
      m_chooser.addOption("chain", new Chain());
      m_chooser.addOption("smiles", new Smiles());
      // m_chooser.addOption("eightball", new Eightball());
      m_chooser.addOption("secondary", new Secondary());
      // m_chooser.addOption("fiveball1", new fiveball1());
      // m_chooser.addOption("fiveball2", new fiveball2());
      m_chooser.addOption("fiveball", new Fiveball());
    } catch (final IOException e1) {
      e1.printStackTrace();
    }
    SmartDashboard.putData("Auto choochooer", m_chooser);

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
    m_autonomousCommand = m_chooser.getSelected();
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

    shooter();

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

    if(RobotContainer.dx.get()){
      resetTheSpaghet();
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
    SmartDashboard.putNumber("winch position 1", RobotContainer.winch1.getSensorCollection().getQuadraturePosition());
    SmartDashboard.putNumber("winch position 2", RobotContainer.winch1.getSensorCollection().getQuadraturePosition());

    SmartDashboard.putNumber("mani POV", RobotContainer.manipulator.getPOV());

    SmartDashboard.putNumber("LiDAR dist", RobotContainer.lidarSub.getDistance());
    // SmartDashboard.putString("Color L", RobotContainer.cpmSub.getColorL());

    SmartDashboard.putBoolean("Climb lock", RobotContainer.climbLock.get() == Value.kReverse);
    SmartDashboard.putBoolean("Winch pull", RobotContainer.winchShift.get() == Value.kReverse);

    SmartDashboard.putBoolean("quad pos", RobotContainer.lift.getSensorCollection().getQuadraturePosition() > -14000);
    // SmartDashboard.putNumber("Rotation Speed of Wheel", RobotContainer.driveSub.getRotationSpeed(RobotContainer.driveSub.getCurrentGear()));
    // SmartDashboard.putNumber("RPM limit gear one", DriveSub.limitRotSpdGear1);
    // SmartDashboard.putNumber("FtPerSecondOfRobot", RobotContainer.driveSub.getRotationSpeed(RobotContainer.driveSub.getCurrentGear()) * (DriveSub.wheelDiameter * Math.PI) / 60);
    // SmartDashboard.putNumber("EncoderVelocity", DriveSub.encoder.getVelocity());
    // SmartDashboard.putBoolean("Exceeded RPM limit gear one", RobotContainer.driveSub.getRotationSpeed(RobotContainer.driveSub.getCurrentGear()) > DriveSub.limitRotSpdGear1 ? true : false);
    
    SmartDashboard.putNumber("RPM of flywheel", RobotContainer.flywheelSub.getFlywheelSpeed());

    SmartDashboard.putBoolean("Turret limit 1", RobotContainer.turretMotor.getSensorCollection().isFwdLimitSwitchClosed());
    SmartDashboard.putBoolean("Turret limit 2", RobotContainer.turretMotor.getSensorCollection().isRevLimitSwitchClosed());

    SmartDashboard.putBoolean("Intake 1 touch", RobotContainer.touchOne.get());
    SmartDashboard.putBoolean("Intake 2 touch", RobotContainer.touchTwo.get());
    SmartDashboard.putBoolean("Intake 3 touch", RobotContainer.touchThree.get());
    SmartDashboard.putBoolean("Intake cycling ball", IntakeSub.cyclingBall);
  
    //hood is 4 
    //position three is ball 3 is digi 1intake 2 is digi two digi three is intake one
  }

  public void configRobot(){
    RobotContainer.diffDrive.setSafetyEnabled(false);

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
    RobotContainer.flywheelMotor.setIdleMode(IdleMode.kCoast);
    RobotContainer.flywheelMotor2.setIdleMode(IdleMode.kCoast);
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
    RobotContainer.flywheelMotor.setClosedLoopRampRate(ramprate+.75);
    RobotContainer.flywheelMotor.setOpenLoopRampRate(ramprate+.75);
    RobotContainer.flywheelMotor2.setSmartCurrentLimit(current);
    RobotContainer.flywheelMotor2.setClosedLoopRampRate(ramprate+.75);
    RobotContainer.flywheelMotor2.setOpenLoopRampRate(ramprate+.75);

    RobotContainer.intakePistons.set(Value.kForward);

    // RobotContainer.turretMotor.overrideLimitSwitchesEnable(false);
    // RobotContainer.turretMotor.overrideSoftLimitsEnable(false);

  }

  public void resetTheSpaghet(){
    RobotContainer.turretMotor.getSensorCollection().setQuadraturePosition(0, 10);
    RobotContainer.hoodMotor.getSensorCollection().setQuadraturePosition(0, 10);
     // RobotContainer.hoodEncoder.resetAccumulator();
    RobotContainer.lift.getSensorCollection().setQuadraturePosition(0, 10);
    RobotContainer.winch2.getSensorCollection().setQuadraturePosition(0, 10);
    RobotContainer.l1.getEncoder().setPosition(0);
    RobotContainer.l2.getEncoder().setPosition(0);
    RobotContainer.r1.getEncoder().setPosition(0);
    RobotContainer.r2.getEncoder().setPosition(0);
    RobotContainer.flywheelMotor.getEncoder().setPosition(0);
    RobotContainer.flywheelMotor2.getEncoder().setPosition(0);
    RobotContainer.navX.reset();
  }
  
  public void shooter(){
    if(RobotContainer.manipulator.getPOV() == 90 || RobotContainer.manipulator.getPOV() == 180 || RobotContainer.manipulator.getPOV() == 0){
      // pt.schedule();
      ph.schedule();
    }
  }

}