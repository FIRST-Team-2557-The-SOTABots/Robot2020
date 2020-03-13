package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
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
  boolean startTimer = false;
  double tim = 0;
  Timer t = new Timer();
  public static boolean finished = false;

  public AutoShootIntake(boolean intaking) {
    addRequirements(RobotContainer.intakeSub, RobotContainer.flywheelSub);
    this.intaking = intaking;
  }

  @Override
  public void initialize() {
    t.reset();
    shoot = false;
    countBalls = 0;
    finished = false;
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
        RobotContainer.intakeSub.runConveyorAndCPM(IntakeSub.starWheelAndCPMSpeed);
        RobotContainer.intakeSub.runTurretFeeder(IntakeSub.conveyorMotorSpeed);
      }
    }else{
      RobotContainer.intakeSub.intakeOut();
      RobotContainer.intakeSub.runIntake(IntakeSub.intakeSpeed);
    }
    if(t.get() != 0){
      System.out.println("Timer is:  " + t.get());
    }

  }

  @Override
  public void end(boolean interrupted) {
    RobotContainer.intakeSub.runConveyorAndCPM(0);
    RobotContainer.intakeSub.runTurretFeeder(0);
    RobotContainer.intakeSub.intakeOut();
    finished = true;
  }

  @Override
  public boolean isFinished() {
    return countBalls()>=3 || t.get()>5 || DriverStation.getInstance().getMatchTime()-tim > 5;
  }

  int countBalls = 0;
  boolean prevTrue = false;
  public int countBalls(){
    if(!prevTrue && RobotContainer.touchThree.get()){
      countBalls++;
    }

    if(!RobotContainer.touchThree.get()) prevTrue = false;
    else prevTrue = true;

    System.out.println("counting: " + countBalls);
    System.out.println("prevTrue: " + prevTrue);
    return countBalls;
  }
}
