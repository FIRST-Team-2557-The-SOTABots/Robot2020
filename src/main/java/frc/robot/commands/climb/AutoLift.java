package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class AutoLift extends CommandBase {
  boolean up2;
  boolean first = false;
  double count2;
  Timer t = new Timer();
  public AutoLift(double count, boolean up) {
    addRequirements(RobotContainer.climbSub);
    count2=count;//-14000 to work
    up2 = up;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    t.reset();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putBoolean("up in command", up2);
    System.out.println("climb go");
    // if(RobotContainer.lift.getSensorCollection().getQuadratureVelocity() == 0 && t.get() > 3) {
      // RobotContainer.climbSub.autoLift(0, up2);
    // }
    // else RobotContainer.climbSub.autoLift(count2, up2);

    // RobotContainer.climbSub.autoLift(count2, up2);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // if(RobotContainer.lift.getSensorCollection().getQuadraturePosition() > -14000 ){//&& count2 == 14000){
    //   return false;
    // }else if(RobotContainer.lift.getSensorCollection().getQuadraturePosition() > -13000){ //&& count2 == 13000){
    //   return false;
    // }
    return true;
  }
}
