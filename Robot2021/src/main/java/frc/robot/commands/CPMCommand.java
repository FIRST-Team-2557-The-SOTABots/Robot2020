package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class CPMCommand extends CommandBase {

  public CPMCommand() {
      addRequirements(RobotContainer.cpmSub);
    }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    String[] colors1 = {"B","G","R","Y"};
    String[] colors2 = {"R","Y","B","G"};
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
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
