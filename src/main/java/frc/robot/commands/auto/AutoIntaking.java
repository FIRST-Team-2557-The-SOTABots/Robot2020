/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.AutoIntakeIntake;
import frc.robot.commands.AutoShootIntake;
import frc.robot.commands.auto.paths.Move;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class AutoIntaking extends SequentialCommandGroup {
  /**
   * Creates a new AutoIntaking.
   */
  public AutoIntaking() {

    addCommands(
      // new AutoDistanceDriveCommand(),
      new BasicAuto(),
      // new Move(1, false).withTimeout(Move.commandTime),
      new ParallelCommandGroup(
        new AutoIntakeIntake(),
        new Move(1, false).withTimeout(Move.commandTime)
        )
    );
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
  }
}
