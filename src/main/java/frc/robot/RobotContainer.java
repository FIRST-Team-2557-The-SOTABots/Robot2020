/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.commands.AimCommand;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.PIDFlywheel;
import frc.robot.commands.TurretFeeder;
import frc.robot.subsystems.DriveSub;
import frc.robot.subsystems.FlywheelSub;
import frc.robot.subsystems.HoodSub;
import frc.robot.subsystems.IntakeSub;
// import frc.robot.subsystems.LidarSub;
import frc.robot.subsystems.TurretSub;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class RobotContainer {
  // The robot's subsystems
  public static final DriveSub driveSub = new DriveSub();
  public static final TurretSub turretSub = new TurretSub();
  public static final HoodSub hoodSub = new HoodSub();
  public static final FlywheelSub flywheelSub = new FlywheelSub();
  // public static final LidarSub lidarSub = new LidarSub(new DigitalInput(0));
  public static final IntakeSub intakeSub = new IntakeSub();

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

  public static CANSparkMax l1 = new CANSparkMax(10, MotorType.kBrushless);
  public static CANSparkMax l2 = new CANSparkMax(11, MotorType.kBrushless);
  public static CANSparkMax r1 = new CANSparkMax(12, MotorType.kBrushless);
  public static CANSparkMax r2 = new CANSparkMax(13, MotorType.kBrushless);

  public static SpeedControllerGroup left = new SpeedControllerGroup(l1, l2);
  public static SpeedControllerGroup right = new SpeedControllerGroup(r1, r2);
  
  public static DifferentialDrive diffDrive = new DifferentialDrive(left, right);

  public static Compressor compressor = new Compressor(1);
  public static DoubleSolenoid dsL = new DoubleSolenoid(1, 0, 1);
  public static AHRS navX = new AHRS(SPI.Port.kMXP);

  public static WPI_TalonSRX flywheelMotor = new WPI_TalonSRX(1);
  public static WPI_TalonSRX flywheelMotor2 = new WPI_TalonSRX(2);

  public static WPI_TalonSRX bigIntakeAngler = new WPI_TalonSRX(64);
  public static WPI_TalonSRX bigIntakeRunner = new WPI_TalonSRX(63);
  public static WPI_TalonSRX smallIntakeAngler = new WPI_TalonSRX(62);
  public static WPI_TalonSRX smallIntakeRunner = new WPI_TalonSRX(61);
  public static WPI_TalonSRX conveyorMotor = new WPI_TalonSRX(60);

  public static WPI_TalonSRX turretMotor = new WPI_TalonSRX(0);
  public static WPI_TalonSRX hoodMotor = new WPI_TalonSRX(3);

  public static DigitalInput touchHigh = new DigitalInput(0);
  public static DigitalInput touchLow = new DigitalInput(1);


  public RobotContainer() {
    driveSub.setDefaultCommand(new DriveCommand());
    configureButtonBindings();

    //lift.setDefaultCommand(new RunCommand( () -> lift.lift(manipulator.getRawAxis(5)) , lift));
    
    // flywheelSub.setDefaultCommand(new RunCommand( () -> flywheelSub.spinFlywheel(driver.getRawAxis(1)), flywheelSub));
    // hoodSub.setDefaultCommand(new RunCommand( () -> hoodSub.angleHood(driver.getRawAxis(5)), hoodSub));
    // turretSub.setDefaultCommand(new RunCommand( () -> turretSub.rotate(driver.getRawAxis(0)), flywheelSub));

  }

  private void configureButtonBindings() {

    da.whenPressed(() -> driveSub.shift(), driveSub);

    mb.whileHeld(
      new AimCommand()
    );

    mx.whileHeld(
      new ParallelCommandGroup(
        new TurretFeeder(),
        new PIDFlywheel()
      )
    );

  }

  public Command getAutonomousCommand() {
    return null;
  }
}