/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot;

import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.util.Units;

public final class Constants {

    // General Constants
    public static final int PCM_ID = 3;
    public static final int TIMEOUT_MS = 30;

    // Drivetrain Constants
    public static final double S_DRIVE = 0.22;// volts
    public static final double V_DRIVE = 1.98;// volts * s/m
    public static final double A_DRIVE = 0.2;// volts * s^2/m

    public static final double P_DRIVE = 8.5;

    public static final double TRACK_WIDTH_METERS = 0.69;

    public static final double MAX_VELOCITY = 3;// m/s
    public static final double MAX_ACCELERATION = 3;// m/s^2

    public static final double RAMSETE_B = 2;
    public static final double RAMSETE_ZETA = 0.7;

    /**
     * Motor neutral dead-band, set to the minimum 0.1%.
     */
    public static final double NEUTRAL_DEADBAND = 0.05;
    public static final int UNITS_PER_REVOLUTION = 2048; /* this is constant for Talon FX */

    // Gains
    public static final int TALON_PRIMARY_CLOSED_LOOP = 0;

    // Turret Constants                                     -
    public static final int TURRET_ENCODER_LIMIT = 85; // degree of freedom, if not met seeking will be required
    public static final double TURRT_ROTATION_SPEED = 0.15; // percentage of output for turret
    public static final int TURRET_PID_SLOT = 0;

    public static final double P_TURRET = 0.1;
    public static final double I_TURRET = 0.0;
    public static final double D_TURRET = 0.0;
    public static final double F_TURRET = 0.0;
    public static final double IZONE_TURRET = 100;

    // Shooter Constants
    public static final double SHOOTER_DEADBAND = 0.02;
    public static final double SHOOTER_ANGLE = 26.5;// in degrees
    public static final double GRAVITY = -9.8;// in m/s2

    // Limelight Constants
    public static final double MOUNTED_ANGLE = 26.5;// in degrees
    public static final double PORT_HEIGHT = Units.inchesToMeters(98.25);
    public static final double HEIGHT_ABOVE_GROUND = .45;// tbd in meter also
    public static final double APPROXIMATE_ANGLE = 1.5;// degrees

    // Pigeon Constants
    public static final int PIGEON_PORT = 0;

    // DriveTrain and Driver Controller Constants

    //Intake Constants
    public static final double INTAKE_SPEED_IN = -0.4;
    public static final double INTAKE_SPEED_OUT = 0.4;
    public static final int INTAKE_ID = 15;

    //Storage Constants
    public static final double STORAGE_SPIN = 0.5;
    public static final double STORAGE_SPIN_SPEED = 5;
    //what is the speed in inches per second?
    

    // Subsystem Constants
    public static final class DRIVE {
        public static final int FORWARD_CHANNEL_ID = 6;
        public static final int REVERSE_CHANNEL_ID = 7;
        public static final int RIGHT_MASTER_ID = 5;
        public static final int RIGHT_FOLLOWER_1_ID = 4;
        public static final int RIGHT_FOLLOWER_2_ID = 9;
        public static final int LEFT_MASTER_ID = 6;
        public static final int LEFT_FOLLOWER_1_ID = 7;
        public static final int LEFT_FOLLOWER_2_ID = 8;
        public static final double SPEED_MULTIPLIER = 1;
        public static final double TURN_MULTIPLIER = 0.4;

        public static final double DECELERATION_MULTIPLIER = 0.5;
        // 0 is infinite deceleration (AKA no change ever) and 1 is no deceleration (instant change)
        public static final double SLOWDOWN_MULTIPLIER = 0.5;
    }

    public static final class CONTROLS {
        public static final int JOYSTICK_PORT = 0;
        public static final int ARCADE_JOYSTICK_PORT = 1;

        public static final class JOYSTICK {
            public static final int TRIGGER = 1; // BUTTON
            public static final int THUMB = 2; // BUTTON

            public static final int THROTTLE = 3; // AXIS
            public static final int X = 0; // AXIS
            public static final int Y = 1; // AXIS
            public static final int Z = 2; // AXIS

            public static final class TOP {
                public static final int RIGHT_TOP = 6; // BUTTON
                public static final int RIGHT_BOT = 4; // BUTTON
                public static final int LEFT_TOP = 5; // BUTTON
                public static final int LEFT_BOT = 3; // BUTTON
            }

            public static final class SIDE {
                public static final int BUTTON_7 = 7; // BUTTON
                public static final int BUTTON_8 = 8; // BUTTON
                public static final int BUTTON_9 = 9; // BUTTON
                public static final int BUTTON_10 = 10; // BUTTON
                public static final int BUTTON_11 = 11; // BUTTON
                public static final int BUTTON_12 = 12; // BUTTON
            }

            public static final class POV {
                public static final int ID = 0; // POV
                public static final int TOP = 0; // POV
                public static final int TOP_RIGHT = 45; // POV
                public static final int RIGHT = 90; // POV
                public static final int RIGHT_BOT = 135; // POV
                public static final int BOT = 180; // POV
                public static final int LEFT_BOT = 225; // POV
                public static final int LEFT = 270; // POV
                public static final int LEFT_TOP = 315; // POV
            }
        }

        public static final class ARCADE {
            public static final int A = 1; // BUTTON
            public static final int B = 2; // BUTTON
            public static final int X = 3; // BUTTON
            public static final int Y = 4; // BUTTON
            public static final int L1 = 5; // BUTTON
            public static final int R1 = 6; // BUTTON
            public static final int TOP_LEFT = 8; // BUTTON

            public static final int R2 = 3; // AXIS
            public static final int L2 = 2; // AXIS

            public static final class POV {
                public static final int ID = 0; // POV
                public static final int TOP = 0; // POV
                public static final int TOP_RIGHT = 45; // POV
                public static final int RIGHT = 90; // POV
                public static final int RIGHT_BOT = 135; // POV
                public static final int BOT = 180; // POV
                public static final int LEFT_BOT = 225; // POV
                public static final int LEFT = 270; // POV
                public static final int LEFT_TOP = 315; // POV
            }
        }
    }
}
