package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class HoodCommand extends CommandBase {
  public HoodCommand() {
    addRequirements(RobotContainer.hoodSub);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    RobotContainer.hood.set(RobotContainer.manipulator.getRawAxis(1));
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
