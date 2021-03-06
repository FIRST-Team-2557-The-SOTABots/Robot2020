package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class IntakeSub extends SubsystemBase {

  public static final double intakeSpeed = 1;
  public static final double starWheelAndCPMSpeed = 0.8;
  public static final double conveyorMotorSpeed = -1;

  public static int targetTS; //which touch sensor are we looking for?
  public static boolean cyclingBall; 

  public IntakeSub() {
    targetTS = 3;
    cyclingBall = false;
  }

  public void runIntake(double speed) {
    RobotContainer.intake1.set(speed);
  }

  public void angleIntake() {
    if (RobotContainer.intakePistons.get() == Value.kForward) {
      RobotContainer.intakePistons.set(Value.kReverse);
    } else if (RobotContainer.intakePistons.get() == Value.kReverse) {
      RobotContainer.intakePistons.set(Value.kForward);
    }
  }

  public void intakeIn() {
    //Value.kForward is in
    RobotContainer.intakePistons.set(Value.kForward);
  }

  public void intakeOut() {
    RobotContainer.intakePistons.set(Value.kReverse);
  }

  public void runConveyorAndCPM (double speed) {
    RobotContainer.intake2.set(speed);
  }

  public void runTurretFeeder(double speed) {
    RobotContainer.intake3.set(speed);
  }

  @Override
  public void periodic() {

  }
}
