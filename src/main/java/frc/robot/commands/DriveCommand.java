/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveSub;

public class DriveCommand extends CommandBase {

  public DriveCommand() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.driveSub);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    RobotContainer.driveSub.drive(RobotContainer.driver.getRawAxis(1), 0.6 * RobotContainer.driver.getRawAxis(4));

    // if (RobotContainer.driveSub.getCurrentGear() == 1 && Math.abs(RobotContainer.driveSub.getRotationSpeed(RobotContainer.driveSub.getCurrentGear())) > DriveSub.limitRotSpdGear1) {
    //   RobotContainer.driveSub.shift();
    // } else if (RobotContainer.driveSub.getCurrentGear() == 2 && Math.abs(RobotContainer.driveSub.getRotationSpeed(RobotContainer.driveSub.getCurrentGear())) < DriveSub.limitRotSpdGear1) {
    //   RobotContainer.driveSub.shift();
    // }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
