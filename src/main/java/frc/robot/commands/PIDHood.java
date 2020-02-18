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
import frc.robot.subsystems.Projectile;

public class PIDHood extends CommandBase {

  PIDController pidController;//hi
  private final double kP = 0;
  private final double kI = 0;
  private final double kD = 0;
  private final double tolerance = 0;
  private double setpoint;

  public PIDHood() {
    addRequirements(RobotContainer.hoodSub);
  }

  @Override
  public void initialize() {
    pidController = new PIDController(kP, kI, kD);
    pidController.disableContinuousInput(); 
    pidController.setTolerance(tolerance);
    pidController.reset();

    setpoint = Projectile.getProjectileAngle();
  }

  @Override
  public void execute() { 
    double output = pidController.calculate(RobotContainer.hoodSub.getHoodAngle(), setpoint);
    RobotContainer.hoodSub.angleHood(output);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(final boolean interrupted) {
    RobotContainer.hoodSub.angleHood(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return pidController.atSetpoint();
  }
}
