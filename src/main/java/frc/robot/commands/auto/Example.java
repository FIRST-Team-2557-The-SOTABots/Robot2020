/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import java.io.IOException;
import java.nio.file.Paths;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.Constants.AutoConstants;
import frc.robot.Constants.DriveConstants;

public class Example extends CommandBase {
  /**
   * Creates a new Forward.
   */
  Trajectory exampleTrajectory;// =
                               // TrajectoryUtil.fromPathweaverJson(Paths.get("/home/lvuser/deploy/output/example.wpilib.json"));
  RamseteCommand gordonRamsete;

  public Example() throws IOException {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.driveSub);
    
    exampleTrajectory = TrajectoryUtil.fromPathweaverJson(Paths.get("/home/lvuser/deploy/output/example.wpilib.json"));
   
    gordonRamsete = new RamseteCommand(
    exampleTrajectory,
    RobotContainer.driveSub::getPose,
    new RamseteController(AutoConstants.kRamseteB, AutoConstants.kRamseteZeta),
    new SimpleMotorFeedforward(DriveConstants.ksVoltsLow, DriveConstants.kvVoltSecondsPerMeterLow,
    DriveConstants.kaVoltSecondsSquaredPerMeterLow),
    DriveConstants.kDriveKinematics, 
    RobotContainer.driveSub::getWheelSpeeds,
    new PIDController(DriveConstants.kPDriveVelLow, 0, 0), 
    new PIDController(DriveConstants.kPDriveVelLow, 0, 0),
    // RamseteCommand passes volts to the callback
    RobotContainer.driveSub::tankDriveVolts, 
    RobotContainer.driveSub
    );
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    gordonRamsete.schedule();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.left.set(0);
    RobotContainer.right.set(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return gordonRamsete.isFinished();
  }
}
