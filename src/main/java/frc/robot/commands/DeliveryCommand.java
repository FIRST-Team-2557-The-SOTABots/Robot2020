// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class DeliveryCommand extends CommandBase {
  /** Creates a new DeliveryCommand. */
  public DeliveryCommand() {
    addRequirements(RobotContainer.deliverySub);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(RobotContainer.manipulator.getRawAxis(2) > 0.5){
      if(RobotContainer.my.get()) RobotContainer.deliverySub.runDelivery(-0.85);
      else RobotContainer.deliverySub.runDelivery(0.85);
    }else{
      RobotContainer.deliverySub.runDelivery(0);
    }

    if(RobotContainer.mbumperRight.get()) RobotContainer.deliverySub.runStarDelivery(Constants.STAR_WHEEL_SPEED);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
