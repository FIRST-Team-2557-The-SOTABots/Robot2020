/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.IntakeSub;

public class AutoIntakeIntake extends CommandBase {

  public AutoIntakeIntake() {
    addRequirements(RobotContainer.intakeSub);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("Intake intake Command init");
    SmartDashboard.putBoolean("Exist", true);
    SmartDashboard.putBoolean("Not Exist", false);
    SmartDashboard.putBoolean("Exist  Execute", false);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putBoolean("Exist  Execute", true);
    System.out.println("Intake intake Command exe");
    RobotContainer.intakeSub.intakeOut();
    RobotContainer.intakeSub.runIntake(IntakeSub.INTAKESPEED);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.intakeSub.runIntake(0);
    SmartDashboard.putBoolean("Not Exist", true);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
