package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class DriveCommand extends CommandBase {

  public DriveCommand() {
    addRequirements(RobotContainer.driveSub);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    
    // RobotContainer.driveSub.drive(RobotContainer.driver.getRawAxis(1), 0.6 * RobotContainer.driver.getRawAxis(4));
    RobotContainer.driveSub.teleDrive();

    // if (RobotContainer.driveSub.getCurrentGear() == 1 && Math.abs(RobotContainer.driveSub.getRotationSpeed(RobotContainer.driveSub.getCurrentGear())) > DriveSub.limitRotSpdGear1) {
    //   RobotContainer.driveSub.shift();
    // } else if (RobotContainer.driveSub.getCurrentGear() == 2 && Math.abs(RobotContainer.driveSub.getRotationSpeed(RobotContainer.driveSub.getCurrentGear())) < DriveSub.limitRotSpdGear1) {
    //   RobotContainer.driveSub.shift();
    // }
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
