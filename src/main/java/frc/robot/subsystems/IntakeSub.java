 
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class IntakeSub extends SubsystemBase {

  public static final double BEAVER_TAIL_SPEED = 0.5;
  public static final double starWheelAndCPMSpeed = 0.5;
  public static final double conveyorMotorSpeed = 0.5;

  public static int targetTS; //which touch sensor are we looking for?
  public static boolean cyclingBall; 

  public IntakeSub() {
    targetTS = 2;
    cyclingBall = false;
  }

  public void runBeaverTail(double speed) {
    RobotContainer.beaverTail.set(speed);
  }

  public void angleBeaverTail() {
    if (RobotContainer.intakePistons.get() == Value.kForward) {
      RobotContainer.intakePistons.set(Value.kReverse);
    } else if (RobotContainer.intakePistons.get() == Value.kReverse) {
      RobotContainer.intakePistons.set(Value.kForward);
    }
  }

  public void runStarWheelAndCPM (double speed) {
    RobotContainer.starWheelAndCPM.set(speed);
  }

  public void runConveyorBelt(final double speed) {
    RobotContainer.conveyorMotor.set(speed);
  }

  @Override
  public void periodic() {

  }
}
