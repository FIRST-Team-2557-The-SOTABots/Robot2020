package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.RunCommand;
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

    if(RobotContainer.manipulator.getPOV() == 180){
      RobotContainer.intakeSub.intakeOut();
    }

    if(RobotContainer.ma.get()){
      RobotContainer.intakeSub.intakeOut();
      if (RobotContainer.touchOne.get() && RobotContainer.touchThree.get()) {
        RobotContainer.intakeSub.runStarWheelAndCPM(0);
      } else {
        RobotContainer.intakeSub.runStarWheelAndCPM(IntakeSub.starWheelAndCPMSpeed);
      }
      RobotContainer.intakeSub.runIntake(IntakeSub.intakeSpeed);
    }

    if(!RobotContainer.ma.get()){
      RobotContainer.intakeSub.intakeIn();
      RobotContainer.intakeSub.runIntake(0);
      RobotContainer.intakeSub.runStarWheelAndCPM(0);
    }

    if (RobotContainer.touchOne.get() && !RobotContainer.touchThree.get() && !RobotContainer.mb.get()) {
      IntakeSub.cyclingBall = true;
    }

    if (IntakeSub.cyclingBall) {
      if (IntakeSub.targetTS == 2) {
        if (!RobotContainer.touchTwo.get()) {
          RobotContainer.intakeSub.runConveyorBelt(IntakeSub.conveyorMotorSpeed);
        } else {
          RobotContainer.intakeSub.runConveyorBelt(0);
          IntakeSub.targetTS = 3;
          IntakeSub.cyclingBall = false;
        }
      } else if (IntakeSub.targetTS == 3) {
        if (!RobotContainer.touchThree.get()) {
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
