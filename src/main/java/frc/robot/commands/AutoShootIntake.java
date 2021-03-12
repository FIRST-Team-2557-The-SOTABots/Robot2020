package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.IntakeSub;

public class AutoShootIntake extends CommandBase {

  boolean shoot;
  boolean intaking;
  boolean startTimer = false;
  double tim = 0;
  Timer t = new Timer();

  public AutoShootIntake(boolean intaking) {
    addRequirements(RobotContainer.intakeSub, RobotContainer.flywheelSub);
    this.intaking = intaking;
  }

  @Override
  public void initialize() {
    t.reset();
    shoot = false;
    countBalls = 0;
    RobotContainer.intakeSub.intakeIn();
  }

  @Override
  public void execute() {
    System.out.println("Intaking:  " + intaking);
    if(!intaking){
      RobotContainer.flywheelSub.spinFlywheels(1);
      if(RobotContainer.flywheelSub.getFlywheelSpeed() > 16000) {//16000
        shoot = true;
        if(!startTimer){
          t.start();
          startTimer = true;
          tim = DriverStation.getInstance().getMatchTime();
        }
      }

      if(shoot){
        // RobotContainer.intakeSub.runTurretFeeder(IntakeSub.CONVEYOR_MOTOR_SPEED);
      }
    }else{
      RobotContainer.intakeSub.intakeOut();
      RobotContainer.intakeSub.runIntake(IntakeSub.INTAKE_SPEED);
    }
    if(t.get() != 0){
      System.out.println("Timer is:  " + t.get());
    }

  }

  @Override
  public void end(boolean interrupted) {
    // RobotContainer.intakeSub.runTurretFeeder(0);
    RobotContainer.intakeSub.intakeOut();
  }

  @Override
  public boolean isFinished() {
    return true; //temp
  }

  int countBalls = 0;
  boolean prevTrue = false;
}
