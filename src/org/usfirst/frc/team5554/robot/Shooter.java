package org.usfirst.frc.team5554.robot;

import org.usfirst.frc.team5554.Controllers.Motor;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Victor;

public class Shooter 
{
	private Motor firstShooter;
	private Victor scramble;
	private Encoder encoder;

	public Shooter(int shooterPort, int scramblePort , int leftEncPort0 , int leftEncPort1)
	{
		firstShooter = new Motor(shooterPort);
		scramble = new Victor(scramblePort);
		
		encoder = new Encoder(leftEncPort0 , leftEncPort1 , true , EncodingType.k4X);
		encoder.setDistancePerPulse(15.24 / 360);
	}
	
	public void shoot(double speed)
	{
		firstShooter.set(speed);
	}
	
	public void maintainSpeed(double vel)              // gets velocity in m/s
	{
		firstShooter.SetFeedbackDevice(encoder);
		firstShooter.SetPIDType(PIDSourceType.kRate);
		
		firstShooter.SetPID(0.2, 0.2, 0, 1);               //find the right f value
		
		this.firstShooter.GoSteady(vel , false);
	}
		
	public void scramble(double speed)
	{
		scramble.set(speed);
	}
	
	public void autoShoot()
	{
		double velocity = 20;
		maintainSpeed(velocity);
		
		System.out.println("The speed is: " + encoder.getRate());
	}
	
	public void disController()
	{
		firstShooter.disController();
	}
	
}
	
