package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.IntakeSub;

public class TurretFeeder extends CommandBase {

  public TurretFeeder() {
    addRequirements(RobotContainer.intakeSub);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    if (PIDFlywheel.flywheelAtSetpoint) {
      RobotContainer.intakeSub.runConveyorBelt(IntakeSub.conveyorMotorSpeed);
    }
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
