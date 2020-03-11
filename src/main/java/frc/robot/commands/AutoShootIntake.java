package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.RobotContainer;
import frc.robot.subsystems.FlywheelSub;
import frc.robot.subsystems.IntakeSub;

public class AutoShootIntake extends CommandBase {

  boolean runConveyor = false;
  boolean finalBall = false;
  int numBall = 0;
  boolean shoot;
  boolean intaking;
  Timer t = new Timer();

  public AutoShootIntake(boolean intaking) {
    addRequirements(RobotContainer.flywheelSub);
    this.intaking = intaking;
  }

  @Override
  public void initialize() {
    shoot = false;
  }

  @Override
  public void execute() {
    System.out.println("0");
    if(!intaking){
      System.out.println("1");
      RobotContainer.intakeSub.intakeIn();
      RobotContainer.flywheelSub.spinFlywheels(1);
      System.out.println("1.5");
      if(RobotContainer.flywheelSub.getFlywheelSpeed() > 16000) {shoot = true;}

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

      if(numBall == 0){
        t.reset();
      }

      if(shoot && numBall != 0){
        RobotContainer.intakeSub.runConveyorAndCPM(IntakeSub.starWheelAndCPMSpeed);
        RobotContainer.intakeSub.runTurretFeeder(IntakeSub.conveyorMotorSpeed);
      }
    }else{
      System.out.println("1");
      RobotContainer.intakeSub.intakeOut();
      RobotContainer.intakeSub.runIntake(IntakeSub.intakeSpeed);
    }

  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    if(!intaking)return numBall == 0 && t.get()>5;
    return false;
  }
}
