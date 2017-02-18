package org.usfirst.frc.team5554.robot;

import org.usfirst.frc.team5554.Controllers.Motor;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;

public class Driver
{
	private Motor left;
	private Motor right;	
	
	private Encoder leftEncoder;
	private Encoder rightEncoder;
	private ADXRS450_Gyro gyro;
	
	
	/**
	 * The constructor configurates the motors objects to certain ports
	 * @since 15/1/2017
	 * @param MOTOR_LEFT port for left motor
	 * @param MOTOR_RIGHT port for right motor
	 * Author: Gil Meri
	 */
	public Driver(int motorLeft, int motorRight , Encoder leftEnc , Encoder rightEnc , ADXRS450_Gyro gyro) 
	{	
		left = new Motor(motorLeft);
		
		right = new Motor(motorRight);		
	}
	
	/**
	 * This function in charge of the movement of the robot with the joystick
	 * @since 15/1/2017
	 * @param y The value of the joystick's y axis
	 * @param z The value of the joystick's z axis
	 * @param slider The value of the joystick's slider axis
	 * @author Gil Meri
	 */
	public void Moving(double y, double z, double slider) 
	{
		slider = (-slider+1)/2;
		
		double powerLeft = (y+z) * slider;
		double powerRight = (-y+z) * slider;
		
		if (powerLeft > 1)powerLeft=1;
		if (powerLeft < -1)powerLeft=-1;
		if (powerRight > 1)powerRight=1;
		if (powerRight < -1)powerRight=-1;
		
		this.left.set(powerLeft);
		this.right.set(powerRight);
	}
	
	
	public void Spin(double degrees)
	{				
		right.SetFeedbackDevice(gyro);
		left.SetFeedbackDevice(gyro);
		
		right.SetPIDType(PIDSourceType.kDisplacement);
		left.SetPIDType(PIDSourceType.kDisplacement);		
		
		right.SetPID(1, 0.001, 2);
		left.SetPID(1, 0.001, 2);
		
		this.right.GoDistance(degrees);
		this.left.GoDistance(degrees);
	}	
	
	public void DriveDistance(double leftDistance, double rightDistance)
	{		
		right.SetFeedbackDevice(rightEncoder);
		left.SetFeedbackDevice(leftEncoder);
		
		right.SetPIDType(PIDSourceType.kDisplacement);
		left.SetPIDType(PIDSourceType.kDisplacement);
		
		right.SetPID(1, 0.001, 2);
		left.SetPID(1, 0.001, 2);
		
		this.right.GoDistance(rightDistance);
		this.left.GoDistance(leftDistance);
	}

	public boolean LeftOnTarget(double tolerance)
	{
		return left.onTarget(tolerance);
	}
	
	public boolean RightOnTarget(double tolerance)
	{
		return right.onTarget(tolerance);
	}
		
		
}
