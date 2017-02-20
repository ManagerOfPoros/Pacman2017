package org.usfirst.frc.team5554.CommandGroups.Commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team5554.robot.Driver;

public class DriveDistance extends Command
{
	private double speed;
	private Driver driver;
	
	public DriveDistance(double speed, Driver driver, double timeout)
	{
		super("DriveDistance", timeout);
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
		
		return false;
	}
}
