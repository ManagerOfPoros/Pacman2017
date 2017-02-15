package org.usfirst.frc.team5554.CommandGroups.Commands;

import edu.wpi.first.wpilibj.command.InstantCommand;

import org.usfirst.frc.team5554.robot.Driver;

public class DriveDistance extends InstantCommand
{
	double wantedValue;
	Driver driver;
	
	public DriveDistance(double _wantedValue, Driver _driver)
	{
		super("Drive Distance");
		wantedValue = _wantedValue;
		driver = _driver;
	}
	
	@Override
	public void initialize()
	{
		driver.DriveDistance(wantedValue, wantedValue);
		System.out.printf("We drove %d meters!", wantedValue);
	}
}
