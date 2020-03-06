package frc.robot.subsystems;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class CPMSub extends SubsystemBase {  
  
  private static final I2C.Port i2c1 = I2C.Port.kOnboard;
  private static final I2C.Port i2c2 = I2C.Port.kOnboard;
  
  private static final ColorSensorV3 l_colorSensor = new ColorSensorV3(i2c1);
  private static final ColorSensorV3 r_colorSensor = new ColorSensorV3(i2c2);

  private static final ColorMatch l_colorMatcher = new ColorMatch();
  private static final ColorMatch r_colorMatcher = new ColorMatch();

  public static final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  public static final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
  public static final Color kRedTarget = ColorMatch.makeColor(0.458, 0.375, 0.135);
  public static final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);
  
  public static final double starWheelAndCPMSpeed = 0.6;



  public CPMSub() {
    l_colorMatcher.addColorMatch(kBlueTarget);
    l_colorMatcher.addColorMatch(kGreenTarget);
    l_colorMatcher.addColorMatch(kRedTarget);
    l_colorMatcher.addColorMatch(kYellowTarget);
  
    r_colorMatcher.addColorMatch(kBlueTarget);
    r_colorMatcher.addColorMatch(kGreenTarget);
    r_colorMatcher.addColorMatch(kRedTarget);
    r_colorMatcher.addColorMatch(kYellowTarget);
  
  }

  public static String getColorL(){
    Color color = l_colorSensor.getColor();
    ColorMatchResult result = l_colorMatcher.matchClosestColor(color);
    String returnable;

    if (result.color == kBlueTarget) {
        returnable = "Blue";
      } else if (result.color == kRedTarget) {
        returnable = "Red";
      } else if (result.color == kGreenTarget) {
        returnable = "Green";
      } else if (result.color == kYellowTarget) {
        returnable = "Yellow";
      } else {
        returnable = "Unknown";
      }

      return returnable;
      
  }
  
  public void runStarWheelAndCPM (double speed) {
    RobotContainer.intake2.set(speed);
  }


  public static String getColorR(){
    Color color = r_colorSensor.getColor();
    ColorMatchResult result = r_colorMatcher.matchClosestColor(color);
    String returnable;

    if (result.color == kBlueTarget) {
        returnable = "Blue";
      } else if (result.color == kRedTarget) {
        returnable = "Red";
      } else if (result.color == kGreenTarget) {
        returnable = "Green";
      } else if (result.color == kYellowTarget) {
        returnable = "Yellow";
      } else {
        returnable = "Unknown";
      }

      return returnable;
      
  }

  public boolean onSameColor() {
      if (getColorR() == getColorL()) {
          return true;
      }
      return false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}