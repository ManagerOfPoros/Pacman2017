package org.usfirst.frc.team5554.CommandGroups.Commands;

import org.usfirst.frc.team5554.robot.Driver;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This command drives the robot straight using a gyro
 * for a specified period of time.
 *
 */
public class TimedGyroDrive extends Command {

	private double speed;
	private Driver driver;
	private double kP = 0.003;

	/**
	 * Creates the command, with a speed a driver
	 * object and specified time the command will run
	 *
	 * @param speed The speed in pwm values The robot will drive
	 * @param driver The driver object containing the robot gyro and speed controllers
	 * @param timeout The time the robot will drive
	 * @param kp The proportional constant that is multiplied by the gyro error from 0
	 * to keep the robot correction to the gyro 0 value
	 */
    public TimedGyroDrive(double speed, Driver driver, double timeout, double kp)
    {
    	super("TimedGyroDrive" , timeout);
        this.speed = speed;
        this.driver = driver;
        this.kP = kp;
    }

    /**
     * Initializes the command, resets the gyro
     *
     */
    @Override
    protected void initialize()
    {
        this.driver.ResetGyro();
    }

    /**
     * The body of the command, constantly changes the robot's speed according to the
     * gyro's error from 0 by a proportional value..
     *
     */
    @Override
    protected void execute()
    {
    	if(speed>0)
    	{
    		driver.autonomousDrive(-this.speed, -driver.GetAngle()*kP);
    	}
    	else if(speed<0)
    	{
    		driver.autonomousDrive(this.speed, +driver.GetAngle()*kP);
    	}
    	Timer.delay(0.04);
    }

    /**
     * Ends the command when the timeout ended
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
		driver.stopMotor();
    }

}
