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
import frc.robot.commands.DeliveryCommand;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.FlywheelCommand;
import frc.robot.commands.HoodCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.TurretCommand;
import frc.robot.subsystems.DeliverySub;
import frc.robot.subsystems.DriveSub;
import frc.robot.subsystems.FlywheelSub;
import frc.robot.subsystems.HoodSub;
import frc.robot.subsystems.IntakeSub;
import frc.robot.subsystems.TurretSub;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class RobotContainer {

  public static CANSparkMax l1 = new CANSparkMax(16, MotorType.kBrushless);
  public static CANSparkMax l2 = new CANSparkMax(17, MotorType.kBrushless);
  public static CANSparkMax r1 = new CANSparkMax(14, MotorType.kBrushless);
  public static CANSparkMax r2 = new CANSparkMax(10, MotorType.kBrushless);
  public static SpeedControllerGroup left = new SpeedControllerGroup(l1, l2);
  public static SpeedControllerGroup right = new SpeedControllerGroup(r1, r2);
  public static DifferentialDrive diffDrive = new DifferentialDrive(left, right);
  public static AHRS navX = new AHRS(SPI.Port.kMXP);

  public static CANSparkMax flywheelMotor = new CANSparkMax(13, MotorType.kBrushless);
  public static CANSparkMax flywheelMotor2 = new CANSparkMax(12, MotorType.kBrushless);

  public static WPI_TalonSRX turret = new WPI_TalonSRX(0);
  public static WPI_TalonSRX hood = new WPI_TalonSRX(1);
  public static WPI_TalonSRX delivery = new WPI_TalonSRX(2);
  public static WPI_TalonSRX intake = new WPI_TalonSRX(3);
  public static WPI_TalonSRX deliveryStar = new WPI_TalonSRX(4);

  public static Compressor compressor = new Compressor(1);
  public static DoubleSolenoid dsL = new DoubleSolenoid(0, 4, 5); 
  public static DoubleSolenoid intakePistons = new DoubleSolenoid(0, 2, 3);
  public static DoubleSolenoid hoodLock = new DoubleSolenoid(0, 6, 7);

  // consider the turret side of robot front
  public static DigitalInput rightTurretLimit = new DigitalInput(2); 
  public static DigitalInput leftTurretLimit = new DigitalInput(3); 

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
  public static final IntakeSub intakeSub = new IntakeSub();
  public static final DeliverySub deliverySub = new DeliverySub();

  public RobotContainer() {
    driveSub.setDefaultCommand(new DriveCommand());
    intakeSub.setDefaultCommand(new IntakeCommand());
    hoodSub.setDefaultCommand(new HoodCommand());
    flywheelSub.setDefaultCommand(new FlywheelCommand());
    turretSub.setDefaultCommand(new TurretCommand());
    deliverySub.setDefaultCommand(new DeliveryCommand());
  }

  public Command getAutonomousCommand() {
    return null;
  }
}