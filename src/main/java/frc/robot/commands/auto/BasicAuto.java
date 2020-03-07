package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.commands.PIDHood;
import frc.robot.commands.PIDTurret;
import frc.robot.commands.auto.paths.Forward;
import frc.robot.subsystems.FlywheelSub;

public class BasicAuto extends SequentialCommandGroup {
  public BasicAuto() {

    addCommands(
      new ParallelCommandGroup(
        new Forward(2),
        new PIDTurret(),
        new PIDHood(Constants.hoodTriangle)),
      new RunCommand( () -> RobotContainer.flywheelSub.spinFlywheels(FlywheelSub.flywheelSpeed), RobotContainer.flywheelSub) 
        );

  }
}
