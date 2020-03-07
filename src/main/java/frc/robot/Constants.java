package frc.robot;

import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile;

public final class Constants {
    public static final double hoodFromTrench = 600;
    public static final double hoodTriangle = 348;
    public static final double hoodAutoLine = 767;
    public static final double turretFromTrench = 6;
    public static final double turretClose = 0;
    public static final double ticksPerRevolutionLow = 19;
    public static final double ticksPerRevolutionHigh = 6.4;
    public static final double ratioGearLow = 18.86;
    public static final double ratioGearHigh = 6.45;
    public static final double wheelCircumferenceMeters = 6*Math.PI*0.0254; 
    //HoodTriangle:348
    //HoodAuto:
    //HoodTrench:

    //is that guys name Howard??
    // idk
    //Seth's granpa


    public static final class DriveConstants {
        // public static boolean reverse = true;
        public static boolean reverse = false;

        public static final double kTrackWidth =  0.584;
        // public static final double kTrackWidth =  0.5;
        public static final DifferentialDriveKinematics kDriveKinematics = new DifferentialDriveKinematics(kTrackWidth);

        public static final double ksVoltsLow = 0.147;
        // public static final double kvVoltSecondsPerMeterLow = 1.88;  //estimate
        public static final double kvVoltSecondsPerMeterLow = 4.47;  //characterize
        public static final double kaVoltSecondsSquaredPerMeterLow = 0.552; //0.1225

        public static final double kPDriveVelLow = 16.3; 

        public static final double ksVoltsHigh = 0.122;
        public static final double kvVoltSecondsPerMeterHigh = 0.64; //estimate
        // public static final double kvVoltSecondsPerMeterHigh = 1.63; //characterize
        public static final double kaVoltSecondsSquaredPerMeterHigh = 0.152;

        public static final double kPDriveVelHigh= 6.45;
    }

    public static final class AutoConstants {
        public static final double kMaxSpeedMetersPerSecondLow = 6;
        public static final double kMaxAccelerationMetersPerSecondSquaredLow = 6;
        public static final double kMaxAngularSpeedRadiansPerSecondLow = Math.PI; 
        public static final double kMaxAngularSpeedRadiansPerSecondSquaredLow = Math.PI*2; 

        public static final double kMaxSpeedMetersPerSecondHigh = 18.65;
        public static final double kMaxAccelerationMetersPerSecondSquaredHigh = 18.65;
        public static final double kMaxAngularSpeedRadiansPerSecondHigh = 2*Math.PI; 
        public static final double kMaxAngularSpeedRadiansPerSecondSquaredHigh = 2*Math.PI; 

        public static final TrapezoidProfile.Constraints kThetaControllerConstraintsLow = new 
        TrapezoidProfile.Constraints(kMaxAngularSpeedRadiansPerSecondLow, kMaxAngularSpeedRadiansPerSecondSquaredLow);

    // Reasonable baseline values for a RAMSETE follower in units of meters and seconds
        public static final double kRamseteB = 2;//2

        public static final double kRamseteZeta = 0.7;//.7
    }
}

