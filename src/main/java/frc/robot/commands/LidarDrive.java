// /*----------------------------------------------------------------------------*/
// /* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
// /* Open Source Software - may be modified and shared by FRC teams. The code   */
// /* must be accompanied by the FIRST BSD license file in the root directory of */
// /* the project.                                                               */
// /*----------------------------------------------------------------------------*/

// package frc.robot.commands;

// import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
// import edu.wpi.first.wpilibj2.command.CommandBase;
// import frc.robot.RobotContainer;
// import frc.robot.subsystems.DriveSub;

// public class LidarDrive extends CommandBase {

//   double distance;
//   private static final double TOLERANCE = 8;

//   public LidarDrive(double distance) {
//     addRequirements(RobotContainer.driveSub);

//     this.distance = distance;
//     // Use addRequirements() here to declare subsystem dependencies.
//   }

//   // Called when the command is initially scheduled.
//   @Override
//   public void initialize() {
//     if (RobotContainer.driveSub.getCurrentGear() != 1) {
//       DriveSub.dsL.set(Value.kForward);
//     }
//   }

//   // Called every time the scheduler runs while the command is scheduled.
//   @Override
//   public void execute() {
//     if (distance > 0) {
//       RobotContainer.driveSub.drive(0.5, 0);
//     } else {
//       RobotContainer.driveSub.drive(-.5, 0);
//     }
//   }

//   // Called once the command ends or is interrupted.
//   @Override
//   public void end(boolean interrupted) {
//     RobotContainer.driveSub.drive(0, 0);

//   }

//   // Returns true when the command should end.
//   @Override
//   public boolean isFinished() {
//     if(Math.abs(RobotContainer.lidarSub.getDistance()- distance) < TOLERANCE)return true;
//     return false;
//   }
// }
