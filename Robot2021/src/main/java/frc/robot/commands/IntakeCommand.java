package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.RobotContainer;
import frc.robot.subsystems.FlywheelSub;
import frc.robot.subsystems.IntakeSub;

public class IntakeCommand extends CommandBase {

  boolean runConveyor = false;
  boolean finalBall = false;
  int numBall=0;
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

    SmartDashboard.putBoolean("Conveyor running", runConveyor);

    if(RobotContainer.ma.get()){
      RobotContainer.intakeSub.intakeOut();
      if(RobotContainer.mb.get()){
        RobotContainer.intakeSub.runConveyorAndCPM(IntakeSub.intakeSpeed*-.5);
      }else if(!IntakeSub.cyclingBall){
        RobotContainer.intakeSub.runConveyorAndCPM(IntakeSub.intakeSpeed);
      }else{
        RobotContainer.intakeSub.runConveyorAndCPM(0);
      }

      if(RobotContainer.mb.get()){
        RobotContainer.intakeSub.runIntake(-IntakeSub.intakeSpeed);
      }else{
        RobotContainer.intakeSub.runIntake(IntakeSub.intakeSpeed);
      }
    } else if (!RobotContainer.ma.get()){
        RobotContainer.intakeSub.runConveyorAndCPM(0);
        RobotContainer.intakeSub.runIntake(0);
    }

    if(axis2 > 0.5 && shoot){ //&& RobotContainer.flywheelSub.getFlywheelSpeed() > 15000
      RobotContainer.intakeSub.runTurretFeeder(IntakeSub.conveyorMotorSpeed);
    }else if(RobotContainer.manipulator.getPOV() == 270){
      RobotContainer.intakeSub.runTurretFeeder(0);
      IntakeSub.targetTS = 3;
      IntakeSub.cyclingBall = false;
    }

    if(RobotContainer.touchOne.get() && RobotContainer.touchTwo.get() && RobotContainer.touchThree.get()){
      finalBall = true;
    }

    if(RobotContainer.manipulator.getRawAxis(3) > 0.5){
      finalBall = false;
    }

    if (RobotContainer.touchOne.get() && !RobotContainer.touchTwo.get() && axis2 < 0.5) {//axis2 is manual conveyor belt, not intake
      IntakeSub.cyclingBall = true;
    }else if(axis2 > 0.5){
      IntakeSub.cyclingBall = false;
    }

    if(RobotContainer.touchOne.get() && RobotContainer.touchTwo.get() && RobotContainer.touchThree.get()) numBall = 3;
    else if(!RobotContainer.touchOne.get() && RobotContainer.touchTwo.get() && RobotContainer.touchThree.get() || 
      RobotContainer.touchOne.get() && !RobotContainer.touchTwo.get() && RobotContainer.touchThree.get() ||
      RobotContainer.touchOne.get() && RobotContainer.touchTwo.get() && !RobotContainer.touchThree.get()
    ) numBall = 2;
    else if(!RobotContainer.touchOne.get() && !RobotContainer.touchTwo.get() && RobotContainer.touchThree.get() ||
      !RobotContainer.touchOne.get() && RobotContainer.touchTwo.get() && !RobotContainer.touchThree.get() ||
      RobotContainer.touchOne.get() && !RobotContainer.touchTwo.get() && !RobotContainer.touchThree.get()
    ) numBall = 1;
    else numBall = 0;

    if(numBall == 0 || numBall == 1){
      IntakeSub.targetTS = 3;
    }
    SmartDashboard.putNumber("TargetTS", IntakeSub.targetTS);

    if(RobotContainer.touchOne.get()){
      System.out.println("Touched 1");
    }

    if(RobotContainer.touchTwo.get()){
      System.out.println("Touched 2");
    }

    if(RobotContainer.touchThree.get()){
      System.out.println("Touched 3");
    }

    if(numBall != 3){
      if(finalBall){
        RobotContainer.intakeSub.runConveyorAndCPM(0);
        RobotContainer.intakeSub.runTurretFeeder(0);
      }else{
        if (IntakeSub.cyclingBall && !(RobotContainer.manipulator.getRawAxis(3) > 0.5)) {
          if(numBall == 1 && RobotContainer.touchOne.get()){
            RobotContainer.intakeSub.runIntake(0);
          }else if(numBall == 2 && RobotContainer.touchOne.get()){
            RobotContainer.intakeSub.runIntake(0);
          }
          if (IntakeSub.targetTS == 2 && RobotContainer.touchThree.get()) {
            if (!RobotContainer.touchTwo.get()) {
              RobotContainer.intakeSub.runConveyorAndCPM(IntakeSub.starWheelAndCPMSpeed);
            } else {
              RobotContainer.intakeSub.runConveyorAndCPM(0);
              IntakeSub.cyclingBall = false;
            }
          } else if (IntakeSub.targetTS == 3) {
            if (!RobotContainer.touchThree.get()) {
              RobotContainer.intakeSub.runConveyorAndCPM(IntakeSub.starWheelAndCPMSpeed);
              RobotContainer.intakeSub.runTurretFeeder(IntakeSub.conveyorMotorSpeed*.4);
            } else {
              RobotContainer.intakeSub.runConveyorAndCPM(0);
              RobotContainer.intakeSub.runTurretFeeder(0);
              IntakeSub. targetTS = 2;
              IntakeSub.cyclingBall = false;
            }
          }
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
