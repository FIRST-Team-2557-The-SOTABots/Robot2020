// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class TimedDriveCommand extends CommandBase {
  private boolean forward;

  /** Creates a new TimedDriveCommand. */
  public TimedDriveCommand(boolean forward) {
    addRequirements(RobotContainer.driveSub);
    // Use addRequirements() here to declare subsystem dependencies.
    this.forward = forward;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    RobotContainer.driveSub.resetEncoders();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    RobotContainer.driveSub.drive(forward ? -1 * Constants.TIMED_DRIVE_SPEED : Constants.TIMED_DRIVE_SPEED, 0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.driveSub.drive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.abs(RobotContainer.l1.getEncoder().getPosition()) >= Constants.TIMED_DRIVE_ROTATIONS;
  }
}
