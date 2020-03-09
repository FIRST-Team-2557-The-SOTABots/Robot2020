package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.CPMSub;

public class CPMAlignCommand extends CommandBase {
  
  public CPMAlignCommand() {
    // addRequirements(RobotContainer.cpmSub);
  }

  @Override
  public void initialize() {

  }

  @Override
  public void execute() {
    RobotContainer.cpmSub.runCPMAndIntake(CPMSub.CPMSpeed);
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    if (RobotContainer.cpmSub.getColorL() == RobotContainer.cpmSub.getColorR()) {
      return true;
    }
    return false;
  }
}
