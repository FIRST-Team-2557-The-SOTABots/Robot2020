package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;

public class FlywheelCommand extends CommandBase {
  /**
   * Creates a new FlywheelCommand.
   */
  public FlywheelCommand() {
    addRequirements(RobotContainer.flywheelSub);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(RobotContainer.manipulator.getRawAxis(3) > 0.5 && Robot.ph.getHoodSetpoint() != Constants.HOOD_LOW){
      // speed of 1 breaks wheels
      RobotContainer.flywheelSub.spinFlywheels(0.45);
    }else if(RobotContainer.manipulator.getRawAxis(3) > 0.5 && Robot.ph.getHoodSetpoint() == Constants.HOOD_LOW){
      RobotContainer.flywheelSub.spinFlywheels(0.3);
    }else{
      RobotContainer.flywheelSub.spinFlywheels(0);
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
