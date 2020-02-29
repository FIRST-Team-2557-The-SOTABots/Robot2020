package frc.robot.commands.auto.components;

 import java.io.IOException;
import java.nio.file.Paths;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import frc.robot.RobotContainer;
import frc.robot.Constants.AutoConstants;
import frc.robot.Constants.DriveConstants;

public class fiveball2 extends CommandBase {
  Trajectory exampleTrajectory;
  RamseteCommand gordonRamsete;
  public static double commandTime = 0;

  public fiveball2() throws IOException {
    addRequirements(RobotContainer.driveSub);
    
    exampleTrajectory = TrajectoryUtil.fromPathweaverJson(Paths.get("/home/lvuser/deploy/output/fiveball2.wpilib.json"));

    commandTime = exampleTrajectory.getTotalTimeSeconds();
   
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
