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

    if(RobotContainer.touchOne.get() && RobotContainer.touchThree.get()){
      IntakeSub.starWheelOff = true;
    }
    if(RobotContainer.manipulator.getRawAxis(3) > 0.1){
      IntakeSub.starWheelOff = false;
    }

    double axis2 = RobotContainer.manipulator.getRawAxis(2);

    // if(RobotContainer.manipulator.getPOV() == 180){
    //   RobotContainer.intakeSub.intakeOut();
    // }

    // if(RobotContainer.manipulator.getPOV() == 0){
    //   RobotContainer.intakeSub.intakeIn();
    // }

    if(RobotContainer.ma.get()){
      System.out.println("1");
      RobotContainer.intakeSub.intakeOut();
      if(IntakeSub.starWheelOff){
        System.out.println("2");
        RobotContainer.intakeSub.runStarWheelAndCPM(0);
      }else{
        System.out.println("3");
        RobotContainer.intakeSub.runStarWheelAndCPM(IntakeSub.intakeSpeed);
      }

      if(RobotContainer.mb.get()){
        System.out.println("4");
        RobotContainer.intakeSub.runIntake(-IntakeSub.intakeSpeed);
      }else{
        System.out.println("5");
        RobotContainer.intakeSub.runIntake(IntakeSub.intakeSpeed);
      }
    } else if (!RobotContainer.ma.get()){
        System.out.println("6");
        RobotContainer.intakeSub.runStarWheelAndCPM(0);
        RobotContainer.intakeSub.runIntake(0);
      // RobotContainer.intakeSub.intakeIn();      
    }

    // if(axis2 > 0.5 && (RobotContainer.flywheelSub.getFlywheelSpeed() > 15000)){
    //   RobotContainer.intakeSub.runConveyorBelt(IntakeSub.conveyorMotorSpeed);
    // }else{
    //   RobotContainer.intakeSub.runConveyorBelt(0);
    // }

    if (RobotContainer.touchOne.get() && !RobotContainer.touchThree.get() && axis2 < 0.5) {
      IntakeSub.cyclingBall = true;
    }

    if (IntakeSub.cyclingBall) {
      if (IntakeSub.targetTS == 2) {
        if (!RobotContainer.touchTwo.get()) {
          RobotContainer.intakeSub.runConveyorBelt(IntakeSub.conveyorMotorSpeed);
          if(!IntakeSub.starWheelOff) RobotContainer.intakeSub.runStarWheelAndCPM(IntakeSub.starWheelAndCPMSpeed);
        } else {
          RobotContainer.intakeSub.runConveyorBelt(0);
          RobotContainer.intakeSub.runStarWheelAndCPM(0);;
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
