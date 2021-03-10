package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.FlywheelSub;

public class PIDFlywheel extends CommandBase {
  
  private static PIDController pidController;
  private static final double KP = 0.00011;
  private static final double KI = 0.0001;
  private static final double KD = 0;
  private static final double TOLERANCE = 1;
  public double setpoint;
  public static boolean flywheelAtSetpoint;

  public PIDFlywheel(double setpoint) {
    pidController = new PIDController(KP, KI, KD);
    pidController.reset();
    pidController.disableContinuousInput();
    pidController.setTolerance(TOLERANCE);
    addRequirements(RobotContainer.flywheelSub);
    this.setpoint = setpoint;
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    double output = pidController.calculate(RobotContainer.flywheelSub.getFlywheelSpeed(), setpoint);
    System.out.println(setpoint);
    RobotContainer.flywheelSub.spinFlywheels(output);
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    // return pidController.atSetpoint();
    return false;
  }
}
