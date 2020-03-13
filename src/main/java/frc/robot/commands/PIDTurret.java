package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class PIDTurret extends CommandBase {

  static PIDController pidController;
  private static final double kP = 0.025;
  private static final double kI = 0.0001;
  private static final double kD = 0;
  private static final double tolerance = 2;

  static double x;
  static double valid;
  static NetworkTable table;
  static NetworkTableEntry tx;
  static NetworkTableEntry tv;
  private static double setpoint = -2;

  public PIDTurret() {
    table = NetworkTableInstance.getDefault().getTable("limelight");
    addRequirements(RobotContainer.turretSub);
  }

  public PIDTurret(double setpoint) {
    table = NetworkTableInstance.getDefault().getTable("limelight");
    addRequirements(RobotContainer.turretSub);
    this.setpoint = setpoint;
  }

  public void turretPosition(){
    if(RobotContainer.manipulator.getPOV() == 90){
      setpoint = Constants.turretFromTrench;
    }else if(RobotContainer.manipulator.getPOV() == 180){
      setpoint = Constants.turretLine;
    }else if(RobotContainer.manipulator.getPOV() == 0){
      setpoint = Constants.turretTriangle;
    }
  }

  @Override
  public void initialize() {
    pidController = new PIDController(kP, kI, kD);
    pidController.disableContinuousInput();
    pidController.setTolerance(tolerance); 
    pidController.reset();
  }

  public static void getCamData() {
	  tx = table.getEntry("tx");
    tv = table.getEntry("tv");

    x = tx.getDouble(0.0);
    valid = tv.getDouble(0.0);
 }

  @Override
  public void execute() {
    turretPosition();
    getCamData();
    double output = -pidController.calculate(x, setpoint);
    RobotContainer.turretSub.rotate(output);

    SmartDashboard.putNumber("This is PID turret output", output);
    SmartDashboard.putNumber("This is PID turret error!!", pidController.getPositionError());
    SmartDashboard.putNumber("This is PID turret setpoint!", pidController.getSetpoint());
  }

  @Override
  public void end(boolean interrupted) {
    RobotContainer.turretSub.rotate(0);
  }

  @Override
  public boolean isFinished() {
    return pidController.atSetpoint();
  }
}
