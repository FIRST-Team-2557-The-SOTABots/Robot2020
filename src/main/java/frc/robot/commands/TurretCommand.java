package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class TurretCommand extends CommandBase {
  public TurretCommand() {
    addRequirements(RobotContainer.turretSub);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    RobotContainer.turretSub.rotate(RobotContainer.manipulator.getRawAxis(4) * -1);
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
