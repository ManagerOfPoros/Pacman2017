package org.usfirst.frc.team5554.robot;

import org.usfirst.frc.team5554.Controllers.Motor;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Victor;

public class Shooter 
{
	private Motor firstShooter;
	private Victor scramble;
//	private double Velocity;      			// meters per second 

	public Shooter(int shooterPort, int scramblePort , Encoder shooterEncoder)
	{
		firstShooter = new Motor(shooterPort);
		scramble = new Victor(scramblePort);
		
		firstShooter.SetFeedbackDevice(shooterEncoder);
	}
	
	public void shoot(double speed)
	{
		firstShooter.set(speed);
	}
	
	public void maintainSpeed(double vel)              // gets velocity in m/s
	{
		firstShooter.SetPID(0.2, 0.2, 0, 1);               //find the right f value
				
		firstShooter.SetPIDType(PIDSourceType.kRate);
		
		this.firstShooter.GoSteady(vel);
	}
		
	public void scramble(double speed)
	{
		scramble.set(speed);
	}
	
	public void autoShoot()
	{
		//double distanceToBoiler = CameraThread.distance;
		//enter equation
	}
	
}
	
