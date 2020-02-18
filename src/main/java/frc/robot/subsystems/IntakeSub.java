/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSub extends SubsystemBase {

  private static final WPI_TalonSRX bigIntakeAngler = new WPI_TalonSRX(64);
  private static final WPI_TalonSRX bigIntakeRunner = new WPI_TalonSRX(63);
  private static final WPI_TalonSRX smallIntakeAngler = new WPI_TalonSRX(62);
  private static final WPI_TalonSRX smallIntakeRunner = new WPI_TalonSRX(61);
  private static final WPI_TalonSRX conveyorMotor = new WPI_TalonSRX(60);

  public IntakeSub() {

  }

  public void angleBigIntake(double speed) {
    bigIntakeAngler.set(speed);
  }

  public void runBigIntake(double speed) {
    bigIntakeRunner.set(speed);
  }

  public void angleSmallIntake(double speed) {
    smallIntakeAngler.set(speed);
  }

  public void runSmallIntake(double speed) {
    smallIntakeRunner.set(speed);
  }

  public void runConveyorBelt(double speed) {
    conveyorMotor.set(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
