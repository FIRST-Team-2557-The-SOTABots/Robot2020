// package frc.robot.commands;

// import com.revrobotics.ColorMatchResult;

// import edu.wpi.first.wpilibj.util.Color;
// import frc.robot.Constants;

// public class CPMCommand extends CommandBase {
//   /**
//    * Creates a new ColorSpinCommand.
//    */
//   Color detectedColorRio;
//   Color detectedColorMXP;

//   ColorMatchResult l_Result;
//   ColorMatchResult r_Result;


// // Called when the command is initially scheduled.
//   @Override
//   public void initialize() {
//     Constants.l_colorMatcher.addColorMatch(Constants.kBlueTarget);
//     Constants.l_colorMatcher.addColorMatch(Constants.kGreenTarget);
//     Constants.l_colorMatcher.addColorMatch(Constants.kRedTarget);
//     Constants.l_colorMatcher.addColorMatch(Constants.kYellowTarget);

//     Constants.r_colorMatcher.addColorMatch(Constants.kBlueTarget);
//     Constants.r_colorMatcher.addColorMatch(Constants.kGreenTarget);
//     Constants.r_colorMatcher.addColorMatch(Constants.kRedTarget);
//     Constants.r_colorMatcher.addColorMatch(Constants.kYellowTarget);   
    
//   }

//   // Called every time the scheduler runs while the command is scheduled.
//   @Override
//   public void execute() {
    
//     detectedColorRio = Constants.l_colorSensor.getColor();
//     detectedColorMXP = Constants.r_colorSensor.getColor();

//     String colorStringRio;
//     String colorStringMXP;
//     lMatch = Constants.l_colorMatcher.matchClosestColor(detectedColorRio);
//     rMatch = Constants.r_colorMatcher.matchClosestColor(detectedColorMXP);

//     System.out.println("Executing");
//     if (lMatch.color == Constants.kBlueTarget) {
//       colorStringRio = "Blue";
//     } else if (lMatch.color == Constants.kRedTarget) {
//       colorStringRio = "Red";
//     } else if (lMatch.color == Constants.kGreenTarget) {
//       colorStringRio = "Green";
//     } else if (lMatch.color == Constants.kYellowTarget) {
//       colorStringRio = "Yellow";
//     } else {
//       colorStringRio = "Unknown";
//     }
    
//     if (rMatch.color == Constants.kBlueTarget) {
//       colorStringMXP = "Blue";
//     } else if (rMatch.color == Constants.kRedTarget) {
//       colorStringMXP = "Red";
//     } else if (rMatch.color == Constants.kGreenTarget) {
//       colorStringMXP = "Green";
//     } else if (rMatch.color == Constants.kYellowTarget) {
//       colorStringMXP = "Yellow";
//     } else {
//       colorStringMXP = "Unknown";
//     }

//     if(colorStringRio == "Red"){
//       startColor = 0;
//     }else if(colorStringRio == "Green"){
//       startColor = 1;
//     }else if(colorStringRio == "Blue"){
//       startColor = 2;
//     }else if(colorStringRio == "Yellow"){
//       startColor = 3;
//     }

//     colorsAway = targetColor - startColor;

//     ColorSpinSubsystem.spin((colorsAway * (colorMotorEncoderCount / 8)) + colorMotorEncoderCount / 4);

//     SmartDashboard.putNumber("RioRed", detectedColorRio.red);
//     SmartDashboard.putNumber("RioGreen", detectedColorRio.green);
//     SmartDashboard.putNumber("RioBlue", detectedColorRio.blue);
//     SmartDashboard.putNumber("MXPRed", detectedColorMXP.red);
//     SmartDashboard.putNumber("MXPGreen", detectedColorMXP.green);
//     SmartDashboard.putNumber("MXPBlue", detectedColorMXP.blue);
//     SmartDashboard.putString("Rio Color", colorStringRio);
//     SmartDashboard.putString("MXP Color", colorStringMXP);
  
//     // SmartDashboard.putString("This is the color Sting MXP", colorStringMXP);
//     // SmartDashboard.putString("This is the color Sting Rio", colorStringRio);

//     SmartDashboard.putNumber("ColorsAway", colorsAway);
//     SmartDashboard.putNumber("EncoderCount", colorsAway * (colorMotorEncoderCount / 8));

//     isFinished = true;

//   }

//   // Called once the command ends or is interrupted.
//   @Override
//   public void end(final boolean interrupted) {
//   }

//   // Returns true when the command should end.
//   @Override
//   public boolean isFinished() {
//     if(isFinished == true){
//       return true;
//     } else {
//       return false;
//     }
//   }
// }