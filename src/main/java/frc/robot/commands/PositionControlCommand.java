/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.CPMSub;

public class PositionControlCommand extends CommandBase {
  
  String target;
  String currentColor;
  String[] colors1 = {"B","G","R","Y"};
  String[] colors2 = {"R","Y","B","G"};
  int numOfColorsAway;
  /**
   * Creates a new PositionControlCommand.
   */
  public PositionControlCommand() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    target = DriverStation.getInstance().getGameSpecificMessage();
    currentColor = CPMSub.getColorL();
    if(target == "G" || target == "R") {

      numOfColorsAway = CPMSub.findMatchingColorIndex(colors1, target) - CPMSub.findMatchingColorIndex(colors1, currentColor);

    } else if(target == "Y" || target == "B"){

      numOfColorsAway = CPMSub.findMatchingColorIndex(colors2, target) - CPMSub.findMatchingColorIndex(colors2, currentColor);

    }

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (numOfColorsAway <= 0) {
      RobotContainer.cpmSub.runStarWheelAndCPM(CPMSub.starWheelAndCPMSpeed);
    } else if (numOfColorsAway >= 0) {
      RobotContainer.cpmSub.runStarWheelAndCPM(-CPMSub.starWheelAndCPMSpeed);
    }
  }

  @Override
  public void end(boolean interrupted) {


  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (CPMSub.getColorL() == target && CPMSub.getColorR() == target) {
      return true;
    }
    return false;
  }
}
