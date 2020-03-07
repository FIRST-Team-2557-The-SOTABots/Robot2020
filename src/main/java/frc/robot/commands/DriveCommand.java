package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveSub;

public class DriveCommand extends CommandBase {

  public DriveCommand() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.driveSub);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    RobotContainer.driveSub.teleDrive();
    
    if (RobotContainer.dsL.get() == Value.kReverse && Math.abs(RobotContainer.driveSub.getWheelVelocity())>2.1){ //1.8
      RobotContainer.dsL.set(Value.kForward);
    } else if (RobotContainer.dsL.get() == Value.kForward && Math.abs(RobotContainer.driveSub.getWheelVelocity()) < 0.8) { //1.2
      RobotContainer.dsL.set(Value.kReverse);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
