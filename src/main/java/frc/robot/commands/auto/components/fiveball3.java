package frc.robot.commands.auto.components;

 import java.io.IOException;
import java.nio.file.Paths;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.Constants.AutoConstants;
import frc.robot.Constants.DriveConstants;

public class fiveball3 extends CommandBase {
  Trajectory exampleTrajectory;
  RamseteCommand gordonRamsete;

  public fiveball3() throws IOException {
    addRequirements(RobotContainer.driveSub);
    
    exampleTrajectory = TrajectoryUtil.fromPathweaverJson(Paths.get("/home/lvuser/deploy/output/fiveball3.wpilib.json"));
   
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

  @Override
  public void initialize() {
    RobotContainer.driveSub.zeroHeading();
    RobotContainer.driveSub.resetEncoders();    
    RobotContainer.driveSub.resetOdometry(new Pose2d(0,0,new Rotation2d(0)));
    DriveConstants.reverse = true;
    gordonRamsete.schedule();
  }

  @Override
  public void execute() {
  }

  @Override
  public void end(boolean interrupted) {
    RobotContainer.left.set(0);
    RobotContainer.right.set(0);
  }

  @Override
  public boolean isFinished() {
    return gordonRamsete.isFinished();
  }
}
