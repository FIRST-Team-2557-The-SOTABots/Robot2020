package frc.robot.commands.auto;

import java.io.IOException;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.auto.components.fiveball1;
import frc.robot.commands.auto.components.fiveball2;
import frc.robot.commands.auto.components.fiveball3;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class Fiveball extends SequentialCommandGroup {
  public Fiveball() {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    try {
      // addCommands(new fiveball1().withTimeout(fiveball1.commandTime+2), new fiveball2().withTimeout(fiveball2.commandTime+1), new fiveball3() );
      addCommands(new fiveball1().withTimeout(fiveball1.commandTime), new TurnReverse().withTimeout(TurnReverse.commandTime+1), new fiveball3() );
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
