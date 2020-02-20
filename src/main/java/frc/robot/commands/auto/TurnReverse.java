/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import java.util.List;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.Constants.AutoConstants;
import frc.robot.Constants.DriveConstants;

public class TurnReverse extends CommandBase {
  /**
   * Creates a new Forward.
   */
  public static double commandTime = 0;
  
  DifferentialDriveVoltageConstraint autoVoltageConstraint = new DifferentialDriveVoltageConstraint(
      new SimpleMotorFeedforward(DriveConstants.ksVoltsLow, DriveConstants.kvVoltSecondsPerMeterLow,
          DriveConstants.kaVoltSecondsSquaredPerMeterLow),
      DriveConstants.kDriveKinematics, 12);

  TrajectoryConfig config = new TrajectoryConfig(AutoConstants.kMaxSpeedMetersPerSecondLow,
      AutoConstants.kMaxAccelerationMetersPerSecondSquaredLow)
          // Add kinematics to ensure max speed is actually obeyed
          .setKinematics(DriveConstants.kDriveKinematics)
          // Apply the voltage co nstraint
          .addConstraint(autoVoltageConstraint);

  Trajectory exampleTrajectory = TrajectoryGenerator.generateTrajectory(
    new Pose2d(0,0,new Rotation2d(0)),
    List.of(
      // new Translation2d(1,1),
      // new Translation2d(2,0)
    ),
    // new Pose2d(3,0,new Rotation2d(Math.toRadians(0))), config);
  new Pose2d(1,-1,new Rotation2d(Math.toRadians(-90))), config); //y & rot are reverse

  RamseteCommand gordonRamsete = new RamseteCommand(exampleTrajectory, RobotContainer.driveSub::getPose,
      new RamseteController(AutoConstants.kRamseteB, AutoConstants.kRamseteZeta),
      new SimpleMotorFeedforward(DriveConstants.ksVoltsLow, DriveConstants.kvVoltSecondsPerMeterLow,
          DriveConstants.kaVoltSecondsSquaredPerMeterLow),
      DriveConstants.kDriveKinematics, RobotContainer.driveSub::getWheelSpeeds,
      new PIDController(DriveConstants.kPDriveVelLow, 0, 0), new PIDController(DriveConstants.kPDriveVelLow, 0, 0),
      // RamseteCommand passes volts to the callback
      RobotContainer.driveSub::tankDriveVolts, RobotContainer.driveSub);

  public TurnReverse() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.driveSub);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    commandTime = exampleTrajectory.getTotalTimeSeconds();
    RobotContainer.driveSub.zeroHeading();
    RobotContainer.driveSub.resetEncoders();    
    RobotContainer.driveSub.resetOdometry(new Pose2d(0,0,new Rotation2d(0)));
    // System.out.println("begin 2");
    DriveConstants.reverse = true;
    gordonRamsete.schedule();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // System.out.println(exampleTrajectory.getTotalTimeSeconds());
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