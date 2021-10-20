package frc.robot;


public final class Constants {
    // at home challenges
    // public static final double HOOD_LOW = 3000;
    // public static final double HOOD_NEAR = 1225; 
    // public static final double HOOD_MID = 1700;//1600
    // public static final double HOOD_FAR = 1650;

    // competition
    public static final double HOOD_LOW = 3000; // low goal
    public static final double HOOD_NEAR = 1245; // from line
    public static final double HOOD_MID = 1095; // from trench
    public static final double HOOD_FAR = 0; // from between line/low goal

    public static final int TURRET_CENTER_POSITION = 3905; // encoder counts
    public static final double TURRET_NEAR = -6;
    public static final double TURRET_MID = -7;
    public static final double TURRET_FAR = 0;
    public static final double TICKS_PER_REVOLUTION_LOW = 18.6;
    public static final double TICKS_PER_REVOLUTION_HIGH = 6.4;
    public static final double RATIO_GEAR_LOW = 18.86;
    public static final double RATIO_GEAR_HIGH = 6.45;
    public static final double WHEEL_CIRCUMFERENCE_METERS = 6*Math.PI*0.0254;

    // the RPM of the flywheel when it is ready to shoot
    public static final double FLYWHEEL_SHOOT_RPM = 5000; 
    public static final double FLYWHEEL_SHOOT_RPM_TOLERANCE = 500; 

    public static final double TIMED_DRIVE_ROTATIONS = 30;
    public static final double TIMED_DRIVE_SPEED = 0.5;

    // motor speeds
    public static final double STAR_WHEEL_SPEED = 0.3;

    // Drive Shoot Auto Constants
    public static final double DSA_DELIVERY_WAIT_TIME = 5.0; // how long until delivery starts
    public static final double DSA_DELIVERY_DURATION = 5.0;
    public static final double DSA_DELIVERY_SPEED = 0.85;
    public static final double DSA_FLYWHEEL_SPEED = 0.5;
    public static final class DriveConstants {
        public static final boolean REVERSE = false;
    }
}

