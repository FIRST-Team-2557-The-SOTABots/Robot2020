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
    }

    if(RobotContainer.mbumperRight.get()){
    }

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
    return (120 * vel) / (2 * Math.PI * RobotContainer.flywheelSub.FLYWHEELRADIUS);
  }

  public void Smartdashboarding(){
    SmartDashboard.putNumber("Current Gear", RobotContainer.driveSub.getCurrentGear());

    SmartDashboard.putNumber("Gyro Heading", RobotContainer.driveSub.getHeading());
    SmartDashboard.putNumber("Raw gyro", RobotContainer.navX.getAngle());
    SmartDashboard.putString("Pose", RobotContainer.driveSub.getPose().toString());
    
    SmartDashboard.putNumber("hood position",  RobotContainer.hoodMotor.getSensorCollection().getQuadraturePosition());
    SmartDashboard.putNumber("turret position", RobotContainer.turretMotor.getSensorCollection().getQuadraturePosition());

    SmartDashboard.putNumber("LiDAR dist", RobotContainer.lidarSub.getDistance());
    
    SmartDashboard.putNumber("RPM of flywheel", RobotContainer.flywheelSub.getFlywheelSpeed());

    SmartDashboard.putBoolean("Turret limit 1", RobotContainer.turretMotor.getSensorCollection().isFwdLimitSwitchClosed());
    SmartDashboard.putBoolean("Turret limit 2", RobotContainer.turretMotor.getSensorCollection().isRevLimitSwitchClosed());
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

    RobotContainer.turretMotor.overrideLimitSwitchesEnable(false);
    RobotContainer.turretMotor.overrideSoftLimitsEnable(false);
  }

  public void resetTheSpaghet(){
    RobotContainer.turretMotor.getSensorCollection().setQuadraturePosition(0, 10);
    RobotContainer.hoodMotor.getSensorCollection().setQuadraturePosition(0, 10);
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