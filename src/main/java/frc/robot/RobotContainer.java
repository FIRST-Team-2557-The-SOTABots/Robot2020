package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.FlywheelCommand;
import frc.robot.commands.HoodCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.PIDTurret;
import frc.robot.commands.climb.ClimbCommand;
import frc.robot.commands.climb.ClimbSequence1;
import frc.robot.commands.climb.ClimbSequence2;
import frc.robot.commands.climb.LockClimb;
import frc.robot.commands.climb.UnlockClimb;
import frc.robot.subsystems.CPMSub;
import frc.robot.subsystems.ClimbSub;
import frc.robot.subsystems.DriveSub;
import frc.robot.subsystems.FlywheelSub;
import frc.robot.subsystems.HoodSub;
import frc.robot.subsystems.IntakeSub;
import frc.robot.subsystems.LidarSub;
import frc.robot.subsystems.TurretSub;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class RobotContainer {

  public static CANSparkMax l1 = new CANSparkMax(16, MotorType.kBrushless);
  public static CANSparkMax l2 = new CANSparkMax(17, MotorType.kBrushless);
  public static CANSparkMax r1 = new CANSparkMax(14, MotorType.kBrushless);//11
  public static CANSparkMax r2 = new CANSparkMax(10, MotorType.kBrushless);
  public static SpeedControllerGroup left = new SpeedControllerGroup(l1, l2);
  public static SpeedControllerGroup right = new SpeedControllerGroup(r1, r2);
  public static DifferentialDrive diffDrive = new DifferentialDrive(left, right);
  public static AHRS navX = new AHRS(SPI.Port.kMXP);

  public static CANSparkMax flywheelMotor = new CANSparkMax(1, MotorType.kBrushless);
  public static CANSparkMax flywheelMotor2 = new CANSparkMax(2, MotorType.kBrushless);

  public static WPI_TalonSRX intake1 = new WPI_TalonSRX(4);
  public static WPI_TalonSRX intake2 = new WPI_TalonSRX(5);//cpm
  public static WPI_TalonSRX intake3 = new WPI_TalonSRX(6);

  public static WPI_TalonSRX winch1 = new WPI_TalonSRX(7);
  public static WPI_TalonSRX lift = new WPI_TalonSRX(8);
  public static WPI_TalonSRX winch2 = new WPI_TalonSRX(9);
  //lift to -14000 for 12in\]]

  public static WPI_TalonSRX turretMotor = new WPI_TalonSRX(0);
  public static WPI_TalonSRX hoodMotor = new WPI_TalonSRX(3);

//  CPM:kReverse=up,kForward=down
//  climbLock:kReverse=lock,kForward=unlock
//  winchShift:kReverse=pull,kForward=freespin
//  intakePistons:kReverse=in,kForward=out
//  dSl:kReverse=low,kForward=high
  public static Compressor compressor = new Compressor(0);
  public static DoubleSolenoid dsL = new DoubleSolenoid(0, 0, 1);
  public static DoubleSolenoid intakePistons = new DoubleSolenoid(0, 2, 3);
  public static DoubleSolenoid winchShift = new DoubleSolenoid(0, 4, 5);
  public static DoubleSolenoid climbLock = new DoubleSolenoid(0, 6, 7);
  public static DoubleSolenoid CPMshift = new DoubleSolenoid(1, 0, 1);

  public static DigitalInput touchThree = new DigitalInput(1);
  public static DigitalInput touchTwo = new DigitalInput(2); 
  public static DigitalInput touchOne = new DigitalInput(3); 

  // public static AnalogInput ani = new AnalogInput(70);
  // public static LidarLitePWM lidarLite = new LidarLitePWM(new DigitalInput(70));

  // public static AnalogInput hoodEncoder = new AnalogInput(0);

  // Joysticks and Buttons
  public static Joystick driver = new Joystick(0);
  public static JoystickButton da = new JoystickButton(driver, 1);
  public static JoystickButton db = new JoystickButton(driver, 2);
  public static JoystickButton dx = new JoystickButton(driver, 3); 
  public static JoystickButton dy = new JoystickButton(driver, 4);
	public static JoystickButton dbumperLeft = new JoystickButton(driver, 5);
  public static JoystickButton dbumperRight = new JoystickButton(driver, 6);
  public static JoystickButton dback = new JoystickButton(driver, 7);
  public static JoystickButton dstart = new JoystickButton(driver, 8);
  public static JoystickButton dterribleLeft = new JoystickButton(driver, 9);
  public static JoystickButton dterribleRight = new JoystickButton(driver, 10);

  public static Joystick manipulator = new Joystick(1);
	public static JoystickButton ma = new JoystickButton(manipulator, 1);
  public static JoystickButton mb = new JoystickButton(manipulator, 2);
  public static JoystickButton mx = new JoystickButton(manipulator, 3);
  public static JoystickButton my = new JoystickButton(manipulator, 4);
  public static JoystickButton mbumperLeft = new JoystickButton(manipulator, 5);
  public static JoystickButton mbumperRight = new JoystickButton(manipulator, 6);
  public static JoystickButton mback = new JoystickButton(manipulator, 7);
  public static JoystickButton mstart = new JoystickButton(manipulator, 8);
  public static JoystickButton mterribleLeft = new JoystickButton(manipulator, 9);
  public static JoystickButton mterribleRight = new JoystickButton(manipulator, 10);

  public static Joystick testStick = new Joystick(2);
	public static JoystickButton ta = new JoystickButton(testStick, 1);
  public static JoystickButton tb = new JoystickButton(testStick, 2);
  public static JoystickButton tx = new JoystickButton(testStick, 3);
  public static JoystickButton ty = new JoystickButton(testStick, 4);
  public static JoystickButton tbumperLeft = new JoystickButton(testStick, 5);
  public static JoystickButton tbumperRight = new JoystickButton(testStick, 6);
  public static JoystickButton tback = new JoystickButton(testStick, 7);
  public static JoystickButton tstart = new JoystickButton(testStick, 8);
  public static JoystickButton tterribleLeft = new JoystickButton(testStick, 9);
  public static JoystickButton tterribleRight = new JoystickButton(testStick, 10);

  // The robot's subsystems
  public static final DriveSub driveSub = new DriveSub();
  public static final TurretSub turretSub = new TurretSub();
  public static final HoodSub hoodSub =new HoodSub();
  public static final FlywheelSub flywheelSub = new FlywheelSub();
  public static final LidarSub lidarSub = new LidarSub(new DigitalInput(0));
  public static final IntakeSub intakeSub = new IntakeSub();
  public static final ClimbSub climbSub = new ClimbSub();
  public static final CPMSub cpmSub = new CPMSub();

  public RobotContainer() {
    driveSub.setDefaultCommand(new DriveCommand());
    intakeSub.setDefaultCommand(new IntakeCommand());
    hoodSub.setDefaultCommand(new HoodCommand());
    flywheelSub.setDefaultCommand(new FlywheelCommand());
    climbSub.setDefaultCommand(new ClimbCommand());
    configureButtonBindings();

    //lift.setDefaultCommand(new RunCommand( () -> lift.lift(manipulator.getRawAxis(5)) , lift));
    
    // flywheelSub.setDefaultCommand(new RunCommand( () -> flywheelSub.spinFlywheel(driver.getRawAxis(1)), flywheelSub));
    // hoodSub.setDefaultCommand(new RunCommand( () -> hoodSub.angleHood(driver.getRawAxis(5)), hoodSub));
    // turretSub.setDefaultCommand(new RunCommand( () -> turretSub.rotate(driver.getRawAxis(0)), flywheelSub));

  }

  private void configureButtonBindings() {

    // ma.whileHeld(new PIDTurret());

    da.whileHeld(new ClimbSequence1());
    db.whileHeld(new ClimbSequence2());
    db.whenReleased(new LockClimb());

  }

  public Command getAutonomousCommand() {
    return null;
  }
}