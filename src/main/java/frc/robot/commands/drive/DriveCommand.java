/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drive;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
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
    
    RobotContainer.driveSub.teleDrive();

    // if (RobotContainer.sol1.get() == Value.kForward && Math.abs(Robot.driveSub.getWheelVelocity()) > 2.3) {
    //   RobotContainer.sol1.set(Value.kReverse);
    // } else if (RobotContainer.sol1.get() == Value.kReverse && Math.abs(Robot.driveSub.getWheelVelocity()) < 3) {
    //   RobotContainer.sol1.set(Value.kForward);
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
