package frc.robot;

import com.revrobotics.CANSparkMax.IdleMode;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.HoodCommand;
import frc.robot.commands.LowerHood;
import frc.robot.commands.PIDCenterTurret;
import frc.robot.commands.PIDHood;
import frc.robot.commands.PIDTurret;
import frc.robot.commands.TurretCommand;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  SendableChooser<Command> m_chooser;

  private RobotContainer m_robotContainer;

  public static PIDTurret pt = new PIDTurret();
  public static PIDCenterTurret pct = new PIDCenterTurret();
  public static TurretCommand tc = new TurretCommand();
  public static PIDHood ph = new PIDHood(Constants.HOOD_NEAR);
  public static HoodCommand hc = new HoodCommand();
  public static LowerHood lh = new LowerHood(RobotContainer.hoodSub);

  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();
    configRobot();

    m_chooser = new SendableChooser<>();
    SmartDashboard.putData("Auto chooBchooser", m_chooser);
    
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
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    lh.schedule();
  }

  @Override
  public void teleopPeriodic() {

    // System.out.println(getRequiredRPM(20));

    // RobotContainer.turretMotor.overrideLimitSwitchesEnable(false);
    // RobotContainer.turretMotor.overrideSoftLimitsEnable(false);

    shooter();
    
    //hood lock
    if(RobotContainer.mbumperLeft.get()) RobotContainer.hoodLock.set(Value.kForward);
    if(RobotContainer.mbumperRight.get()) RobotContainer.hoodLock.set(Value.kReverse);

    if(RobotContainer.mback.get()){
      RobotContainer.intakePistons.set(Value.kForward);
    }

    if(RobotContainer.mstart.get()){
      RobotContainer.intakePistons.set(Value.kReverse);
    }

    if(RobotContainer.mterribleRight.get()){
    }

    if(RobotContainer.mterribleLeft.get()){
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
    return (120 * vel) / (2 * Math.PI * RobotContainer.flywheelSub.FLY_WHEEL_RADIUS);
  }

  public void Smartdashboarding(){
    SmartDashboard.putNumber("Current Gear", RobotContainer.driveSub.getCurrentGear());

    SmartDashboard.putNumber("Gyro Heading", RobotContainer.driveSub.getHeading());
    SmartDashboard.putNumber("Raw gyro", RobotContainer.navX.getAngle());
    
    SmartDashboard.putNumber("hood position",  RobotContainer.hoodSub.getHoodPos());
    SmartDashboard.putNumber("hood position PID",  -RobotContainer.hood.getSensorCollection().getQuadraturePosition());

    // SmartDashboard.putNumber("LiDAR dist", RobotContainer.lidarSub.getDistance());
    
    SmartDashboard.putNumber("Wheel Vels", RobotContainer.driveSub.getWheelVelocity());

    SmartDashboard.putNumber("RPM of flywheel", RobotContainer.flywheelSub.getFlywheelSpeed());

    SmartDashboard.putBoolean("Left Turret Limit", RobotContainer.leftTurretLimit.get());
    SmartDashboard.putBoolean("Right Turret Limit", RobotContainer.rightTurretLimit.get());
    SmartDashboard.putNumber("Turret Position", RobotContainer.turretSub.getEncoderValue());
  }

  public void configRobot(){
    RobotContainer.diffDrive.setSafetyEnabled(false);

    RobotContainer.dsL.set(Value.kReverse);
    RobotContainer.intake.enableCurrentLimit(true);
    RobotContainer.intake.configPeakCurrentDuration(0,0);
    RobotContainer.intake.configPeakCurrentLimit(50,0);

    RobotContainer.dsL.set(Value.kReverse);

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
    final double RAMPRATE = .5;
    final int CURRENT = 40;
    final double RAMPRATE_FW = 5;
    final int CURRENT_FW = 40;
    // final double RAMPRATE_FW = 1;
    // final int CURRENT_FW = 120;
    RobotContainer.l1.setSmartCurrentLimit(CURRENT);
    RobotContainer.l1.setClosedLoopRampRate(RAMPRATE);
    RobotContainer.l1.setOpenLoopRampRate(RAMPRATE);
    RobotContainer.l2.setSmartCurrentLimit(CURRENT);
    RobotContainer.l2.setClosedLoopRampRate(RAMPRATE);
    RobotContainer.l2.setOpenLoopRampRate(RAMPRATE);
    RobotContainer.r1.setSmartCurrentLimit(CURRENT);
    RobotContainer.r1.setClosedLoopRampRate(RAMPRATE);
    RobotContainer.r1.setOpenLoopRampRate(RAMPRATE);
    RobotContainer.r2.setSmartCurrentLimit(CURRENT);
    RobotContainer.r2.setClosedLoopRampRate(RAMPRATE);
    RobotContainer.r2.setOpenLoopRampRate(RAMPRATE);

    RobotContainer.flywheelMotor.setSmartCurrentLimit(CURRENT_FW);
    RobotContainer.flywheelMotor.setClosedLoopRampRate(RAMPRATE_FW);
    RobotContainer.flywheelMotor.setOpenLoopRampRate(RAMPRATE_FW);
    RobotContainer.flywheelMotor2.setSmartCurrentLimit(CURRENT_FW);
    RobotContainer.flywheelMotor2.setClosedLoopRampRate(RAMPRATE_FW);
    RobotContainer.flywheelMotor2.setOpenLoopRampRate(RAMPRATE_FW);

    RobotContainer.intakePistons.set(Value.kForward);

    RobotContainer.turret.overrideLimitSwitchesEnable(false);
    RobotContainer.turret.overrideSoftLimitsEnable(false);
    
    RobotContainer.delivery.enableCurrentLimit(true);
    RobotContainer.delivery.configContinuousCurrentLimit(20);
  }

  public void resetTheSpaghet(){
    RobotContainer.hood.getSensorCollection().setQuadraturePosition(0, 10);
    RobotContainer.driveSub.resetEncoders();
    RobotContainer.flywheelMotor.getEncoder().setPosition(0);
    RobotContainer.flywheelMotor2.getEncoder().setPosition(0);
    RobotContainer.navX.reset();
  }
  
  public void shooter(){
    if(RobotContainer.manipulator.getPOV() == 0 || RobotContainer.manipulator.getPOV() == 90 || RobotContainer.manipulator.getPOV() == 180 || RobotContainer.manipulator.getPOV() == 270){
      ph.schedule(true);
      if (RobotContainer.manipulator.getPOV() == 270) {
        pct.schedule(true);
      } else pt.schedule(true); //it's better than nothing 

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
      if (pct != null) {
        pct.cancel();
        tc.schedule(true);
      }
    }
  }
  
}