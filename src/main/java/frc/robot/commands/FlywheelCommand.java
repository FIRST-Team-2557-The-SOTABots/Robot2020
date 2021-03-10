package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
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
    if(RobotContainer.manipulator.getRawAxis(3) > 0.5){
      RobotContainer.flywheelSub.spinFlywheels(1);
    }else{
      RobotContainer.flywheelSub.spinFlywheels(0);
    }
    
    if(RobotContainer.manipulator.getRawAxis(2) > 0.5){
      RobotContainer.flywheelSub.delivery(1);
    }else{
      RobotContainer.flywheelSub.delivery(0);
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
