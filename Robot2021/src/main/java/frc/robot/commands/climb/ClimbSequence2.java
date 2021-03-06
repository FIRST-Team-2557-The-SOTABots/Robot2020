package frc.robot.commands.climb;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class ClimbSequence2 extends SequentialCommandGroup {
  /**
   * Creates a new ClimbSequence.
   */
  public ClimbSequence2() {
        addCommands(
            new UnlockClimb(),
            new PullWinch(),
            new AutoLift(2000, false),
            new AutoWinch(),
            new LockClimb()
         );

  
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());

  }
}
