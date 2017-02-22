package org.usfirst.frc.team5554.CommandGroups.Commands;

import org.usfirst.frc.team5554.robot.Driver;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TimedGyroDrive extends Command {
	
	private double speed;
	private Driver driver;
	private final double kP = 0.01;

    public TimedGyroDrive(double speed, Driver driver, double timeout) 
    {
    	super("TimedGyroDrive" , timeout);
        this.speed = speed;
        this.driver = driver;
        this.driver.ResetGyro();
    }

    @Override
    protected void initialize() 
    {
    }

    @Override
    protected void execute() 
    {
    	double angle = driver.GetAngle();
    	if(speed>0)
    	{
    		driver.autonomousDrive(this.speed, -angle*kP);
    	}
    	else
    	{
    		driver.autonomousDrive(this.speed, +angle*kP);
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
