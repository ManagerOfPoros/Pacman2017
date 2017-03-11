package org.usfirst.frc.team5554.CommandGroups.Commands;

import org.usfirst.frc.team5554.robot.Driver;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class DistanceGyroDrive extends Command{

	private double speed;
	private Driver driver;
	private double distance;
	
	private final double kP = 0.03;
	
	public DistanceGyroDrive(Driver driver , double speed , double distance)
	{
		super("DistanceGyroDrive");
		
		this.driver = driver;
		this.speed = speed;
		this.distance = distance;
	}
	
	
	@Override
	protected void initialize()
	{
        this.driver.ResetGyro();
        this.driver.ResetEncoders();
	}
	
	@Override
	protected void execute()
	{
    	if(speed>0)
    	{
    		driver.autonomousDrive(this.speed, driver.GetAngle()*kP, false);
    	}
    	else if(speed<0)
    	{
    		driver.autonomousDrive(this.speed, -driver.GetAngle()*kP, false);
    	}
    	Timer.delay(0.04);
	}
		
	@Override
	protected boolean isFinished() 
	{
		if(speed>0)
			if(driver.GetLeftEncValue() >= distance)
			{
				return true;
			}
			else
			{
				return false;
			}
		else
		{
			if(driver.GetLeftEncValue() <= distance)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
	}
	
	@Override
	protected void end()
	{
		driver.stopMotor();
	}

}
