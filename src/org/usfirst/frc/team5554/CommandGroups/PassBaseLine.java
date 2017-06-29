package org.usfirst.frc.team5554.CommandGroups;

import org.usfirst.frc.team5554.CommandGroups.Commands.*;
import org.usfirst.frc.team5554.robot.Driver;
import org.usfirst.frc.team5554.robot.RobotMap;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * This command makes Pacman pass the base line
 *
 */
public class PassBaseLine extends CommandGroup
{
	/**
	 * Creates the command with a driver object containing the robot's speed controllers and encoders
	 *
	 * @param driver The driver object
	 */
	public PassBaseLine(Driver driver)
	{
		driver.CalibrateGyro();
		addSequential(new DistanceGyroDrive(0.3, driver , RobotMap.DISTANCE_FROM_ALLIANCE_WALL_TO_RIGHT_SPRING, 0.00245));

	}
}
