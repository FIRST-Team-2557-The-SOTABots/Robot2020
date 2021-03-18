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
    if (Math.abs(RobotContainer.manipulator.getRawAxis(4)) > 0.05) {
      RobotContainer.turretSub.rotateMax(RobotContainer.manipulator.getRawAxis(4) * -1);
    } else RobotContainer.turretSub.rotateMax(0);
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
