package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class PIDHood extends CommandBase {

  PIDController pidController;
  private final double kP = 0;
  private final double kI = 0;
  private final double kD = 0;
  private final double tolerance = 100;
  private double setpoint;

  public PIDHood(double setpoint) {
    addRequirements(RobotContainer.hoodSub);
    this.setpoint = setpoint;
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
    double output = pidController.calculate(RobotContainer.hoodSub.getHoodAngle(), setpoint);
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
