package org.usfirst.frc.team5554.CommandGroups.Commands;

import org.usfirst.frc.team5554.robot.Driver;

import edu.wpi.first.wpilibj.command.Command;

/**
 * The command makes the robot turn using a gyro sensor,
 * and to reach the wanted angle, uses a PID controller from the driver.
 *
 */
public class AutoSpin extends Command
{

	double degrees;
	Driver driver;

	/**
	 * Creates the command with a driver object and
	 * the degrees the command will turn the robot.
	 *
	 * @param degrees The amount of degrees the command will turn the robot
	 * @param driver The driver object containing the robot speed controllers and gyro
	 */
    public AutoSpin(double degrees, Driver driver)
    {
    	super("Auto Spin");
    	this.degrees = degrees;
    	this.driver = driver;
    }

    /**
     * Initializes the command, resets the gyro and starts the PID controller.
     *
     */
	@Override
    protected void initialize()
	{
		driver.ResetGyro();
		driver.Spin(degrees);
	}


	/**
	 * The command ends when the robot finished the turn
	 *
	 */
	@Override
	protected boolean isFinished()
	{
		if(degrees<0)
		{

			if(driver.GetRightError()>-3)
				return true;

		}

		if(degrees>0)
		{

			if(driver.GetRightError()<3)
				return true;
		}

		return false;
	}




	/**
	 * When the command ends, stops the robot
	 *
	 */
	@Override
	protected void end()
	{
		driver.stopMotor();
		driver.setControllerEnb(false);
	}

}
