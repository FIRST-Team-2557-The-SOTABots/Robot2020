package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class PIDHood extends CommandBase {

  PIDController pidController;
  private final double kP = 0.0015;
  private final double kI = 0;
  private final double kD = 0;
  private final double tolerance = 7;
  private double setpoint;

  public PIDHood(double setpoint) {
    addRequirements(RobotContainer.hoodSub);
    this.setpoint = setpoint;
  }

  public void hoodPosition(){
    if(RobotContainer.manipulator.getPOV() == 90){
      setpoint = Constants.hoodFromTrench;
    }else if(RobotContainer.manipulator.getPOV() == 180){
      setpoint = Constants.hoodAutoLine;
    }else if(RobotContainer.manipulator.getPOV() == 0){
      setpoint = Constants.hoodTriangle;
    }
  }

  @Override
  public void initialize() {
    pidController = new PIDController(kP, kI, kD);
    pidController.disableContinuousInput(); 
    pidController.setTolerance(tolerance);
    pidController.reset();

    // setpoint = Projectile.getProjectileAngle();
  }

  @Override
  public void execute() { 
    hoodPosition();
    System.out.println("going with the PID hood");
    SmartDashboard.putNumber("Hood error!", pidController.getPositionError());
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
