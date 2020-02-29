 
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

    if (RobotContainer.touch1.get() && !RobotContainer.touch3.get() && !RobotContainer.mx.get()) {
      IntakeSub.cyclingBall = true;
    }

    if (IntakeSub.cyclingBall) {
      if (IntakeSub.targetTS == 2) {
        if (!RobotContainer.touch2.get()) {
          RobotContainer.intakeSub.runConveyorBelt(IntakeSub.conveyorMotorSpeed);
        } else {
          RobotContainer.intakeSub.runConveyorBelt(0);
          IntakeSub.targetTS = 3;
          IntakeSub.cyclingBall = false;
        }
      } else if (IntakeSub.targetTS == 3) {
        if (!RobotContainer.touch3.get()) {
          RobotContainer.intakeSub.runConveyorBelt(IntakeSub.conveyorMotorSpeed);
        } else {
          RobotContainer.intakeSub.runConveyorBelt(0);
          IntakeSub. targetTS = 2;
          IntakeSub.cyclingBall = false;
        }
      }
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
