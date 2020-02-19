/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class IntakeSub extends SubsystemBase {

  public IntakeSub() {

  }

  public void angleBigIntake(double speed) {
    RobotContainer.bigIntakeAngler.set(speed);
  }

  public void runBigIntake(double speed) {
    RobotContainer.bigIntakeRunner.set(speed);
  }

  public void angleSmallIntake(double speed) {
    RobotContainer.smallIntakeAngler.set(speed);
  }

  public void runSmallIntake(double speed) {
    RobotContainer.smallIntakeRunner.set(speed);
  }

  public void runConveyorBelt(double speed) {
    RobotContainer.conveyorMotor.set(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
