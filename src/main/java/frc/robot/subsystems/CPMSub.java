package frc.robot.subsystems;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class CPMSub extends SubsystemBase {  
  
  private static final I2C.Port i2c1 = I2C.Port.kOnboard;
  private static final I2C.Port i2c2 = I2C.Port.kMXP;
  
  private static final ColorSensorV3 l_colorSensor = new ColorSensorV3(i2c1);
  private static final ColorSensorV3 r_colorSensor = new ColorSensorV3(i2c2);

  private static final ColorMatch l_colorMatcher = new ColorMatch();
  private static final ColorMatch r_colorMatcher = new ColorMatch();


  Color detectedColorRight;
  Color detectedColorLeft;

  public static final Color kBlueTargetRight = ColorMatch.makeColor(0.193, 0.361, 0.400); //R: 206 G: 83 B: 113 //0.193, 0.361, 0.400
  // public static final Color kGreenTargetRight = ColorMatch.makeColor(0.197, 0.561, 0.240);
  public static final Color kGreenTargetRight = ColorMatch.makeColor(0.227, 0.251, 0.511); //R: 17 G: 205 B: 155 //0.227, 0.251, 0.511
  public static final Color kRedTargetRight = ColorMatch.makeColor(0.227, 0.251, 0.511); //16,21,198) /0.227, 0.251, 0.511
  public static final Color kYellowTargetRight = ColorMatch.makeColor(0.283, 0.188, 0.101); //R: 27 G: 200 B: 101

  public static final Color kBlueTargetLeft = ColorMatch.makeColor(0.143, 0.427, 0.429);
  public static final Color kGreenTargetLeft = ColorMatch.makeColor(0.197, 0.561, 0.240);
  public static final Color kRedTargetLeft = ColorMatch.makeColor(0.458, 0.375, 0.135);
  public static final Color kYellowTargetLeft = ColorMatch.makeColor(0.361, 0.524, 0.113);
  
  public static final double starWheelSpeed = 0.6;
  public static final double CPMSpeed = 0.5;

  public static final String[] colors1 = { "B", "G", "R", "Y" };
  public static final String[] colors2 = { "R", "Y", "B", "G" };

  public CPMSub() {
    l_colorMatcher.addColorMatch(kBlueTargetLeft);
    l_colorMatcher.addColorMatch(kGreenTargetLeft);
    l_colorMatcher.addColorMatch(kRedTargetLeft);
    l_colorMatcher.addColorMatch(kYellowTargetLeft);

    r_colorMatcher.addColorMatch(kBlueTargetRight);
    r_colorMatcher.addColorMatch(kGreenTargetRight);
    r_colorMatcher.addColorMatch(kRedTargetRight);
    r_colorMatcher.addColorMatch(kYellowTargetRight);

  }

  public void printGetting(){

    detectedColorRight = r_colorSensor.getColor();
    detectedColorLeft = l_colorSensor.getColor();

    SmartDashboard.putNumber("Left Red detect", detectedColorRight.red);
    SmartDashboard.putNumber("Left Blue detect", detectedColorRight.blue);
    SmartDashboard.putNumber("Left Green detect", detectedColorRight.green);
    SmartDashboard.putNumber("Right Red detect", detectedColorLeft.red);
    SmartDashboard.putNumber("Right Blue detect", detectedColorLeft.blue);
    SmartDashboard.putNumber("Right Green detect", detectedColorLeft.green);

    SmartDashboard.putNumber("Left Red", l_colorSensor.getRed());
    SmartDashboard.putNumber("Left Blue", l_colorSensor.getBlue());
    SmartDashboard.putNumber("Left Green", l_colorSensor.getGreen());
    SmartDashboard.putNumber("Right Red", r_colorSensor.getRed());
    SmartDashboard.putNumber("Right Blue", r_colorSensor.getBlue());
    SmartDashboard.putNumber("Right Green", r_colorSensor.getGreen());
    
  }

  public static String getColorL() {
    Color color = l_colorSensor.getColor();
    ColorMatchResult result = l_colorMatcher.matchClosestColor(color);
    String returnable;

    // SmartDashboard.putString("resulted color!!", result.color.toString());

    if (result.color == kBlueTargetLeft) {
      // if(result.color == kGreenTargetLeft){
      //   returnable = "Green";
      // }else{
        returnable = "Blue";
      // }
    } else if (result.color == kRedTargetLeft) {
      // if(result.color == kYellowTargetLeft){
      //   returnable = "Yellow";
      // }else{
        returnable = "Red";
      // }
    } else if (result.color == kGreenTargetLeft) {
      returnable = "Green";
    } else if (result.color == kYellowTargetLeft) {
      returnable = "Yellow";
    } else {
      returnable = "Unknown";
    }

    return returnable;

  }

  public void runCPMAndIntake(double speed) {
    RobotContainer.intake2.set(speed);
  }

  public static String getColorR() {
    Color color = r_colorSensor.getColor();
    ColorMatchResult result = r_colorMatcher.matchClosestColor(color);
    String returnable;

    SmartDashboard.putString("Right result match", r_colorMatcher.matchClosestColor(color).toString());
    SmartDashboard.putString("resulted color!! Right", result.color.toString());


    if (result.color == kBlueTargetRight) {
        returnable = "Blue";
    } else if (result.color == kRedTargetRight) {
        returnable = "Red";
    } else if (result.color == kGreenTargetRight) {
      returnable = "Green";
    } else if (result.color == kYellowTargetRight) {
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

  public static int findMatchingColorIndex(String[] arr, String key) {
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] == key) {
        return i;
      }
    }
    return arr.length + 1;
  }

  public static String getTargetColor() {
    String target = DriverStation.getInstance().getGameSpecificMessage();

    if (target == "R" || target == "Y") {
      return colors1[findMatchingColorIndex(colors1, target) - 2];
    } else if (target == "B" || target == "G"){
      return colors2[findMatchingColorIndex(colors2, target) - 2];
    }
    return null;
  }//returns a single character representing the color the color sensors should be 
  //looking for to make the field sensor sense the proper target color

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}