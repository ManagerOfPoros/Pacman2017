package org.usfirst.frc.team5554.CommandGroups.Commands;

import org.usfirst.frc.team5554.robot.Driver;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TimedGyroDrive extends Command {
	
	private double speed;
	private Driver driver;
	private double kP = 0.02;

    public TimedGyroDrive(double speed, Driver driver, double timeout) 
    {
    	super("TimedGyroDrive" , timeout);
        this.speed = speed;
        this.driver = driver;
    }

    @Override
    protected void initialize() 
    {
        this.driver.ResetGyro();
    }

    @Override
    protected void execute() 
    {
    	if(speed>0)
    	{
    		driver.autonomousDrive(this.speed, -driver.GetAngle()*kP);
    	}
    	else
    	{
    		driver.autonomousDrive(this.speed, +driver.GetAngle()*kP);
    	}
    }

    @Override
    protected boolean isFinished()
    {
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
		driver.autonomousDrive(0 , 0);
    }

}
