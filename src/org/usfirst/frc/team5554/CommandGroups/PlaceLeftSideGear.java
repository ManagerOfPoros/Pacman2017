package org.usfirst.frc.team5554.CommandGroups;

import org.usfirst.frc.team5554.CommandGroups.Commands.AutoSpin;
import org.usfirst.frc.team5554.CommandGroups.Commands.DistanceGyroDrive;
import org.usfirst.frc.team5554.CommandGroups.Commands.Timeout;
import org.usfirst.frc.team5554.robot.Driver;
import org.usfirst.frc.team5554.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
*
* This command makes Pacman place a gear in the left side pin
*
*/
public class PlaceLeftSideGear extends CommandGroup {

	/**
	 * Creates the command with a driver object containing the robot's speed controllers, encoders and gyro
	 *
	 * @param driver The driver object
	 */
    public PlaceLeftSideGear(Driver driver)
    {
    	driver.CalibrateGyro();
		addSequential(new Timeout(0.1));
		addSequential(new DistanceGyroDrive(0.3, driver , RobotMap.DISTANCE_FROM_ALLIANCE_WALL_TO_LEFT_SPRING ,0.00245));
		addSequential(new Timeout(0.8));
		addSequential(new AutoSpin(RobotMap.ROTATION_DEGREE_FROM_ALLIANCE_WALL_TO_SIDE_GEAR_LEFT, driver));
		addSequential(new Timeout(0.8));
		addSequential(new DistanceGyroDrive(0.3, driver , 65 ,0.00245));

    }
}
