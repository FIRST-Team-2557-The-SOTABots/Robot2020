 
package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.FlywheelSub;

public class PIDFlywheel extends CommandBase {
  
  private static PIDController pidController;
  private static final double kP = 0;
  private static final double kI = 0;
  private static final double kD = 0;
  private static final double tolerance = 1;
  public double setpoint;
  public static boolean flywheelAtSetpoint;

  public PIDFlywheel() {
    pidController = new PIDController(kP, kI, kD);
    pidController.reset();
    pidController.disableContinuousInput();
    pidController.setTolerance(tolerance);
    addRequirements(RobotContainer.flywheelSub);
    setpoint = FlywheelSub.getRequiredRPM();
  }

  public PIDFlywheel(double setpoint) {
    pidController = new PIDController(kP, kI, kD);
    pidController.reset();
    pidController.disableContinuousInput();
    pidController.setTolerance(tolerance);
    addRequirements(RobotContainer.flywheelSub);
    this.setpoint = setpoint;
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    double output = pidController.calculate(RobotContainer.flywheelSub.getFlywheelSpeed(), setpoint);
    RobotContainer.flywheelSub.spinFlywheel(output);
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    if(pidController.atSetpoint()) {
      flywheelAtSetpoint = true;
    } else {
      flywheelAtSetpoint = false;
    }
    return pidController.atSetpoint();
  }
}
