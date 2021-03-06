package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.subsystems.IntakeSub;

public class AimCommand extends SequentialCommandGroup {
  public AimCommand(double hoodPos) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(
      new ParallelCommandGroup(
        new PIDTurret(),
        new PIDHood(hoodPos),
        new PIDFlywheel(16500)
      ),
      new ParallelCommandGroup(
        new RunCommand( () -> RobotContainer.intakeSub.runTurretFeeder(IntakeSub.CONVEYOR_MOTOR_SPEED), RobotContainer.intakeSub),
        new RunCommand( () -> RobotContainer.intakeSub.runConveyorAndCPM(IntakeSub.STAR_WHEEL_SPEED), RobotContainer.intakeSub),
        new PIDFlywheel()
      )

    );
  }
}
