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

// //   l_colorSensor.getColor();
  
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

//   @Override
//   public void periodic() {
//     // This method will be called once per scheduler run
//   }
// }
