package frc.robot.subsystems;

import frc.robot.RobotContainer;

public class Projectile {

    private static double hoodHeight = 1.6875;
    private static double outerTargetHeight = 8.1875;
    private static double gravity = -32.2;

    public static double getProjectileVelocity(){
        double d = RobotContainer.lidarSub.getDistance();
        return Math.sqrt((-(Math.pow(d, 2) * (2 * gravity * (outerTargetHeight - hoodHeight)))
         / Math.pow((outerTargetHeight - hoodHeight), 2) + 2 * (gravity * Math.pow(d, 2) / 2) / (outerTargetHeight - hoodHeight)
        - 4 * gravity * (outerTargetHeight - hoodHeight)) / 2);
    }

    public static double getProjectileAngle(){
        double velocity = getProjectileVelocity();
        return Math.asin(Math.sqrt(-(2 * gravity * (outerTargetHeight - hoodHeight))/Math.pow(velocity, 2)));
    }
    
    public static double experimentalMath(){
        double d = RobotContainer.lidarSub.getDistance();
        return (d * 55.6122 + 137.245);
    }
    
}
