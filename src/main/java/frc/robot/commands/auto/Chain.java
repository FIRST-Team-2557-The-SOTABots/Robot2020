 
package frc.robot.commands.auto;

import java.io.IOException;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class Chain extends SequentialCommandGroup {
  /**
   * Creates a new Chain.
   * 
   * @throws IOException
   */
  public Chain() throws IOException {
    addCommands(
      new Forward(1).withTimeout(Forward.commandTime),
      new TurnReverse()
    );
  }
}
