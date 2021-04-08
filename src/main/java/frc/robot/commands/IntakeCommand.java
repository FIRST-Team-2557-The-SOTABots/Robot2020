package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.IntakeSub;

public class IntakeCommand extends CommandBase {

  public IntakeCommand() {
    addRequirements(RobotContainer.intakeSub);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    if(RobotContainer.mx.get()){
      RobotContainer.intakeSub.intakeIn();
    }

    if(RobotContainer.ma.get() || RobotContainer.mb.get()){
      RobotContainer.intakeSub.intakeOut();
    }
    if(RobotContainer.ma.get()){
        RobotContainer.intakeSub.runIntake(IntakeSub.INTAKE_SPEED);
    }else if(RobotContainer.mb.get()){
        RobotContainer.intakeSub.runIntake(-IntakeSub.INTAKE_SPEED);
    } else {
        RobotContainer.intakeSub.runIntake(0);
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
