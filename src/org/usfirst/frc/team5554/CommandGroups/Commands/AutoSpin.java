package org.usfirst.frc.team5554.CommandGroups.Commands;

import org.usfirst.frc.team5554.robot.Driver;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoSpin extends Command 
{
	
	int degrees;
	Driver driver;
	
    public AutoSpin(int degrees, Driver driver, double timeout)
    {
    	super("Auto Spin", timeout);
    	this.degrees = degrees;
    	this.driver = driver;
    }

	@Override
    protected void initialize() 
	{
	    	driver.Spin(degrees , false, false);
	}


	@Override
	protected boolean isFinished()
	{
		if(degrees<0){
			
			if(driver.GetError()>-3 || isTimedOut())
				return true;
			
		}
		
		if(degrees>0){
			
			if(driver.GetError()<3 || isTimedOut())
				return true;
		}
		
		return false;
	}
	
	

	
	@Override
	protected void end()
	{
		//driver.stopMotor();
		driver.disController();		
	}

}
