package frc.robot;

import java.io.IOException;
import com.revrobotics.CANSparkMax.IdleMode;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.HoodCommand;
import frc.robot.commands.PIDFlywheel;
import frc.robot.commands.PIDHood;
import frc.robot.commands.PIDTurret;
import frc.robot.commands.TurretCommand;
import frc.robot.commands.auto.BasicAuto;
import frc.robot.commands.auto.paths.BasicTurn;
import frc.robot.commands.auto.paths.Chain;
import frc.robot.commands.auto.paths.Example;
import frc.robot.commands.auto.paths.Fiveball;
import frc.robot.commands.auto.paths.Move;
import frc.robot.commands.auto.paths.Secondary;
import frc.robot.commands.auto.paths.Smiles;
import frc.robot.commands.auto.paths.TurnReverse;
import frc.robot.subsystems.CPMSub;
import frc.robot.subsystems.IntakeSub;
import frc.robot.subsystems.LidarSub;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  SendableChooser<Command> m_chooser;

  private RobotContainer m_robotContainer;

  public static PIDTurret pt = new PIDTurret();
  public static TurretCommand tc = new TurretCommand();
  public static PIDHood ph = new PIDHood(Constants.hoodFromTrench);
  public static HoodCommand hc = new HoodCommand();

  public static boolean auto;

  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();
    configRobot();

    m_chooser = new SendableChooser<>();
    m_chooser.addOption("forward", new Move(1, false));
    m_chooser.addOption("reverse", new Move(1, true));
    m_chooser.addOption("reverse turn", new TurnReverse());
    m_chooser.addOption("basic turning", new BasicTurn());
    try {
      m_chooser.addOption("basic auto", new BasicAuto());
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

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
    Smartdashboarding();
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();
    auto = true;

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    auto = false;
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {

    // System.out.println(getRequiredRPM(20));

    // RobotContainer.turretMotor.overrideLimitSwitchesEnable(false);
    // RobotContainer.turretMotor.overrideSoftLimitsEnable(false);

    shooter();
    // RobotContainer.turretMotor.set(RobotContainer.manipulator.getRawAxis(4));
    
    if(RobotContainer.mbumperLeft.get()){
      RobotContainer.CPMshift.set(Value.kForward);
    }

    if(RobotContainer.mbumperRight.get()){
      RobotContainer.CPMshift.set(Value.kReverse);
    }

    if(RobotContainer.mback.get()){
      RobotContainer.intakePistons.set(Value.kForward);
    }

    if(RobotContainer.mstart.get()){
      RobotContainer.intakePistons.set(Value.kReverse);
    }

    if(RobotContainer.mterribleRight.get()){
      RobotContainer.CPMshift.set(Value.kForward);
    }

    if(RobotContainer.mterribleLeft.get()){
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

  @Override
  public void testPeriodic() {
  }

  public static double getRequiredRPM(double vel){
    return (120 * vel) / (2 * Math.PI * RobotContainer.flywheelSub.flywheelRadius);
  }

  public void Smartdashboarding(){
    // SmartDashboard.putNumber("Current Gear", RobotContainer.driveSub.getCurrentGear());

    SmartDashboard.putString("Wheel Speeds", RobotContainer.driveSub.getWheelSpeeds().toString());
    SmartDashboard.putNumber("l1",RobotContainer.l1.getEncoder().getPosition());
    // SmartDashboard.putNumber("l2",RobotContainer.l2.getEncoder().getPosition());
    SmartDashboard.putNumber("r1",-RobotContainer.r1.getEncoder().getPosition());
    // SmartDashboard.putNumber("r2",-RobotContainer.r2.getEncoder().getPosition());
    SmartDashboard.putNumber("Gyro Heading", RobotContainer.driveSub.getHeading());
    SmartDashboard.putNumber("Raw gyro", RobotContainer.navX.getAngle());
    SmartDashboard.putString("Pose", RobotContainer.driveSub.getPose().toString());
    // SmartDashboard.putString("Pose Rot", RobotContainer.driveSub.getPose().getRotation().toString());
    // SmartDashboard.putString("Pose Dist", RobotContainer.driveSub.getPose().getTranslation().toString());
    // SmartDashboard.putNumber("Average encoder distance", RobotContainer.driveSub.getAverageEncoderDistance());
    // if(RobotContainer.dsL.get() == Value.kForward){
      SmartDashboard.putNumber("R Dist", (-RobotContainer.r1.getEncoder().getPosition() / Constants.ticksPerRevolutionLow) * Constants.wheelCircumferenceMeters);
      SmartDashboard.putNumber("L Dist", (RobotContainer.l1.getEncoder().getPosition() / Constants.ticksPerRevolutionLow) * Constants.wheelCircumferenceMeters);
      SmartDashboard.putNumber("R Vel", (-RobotContainer.r1.getEncoder().getVelocity()/60 / Constants.ticksPerRevolutionLow) * Constants.wheelCircumferenceMeters);
      SmartDashboard.putNumber("L Vel", (RobotContainer.l1.getEncoder().getVelocity()/60 / Constants.ticksPerRevolutionLow) * Constants.wheelCircumferenceMeters);
    // }else{
    //   SmartDashboard.putNumber("R Dist", (-RobotContainer.r1.getEncoder().getPosition() / Constants.ticksPerRevolutionHigh) * Constants.wheelCircumferenceMeters);
    //   SmartDashboard.putNumber("L Dist", (RobotContainer.l1.getEncoder().getPosition() / Constants.ticksPerRevolutionHigh) * Constants.wheelCircumferenceMeters);
    //   SmartDashboard.putNumber("R Vel", (-RobotContainer.r1.getEncoder().getVelocity()/60 / Constants.ticksPerRevolutionHigh) * Constants.wheelCircumferenceMeters);
    //   SmartDashboard.putNumber("L Vel", (RobotContainer.l1.getEncoder().getVelocity()/60 / Constants.ticksPerRevolutionHigh) * Constants.wheelCircumferenceMeters);  
    // }
    SmartDashboard.putNumber("R Dist", (RobotContainer.r2.getEncoder().getPosition() / Constants.ticksPerRevolutionLow) * Constants.wheelCircumferenceMeters);
    SmartDashboard.putNumber("R Vel", (RobotContainer.r2.getEncoder().getVelocity()/60 / Constants.ticksPerRevolutionLow) * Constants.wheelCircumferenceMeters);
    SmartDashboard.putNumber("Rotation speeds", RobotContainer.driveSub.getRotationSpeed());
    SmartDashboard.putNumber("Wheel vels", RobotContainer.driveSub.getWheelVelocity());
    
    SmartDashboard.putNumber("hood position",  RobotContainer.hoodMotor.getSensorCollection().getQuadraturePosition());
    SmartDashboard.putNumber("turret position", RobotContainer.turretMotor.getSensorCollection().getQuadraturePosition());
    // SmartDashboard.putNumber("lift position", RobotContainer.lift.getSensorCollection().getQuadraturePosition());

    SmartDashboard.putNumber("LiDAR dist", RobotContainer.lidarSub.getDistance());
    // SmartDashboard.putString("Color L", RobotContainer.cpmSub.getColorL());

    SmartDashboard.putBoolean("Climb lock", RobotContainer.climbLock.get() == Value.kReverse);
    SmartDashboard.putBoolean("Winch pull", RobotContainer.winchShift.get() == Value.kReverse);

    // SmartDashboard.putBoolean("quad pos", RobotContainer.lift.getSensorCollection().getQuadraturePosition() > -14000);
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

    RobotContainer.dsL.set(Value.kReverse);
    RobotContainer.intake1.enableCurrentLimit(true);
    RobotContainer.intake1.configPeakCurrentDuration(0,0);
    RobotContainer.intake1.configPeakCurrentLimit(50,0);

    // RobotContainer.intake2.enableCurrentLimit(true);
    // RobotContainer.intake2.configPeakCurrentDuration(0,0);
    // RobotContainer.intake2.configPeakCurrentLimit(50,0);   
  
    // RobotContainer.intake3.enableCurrentLimit(true);
    // RobotContainer.intake3.configPeakCurrentDuration(0,0);
    // RobotContainer.intake3.configPeakCurrentLimit(30,0);

    RobotContainer.r1.setIdleMode(IdleMode.kBrake);
    RobotContainer.r2.setIdleMode(IdleMode.kBrake);
    RobotContainer.l1.setIdleMode(IdleMode.kBrake);
    RobotContainer.l2.setIdleMode(IdleMode.kBrake);
    RobotContainer.flywheelMotor.setIdleMode(IdleMode.kCoast);
    RobotContainer.flywheelMotor2.setIdleMode(IdleMode.kCoast);
    final double ramprate = .25;
    final int current = 40;
    final double ramprateFW = 5;
    final int currentFW = 40;
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
    RobotContainer.flywheelMotor.setSmartCurrentLimit(currentFW);
    RobotContainer.flywheelMotor.setClosedLoopRampRate(ramprateFW);
    RobotContainer.flywheelMotor.setOpenLoopRampRate(ramprateFW);
    RobotContainer.flywheelMotor2.setSmartCurrentLimit(currentFW);
    RobotContainer.flywheelMotor2.setClosedLoopRampRate(ramprateFW);
    RobotContainer.flywheelMotor2.setOpenLoopRampRate(ramprateFW);

    RobotContainer.intakePistons.set(Value.kForward);
    // RobotContainer.climbSub.pullWinch();
    RobotContainer.climbSub.freespinWinch();
    RobotContainer.climbSub.unlockClimb();

    RobotContainer.turretMotor.overrideLimitSwitchesEnable(false);
    RobotContainer.turretMotor.overrideSoftLimitsEnable(false);
  }

  public void resetTheSpaghet(){
    RobotContainer.turretMotor.getSensorCollection().setQuadraturePosition(0, 10);
    RobotContainer.hoodMotor.getSensorCollection().setQuadraturePosition(0, 10);
     // RobotContainer.hoodEncoder.resetAccumulator();
    // RobotContainer.lift.getSensorCollection().setQuadraturePosition(0, 10);
    RobotContainer.winch2.getSensorCollection().setQuadraturePosition(0, 10);
    RobotContainer.driveSub.resetEncoders();
    RobotContainer.flywheelMotor.getEncoder().setPosition(0);
    RobotContainer.flywheelMotor2.getEncoder().setPosition(0);
    RobotContainer.navX.reset();
    RobotContainer.driveSub.resetOdometry();
  }
  
  public void shooter(){
    if(RobotContainer.manipulator.getPOV() == 0 || RobotContainer.manipulator.getPOV() == 90 || RobotContainer.manipulator.getPOV() == 180){ //RobotContainer.manipulator.getPOV() == 90 || RobotContainer.manipulator.getPOV() == 180 || 
      ph.schedule(true);
      pt.schedule(true);
      if(tc != null){
        tc.cancel();
      }
      if(hc != null){
        hc.cancel();
      }
    }else{
      if(ph != null){
        ph.cancel();
        hc.schedule(true);
      } 
      if(pt != null){
        pt.cancel();
        tc.schedule(true);
      }
    }
  }
  
}