package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class PIDHood extends CommandBase {

  PIDController pidController;
  private final double KP = 0.0014;
  private final double KI = 0.0013;
  private final double KD = 0;
  private final double TOLERANCE = 7;
  private double setpoint;

  public PIDHood(double setpoint) {
    addRequirements(RobotContainer.hoodSub);
    this.setpoint = setpoint;
  }

  public void hoodPosition(){
    if(RobotContainer.manipulator.getPOV() == 90){
      setpoint = Constants.HOOD_FROM_TRENCH;
    }else if(RobotContainer.manipulator.getPOV() == 180){
      setpoint = Constants.HOOD_AUTO_LINE;
    }else if(RobotContainer.manipulator.getPOV() == 0){
      setpoint = Constants.HOOD_TRIANGLE;
    }
  }

  @Override
  public void initialize() {
    pidController = new PIDController(KP, KI, KD);
    pidController.disableContinuousInput(); 
    pidController.setTolerance(TOLERANCE);
    pidController.reset();

    // setpoint = Projectile.getProjectileAngle();
  }

  @Override
  public void execute() { 
    hoodPosition();
    System.out.println("going with the PID hood");
    SmartDashboard.putNumber("Hood error!", pidController.getPositionError());
    SmartDashboard.putNumber("Hood setpoint", pidController.getSetpoint());
    double output = -pidController.calculate(RobotContainer.hoodSub.getHoodPos(), setpoint);

    SmartDashboard.putNumber("Hood PID output", output);
    RobotContainer.hoodSub.angleHood(output);
  }

  @Override
  public void end(final boolean interrupted) {
    RobotContainer.hoodSub.angleHood(0);
  }

  @Override
  public boolean isFinished() {
    return pidController.atSetpoint();
  }
}
