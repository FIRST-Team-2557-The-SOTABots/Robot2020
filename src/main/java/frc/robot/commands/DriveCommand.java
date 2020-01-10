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

  public DriveCommand(DriveSub subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    DriveSub.drive(RobotContainer.stick.getRawAxis(1), RobotContainer.stick.getRawAxis(4));

    if (DriveSub.getCurrentGear() == 1 && DriveSub.getRotationSpeed(DriveSub.getCurrentGear()) > DriveSub.limitRotSpdGear1) {
      DriveSub.shift();
    } else if (DriveSub.getCurrentGear() == 2 && DriveSub.getRotationSpeed(DriveSub.getCurrentGear()) < DriveSub.limitRotSpdGear1){
      DriveSub.shift();
    }
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
