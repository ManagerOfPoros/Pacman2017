package org.usfirst.frc.team5554.CommandGroups.Commands;

import org.usfirst.frc.team5554.robot.Driver;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TimedGyroDrive extends Command {
	
	private double speed;
	private Driver driver;
	private final double kP = 0.03;

    public TimedGyroDrive(double speed, Driver driver, double timeout) 
    {
        this.speed = speed;
        this.driver = driver;
        this.driver.ResetGyro();
    }

    protected void initialize() 
    {
    }

    protected void execute() 
    {
    	double angle = driver.GetAngle();
    	driver.autonomousDrive(this.speed, angle*kP);
    }

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

    protected void end() {
    }

    protected void interrupted() {
    }
}
