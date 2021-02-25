package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.CPMSub;

public class PositionControlCommand extends CommandBase {
  
  String target;
  String currentColor;
  String[] colors1 = {"B","G","R","Y"};
  String[] colors2 = {"R","Y","B","G"};
  int numOfColorsAway;
  public PositionControlCommand() {
  }

  @Override
  public void initialize() {
    target = CPMSub.getTargetColor();
    currentColor = CPMSub.getColorL();
    if(target == "G" || target == "R") {

      numOfColorsAway = CPMSub.findMatchingColorIndex(colors1, target) - CPMSub.findMatchingColorIndex(colors1, currentColor);

    } else if(target == "Y" || target == "B"){

      numOfColorsAway = CPMSub.findMatchingColorIndex(colors2, target) - CPMSub.findMatchingColorIndex(colors2, currentColor);

    } else {
      numOfColorsAway = 100000000;
    }

  }

  @Override
  public void execute() {

    RobotContainer.cpmSub.printGetting();

    SmartDashboard.putString("Get Color L",  CPMSub.getColorL().toString());
    SmartDashboard.putString("Get Color R",  CPMSub.getColorR().toString());

    // SmartDashboard.putString("Get Target Color",  CPMSub.getTargetColor());
    
      RobotContainer.intake2.set(-0.3);
      //spin cpm motor
    // if (numOfColorsAway != 100000000){
    //   if (numOfColorsAway <= 0) {
    //     RobotContainer.cpmSub.runCPMAndIntake(CPMSub.CPMSpeed);
    //   } else if (numOfColorsAway >= 0) {
    //     RobotContainer.cpmSub.runCPMAndIntake(-CPMSub.CPMSpeed);
    //   }
    // }
  }

  @Override
  public void end(boolean interrupted) {
    
  }

  @Override
  public boolean isFinished() {
    if (CPMSub.getColorL() == target && CPMSub.getColorR() == target) {
      return true;
    }
    return false;
  }
}
