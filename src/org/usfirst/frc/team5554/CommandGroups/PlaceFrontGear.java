package org.usfirst.frc.team5554.CommandGroups;

import org.usfirst.frc.team5554.CommandGroups.Commands.*;
import org.usfirst.frc.team5554.robot.Driver;
import org.usfirst.frc.team5554.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
*
* This command makes Pacman place a gear in the front pin
*
*/
public class PlaceFrontGear extends CommandGroup
{

	/**
	 * Creates the command with a driver object containing the robot's speed controllers and encoders
	 *
	 * @param driver The driver object
	 */
    public PlaceFrontGear(Driver driver)
    {
		driver.CalibrateGyro();
		addSequential(new Timeout(0.1));
		addSequential(new DistanceGyroDrive(0.35, driver , RobotMap.DISTANCE_FROM_ALLIANCE_WALL_TO_AIRSHIP,0.00245));
    }
}
