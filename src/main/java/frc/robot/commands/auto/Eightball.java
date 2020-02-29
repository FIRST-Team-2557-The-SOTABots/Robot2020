 
package frc.robot.commands.auto;

import java.io.IOException;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.auto.components.eightball1;
import frc.robot.commands.auto.components.eightball2;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class Eightball extends SequentialCommandGroup {
  /**
   * Creates a new Eightball.
   */
  public Eightball() {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    try {
      addCommands(new eightball1(), new eightball2());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
