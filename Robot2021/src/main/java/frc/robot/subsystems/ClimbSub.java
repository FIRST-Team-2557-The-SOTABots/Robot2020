package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class ClimbSub extends SubsystemBase {

  public ClimbSub() {

  }

  public void lift(double speed){
    // RobotContainer.lift.set(speed);
  }

  public void autoLift(double count, boolean up){
    // SmartDashboard.putBoolean("lift condition for up", RobotContainer.lift.getSensorCollection().getQuadraturePosition() > -count);
    // SmartDashboard.putBoolean("this is up", up);

    // if(RobotContainer.lift.getSensorCollection().getQuadraturePosition() > -count && up){
    //   System.out.println("climb up");
    //   RobotContainer.lift.set(-.3);
    // }else if(RobotContainer.lift.getSensorCollection().getQuadraturePosition() < -count && !up){
    //   System.out.println("climb down");
    //   RobotContainer.lift.set(.2);
    // }else{
    //   System.out.println("climb no");
    //   RobotContainer.lift.set(0);
    // }
  }

  public void autoWinch(){
     RobotContainer.winch1.set(.3); //down positive
     RobotContainer.winch2.set(-.3);
  }

  public void testLift(){

    //unshift dog shift
    //go up, amiright
    //all locks engaged
    //

    if(RobotContainer.ta.get()){
      RobotContainer.winch1.set(.3); //down positive
      RobotContainer.winch2.set(-.3); //down negative
    }else {
      RobotContainer.winch1.set(0);
      RobotContainer.winch2.set(0);
    }

    // if(RobotContainer.tbumperLeft.get()){//down
      // RobotContainer.lift.set(.3);
    // }else if(RobotContainer.tbumperRight.get() && RobotContainer.lift.getSensorCollection().getQuadraturePosition() > -14500){ // && RobotContainer.lift.getSensorCollection().getQuadraturePosition() > -14500
      // RobotContainer.lift.set(-.3);
    // }else{
      // RobotContainer.lift.set(0);
    // }

    if(RobotContainer.tback.get()){
      lockClimb();
    }

    if(RobotContainer.tstart.get()){
      unlockClimb();
    }

    if(RobotContainer.testStick.getPOV() == 270){
      pullWinch();
    }

    if(RobotContainer.testStick.getPOV() == 90){
      freespinWinch();
    }

  }

  public void lockClimb(){
    RobotContainer.climbLock.set(Value.kReverse);
  }

  public void unlockClimb(){
    RobotContainer.climbLock.set(Value.kForward);
  }

  public void pullWinch(){
    RobotContainer.winchShift.set(Value.kReverse);
  }

  public void freespinWinch(){
    RobotContainer.winchShift.set(Value.kForward);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
