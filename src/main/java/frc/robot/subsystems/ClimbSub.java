/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class ClimbSub extends SubsystemBase {

  public ClimbSub() {

  }

  public void lift(double speed){
    RobotContainer.lift.set(speed);
  }

  public void testLift(){

    //unshift dog shift
    //go up, amiright
    //all locks engaged
    //

    if(RobotContainer.mbumperLeft.get() && RobotContainer.lift.getSensorCollection().getQuadraturePosition() < 0){
      RobotContainer.lift.set(.3);
    }else if(RobotContainer.mbumperRight.get() && RobotContainer.lift.getSensorCollection().getQuadraturePosition() > -16000){
      RobotContainer.lift.set(-.3);
    }else{
      RobotContainer.lift.set(0);
    }

    // if(RobotContainer.dback.get()){
    //   lockClimb();
    // }

    // if(RobotContainer.dstart.get()){
    //   unlockClimb();
    // }

    // if(RobotContainer.driver.getPOV() == 270){
    //   engageWinch();
    // }

    // if(RobotContainer.driver.getPOV() == 90){
    //   disengageWinch();
    // }

  }

  public void lockClimb(){
    RobotContainer.climbLock.set(Value.kReverse);
  }

  public void unlockClimb(){
    RobotContainer.climbLock.set(Value.kForward);
  }

  public void engageWinch(){
    RobotContainer.winchShift.set(Value.kReverse);
  }

  public void disengageWinch(){
    RobotContainer.winchShift.set(Value.kForward);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
