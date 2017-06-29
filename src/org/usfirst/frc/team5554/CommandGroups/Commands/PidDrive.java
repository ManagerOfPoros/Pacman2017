package org.usfirst.frc.team5554.CommandGroups.Commands;

import org.usfirst.frc.team5554.robot.Driver;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command drives the robot a certain amount of distance
 * using a pid controller that reads values from the robot's driver encoders
 */
public class PidDrive extends Command {

	private Driver driver;
	private double distance;

	/**
	 * Creates the command, sets the driver's speed, and the distance
	 * the robot will drive
	 *
	 * @param driver The driver object containing the robot speed controllers and encoders
	 * @param distance The distance the robot will drive
	 */
    public PidDrive(Driver driver , double distance)
    {
    	super("PidDrive");
        this.driver = driver;
        this.distance = distance;
    }

    /**
     * Initializes the command, resets the encoders count and starts the PID controller.
     *
     */
    @Override
    protected void initialize()
    {
		this.driver.ResetEncoders();
		this.driver.DriveDistance(distance, -distance);
    }

    @Override
    protected void execute()
    {

    }

    /**
     * If the robot made the specified distance, the command ends
     *
     */
    @Override
    protected boolean isFinished()
    {
		if(driver.GetRightError() < 0.5)
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
     */
    @Override
    protected void end()
    {
		driver.stopMotor();
    }

}

