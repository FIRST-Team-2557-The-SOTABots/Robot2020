package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
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
    if(RobotContainer.hoodLock.get() == Value.kForward){
      RobotContainer.hood.set(-0.5*RobotContainer.manipulator.getRawAxis(1));
    }else RobotContainer.hood.set(0);
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
