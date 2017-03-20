package org.usfirst.frc.team5554.robot;

import edu.wpi.first.wpilibj.SPI;

public class RobotMap {

/*******************Motor Ports**************************************************************************/
    
	public final static int MOTOR_LEFT = 0;
    public final static int MOTOR_RIGHT = 1;
    public final static int MOTOR_FEEDER = 3;
    public final static int MOTOR_SHOOTER_ZERO = 0;
    public final static int MOTOR_SHOOTER_ONE = 1;
    public final static int MOTOR_SCRAMBLE = 2;
	public final static int MOTOR_CLIMBER_ONE = 4;
	public final static int MOTOR_CLIMBER_TWO = 5;
    
/*******************Joystick Ports***************************************************************/
   
	public final static int DRIVER_JOYSTICK_PORT = 0;
    public final static int DRIVER_XBOXJOYSTICK_PORT = 1;
    
/****************************IO for Joysticks*******************************************************/    
  
    //Joystick Controller
    public final static int JOYSTICK_Y_AXIS = 1; // Forward and backward joystick axis
    public final static int JOYSTICK_Z_AXIS = 2; // Spinning joystick controller axis
	public final static int JOYSTICK_X_AXIS = 0;
    public final static int JOYSTICK_SLIDER_AXIS = 3; // Slider
    public final static int JOYSTICK_FEEDER_BUTTON = 1; // Joystick trigger
    public final static int JOYSTICK_FEEDER_REVERSE_BUTTON = 6; // Joystick Button for realising stuck balls
    public final static int JOYSTICK_CAM_SWITCH = 2; // Thumb button
    public final static int JOYSTICK_SHOW_GEARGL = 3;
    
    //XBOX Controller
    public final static int XBOX_SHOW_SYSCAM = 6; // RB Button
    public final static int XBOX_JOYSTICK_AUTO_SHOOT = 3; // RT Axis
    public final static int XBOX_CLIMB_FAST_BUTTON = 1; // A Button
    public final static int XBOX_CLIMB_SUPERFAST_BUTTON = 3; // x Button
    public final static int XBOX_JOYSTICK_SCRAMBLE_FORWARD = 2; // LT Axis
    public final static int XBOX_JOYSTICK_SCRAMBLE_BACKWARD = 5; // LB Button
    public final static int XBOX_CLIMB_BUTTON = 4; // Y Button
    public final static int XBOX_REVERSE_CLIMB_BUTTON = 7; // Back Button
    
/*******************Sensors ports****************************************************************/
    
    public final static int LEFT_ENCODER_CHANNELA = 0;
    public final static int LEFT_ENCODER_CHANNELB = 1;
    public final static int RIGHT_ENCODER_CHANNELA = 2;
    public final static int RIGHT_ENCODER_CHANNELB = 3;
    public final static int SHOOTER_ENCODER_CHANNELA = 1;
    public final static int SHOOTER_ENCODER_CHANNELB = 2;
//    public final static int GEAR_MICROSWITCH_PORT =  8;
    public static final SPI.Port GYRO_PORT  = SPI.Port.kOnboardCS0;
    
/*****************Encoder Values*********************************************************/
    
    public final static double PERIMETER_OF_LEFT_WHEEL = 28*3.14;   //working values, not really the perimeter
    public final static double PERIMETER_OF_RIGHT_WHEEL = 28*3.14;
    public final static double ENCODER_ROUNDS_PER_REVOLUTION = 360;
    public final static double PID_VALUE_P = 0.6; //0.08746
    public final static double PID_VALUE_I = 0;	//0.00
    public final static double PID_VALUE_D = 0.015; //0.014
    public final static double PID_VALUE_F = 0.1;    
    public final static double PID_SPEED = 0.4;    //0.62
    
/*******************Cameras Values***************************************************/
    
    public final static int NUMBER_OF_CAMERAS = 3;
    public final static int FRONT_CAMERA_IDX = 0;
    public final static int SYSTEMS_CAMERA_IDX = 1;
    public final static int SHOOTER_CAMERA_IDX = 2;
//    public final static double FOCAL_LENGTH = 334.595;
    
/*******************************Field Distances In Centimeters And Degrees******************************/
    
    public final static double DISTANCE_FROM_ALLIANCE_WALL_TO_AIRSHIP = 300;
    public final static int DISTANCE_FROM_ALLIANCE_WALL_TO_BASELINE = 250;
    public final static double DISTANCE_FROM_ALLIANCE_WALL_TO_LEFT_SPRING = 400; 
    public final static double DISTANCE_FROM_ALLIANCE_WALL_TO_RIGHT_SPRING = 400;
    public final static int ROTATION_DEGREE_FROM_ALLIANCE_WALL_TO_SIDE_GEAR_RIGHT = 60;
    public final static int ROTATION_DEGREE_FROM_ALLIANCE_WALL_TO_SIDE_GEAR_LEFT = -62;
    
}

