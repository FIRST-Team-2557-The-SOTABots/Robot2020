// /*----------------------------------------------------------------------------*/
// /* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
// /* Open Source Software - may be modified and shared by FRC teams. The code   */
// /* must be accompanied by the FIRST BSD license file in the root directory of */
// /* the project.                                                               */
// /*----------------------------------------------------------------------------*/

// package frc.robot.subsystems;

// import com.revrobotics.ColorMatch;
// import com.revrobotics.ColorMatchResult;
// import com.revrobotics.ColorSensorV3;

// import edu.wpi.first.wpilibj.I2C;
// import edu.wpi.first.wpilibj.util.Color;
// import edu.wpi.first.wpilibj2.command.SubsystemBase;

// public class CPMSub extends SubsystemBase {  
  
//   private static final I2C.Port i2c1 = I2C.Port.kOnboard;
//   private static final I2C.Port i2c2 = I2C.Port.kOnboard;
  
//   private static final ColorSensorV3 l_colorSensor = new ColorSensorV3(i2c1);
//   private static final ColorSensorV3 r_colorSensor = new ColorSensorV3(i2c2);

//   l_colorSensor.getColor();
  
//   private static final ColorMatch l_colorMatcher = new ColorMatch();
//   private static final ColorMatch r_colorMatcher = new ColorMatch();

//   public static final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
//   public static final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
//   public static final Color kRedTarget = ColorMatch.makeColor(0.458, 0.375, 0.135);
//   public static final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

//   l_colorMatcher.addColorMatch(kBlueTarget);
//   l_colorMatcher.addColorMatch(kGreenTarget);
//   l_colorMatcher.addColorMatch(kRedTarget);
//   l_colorMatcher.addColorMatch(kYellowTarget);

//   r_colorMatcher.addColorMatch(kBlueTarget);
//   r_colorMatcher.addColorMatch(kGreenTarget);
//   r_colorMatcher.addColorMatch(kRedTarget);
//   r_colorMatcher.addColorMatch(kYellowTarget);


//   public CPMSub() {
    
//   }

//   public static 

//   @Override
//   public void periodic() {
//     // This method will be called once per scheduler run
//   }
// }

// public class ColorSpinCommand extends CommandBase {
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
