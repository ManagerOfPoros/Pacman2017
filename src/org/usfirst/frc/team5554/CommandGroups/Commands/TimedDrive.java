package org.usfirst.frc.team5554.CommandGroups.Commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team5554.robot.Driver;

public class TimedDrive extends Command
{
	private double speed;
	private Driver driver;
	
	public TimedDrive(double speed, Driver driver, double timeout)
	{
		super("TimedDrive", timeout);
		this.speed = speed;
		this.driver = driver;
	}
	
	@Override
	protected void initialize()
	{
	}
	
	@Override
	protected void execute()
	{
		driver.autonomousDrive(speed);
	}

	@Override
	protected boolean isFinished() {
		
		if(isTimedOut())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	@Override
	protected void end()
	{
		driver.autonomousDrive(0);
	}
}
