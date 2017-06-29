package org.usfirst.frc.team5554.CommandGroups.Commands;

import org.usfirst.frc.team5554.robot.Driver;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This command drives the robot straight using a gyro
 * for a specified distance measured by the driver's left encoder
 *
 */
public class DistanceGyroDrive extends Command{

	private double speed;
	private Driver driver;
	private double distance;

	private double kP;

	/**
	 * Creates the command, with a speed, a driver
	 * object and specified distance the robot will drive
	 *
	 * @param speed The speed in PWM values The robot will drive
	 * @param driver The driver object containing the robot gyro and speed controllers
	 * @param distance The time the robot will drive
	 * @param kp The proportional constant that is multiplied by the gyro error from 0
	 * to keep the robot correction to the gyro 0 value
	 */
	public DistanceGyroDrive(double speed , Driver driver , double distance , double kp)
	{
		super("DistanceGyroDrive");

		this.driver = driver;
		this.speed = speed;
		this.distance = distance;
		this.kP = kp;
	}


    /**
     * Initializes the command, resets the gyro and the encoder count
     *
     */
	@Override
	protected void initialize()
	{
        this.driver.ResetGyro();
        this.driver.ResetEncoders();

	}

    /**
     * The body of the command, constantly changes the robot's speed according to the
     * gyro's error from 0 multiplied by a proportional value.
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
    		driver.autonomousDrive(this.speed, driver.GetAngle()*kP);
		}
		Timer.delay(0.04);
	}

	/**
	 * The command ends when the robot encoder count reaches the desired distance
	 *
	 */
	@Override
	protected boolean isFinished()
	{
		if(speed>0)

			if(driver.GetLeftEncValue() >= distance)
			{
				return true;
			}
			else
			{
				return false;
			}
		else
		{
			if(driver.GetLeftEncValue() <= distance)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
	}

	/**
	 * When the command ends stops the robot
	 *
	 */
	@Override
	protected void end()
	{
		driver.autonomousDrive(0, 0);
		driver.stopMotor();
	}

}
