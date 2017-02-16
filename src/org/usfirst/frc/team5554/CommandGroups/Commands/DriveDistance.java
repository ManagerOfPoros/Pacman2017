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
		driver.DriveDistance(wantedValue, wantedValue);
	}
	
	@Override
	protected void execute()
	{
		
	}

	@Override
	protected boolean isFinished() {
		
		if(driver.LeftOnTarget() || driver.RightOnTarget())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
