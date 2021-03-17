// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class PIDCenterTurret extends CommandBase {
  private static final int TURRET_MIDPOINT_ENCODER_VALUE = 3942;
  private static final double KP = 0.0001; 
  private static final double KI = 0; 
  private static final double KD = 0;
  private static final int TOLERANCE = 0;
  private static PIDController pidController =  new PIDController(KP, KI, KD);

  /** Creates a new PIDCenterTurret. */
  public PIDCenterTurret() {
    addRequirements(RobotContainer.turretSub);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    pidController.setTolerance(TOLERANCE);
    pidController.disableContinuousInput();
    pidController.reset();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    int input = RobotContainer.turretSub.getEncoderValue();
    double output = pidController.calculate(input, TURRET_MIDPOINT_ENCODER_VALUE);
    RobotContainer.turretSub.rotateMax(output);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.turret.set(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return pidController.atSetpoint();
  }
}
