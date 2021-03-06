package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;

public class DriveCommand extends CommandBase {

  boolean autoShift = true;

  public DriveCommand() {
    addRequirements(RobotContainer.driveSub);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    
    RobotContainer.driveSub.teleDrive();

    if(RobotContainer.driver.getPOV() == 180){
      autoShift = false;
    }
    if(RobotContainer.driver.getPOV() == 0){
      autoShift = true;
    }
    if(!Robot.auto){
      if(autoShift){
        if (RobotContainer.dsL.get() == Value.kReverse && Math.abs(RobotContainer.driveSub.getWheelVelocity())>2.1){ //1.8
          RobotContainer.dsL.set(Value.kForward);
        } else if (RobotContainer.dsL.get() == Value.kForward && Math.abs(RobotContainer.driveSub.getWheelVelocity()) < 0.8) { //1.2
          RobotContainer.dsL.set(Value.kReverse);
        }
      }else{
        if(RobotContainer.driver.getPOV() == 90){
          RobotContainer.dsL.set(Value.kForward);
        }else if(RobotContainer.driver.getPOV() == 270){
          RobotContainer.dsL.set(Value.kReverse);
        }
      }
    }

  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
