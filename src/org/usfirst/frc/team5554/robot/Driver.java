package org.usfirst.frc.team5554.robot;

import org.usfirst.frc.team5554.Controllers.Motor;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.RobotDrive;

public class Driver extends RobotDrive
{
	private Encoder leftEncoder;
	private Encoder rightEncoder;
	private ADXRS450_Gyro gyro;
	private boolean isInvert = true;
	
	private boolean isEnabled = true;
	
	
	/**
	 * The constructor configurates the motors objects to certain ports
	 * @since 15/1/2017
	 * @param MOTOR_LEFT port for left motor
	 * @param MOTOR_RIGHT port for right motor
	 * Author: Gil Meri
	 */
	public Driver(Motor left, Motor right , Encoder leftEnc, Encoder rightEnc, ADXRS450_Gyro gyro) 
	{			
		super(left , right);
		
		setSafetyEnabled(false);
		
		this.leftEncoder = leftEnc;
		this.rightEncoder = rightEnc;
		
		this.gyro = gyro;		
		gyro.reset();
	}
	
	public Driver(Motor left, Motor right , ADXRS450_Gyro gyro) 
	{			
		super(left , right);
		
		setSafetyEnabled(false);
		
		this.gyro = gyro;		
		gyro.reset();
	}
	
	/**
	 * This function in charge of the movement of the robot with the joystick
	 * @since 15/1/2017
	 * @param y The value of the joystick's y axis
	 * @param x The value of the joystick's x axis (used for turns instead of z)
	 * @param slider The value of the joystick's slider axis
	 * @author Gil Meri
	 */
	public void Moving(double slider, double speed , double turn) 
	{
		if(isEnabled)
		{
			slider = (-slider+1)/2;
			
			//Gives us freedom to manipulte the front of the robot.
			//If +slider and -slider can change the front of the motor since
			//they determine if the scalar is from 0-1 or from -1 to 0.
			
			setSafetyEnabled(false);
			
			if(isInvert == false)
			{
				setMaxOutput(slider);
				arcadeDrive(speed , -turn);
			}
			else
			{
				setMaxOutput(-slider);
				arcadeDrive(speed , turn);
			}
			
		}
		
	}
	
	public void autonomousDrive(double speed , double curve , boolean inverted)
	{
		
		setMaxOutput(-1.0);
		setSafetyEnabled(false);
		drive(speed, curve);
		
	}
	
	public void setExpiration(double timeout)
	{
		setExpiration(timeout);
	}
	
	
	public void Spin(double degrees , boolean invertLeft, boolean invertRight)
	{			
		if(isEnabled)
		{
			gyro.reset();
		
			((Motor)this.m_rearLeftMotor).SetFeedbackDevice(gyro);
			((Motor)this.m_rearRightMotor).SetFeedbackDevice(gyro);
		
			((Motor)this.m_rearRightMotor).SetPIDType(PIDSourceType.kDisplacement);
			((Motor)this.m_rearLeftMotor).SetPIDType(PIDSourceType.kDisplacement);		
		
			((Motor)this.m_rearRightMotor).SetPID(0.008, 0.000, 0.001);
			((Motor)this.m_rearLeftMotor).SetPID(0.008, 0.000, 0.001);
		
			((Motor)this.m_rearRightMotor).GoDistance(degrees , invertRight);
			((Motor)this.m_rearLeftMotor).GoDistance(degrees , invertLeft);
		}
	}	
		
	
	public void DriveDistance(double leftDistance, double rightDistance, boolean invertDriver)
	{
		if(isEnabled)
		{
			((Motor)this.m_rearRightMotor).SetFeedbackDevice(rightEncoder);
			((Motor)this.m_rearLeftMotor).SetFeedbackDevice(leftEncoder);
			
			((Motor)this.m_rearRightMotor).SetPIDType(PIDSourceType.kDisplacement);
			((Motor)this.m_rearLeftMotor).SetPIDType(PIDSourceType.kDisplacement);
			
			((Motor)this.m_rearRightMotor).SetPID(0.003, 0, 0);
			((Motor)this.m_rearLeftMotor).SetPID(0.003, 0, 0);
			
			((Motor)this.m_rearRightMotor).GoDistance(rightDistance , invertDriver);
			((Motor)this.m_rearLeftMotor).GoDistance(leftDistance , invertDriver);
		}
	}

	public boolean LeftOnTarget(double tolerance)
	{
		return ((Motor)this.m_rearLeftMotor).onTarget(tolerance);
	}
	
	public boolean RightOnTarget(double tolerance)
	{
		return ((Motor)this.m_rearRightMotor).onTarget(tolerance);
	}
	
	public void enable()
	{
		this.isEnabled = true;
	}
	
	public void disable()
	{
		this.isEnabled = false;
		this.m_rearLeftMotor.set(0);
		this.m_rearRightMotor.set(0);
		
	}
	
	public void disController()
	{
		((Motor)this.m_rearRightMotor).disController();
		((Motor)this.m_rearLeftMotor).disController();
	}
	
	public void enableController()
	{
		((Motor)this.m_rearRightMotor).enableController();
		((Motor)this.m_rearLeftMotor).enableController();
	}
	
	public double GetError()
	{
		return ((Motor)this.m_rearRightMotor).GetError();
	}
	
	public boolean OnTarget(double tolerance)
	{
		return ((Motor)this.m_rearRightMotor).onTarget(tolerance);
	}
	
	public double GetAngle()
	{
		return gyro.getAngle();
	}
	
	public void CalibrateGyro()
	{
		gyro.calibrate();
	}
	
	public void ResetGyro()
	{
		gyro.reset();
	}
	
	public void ResetEncoders()
	{
		leftEncoder.reset();
		rightEncoder.reset();
	}
	
	public double GetLeftEncValue()
	{
		return leftEncoder.getDistance();
	}
	
	public double GetRightEncValue()
	{
		return rightEncoder.getDistance();
	}
	
	public void invert()
	{
		if(this.isInvert == false)
		{
			this.isInvert = true;
		}
		else
		{
			this.isInvert = false;
		}
	}
	
	public boolean isInverted()
	{
		return isInvert;
	}
	
	



}
