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
        new PIDFlywheel(16000)
      ),
      new ParallelCommandGroup(
        new RunCommand( () -> RobotContainer.intakeSub.runConveyorBelt(IntakeSub.conveyorMotorSpeed), RobotContainer.intakeSub),
        new RunCommand( () -> RobotContainer.intakeSub.runStarWheelAndCPM(IntakeSub.starWheelAndCPMSpeed), RobotContainer.intakeSub),
        new PIDFlywheel()
      )

    );
  }
}
