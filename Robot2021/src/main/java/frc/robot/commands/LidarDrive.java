// package frc.robot.commands;

// import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
// import edu.wpi.first.wpilibj.controller.PIDController;
// import edu.wpi.first.wpilibj2.command.CommandBase;
// import frc.robot.RobotContainer;

// public class LidarDrive extends CommandBase {

//   PIDController pidController;
//   double distance;
//   private static final double TOLERANCE = 8;

//   public LidarDrive(final double distance) {
//     addRequirements(RobotContainer.driveSub);

//     this.distance = distance;
//   }

//   @Override
//   public void initialize() {
//     pidController = new PIDController(0, 0, 0);
//     pidController.disableContinuousInput();
//     pidController.setTolerance(100);
//     pidController.reset();
//     if (RobotContainer.driveSub.getCurrentGear() != 1) {
//       RobotContainer.dsL.set(Value.kForward);
//     }
//   }

//   @Override
//   public void execute() {
//     double output = pidController.calculate(RobotContainer.lidarSub.getDistance());

//     if (distance > 0) {
//       output *= 1;
//     } else {
//       output *= 1;
//     }

//     RobotContainer.driveSub.drive(output,0);
//   }

//   @Override
//   public void end(final boolean interrupted) {
//     RobotContainer.driveSub.drive(0, 0);

//   }

//   @Override
//   public boolean isFinished() {
//     return pidController.atSetpoint();
//   }
// }
