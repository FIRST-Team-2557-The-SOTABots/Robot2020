/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.FlywheelSub;

public class PIDFlywheel extends CommandBase {
  
  private static PIDController pidController;
  private static final double kP = 0;
  private static final double kI = 0;
  private static final double kD = 0;
  private static final double tolerance = 1;
  public double setpoint;
  public static boolean flywheelAtSetpoint;

  public PIDFlywheel() {
    pidController = new PIDController(kP, kI, kD);
    pidController.reset();
    pidController.disableContinuousInput();
    pidController.setTolerance(tolerance);
    addRequirements(RobotContainer.flywheelSub);
    setpoint = FlywheelSub.getRequiredRPM();
  }

  public PIDFlywheel(double setpoint) {
    pidController = new PIDController(kP, kI, kD);
    pidController.reset();
    pidController.disableContinuousInput();
    pidController.setTolerance(tolerance);
    addRequirements(RobotContainer.flywheelSub);
    this.setpoint = setpoint;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double output = pidController.calculate(RobotContainer.flywheelSub.getFlywheelSpeed(), setpoint);
    RobotContainer.flywheelSub.spinFlywheel(output);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(pidController.atSetpoint()) {
      flywheelAtSetpoint = true;
    } else {
      flywheelAtSetpoint = false;
    }
    return pidController.atSetpoint();
  }
}
