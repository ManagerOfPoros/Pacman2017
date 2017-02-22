package org.usfirst.frc.team5554.robot;

import org.usfirst.frc.team5554.Controllers.Motor;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Victor;

public class Shooter 
{
	private Motor firstShooter;
	private Motor secondShooter;
	private Victor scramble;
	private Encoder encoder;
	private double velocity;

	public Shooter(int shooterPort, int shooterPort2, int scramblePort , Encoder enc)
	{
		firstShooter = new Motor(shooterPort);
		secondShooter = new Motor(shooterPort2);
		scramble = new Victor(scramblePort);
		
		encoder = enc;
		encoder.setDistancePerPulse(15.24 / 360);
		
		velocity = 0.6;        //in pwm
	}
	
	public void shoot(double speed)
	{
		firstShooter.set(speed);
		secondShooter.set(speed);
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
		// enter equation from excel - turns distance into velocity 
		//maintainSpeed(velocity);
		
		firstShooter.set(velocity);
		secondShooter.set(velocity);		
	}
	
	public void disController()
	{
		firstShooter.disController();
	}
	
	public double GetSpeed()
	{
		return velocity;
	}
	
	//***************For tests only*************************//
	
	public void decreaseVelocity()
	{
		velocity-=0.01;
	}
	
	public void increaseVelocity()
	{
		velocity+=0.01;
	}
	
}
	
