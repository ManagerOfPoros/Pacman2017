package org.usfirst.frc.team5554.CommandGroups.Commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team5554.robot.Driver;

public class DriveDistance extends Command
{
	double wantedValue;
	Driver driver;
	
	public DriveDistance(double wantedValue, Driver driver)
	{
		super("DriveDistance");
		this.wantedValue = wantedValue;
		this.driver = driver;
	}
	
	@Override
	protected void initialize()
	{
		driver.DriveDistance(wantedValue, wantedValue , false);
	}
	
	@Override
	protected void execute()
	{
		
	}

	@Override
	protected boolean isFinished() {
		
		if(driver.LeftOnTarget(0.1) && driver.RightOnTarget(0.1))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
