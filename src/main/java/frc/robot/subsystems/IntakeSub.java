package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class IntakeSub extends SubsystemBase {

  public static final double INTAKE_SPEED = 1;
  public static final double STAR_WHEEL_SPEED = 0.8;
  public static final double CONVEYOR_MOTOR_SPEED = -1;

  public IntakeSub() {
  }

  public void runIntake(double speed) {
    RobotContainer.intake.set(speed);
  }

  public void angleIntake() {
    // if (RobotContainer.intakePistons.get() == Value.kForward) {
    //   RobotContainer.intakePistons.set(Value.kReverse);
    // } else if (RobotContainer.intakePistons.get() == Value.kReverse) {
    //   RobotContainer.intakePistons.set(Value.kForward);
    // }
  }

  public void intakeIn() {
    //Value.kForward is in
    RobotContainer.intakePistons.set(Value.kForward);
  }

  public void intakeOut() {
    RobotContainer.intakePistons.set(Value.kReverse);
  }

  @Override
  public void periodic() {

  }
}
