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
    }else if(RobotContainer.manipulator.getRawAxis(3) > 0.1){
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
      // RobotContainer.intakeSub.intakeOut();
      if(!IntakeSub.starWheelOff && !RobotContainer.mb.get()){
        System.out.println("2");
        RobotContainer.intakeSub.runStarWheelAndCPM(IntakeSub.starWheelAndCPMSpeed);
      }else{
        System.out.println("3");
        RobotContainer.intakeSub.runStarWheelAndCPM(0);
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

    

    // if(RobotContainer.ma.get() && RobotContainer.mb.get()){
    //   RobotContainer.intakeSub.runIntake(-0.5);
    // }else if(RobotContainer.ma.get()){
    //   RobotContainer.intakeSub.intakeOut();
    //   RobotContainer.intakeSub.runIntake(IntakeSub.intakeSpeed);
    //   if (RobotContainer.touchOne.get() && RobotContainer.touchThree.get()) {
    //     RobotContainer.intakeSub.runStarWheelAndCPM(0);
    //   } else {
    //     RobotContainer.intakeSub.runStarWheelAndCPM(IntakeSub.starWheelAndCPMSpeed);
    //   }
    // }else{
    //   RobotContainer.intakeSub.intakeIn();
    //   RobotContainer.intakeSub.runIntake(0);
    //   RobotContainer.intakeSub.runStarWheelAndCPM(0);
    // }

    // System.out.println(RobotContainer.manipulator.getRawAxis(2));

    // if(axis2 > 0.1 && RobotContainer.mb.get()){
    //   RobotContainer.intakeSub.runIntake(-0.5);
    // }else if(axis2 > 0.1){
    //   // RobotContainer.intakeSub.intakeOut();
    //   RobotContainer.intakeSub.runIntake(IntakeSub.intakeSpeed);
    //   if (IntakeSub.starWheelOff) {
    //      RobotContainer.intakeSub.runStarWheelAndCPM(0);
    //   } else {
    //     RobotContainer.intakeSub.runStarWheelAndCPM(IntakeSub.starWheelAndCPMSpeed);
    //   }
    // }else if(RobotContainer.mx.get()){
    //   RobotContainer.intakeSub.runStarWheelAndCPM(IntakeSub.starWheelAndCPMSpeed);
    // }else{
    //   // RobotContainer.intakeSub.intakeIn();
    //   RobotContainer.intakeSub.runIntake(0);
    //   RobotContainer.intakeSub.runStarWheelAndCPM(0);
    // }

    // if(RobotContainer.ma.get() && RobotContainer.mb.get()){
    //   RobotContainer.intakeSub.runIntake(-0.5);
    // }else if(axis2 > 0.1){
    //   RobotContainer.intakeSub.intakeOut();
    //   RobotContainer.intakeSub.runIntake(IntakeSub.intakeSpeed);
    //   RobotContainer.intakeSub.runStarWheelAndCPM(IntakeSub.starWheelAndCPMSpeed);
    // }else{
    //   RobotContainer.intakeSub.intakeIn();
    //   RobotContainer.intakeSub.runIntake(0);
    //   RobotContainer.intakeSub.runStarWheelAndCPM(0);
    // }

    if(RobotContainer.my.get()){
      RobotContainer.intakeSub.runStarWheelAndCPM(IntakeSub.starWheelAndCPMSpeed);
    }else{
      RobotContainer.intakeSub.runStarWheelAndCPM(0);
    }

    //testing intake

    if(RobotContainer.ty.get()){
      RobotContainer.intakeSub.runStarWheelAndCPM(IntakeSub.starWheelAndCPMSpeed);
    }else{
      RobotContainer.intakeSub.runStarWheelAndCPM(0);
    }

    if(RobotContainer.ta.get()){
      RobotContainer.intakeSub.runIntake(IntakeSub.intakeSpeed);
    }else{
      RobotContainer.intakeSub.runIntake(0);
    }

    if(RobotContainer.tb.get()){
      RobotContainer.intakeSub. runConveyorBelt(IntakeSub.conveyorMotorSpeed);
    }else{
      RobotContainer.intakeSub.runConveyorBelt(0);
    }

    // if(RobotContainer.mx.get()){
    //   RobotContainer.intakeSub.runConveyorBelt(IntakeSub.conveyorMotorSpeed);
    // }else{
    //   RobotContainer.intakeSub.runConveyorBelt(0);
    // }
 
    // if(!RobotContainer.ma.get()){
    //   RobotContainer.intakeSub.intakeIn();
    //   RobotContainer.intakeSub.runIntake(0);
    //   RobotContainer.intakeSub.runStarWheelAndCPM(0);
    // }

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
