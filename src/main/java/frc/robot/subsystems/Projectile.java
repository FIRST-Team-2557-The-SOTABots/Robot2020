/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.RobotContainer;

/**
 * Add your docs here.
 */
public class Projectile {

    private static double hoodHeight = 2;
    private static double outerTargetHeight = 8.1875;
    private static double gravity = -32.2;

    public static double getProjectileVelocity(){
        double d = RobotContainer.lidarSub.getDistance();
        return Math.sqrt((-(Math.pow(d, 2) * (2 * gravity * (outerTargetHeight - hoodHeight)))
         / Math.pow((outerTargetHeight - hoodHeight), 2) + 2 * (gravity * Math.pow(d, 2) / 2) / (outerTargetHeight - hoodHeight)
        - Math.pow(2, 2) * gravity * (outerTargetHeight - hoodHeight)) / 2);
      }

    public static double getProjectileAngle(){
        double velocity = getProjectileVelocity();
        return Math.asin(Math.sqrt(-(2 * gravity * (outerTargetHeight - hoodHeight))/Math.pow(velocity, 2)));
    }
    
}