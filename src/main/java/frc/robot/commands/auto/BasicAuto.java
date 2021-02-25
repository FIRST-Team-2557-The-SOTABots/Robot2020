package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.commands.AutoIntakeIntake;
import frc.robot.commands.AutoShootIntake;
import frc.robot.commands.PIDHood;
import frc.robot.commands.PIDTurret;
import frc.robot.commands.auto.paths.Move;
import frc.robot.subsystems.FlywheelSub;

public class BasicAuto extends SequentialCommandGroup {
  public BasicAuto() {
    addCommands(
      new ParallelDeadlineGroup(
        new AutoShootIntake(false).withInterrupt(() -> AutoShootIntake.finished),
        new PIDTurret(Constants.turretLine),
        new PIDHood(Constants.hoodAutoLine)
      )
    );
  }
}
