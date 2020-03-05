package frc.robot.commands.auto.paths;

import java.io.IOException;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.auto.components.secondary1;
import frc.robot.commands.auto.components.secondary2;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class Secondary extends SequentialCommandGroup {
  public Secondary() {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    try {
      addCommands(new secondary1(), new secondary2());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
