package org.usfirst.frc.team5554.CommandGroups.Commands;

import org.usfirst.frc.team5554.robot.Driver;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PidDrive extends Command {
	
	private Driver driver;
	private double distance;

    public PidDrive(Driver driver , double distance) 
    {
    	super("PidDrive");
        this.driver = driver;
        this.distance = distance;
    }

    @Override
    protected void initialize() 
    {
		this.driver.ResetEncoders();
		this.driver.DriveDistance(distance, -distance, false);
    }

    @Override
    protected void execute() 
    {
    	
    }

    @Override
    protected boolean isFinished()
    {
		if(driver.GetError() < 0.5)
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
		driver.stopMotor();
    }

}

