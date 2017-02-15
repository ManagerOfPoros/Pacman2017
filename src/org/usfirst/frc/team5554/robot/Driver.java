package org.usfirst.frc.team5554.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;

public class Driver
{
	private Motor left0;
	private Motor left1;
	private Motor right0;
	private Motor right1;
	
	/**
	 * The constructor configurates the motors objects to certain ports
	 * @since 15/1/2017
	 * @param MOTOR_LEFT port for left motor
	 * @param MOTOR_RIGHT port for right motor
	 * Author: Gil Meri
	 */
	public Driver(int motorLeftOne , int motorLeftTwo , int motorRightOne , int motorRightTwo , Encoder leftEnc , Encoder rightEnc) 
	{
		
		left0 = new Motor(motorLeftOne);
		left0.SetFeedbackDevice(leftEnc);
		
		left1 = new Motor(motorLeftTwo);
		left1.SetFeedbackDevice(leftEnc);
		
		right0 = new Motor(motorRightOne);
		right0.SetFeedbackDevice(leftEnc);
		
		right1 = new Motor(motorRightTwo);
		right1.SetFeedbackDevice(leftEnc);
		
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
		
		//this.left0.set(powerLeft);
		//this.left1.set(powerLeft);
		this.right0.set(powerRight);
		this.right1.set(powerRight);
	}
	
	public void Drive(double speed)
	{
		//here it will start the motors for the driver given a speed
		//to drive an exact distance
	}
	
	public void Spin(double degrees)
	{				
		if(degrees > 0){
			//then spin right
		}else if(degrees < 0){
			degrees *= -1;
			//then spin left
		}
	}	
	
	public void DriveDistance(double leftDistance, double rightDistance)
	{
		right0.SetPID(1, 0.001, 2);
		right1.SetPID(1, 0.001, 2);
		left0.SetPID(1, 0.001, 2);
		left1.SetPID(1, 0.001, 2);
		
		right0.SetPIDType(PIDSourceType.kDisplacement);
		right1.SetPIDType(PIDSourceType.kDisplacement);
		left0.SetPIDType(PIDSourceType.kDisplacement);
		left1.SetPIDType(PIDSourceType.kDisplacement);
		
		this.right0.GoDistance(rightDistance);
		this.right1.GoDistance(rightDistance);
		this.left0.GoDistance(leftDistance);
		this.left1.GoDistance(leftDistance);
	}
	
	public void DriveSteady(double speed)
	{
		right0.SetPID(0.00001, 0, 0.0001);		
		right1.SetPID(0.0001, 0.001, 0.001);
		
		right0.SetPIDType(PIDSourceType.kRate);
		right1.SetPIDType(PIDSourceType.kRate);
		
		this.right0.GoSteady(speed);
		this.right1.GoSteady(speed);
	}
	
		
		
}
