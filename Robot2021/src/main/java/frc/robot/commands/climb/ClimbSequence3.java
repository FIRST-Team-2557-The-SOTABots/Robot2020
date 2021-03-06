package frc.robot.commands.climb;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class ClimbSequence3 extends SequentialCommandGroup {
  /**
   * Creates a new ClimbSequence.
   */
  public ClimbSequence3() {
        addCommands(
            new UnlockClimb(),
            new FreespinWinch(),
            new AutoLift(-10000, false)
         );

  
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());

  }
}
