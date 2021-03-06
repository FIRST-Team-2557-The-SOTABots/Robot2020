package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.IntakeSub;

public class IntakeCommand extends CommandBase {

  boolean shoot;
  public IntakeCommand() {
    addRequirements(RobotContainer.intakeSub);
    shoot=false;
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    if(RobotContainer.flywheelSub.getFlywheelSpeed() > 16000) shoot = true;//16000
    else shoot = false;
    double axis2 = RobotContainer.manipulator.getRawAxis(2);

    if(RobotContainer.ma.get()){
      RobotContainer.intakeSub.intakeOut();
      if(RobotContainer.mb.get()){
        RobotContainer.intakeSub.runConveyorAndCPM(IntakeSub.INTAKESPEED*-.5);
      }else{
        RobotContainer.intakeSub.runConveyorAndCPM(0);
      }

      if(RobotContainer.mb.get()){
        RobotContainer.intakeSub.runIntake(-IntakeSub.INTAKESPEED);
      }else{
        RobotContainer.intakeSub.runIntake(IntakeSub.INTAKESPEED);
      }
    } else if (!RobotContainer.ma.get()){
        RobotContainer.intakeSub.runConveyorAndCPM(0);
        RobotContainer.intakeSub.runIntake(0);
    }

    if(axis2 > 0.5 && shoot){ //&& RobotContainer.flywheelSub.getFlywheelSpeed() > 15000
      RobotContainer.intakeSub.runTurretFeeder(IntakeSub.CONVEYORMOTORSPEED);
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
