/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.commands.AimCommand;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.PIDFlywheel;
import frc.robot.commands.TurretFeeder;
import frc.robot.subsystems.DriveSub;
import frc.robot.subsystems.FlywheelSub;
import frc.robot.subsystems.HoodSub;
import frc.robot.subsystems.IntakeSub;
import frc.robot.subsystems.LidarSub;
import frc.robot.subsystems.TurretSub;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class RobotContainer {
  // The robot's subsystems
  public static final DriveSub driveSub = new DriveSub();
  public static final TurretSub turretSub = new TurretSub();
  public static final HoodSub hoodSub = new HoodSub();
  public static final FlywheelSub flywheelSub = new FlywheelSub();
  public static final LidarSub lidarSub = new LidarSub(new DigitalInput(0));
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

  public static AHRS navX = new AHRS(SPI.Port.kMXP);


  public RobotContainer() {
    driveSub.setDefaultCommand(new DriveCommand());
    configureButtonBindings();

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