package org.usfirst.frc.team5554.CommandGroups.Commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team5554.robot.Driver;

/**
 * This command drives the robot
 * for a specified period of time.
 *
 */
public class TimedDrive extends Command
{
	private double speed;
	private Driver driver;


	/**
	 * Creates the command, with a speed a driver
	 * object and specified time the command will run
	 *
	 * @param speed The speed in pwm values The robot will drive
	 * @param driver The driver object containing the robot gyro and speed controllers
	 * @param timeout The time the robot will drive
	 */
	public TimedDrive(double speed, Driver driver, double timeout)
	{
		super("TimedDrive", timeout);
		this.speed = speed;
		this.driver = driver;
	}

	/**
	 * Initializes the command, starts the robot's movement
	 *
	 */
	@Override
	protected void initialize()
	{
    	driver.autonomousDrive(speed, 0);

	}

	@Override
	protected void execute()
	{
	}

	/**
	 * The command ends when the timeout is finished
	 *
	 *
	 */
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

    /**
     * When the command ends stops the robot
     *
     */
	@Override
	protected void end()
	{
		driver.autonomousDrive(0 , 0);
	}
}
