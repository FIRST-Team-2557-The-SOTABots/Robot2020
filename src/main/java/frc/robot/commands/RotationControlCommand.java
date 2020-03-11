package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.CPMSub;

public class RotationControlCommand extends CommandBase {
  
  String startingColor;
  int numberOfRevs;
  double timeout = 0.5;
  Timer t = new Timer();

  public RotationControlCommand() {
    addRequirements(RobotContainer.cpmSub);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    startingColor = CPMSub.getColorL();
    numberOfRevs = 0;
    t.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    if(numberOfRevs < 3){
      //spin cpm motor
    }
    if (t.hasElapsed(timeout) && CPMSub.getColorL() == startingColor) {
      numberOfRevs+= 0.5;
      t.reset();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(numberOfRevs == 3) {
      return true;
    }
    return false;
  }
}
