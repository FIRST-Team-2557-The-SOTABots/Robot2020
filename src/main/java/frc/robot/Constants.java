package frc.robot;

import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile;

public final class Constants {
    public static final double HOOD_FROM_TRENCH = 930;
    public static final double HOOD_TRIANGLE = 550;
    public static final double HOOD_AUTO_LINE = 1075;
    public static final double TURRET_FROM_TRENCH = -6;
    public static final double TURRET_LINE = -7;
    public static final double TURRET_TRIANGLE = 0;
    public static final double TICKS_PER_REVOLUTION_LOW = 18.6;
    public static final double TICKS_PER_REVOLUTION_HIGH = 6.4;
    public static final double RATIO_GEAR_LOW = 18.86;
    public static final double RATIO_GEAR_HIGH = 6.45;
    public static final double WHEEL_CIRCUMFERENCE_METERS = 6*Math.PI*0.0254; 
    //HOOD_TRIANGLE: 590
    //HoodLine: 1117
    //HoodTrench: 

    //is that guys name Howard??
    // idk
    //Seth's granpa


    public static final class DriveConstants {
        // public static boolean reverse = true;
        public static boolean reverse = false;

        public static final double K_TRACK_WIDTH =  0.584/1.5;
        // public static final double kTrackWidth =  0.0000001;
        public static final DifferentialDriveKinematics kDriveKinematics = new DifferentialDriveKinematics(K_TRACK_WIDTH);

        public static final double KS_VOLTS_LOW = 0.174;
        // public static final double kvVoltSecondsPerMeterLow = 1.88;  //estimate
        public static final double KV_VOLT_SECONDS_PER_METER_LOW = 4.74;  //characterize
        public static final double KA_VOLT_SECONDS_SQUARED_PER_METER_LOW = 0.6;

        public static final double KP_DRIVE_VEL_LOW = 3; 

        public static final double KS_VOLTS_HIGH = 0.122;
        public static final double KV_VOLT_SECONDS_PER_METER_HIGH = 0.64; //estimate
        // public static final double kvVoltSecondsPerMeterHigh = 1.63; //characterize
        public static final double KA_VOLT_SECONDS_SQUARED_PER_METER_HIGH = 0.152;

        public static final double KP_DRIVE_VEL_HIGH = 6.45;
    }
}

